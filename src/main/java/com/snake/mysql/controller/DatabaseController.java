package com.snake.mysql.controller;

import com.snake.system.controller.BasicController;
import com.base.exception.ServiceException;
import com.base.util.Criteria;
import com.base.util.DateTimeUtils;
import com.base.util.MysqlUtils;
import com.base.util.SimpleCriteria;
import com.snake.mysql.model.*;
import com.snake.mysql.service.IDatabaseService;
import com.snake.mysql.service.IColumnService;
import com.snake.mysql.service.ITableService;
import com.snake.mysql.service.IUniqueService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/mysql/database/")
public class DatabaseController extends BasicController {

    @Resource(name = "databaseService")
    private IDatabaseService databaseService;

    @Resource(name = "tableService")
    private ITableService tableService;

    @Resource(name = "columnService")
    private IColumnService columnService;

    @Resource(name = "uniqueService")
    private IUniqueService uniqueService;

    @RequestMapping(value = "page")
    public ModelAndView page() {
        ModelAndView mv = new ModelAndView("/mysql/database/list");
        try {
            Criteria criteria = new SimpleCriteria();
            Page page = databaseService.getList(criteria);
            mv.addObject("page", page);
        } catch (ServiceException e) {
            logger.error("get database page error", e);
        }
        return mv;
    }

    @RequestMapping(value = "toAdd")
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView("/mysql/database/add");
        return mv;
    }

    @RequestMapping(value = "toEdit")
    public ModelAndView toEdit(Long id) {
        ModelAndView mv = new ModelAndView("/mysql/database/edit");
        try {
            Database database = databaseService.getObject(id);
            mv.addObject("database", database);
        } catch (ServiceException e) {
            logger.error("get database error",e);
        }
        return mv;
    }

    @RequestMapping(value = "add", method = {RequestMethod.POST})
    public RedirectView add(Database database,HttpServletRequest request) {
        RedirectView rv = new RedirectView("page");
        try {
            database.setCreatorId(getLoginUserId(request));
            database.setCreatedTime(DateTimeUtils.getNowDateTime());
            databaseService.create(database);
        } catch (ServiceException e) {
            logger.error("create database error",e);
        }
        return rv;
    }

    @RequestMapping(value = "edit", method = {RequestMethod.POST})
    public RedirectView edit(Database database) {
        RedirectView rv = new RedirectView("page");
        try {
            databaseService.update(database);
        } catch (ServiceException e) {
            logger.error("update database error",e);
        }
        return rv;
    }

    @RequestMapping(value = "delete")
    public RedirectView delete(Long id) {
        RedirectView rv = new RedirectView("page");
        try {
            databaseService.delete(id);
        } catch (ServiceException e) {
            logger.error("delete database error",e);
        }
        return rv;
    }

    @RequestMapping(value = "toCreate")
    public ModelAndView toCreate() {
        ModelAndView mv = new ModelAndView("/mysql/database/list");
        return mv;
    }

    @RequestMapping(value = "addMysql")
    public RedirectView addMysql(Database database) {
        RedirectView rv = null;
        if (StringUtils.isNotBlank(database.getCode())) {
            rv = initMysql(database);
        } else {
            rv = showMysqlList(database);
        }
        return rv;
    }

    @RequestMapping(value = "showMysqlList")
    public RedirectView showMysqlList(Database database) {
        RedirectView rv = new RedirectView("page");
        List<Database> list = getMysqlDatabaseList(database.getAddress(), database.getPort(), database.getUsername(), database.getPassword());
        for (Database obj : list) {
            try {
                databaseService.create(obj);
            } catch (ServiceException e) {
                logger.error("create database error name : " + obj.getName(), e);
            }
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
            logger.error(e);
        } catch (SQLException e) {
            logger.error(e);
        } finally {
            if (null != connection) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    logger.error(e);
                }
            }
        }
        return list;
    }

    @RequestMapping(value = "initMysql")
    public RedirectView initMysql(Database database) {
        RedirectView rv = new RedirectView("/table/page", true);
        if (null != database) {
            try {
                if (null != database.getId()) {
                    database = databaseService.getObject(database.getId());
                } else {
                    database.setCreatedTime(DateTimeUtils.getNowDateTime());
                    databaseService.create(database);
                }
                if (StringUtils.isNotBlank(database.getCode())) {
                    rv.addStaticAttribute("databaseId", database.getId());
                } else {
                    rv = showMysqlList(database);
                }
            }catch (ServiceException e){
                logger.error("init mysql database error",e);
            }
        }
        return rv;
    }

    @RequestMapping(value = "doParseSQL")
    public ModelAndView doParseSQL(Long databaseId) {
        ModelAndView mv = new ModelAndView("/mysql/database/sql_list");
        try {
            List<String> sqlList = new ArrayList<String>();

            List<Table> list = tableService.getListByApplicationId(databaseId);
            for (Table table : list) {
                table.setColumns(columnService.getListByTableId(table.getId()));
                table.setUniques(uniqueService.getListByTableId(table.getId()));
                String sql = table.parseSQL();
                sqlList.add(sql);
            }
            mv.addObject("sqlList", sqlList);
        } catch (Exception e) {
            logger.error(e);
        }
        return mv;
    }

}