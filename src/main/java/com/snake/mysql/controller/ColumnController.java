package com.snake.mysql.controller;

import com.snake.system.controller.BasicController;
import com.base.exception.ServiceException;
import com.base.util.*;
import com.snake.mysql.model.Column;
import com.snake.mysql.model.Database;
import com.snake.mysql.model.Table;
import com.snake.mysql.service.IColumnService;
import com.snake.mysql.service.IDatabaseService;
import com.snake.mysql.service.ITableService;
import org.apache.commons.lang.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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
 * mysql column
 * author wenxy
**/
@Controller
@RequestMapping("/mysql/column/")
public class ColumnController extends BasicController{

    @Resource(name="databaseService")
    private IDatabaseService databaseService;

    @Resource(name="tableService")
    private ITableService tableService;

	@Resource(name= "columnService")
	private IColumnService columnService;
	
	@RequestMapping(value = "{tableId}/page")
	public ModelAndView page(@PathVariable Long tableId){
		ModelAndView mv = new ModelAndView("/mysql/column/list");
        try {
            Criteria criteria = new SimpleCriteria();
            criteria.addCondition(0, new Condition("table_id", "=", tableId));
            Table table = tableService.getObject(tableId);
            mv.addObject("table", table);
            Page page = columnService.getList(criteria);
            mv.addObject("page", page);
        }catch (ServiceException e){
            logger.error("query database column page error",e);
        }
        mv.addObject("tableId",tableId);
		return mv;
	}
	
    @RequestMapping(value = "{tableId}/toAdd")
    public ModelAndView toCreate(@PathVariable Long tableId) {
    	ModelAndView mv = new ModelAndView("/mysql/column/add");
        try {
            Table table = tableService.getObject(tableId);
            mv.addObject("table", table);
        } catch (ServiceException e) {
            logger.error("get database table error",e);
        }
        mv.addObject("tableId",tableId);
    	return mv;
    }

    @RequestMapping(value = "{tableId}/toEdit")
    public ModelAndView toEdit(@PathVariable Long tableId,Long id) {
        ModelAndView mv = new ModelAndView("/mysql/column/edit");
        try {
            Column column = columnService.getObject(id);
            mv.addObject("column",column);
        } catch (ServiceException e) {
            logger.error("get database column error",e);
        }
        mv.addObject("tableId",tableId);
        return mv;
    }

    @RequestMapping(value = "{tableId}/add", method = { RequestMethod.POST })
    public RedirectView add(@PathVariable Long tableId,Column column) {
        RedirectView rv = new RedirectView("page");
        try {
            columnService.create(column);
        }catch (ServiceException e){
            logger.error("create database column error",e);
        }
        rv.addStaticAttribute("tableId", tableId);
    	return rv;
    }

    @RequestMapping(value = "{tableId}/edit", method = { RequestMethod.POST })
    public RedirectView edit(@PathVariable Long tableId,Column column) {
        RedirectView rv = new RedirectView("page");
        try {
            columnService.update(column);
        }catch (ServiceException e){
            logger.error("update database column error",e);
        }
        rv.addStaticAttribute("tableId", tableId);
        return rv;
    }

    @RequestMapping(value = "{tableId}/delete")
    public RedirectView delete(@PathVariable Long tableId,Long id) {
        RedirectView rv = new RedirectView("page");
        try {
            columnService.delete(id);
        }catch (ServiceException e){
            logger.error("delete database column error",e);
        }
        rv.addStaticAttribute("tableId", tableId);
        return rv;
    }

    @RequestMapping(value = "{tableId}/clean")
    public RedirectView clean(@PathVariable Long tableId) {
        RedirectView rv = new RedirectView("page");
        try {
            columnService.deleteByTableId(tableId);
        }catch (ServiceException e){
            logger.error("clean database table column error",e);
        }
        rv.addStaticAttribute("tableId", tableId);
        return rv;
    }

    @RequestMapping(value = "{tableId}/importTable")
    public RedirectView importTable(@PathVariable Long tableId,HttpServletRequest request) {
        RedirectView rv = new RedirectView("/mysql/column/"+tableId+"/page", true);
        try {
            Table table = tableService.getObject(tableId);
            if (null != table) {
                Database database = databaseService.getObject(table.getDatabaseId());
                if (null != database) {
                    List<Column> list = getMysqlDatabaseList(database.getAddress(), database.getPort(), database.getUsername(), database.getPassword(), database.getCode(), table.getCode());
                    if (null != list && list.size() > 0) {
                        String createTime = DateTimeUtils.getNowDateTime();
                        Long loginUserId = getLoginUserId(request);
                        for (Column column : list) {
                            column.setTableId(tableId);
                            column.setCreatedTime(createTime);
                            column.setCreatorId(loginUserId);
                            columnService.create(column);
                        }
                    }
                }
            }
        }catch (ServiceException e){
            logger.error("database init table error",e);
        }
        rv.addStaticAttribute("tableId", tableId);
        return rv;
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
                String dataType = rs.getString("DATA_TYPE");
                String columnType = rs.getString("COLUMN_TYPE");
                String columnDefault = rs.getString("COLUMN_DEFAULT");
                Integer ordinalPosition = rs.getInt("ORDINAL_POSITION");
                String comment = rs.getString("COLUMN_COMMENT");
                String isNullable = rs.getString("IS_NULLABLE");
                String extra = rs.getString("EXTRA");
                Column column = new Column();
                column.setName(comment);
                column.setCode(name);
                column.setDataType(dataType);
                column.setType(columnType);
                column.setComment(comment);
                column.setDefaultValue(columnDefault);
                column.setOrderPosition(ordinalPosition);
                column.setAllowBlank(StringUtils.isNotBlank(isNullable) && "YES".equals(isNullable.toUpperCase()));
                column.setAutoIncrement(StringUtils.isNotBlank(extra) && "AUTO_INCREMENT".equals(extra.toUpperCase()));
                list.add(column);
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