package com.snake.freemarker;

import com.base.util.DateTimeUtils;
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
import java.util.*;

public class FreeMarkerUtils {

    private transient Logger logger = Logger.getLogger(getClass());

    private static final String SYSTEM_ENCODING = "UTF-8";
    private static Boolean attach = false;
    private static FreeMarkerUtils instance;

    public static FreeMarkerUtils getInstance() {
        if (null == instance) {
            instance = new FreeMarkerUtils();
        }
        return instance;
    }

    private Configuration templateConfiguration;

    private FreeMarkerUtils() {
    }

    private static String outputPath;

    private String getClassPath() {
        return this.getClass().getClassLoader().getResource("").getPath();
    }

    private String getOutputPath() {
        if (null == outputPath) {
            String webPath = new File(getClassPath()).getParentFile().getParent();
            outputPath = webPath + "/output";
        }
        return outputPath;
    }

    private Configuration getConfiguration() throws Exception {
        if (null == this.templateConfiguration) {
            this.templateConfiguration = new Configuration();
            this.templateConfiguration.setDirectoryForTemplateLoading(new File(getClassPath() + "/template/"));
        }
        return this.templateConfiguration;
    }

    private Template getTemplate(TemplateConfig config) throws Exception {
        return getConfiguration().getTemplate(config.getGroup() + "/" + config.getName(), SYSTEM_ENCODING);
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

    public String buildPath(String... paths) {
        StringBuffer sb = new StringBuffer(getOutputPath());
        if (null != paths && paths.length > 0) {
            for (String path : paths) {
                sb.append("/");
                sb.append(path);
            }
        }
        String realPath = sb.toString().replaceAll("\\/\\/", "/").replaceAll("\\.", "/");
        File savePath = new File(realPath);
        if (!savePath.exists()) {
            savePath.mkdirs();
        }
        return realPath;
    }

    public static boolean exists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    //type:0-经常更新、一般不做手动修改的元素,1-经常手动修改的、不经常更新元素
    public static void writeTemplate(Template template, Map<String, Object> map, String saveFileName, int type) {
        if (type == 1 && exists(saveFileName)) {//不更新必须简单元素
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

    public void buildTemplate(TemplateConfig config, Map<String, Object> params, String application) {
        try {
            String savePathName = buildPath(application, StringTools.parsePath(config.getSavePathModel(), application));
            String saveFileRealName = buildFile(savePathName, StringTools.parsePath(config.getSaveFileModel(), application));
            writeTemplate(getTemplate(config), params, saveFileRealName, config.getUpdateType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void buildTemplate(TemplateConfig config, Map<String, Object> params, String application, String model) {
        try {
            String savePathName = buildPath(application, StringTools.parsePath(config.getSavePathModel(), application, model));
            String saveFileRealName = buildFile(savePathName, StringTools.parsePath(config.getSaveFileModel(), application, model));
            writeTemplate(getTemplate(config), params, saveFileRealName, config.getUpdateType());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void writeInter(Application application, Group group, List<TemplateConfig> configList) {
        if (null != group && null != configList && configList.size() > 0) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("now", DateTimeUtils.getNowDateTime());
            params.put("model", group.getModel());
            params.put("group", group);
            params.put("urlList", group.getUrlList());
            params.put("now", new Date());
            params.put("typeProperties", new TypeProperties());
            for (TemplateConfig config : configList) {
                buildTemplate(config, params, application.getCode());
            }
        }
    }

    public void writeModel(Application application, Model model, List<TemplateConfig> templates) {
        if (null != model && null != templates && templates.size() > 0) {
            Map<String, Object> params = HashMaps.build(String.class, Object.class)
                    .add("now", DateTimeUtils.getNowDateTime())
                    .add("app", application)
                    .add("model", model)
                    .add("parameters", model.getParameterList())
                    .add("typeProperties", new TypeProperties());
            for (TemplateConfig config : templates) {
                if ("model".equals(config.getType())) {
                    buildTemplate(config, params, application.getCode(), model.getCode());
                }
            }
        }
    }

    public void writeApplication(Application application, List<TemplateConfig> templates) {
        if (null != application && null != templates && templates.size() > 0) {
            Map<String, Object> params = HashMaps.build(String.class, Object.class)
                    .add("now", DateTimeUtils.getNowDateTime())
                    .add("app", application)
                    .add("modelList", application.getModelList())
                    .add("typeProperties", new TypeProperties());
            for (TemplateConfig config : templates) {
                if ("application".equals(config.getType())) {
                    buildTemplate(config, params, application.getCode());
                }
            }
            /*
            if (null != application.getGroupList() && application.getGroupList().size() > 0) {
                for (int i = 0; i < application.getGroupList().size(); i++) {
                    Group group = application.getGroupList().get(i);
                    writeInter(application, group, templates);
                }
            }*/
        }
    }
}
