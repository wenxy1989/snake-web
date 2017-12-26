package com.module.config.controller;

import com.base.common.controller.AbstractController;
import com.base.common.service.BaseService;
import com.base.utils.MysqlUtils;
import com.base.utils.StringUtils;
import com.base.utils.TimeTools;
import com.module.config.model.App;
import com.module.config.model.Attr;
import com.module.config.model.Database;
import com.module.config.model.Obj;
import com.module.config.service.IAppService;
import com.module.config.service.IAttrService;
import com.module.config.service.IObjService;
import com.module.config.service.IUniqService;
import com.module.system.service.ParameterService;
import org.hibernate.mapping.Table;
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
@RequestMapping("/app/")
public class AppController extends AbstractController<App> {

    @Resource(name = "appService")
    private IAppService service;

    @Resource(name = "objService")
    private IObjService objService;

    @Resource(name = "attrService")
    private IAttrService attrService;

    @Resource(name = "uniqService")
    private IUniqService uniqService;

    @Resource(name = "parameterService")
    private ParameterService parameterService;

    @Override
    protected ParameterService getParameterService() {
        return parameterService;
    }

    @Override
    protected BaseService<App> getService() {
        return service;
    }

    @Override
    protected Class<App> getClassTemplate() {
        return App.class;
    }

    @Override
    protected String getJspBasePath() {
        return "config/app/";
    }

    @Override
    protected String getBranchName() {
        return "app";
    }

    @RequestMapping(value = "list.do")
    public ModelAndView list() {
        return super.list();
    }

    @RequestMapping(value = "toCreate.do")
    public ModelAndView toCreate() {
        return super.toCreate();
    }

    @RequestMapping(value = "toAdd.do")
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView("/config/app/add_mysql");
        return mv;
    }

    @RequestMapping(value = "addMysql.do")
    public RedirectView addMysql(App app) {
        RedirectView rv = null;
        if (StringUtils.notEmpty(app.getDatabase())) {
            rv = initMysql(app);
        } else {
            rv = showMysqlList(app);
        }
        return rv;
    }

    @RequestMapping(value = "showMysqlList.do")
    public RedirectView showMysqlList(App app) {
        RedirectView rv = new RedirectView("list.do");
        List<Database> list = getMysqlDatabaseList(app.getUrl(), app.getPort(), app.getLoginName(), app.getPassword());
        for (Database database : list) {
            App obj = app.clone();
            parameterService.setUniqueId(obj);
            obj.setName(database.getName());
            service.create(obj);
        }
        return rv;
    }

    public List<Database> getMysqlDatabaseList(String address, Integer port, String username, String password) {
        Connection connection = null;
        List<Database> list = null;
        String url = "jdbc:mysql://" + address + ":" + port + "/";
        String sql = "select * from information_schema.SCHEMATA";
        try {
            connection = MysqlUtils.getConnection(url, username, password);
            list = new ArrayList<Database>();
            ResultSet rs = MysqlUtils.getResultSet(connection, sql);
            while (rs.next()) {
                String name = rs.getString("SCHEMA_NAME");
                Database obj = new Database();
                obj.setName(name);
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

    public List<Table> getMysqlTableList(String address, Integer port, String username, String password, String database) {
        List<Table> list = null;
        String url = "jdbc:mysql://" + address + ":" + port + "/";
        String sql = "select * from information_schema.TABLES where TABLE_SCHEMA='database'".replace("database", database);
        Connection connection = null;
        try {
            connection = MysqlUtils.getConnection(url, username, password);
            list = new ArrayList<Table>();
            ResultSet rs = MysqlUtils.getResultSet(connection, sql);
            while (rs.next()) {
                String table = rs.getString("TABLE_NAME");
//                    String createdTime = rs.getString("CREATE_TIME");
                String comment = rs.getString("TABLE_COMMENT");
                Table obj = new Table();
                obj.setName(table);
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

    public void readDatabase(Long id) {
        App app = service.getObject(App.class, id);
        if (null != app) {
            String createdTime = TimeTools.now();
            List<Table> tables = getMysqlTableList(app.getUrl(), app.getPort(), app.getLoginName(), app.getPassword(), app.getDatabase());
            for (Table table : tables) {
                Obj obj = new Obj();
                parameterService.setUniqueId(obj);
                obj.setApplicationId(app.getId());
                obj.setTable(table.getName());
                obj.setName(table.getComment());
                obj.setCreateTime(createdTime);
                objService.create(obj);
            }
        }
    }

    @RequestMapping(value = "initMysql.do")
    public RedirectView initMysql(App app) {
        RedirectView rv = new RedirectView("/obj/list.do",true);
        if (null != app) {
            if (null != app.getId()) {
                app = service.getObject(App.class, app.getId());
            } else {
                parameterService.setUniqueId(app);
                app.setCreateTime(TimeTools.now());
                service.create(app);
            }
            if (StringUtils.notEmpty(app.getDatabase())) {
                readDatabase(app.getId());
                rv.addStaticAttribute("applicationId", app.getId());
            } else {
                rv = showMysqlList(app);
            }
        }
        return rv;
    }

    @RequestMapping(value = "toEdit.do")
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView(this.getJspBasePath() + "edit");
        App object = this.getService().getObject(this.getClassTemplate(), id);
        mv.addObject("object", object);
        return mv;
    }

    @RequestMapping(value = "create.do", method = {RequestMethod.POST})
    public RedirectView create(App app) {
        super.create(app);
        return new RedirectView("list.do");
    }

    @RequestMapping(value = "edit.do", method = {RequestMethod.POST})
    public RedirectView edit(App app) {
        super.edit(app);
        return new RedirectView("list.do");
    }

    @RequestMapping(value = "delete.do")
    public RedirectView delete(App app) {
        super.delete(app);
        return new RedirectView("list.do");
    }

    @RequestMapping(value = "doParseSQL.do")
    public ModelAndView doParseSQL(Long applicationId) {
        ModelAndView mv = new ModelAndView(this.getJspBasePath() + "sql_list");
        try {
            List<String> sqlList = new ArrayList<String>();

            List<Obj> list = objService.getListByApplicationId(applicationId);
            for (Obj obj : list) {
                obj.setAttrs(attrService.getListByObjectId(obj.getId()));
                obj.setUniqs(uniqService.getListByObjectId(obj.getId()));
                String sql = obj.parseSQL();
                sqlList.add(sql);
            }
            mv.addObject("sqlList", sqlList);
        } catch (Exception e) {
            log.error(e);
        }
        return mv;
    }

    @RequestMapping(value = "createFile")
    public void createFile(Long id) {
        App app = service.getObject(App.class, id);
        List<Obj> objs = objService.getListByApplicationId(id);
        for (Obj obj : objs) {
            obj.setApp(app);
            List<Attr> attrs = this.attrService.getListByObjectId(obj.getId());
            obj.setAttrs(attrs);
            objService.createFiles(obj);
        }
        app.setObjs(objs);
        service.createFile(app);
    }

}