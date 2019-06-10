package com.snake.mysql.controller;

import com.snake.inter.model.Application;
import com.snake.inter.model.Model;
import com.snake.inter.model.ModelParameter;
import com.snake.inter.service.IApplicationService;
import com.snake.inter.service.IModelParameterService;
import com.snake.inter.service.IModelService;
import com.snake.mysql.DatabaseService;
import com.snake.mysql.model.Column;
import com.snake.mysql.model.Database;
import com.snake.mysql.model.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/database/")
public class DatabaseController {

    @Autowired
    public DatabaseService databaseService;

    @Resource(name = "applicationService")
    public IApplicationService applicationService;

    @Autowired
    public IModelService modelService;

    @Autowired
    public IModelParameterService modelParameterService;

    @RequestMapping("mysql/infoView")
    public ModelAndView infoView(){
        return new ModelAndView("/inter/database/mysql");
    }

    @ResponseBody
    @RequestMapping("mysql/info")
    public Object mysqlInfo(@RequestBody Map<String,String> map) {
        databaseService.setProperties(map);
        Database database = new Database(map.get("name"),map.get("username"),map.get("password"),map.get("url"),map.get("database"));
        List<Table> tableList = databaseService.selectTableList(map);
        if (null != tableList && tableList.size() > 0) {
//            int tableSize = tableList.size();
            for (Table table : tableList) {
//                System.out.println(table.getName());
                map.put("table", table.getName());
                List<Column> columnList = databaseService.selectColumnList(map);
                table.setColumnList(columnList);
            }
        }
        database.setTableList(tableList);
        formAndSaveApplication(database);
        return "success";
    }

    public Application formAndSaveApplication(Database database){
        Application application = Application.build(database);
        try {
            applicationService.create(application);
            if (null != database.getTableList()) {
                List<Model> modelList = new ArrayList<Model>();
                for (Table table : database.getTableList()) {
                    Model model = Model.build(table);
                    model.setApplicationId(application.getId());
                    modelService.create(model);
                    if (null != table.getColumnList()) {
                        List<ModelParameter> parameterList = new ArrayList<ModelParameter>();
                        for (Column column : table.getColumnList()) {
                            ModelParameter parameter = ModelParameter.build(column);
                            parameter.setModelId(model.getId());
                            parameterList.add(parameter);
                        }
                        modelParameterService.batchInsert(parameterList);
                        model.setParameterList(parameterList);
                    }
                    modelList.add(model);
                }
                application.setModelList(modelList);
            }
            return application;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
