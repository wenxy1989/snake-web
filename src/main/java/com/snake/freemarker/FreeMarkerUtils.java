package com.snake.freemarker;

import com.base.util.StringTools;
import com.snake.inter.model.Application;
import com.snake.inter.model.Group;
import com.snake.inter.model.Model;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreeMarkerUtils {

    private transient Logger logger = Logger.getLogger(getClass());

    public static final String MODULE_NAME = "moduleName";
    public static final String CLASS_NAME = "className";
    public static final String MODULE_CODE = "moduleCode";
    private static final String SYSTEM_ENCODING = "UTF-8";
    private int type = 0;//0-经常更新、一般不做手动修改的元素,1-经常手动修改的、不经常更新元素
    private static FreeMarkerUtils instance;

    public static FreeMarkerUtils getInstance() {
        if (null == instance) {
            instance = new FreeMarkerUtils();
        }
        return instance;
    }

    private Boolean attach = false;
    private String templateFolder = null;
    private String outputPath = "D:/workspace/school-book/";
    private Configuration cfg;

    private FreeMarkerUtils() {
    }

    private FreeMarkerUtils(String outputPath) {
        this.outputPath = outputPath;
    }

    private FreeMarkerUtils(String outputPath, String templateFolder) {
        this.templateFolder = templateFolder;
        this.outputPath = outputPath;
    }

    public static FreeMarkerUtils getNewInstance(String outputPath) {
        return new FreeMarkerUtils(outputPath);
    }

    public static FreeMarkerUtils getNewInstance(String outputPath, String templateFolder) {
        return new FreeMarkerUtils(outputPath, templateFolder);
    }

    private Configuration getConfiguration() throws Exception {
        if (null == this.cfg) {
            String classPath = this.getClass().getClassLoader().getResource("").getPath();
            String templatePath = classPath + "/template/";
            if (StringUtils.isNotBlank(this.templateFolder)) {
                templatePath = templatePath + this.templateFolder + "/";
            }
            logger.info("template path is : " + templatePath);
            this.cfg = new Configuration();
            this.cfg.setDirectoryForTemplateLoading(new File(templatePath));
        }
        return this.cfg;
    }

    private Template getTemplate(String group, String template) throws Exception {
        return getTemplate(group + "/" + template);
    }

    private Template getTemplate(String template) throws Exception {
        return getConfiguration().getTemplate(template, SYSTEM_ENCODING);
    }

    private String buildFile(String pathName, String fileName) {
        File path = new File(pathName);
        if (!path.exists()) {
            path.mkdirs();
        }
        File file = new File(pathName, fileName);
        String realPath = file.getAbsolutePath();
        return realPath;
    }

    private String buildPath(String pathHead, String... paths) {
        StringBuffer sb = new StringBuffer(pathHead);
        if (null != paths && paths.length > 0) {
            for (String path : paths) {
                sb.append("/");
                sb.append(path);
            }
        }
        String realPath = sb.toString();
        realPath.replace("//", "/");
        File savePath = new File(realPath);
        if (!savePath.exists()) {
            savePath.mkdirs();
        }
        return realPath;
    }

    private void writeTemplate(Template template, Map<String, Object> map, String saveFileName) {
        if(this.type == 1 && exists(saveFileName)){//不更新必须简单元素
            return;
        }
        Writer out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(saveFileName, attach), SYSTEM_ENCODING);
            template.process(map, out);
            if (null != out) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean exists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    public void buildTemplate(String templateName, Map<String, Object> params, String savePathName, String saveFileName) {
        try {
            this.type = 0;
            Template template = getTemplate(templateName);
            savePathName = buildPath(outputPath, savePathName);
            String saveFileRealName = buildFile(savePathName, saveFileName);
            writeTemplate(template, params, saveFileRealName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void buildTemplate(String templateName, Map<String, Object> params, String savePathName, String saveFileName, int type) {
        try {
            this.type = type;
            Template template = getTemplate(templateName);
            savePathName = buildPath(outputPath, savePathName);
            String saveFileRealName = buildFile(savePathName, saveFileName);
            writeTemplate(template, params, saveFileRealName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildTemplate(String group, String templateName, Map<String, Object> params, String savePathName, String saveFileName) {
        try {
            Template template = getTemplate(group, templateName);
            savePathName = buildPath(outputPath, savePathName);
            String saveFileRealName = buildFile(savePathName, saveFileName);
            writeTemplate(template, params, saveFileRealName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public void writeCode(Model model) throws ServiceException {
        if (null != model && null != model.getParameterList() && model.getParameterList().size() > 0) {
            templateFolder = "inter";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(FreeMarkerUtils.MODULE_NAME, model.getCode());
            params.put("model", model);
            params.put("parameters", model.getParameterList());
            params.put("now", new Date());

            {//model java
                String template = "model.ftl";
                String savePath = "src/main/java/com/school/book/model";
                String saveFile = StringTools.parseModel("@{large}.java", model.getCode());
                buildTemplate(template, params, savePath, saveFile);
            }
            {//dao interface java
                String template = "idao.ftl";
                String savePath = "src/main/java/com/school/book/dao";
                String saveFile = StringTools.parseModel("I@{large}Dao.java", model.getCode());
                buildTemplate(template, params, savePath, saveFile,1);
            }
            {//dao java
                String template = "dao.ftl";
                String savePath = "src/main/java/com/school/book/dao";
                String saveFile = StringTools.parseModel("@{large}Dao.java", model.getCode());
                buildTemplate(template, params, savePath, saveFile,1);
            }
            {//service interface java
                String template = "iservice.ftl";
                String savePath = "src/main/java/com/school/book/service";
                String saveFile = StringTools.parseModel("I@{large}Service.java", model.getCode());
                buildTemplate(template, params, savePath, saveFile,1);
            }
            {//service java
                String template = "service.ftl";
                String savePath = "src/main/java/com/school/book/service";
                String saveFile = StringTools.parseModel("@{large}Service.java", model.getCode());
                buildTemplate(template, params, savePath, saveFile,1);
            }
            {//controller java
                String template = "controller.ftl";
                String savePath = "src/main/java/com/school/book/controller";
                String saveFile = StringTools.parseModel("Default@{large}Controller.java", model.getCode());
                buildTemplate(template, params, savePath, saveFile);
            }
            {//sql mapper xml
                String template = "sql_mapper.ftl";
                String savePath = "src/main/resources/sqlmap/book";
                String saveFile = StringTools.parseModel("@{large}Mapper.xml", model.getCode());
                buildTemplate(template, params, savePath, saveFile);
            }
            {//test data provider java
                String template = "test_data_provider.ftl";
                String savePath = "src/test/java/com/school/book/data";
                String saveFile = StringTools.parseModel("@{large}TestDataProvider.java", model.getCode());
                buildTemplate(template, params, savePath, saveFile);
            }
            {//controllerTest java
                String template = "controller_tests.ftl";
                String savePath = "src/test/java/com/school/book/controller/test";
                String saveFile = StringTools.parseModel("Default@{large}ControllerTests.java", model.getCode());
                buildTemplate(template, params, savePath, saveFile);
            }
            {//serviceTest java
                String template = "service_tests.ftl";
                String savePath = "src/test/java/com/school/book/service/test";
                String saveFile = StringTools.parseModel("@{large}ServiceTests.java", model.getCode());
                buildTemplate(template, params, savePath, saveFile);
            }
            {//daoTest java
                String template = "dao_tests.ftl";
                String savePath = "src/test/java/com/school/book/dao/test";
                String saveFile = StringTools.parseModel("@{large}DaoTests.java", model.getCode());
                buildTemplate(template, params, savePath, saveFile);
            }
            {//create sql
                String template = "mysql.ftl";
                String savePath = "mysql-script";
                String saveFile = StringTools.parseModel("@{little}_create.sql", model.getCode());
                buildTemplate(template, params, savePath, saveFile);
            }
        }
    }*/

    public void writeCreateSQL(Application application) {
        if (null != application && null != application.getModelList() && application.getModelList().size() > 0) {
            templateFolder = "inter";
            for (int i = 0; i < application.getModelList().size(); i++) {
                if (i > 0) {
                    attach = true;
                }
                Model model = application.getModelList().get(i);
                if (null != model.getParameterList() && model.getParameterList().size() > 0) {
                    Map<String, Object> params = new HashMap<String, Object>();
                    params.put(FreeMarkerUtils.MODULE_NAME, model.getCode());
                    params.put("model", model);
                    params.put("parameters", model.getParameterList());
                    params.put("now", new Date());
                    String template = "mysql.ftl";
                    String savePath = "mysql-script";
                    String saveFile = StringTools.parseModel("create_@{little}_db.sql", application.getCode());
                    buildTemplate(template, params, savePath, saveFile);
                }
            }
            attach = false;
        }
    }

    public void writeInter(Group group) {
        if (null != group && null != group.getUrlList() && group.getUrlList().size() > 0) {
            templateFolder = "inter";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(FreeMarkerUtils.MODULE_NAME, group.getModel());
            params.put("group", group);
            params.put("urlList", group.getUrlList());
            params.put("now", new Date());
            {//api_controller java
                String template = "api_controller.ftl";
                String savePath = "src/main/java/com/school/inter/controller";
                String saveFile = StringTools.parseModel("@{large}APIController.java", group.getModel());
                buildTemplate(template, params, savePath, saveFile);
            }
            {//api_controller_tests java
                String template = "api_controller_tests.ftl";
                String savePath = "src/test/java/com/school/inter/controller/test";
                String saveFile = StringTools.parseModel("@{large}APIControllerTests.java", group.getModel());
                buildTemplate(template, params, savePath, saveFile);
            }
        }
    }

    public void writeModel(Model model, List<com.snake.template.model.Template> templates) {
        if (null != model && null != model.getParameterList() && model.getParameterList().size() > 0 && null != templates && templates.size() > 0) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put(FreeMarkerUtils.MODULE_NAME, model.getCode());
            params.put("model", model);
            params.put("parameters", model.getParameterList());
            params.put("now", new Date());
            for (com.snake.template.model.Template template : templates) {
                templateFolder = template.getGroup();
                String savePath = StringTools.parseModel(template.getSavePathModel(), model.getCode());
                String saveFile = StringTools.parseModel(template.getSaveFileModel(), model.getCode());
                buildTemplate(template.getName(), params, savePath, saveFile,template.getUpdateType());
            }
        }
    }

    public void buildApplication(Application application, List<com.snake.template.model.Template> templates) {
        if (null != application) {
            writeCreateSQL(application);
            if (null != application.getModelList() && application.getModelList().size() > 0) {
                for (int i = 0; i < application.getModelList().size(); i++) {
                    Model model = application.getModelList().get(i);
                    writeModel(model, templates);
                }
            }
            if (null != application.getGroupList() && application.getGroupList().size() > 0) {
                for (int i = 0; i < application.getGroupList().size(); i++) {
                    Group group = application.getGroupList().get(i);
                    writeInter(group);
                }
            }
        }
    }
}
