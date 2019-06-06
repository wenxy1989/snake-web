package com.snake.mysql.controller;

import com.snake.mysql.DatabaseService;
import com.snake.mysql.model.Column;
import com.snake.mysql.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/database/")
public class DatabaseController {

    @Autowired
    public DatabaseService databaseService;

    @RequestMapping("mysql/infoView")
    public ModelAndView infoView(){
        return new ModelAndView("/inter/database/mysql");
    }

    @ResponseBody
    @RequestMapping("mysql/info")
    public Object mysqlInfo(@RequestBody Map map) {
        databaseService.setProperties(map);
        List<Table> tableList = databaseService.selectTableList(map);
        if (null != tableList && tableList.size() > 0) {
//            int tableSize = tableList.size();
            for (Table table : tableList) {
                System.out.println(table.getName());
                map.put("table", table.getName());
                List<Column> columnList = databaseService.selectColumnList(map);
                table.setColumnList(columnList);
            }
        }
        return tableList;
    }

}
