package com.module.config.controller;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.base.utils.MysqlUtils;
import com.base.utils.StringUtils;
import com.base.utils.TimeTools;
import com.module.config.model.App;
import com.module.config.model.Attr;
import com.module.config.model.Obj;
import com.module.config.service.IAppService;
import com.module.config.service.IAttrService;
import com.module.config.service.IObjService;
import com.module.config.service.IUniqService;
import com.module.system.service.ParameterService;
import org.hibernate.mapping.Column;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 房屋信息模块
 * author wenxy
 */
@Controller
@RequestMapping("/obj/")
public class ObjController extends AbstractController<Obj> {

    @Resource(name = "objService")
    private IObjService service;

    @Resource(name = "appService")
    private IAppService appService;

    @Resource(name = "parameterService")
    private ParameterService parameterService;

    @Resource(name = "attrService")
    private IAttrService attrService;

    @SuppressWarnings("unused")
    @Resource(name = "uniqService")
    private IUniqService uniqService;

    @Override
    protected ParameterService getParameterService() {
        return parameterService;
    }

    @Override
    protected BaseService<Obj> getService() {
        return service;
    }

    @Override
    protected Class<Obj> getClassTemplate() {
        return Obj.class;
    }

    @Override
    protected String getJspBasePath() {
        return "config/obj/";
    }

    @Override
    protected String getBranchName() {
        return "obj";
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list(Long applicationId) {
        ModelAndView mv = new ModelAndView(this.getJspBasePath() + "list");
        App app = appService.getObject(App.class, applicationId);
        mv.addObject("app", app);
        List<Obj> list = service.getListByApplicationId(applicationId);
        mv.addObject("objectList", list);
        return mv;
    }

    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate(Long applicationId) {
        ModelAndView mv = super.toCreate();
        App app = appService.getObject(App.class, applicationId);
        mv.addObject("app", app);
        return mv;
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView(this.getJspBasePath() + "edit");
        Obj object = this.getService().getObject(this.getClassTemplate(), id);
        mv.addObject("object", object);
        return mv;
    }

    @RequestMapping(value = "create.do", method = {RequestMethod.POST})
    public RedirectView create(Obj obj) {
        if (StringUtils.isEmpty(obj.getTable())) {
            App app = appService.getObject(App.class, obj.getApplicationId());
            obj.setApp(app);
        }
        super.create(obj);
        return new RedirectView("list.do?applicationId=" + obj.getApplicationId());
    }

    @RequestMapping(value = "edit.do", method = {RequestMethod.POST})
    public RedirectView edit(Obj obj) {
        if (StringUtils.isEmpty(obj.getTable())) {
            App app = appService.getObject(App.class, obj.getApplicationId());
            obj.setApp(app);
        }
        super.edit(obj);
        return new RedirectView("list.do?applicationId=" + obj.getApplicationId());
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(Obj obj) {
        obj = service.getObject(Obj.class, obj.getId());
        super.delete(obj);
        return new RedirectView("list.do?applicationId=" + obj.getApplicationId());
    }

    @RequestMapping(value = "createFile")
    public void createFile(Long id) {
        Obj obj = service.getObject(Obj.class, id);
        App app = appService.getObject(App.class, obj.getApplicationId());
        List<Attr> attrs = this.attrService.getListByObjectId(id);
        obj.setApp(app);
        obj.setAttrs(attrs);
        service.createFiles(obj);
    }

    public List<Column> getMysqlDatabaseList(String address, Integer port, String username, String password, String database, String table) {
        Connection connection = null;
        List<Column> list = null;
        String url = "jdbc:mysql://" + address + ":" + port + "/";
        String sql = "select * from information_schema.COLUMNS where TABLE_SCHEMA='database' and TABLE_NAME='table'".replace("database", database).replace("table", table);
        try {
            connection = MysqlUtils.getConnection(url, username, password);
            list = new ArrayList<Column>();
            ResultSet rs = MysqlUtils.getResultSet(connection, sql);
            while (rs.next()) {
                String name = rs.getString("COLUMN_NAME");
                String type = rs.getString("DATA_TYPE");
                String comment = rs.getString("COLUMN_COMMENT");
                Column obj = new Column();
                obj.setName(name);
                obj.setSqlType(type);
                obj.setComment(comment);
                list.add(obj);
            }
        } catch (ClassNotFoundException e) {
            log.error(e);
        } catch (SQLException e) {
            log.error(e);
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    log.error(e);
                }
            }
        }
        return list;
    }

    @RequestMapping(value = "initTable.do")
    public RedirectView initTable(Long id) {
        RedirectView rv = new RedirectView("/attr/list.do",true);
        Obj obj = service.getObject(Obj.class, id);
        if (null != obj) {
            App app = appService.getObject(App.class, obj.getApplicationId());
            if (null != app) {
                List<Column> list = getMysqlDatabaseList(app.getUrl(), app.getPort(), app.getLoginName(), app.getPassword(), app.getDatabase(), obj.getTable());
                if (null != list && list.size() > 0) {
                    String createTime = TimeTools.now();
                    for (Column column : list) {
                        Attr attr = new Attr();
                        attr.setUseType(Attr.USE_TYPE_SAVE);
                        attr.setPageStyle(Attr.PAGE_STYLE_TEXT);
                        attr.setObjId(obj.getId());
                        attr.setColumn(column.getName());
                        attr.setName(column.getComment());
                        attr.setDataType(column.getSqlType());
                        attr.setCode(StringUtils.columnToAttribute(column.getName()));
                        attr.setCreateTime(createTime);
                        parameterService.setUniqueId(attr);
                        attrService.create(attr);
                    }
                }
            }
            rv.addStaticAttribute("objId", obj.getId());
        }
        return rv;
    }

}