package com.snake.mysql.controller;

import com.snake.system.controller.BasicController;
import com.base.exception.ServiceException;
import com.base.util.*;
import com.snake.mysql.model.Database;
import com.snake.mysql.model.Table;
import com.snake.mysql.service.IDatabaseService;
import com.snake.mysql.service.ITableService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
@RequestMapping("/mysql/table/")
public class TableController extends BasicController {

    @Resource(name = "tableService")
    private ITableService tableService;

    @Resource(name = "databaseService")
    private IDatabaseService databaseService;

    @RequestMapping(value = "{databaseId}/page")
    public ModelAndView page(@PathVariable Long databaseId) {
        ModelAndView mv = new ModelAndView("/mysql/table/list");
        try {
            Database database = databaseService.getObject(databaseId);
            mv.addObject("database", database);
            Criteria criteria = new SimpleCriteria();
            criteria.addCondition(0,new Condition("database_id","=",databaseId));
            Page page = tableService.getList(criteria);
            mv.addObject("page", page);
        } catch (ServiceException e) {
            logger.error("query table page error", e);
        }
        mv.addObject("databaseId",databaseId);
        return mv;
    }

    @RequestMapping(value = "{databaseId}/toAdd")
    public ModelAndView toCreate(@PathVariable Long databaseId) {
        ModelAndView mv = new ModelAndView("/mysql/table/add");
        try {
            Database database = databaseService.getObject(databaseId);
            mv.addObject("database", database);
        } catch (ServiceException e) {
            logger.error("get database error", e);
        }
        mv.addObject("databaseId",databaseId);
        return mv;
    }

    @RequestMapping(value = "{databaseId}/toEdit")
    public ModelAndView toEdit(@PathVariable Long databaseId,Long id) {
        ModelAndView mv = new ModelAndView("/mysql/table/edit");
        try {
            Table table = tableService.getObject(id);
            mv.addObject("table", table);
        } catch (ServiceException e) {
            logger.error("get table error", e);
        }
        mv.addObject("databaseId",databaseId);
        return mv;
    }

    @RequestMapping(value = "{databaseId}/add", method = {RequestMethod.POST})
    public RedirectView create(@PathVariable Long databaseId,Table table) {
        RedirectView rv = new RedirectView(databaseId+"/page");
        try {
            tableService.create(table);
        } catch (ServiceException e) {
            logger.error("create table error", e);
        }
        rv.addStaticAttribute("databaseId", databaseId);
        return rv;
    }

    @RequestMapping(value = "{databaseId}/edit", method = {RequestMethod.POST})
    public RedirectView edit(@PathVariable Long databaseId,Table table) {
        RedirectView rv = new RedirectView(databaseId+"/page");
        try {
            tableService.update(table);
        } catch (ServiceException e) {
            logger.error("update table error", e);
        }
        rv.addStaticAttribute("databaseId", databaseId);
        return rv;
    }

    @RequestMapping(value = "{databaseId}/delete")
    public RedirectView delete(@PathVariable Long databaseId,Long id) {
        RedirectView rv = new RedirectView(databaseId+"/page");
        try {
            tableService.delete(id);
        } catch (ServiceException e) {
            logger.error("delete table error", e);
        }
        rv.addStaticAttribute("databaseId", databaseId);
        return rv;
    }

    @RequestMapping(value = "{databaseId}/clean")
    public RedirectView clean(@PathVariable Long databaseId) {
        RedirectView rv = new RedirectView("page");
        try {
            tableService.deleteByDatabaseId(databaseId);
        }catch (ServiceException e){
            logger.error("clean database table error",e);
        }
        rv.addStaticAttribute("databaseId", databaseId);
        return rv;
    }

    @RequestMapping(value = "{databaseId}/importDatabase")
    public RedirectView importDatabase(Long databaseId) {
        RedirectView rv = new RedirectView(databaseId+"/page");
        try {
            Database database = databaseService.getObject(databaseId);
            if (null != database) {
                String createdTime = DateTimeUtils.getNowDateTime();
                List<Table> tables = getMysqlTableList(database.getAddress(), database.getPort(), database.getUsername(), database.getPassword(), database.getCode());
                for (Table table : tables) {
                    table.setDatabaseId(database.getId());
                    table.setCode(table.getName());
                    table.setName(table.getComment());
                    table.setCreatedTime(createdTime);
                    tableService.create(table);
                }
            }
        } catch (ServiceException e) {
            logger.error("import table from mysql database error", e);
        }
        rv.addStaticAttribute("databaseId",databaseId);
        return rv;
    }

    public List<Table> getMysqlTableList(String address, Integer port, String username, String password, String database) {
        List<Table> list = null;
        String url = "jdbc:mysql://" + address + ":" + port + "/";
        String sql = "select * from information_schema.TABLES where TABLE_SCHEMA='@database'".replace("@database", database);
        Connection connection = null;
        try {
            connection = MysqlUtils.getConnection(url, username, password);
            list = new ArrayList<Table>();
            ResultSet rs = MysqlUtils.getResultSet(connection, sql);
            while (rs.next()) {
                String name = rs.getString("TABLE_NAME");
//                    String createdTime = rs.getString("CREATE_TIME");
                String comment = rs.getString("TABLE_COMMENT");
                Table table = new Table(name);
                table.setComment(comment);
                list.add(table);
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

}