package com.snake.freemarker;

import com.base.util.HashMaps;
import com.base.util.StringTools;
import com.snake.inter.model.Application;
import com.snake.inter.model.Group;
import com.snake.inter.model.Model;
import com.snake.template.model.TemplateConfig;
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
    private String outputFolder = null;
    private Configuration templateConfiguration;

    private FreeMarkerUtils() {
    }

    private FreeMarkerUtils(String outputFolder) {
        this.outputFolder = outputFolder;
    }

    public static FreeMarkerUtils getNewInstance(String outputFolder) {
        return new FreeMarkerUtils(outputFolder);
    }

    private String getClassPath() {
        return this.getClass().getClassLoader().getResource("").getPath();
    }

    private Configuration getConfiguration() throws Exception {
        if (null == this.templateConfiguration) {
            String templatePath = getClassPath() + "/template/";
            if (StringUtils.isNotBlank(this.templateFolder)) {
                templatePath = templatePath + this.templateFolder + "/";
            }
            this.templateConfiguration = new Configuration();
            this.templateConfiguration.setDirectoryForTemplateLoading(new File(templatePath));
        }
        return this.templateConfiguration;
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
        StringBuffer sb = new StringBuffer(getClassPath());
        sb.append(pathHead);
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

    private boolean exists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    private void writeTemplate(Template template, Map<String, Object> map, String saveFileName) {
        if (this.type == 1 && exists(saveFileName)) {//不更新必须简单元素
            return;
        }
        Writer out = null;
        try {
            out = new OutputStreamWriter(new FileOutputStream(saveFileName, attach), SYSTEM_ENCODING);
            template.process(map, out);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != out) {
                try {
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void buildTemplate(String templateName, Map<String, Object> params, String savePathName, String saveFileName) {
        try {
            this.type = 0;
            Template template = getTemplate(templateName);
            savePathName = buildPath(outputFolder, savePathName);
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
            savePathName = buildPath(outputFolder, savePathName);
            String saveFileRealName = buildFile(savePathName, saveFileName);
            writeTemplate(template, params, saveFileRealName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildTemplate(String group, String templateName, Map<String, Object> params, String savePathName, String saveFileName) {
        try {
            Template template = getTemplate(group, templateName);
            savePathName = buildPath(outputFolder, savePathName);
            String saveFileRealName = buildFile(savePathName, saveFileName);
            writeTemplate(template, params, saveFileRealName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeInter(Group group) {
        if (null != group && null != group.getUrlList() && group.getUrlList().size() > 0) {
            templateFolder = "inter";
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("model", group.getModel());
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

    public void writeModel(Model model, List<TemplateConfig> templates) {
        if (null != model && null != model.getParameterList() && model.getParameterList().size() > 0 && null != templates && templates.size() > 0) {
            Map<String, Object> params = HashMaps.build(String.class, Object.class)
                    .add("now", new Date())
                    .add("application", model.getApplication())
                    .add("model", model)
                    .add("parameters", model.getParameterList());
            for (TemplateConfig template : templates) {
                templateFolder = template.getGroup();
                String savePath = StringTools.parseModel(template.getSavePathModel(), model.getCode());
                String saveFile = StringTools.parseModel(template.getSaveFileModel(), model.getCode());
                buildTemplate(template.getName(), params, savePath, saveFile, template.getUpdateType());
            }
        }
    }

    public void writeApplication(Application application, List<TemplateConfig> templates) {
        if (null != application && null != templates && templates.size() > 0) {
            Map<String, Object> params = HashMaps.build(String.class, Object.class)
                    .add("now", new Date())
                    .add("application", application);
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
