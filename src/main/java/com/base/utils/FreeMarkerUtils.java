package com.base.utils;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.URL;
import java.util.Map;

public class FreeMarkerUtils {


    private FreeMarkerUtils(){}

    private Logger logger = Logger.getLogger(FreeMarkerUtils.class);

	public static final String MODULE_NAME = "moduleName";
	public static final String CLASS_NAME = "className";
	public static final String MODULE_CODE = "moduleCode";
	private static final String SYSTEM_ENCODING = "UTF-8";
	private static final String MODULE_LARGE_MODEL = "@{large}";
	private static final String MODULE_LITTLE_MODEL = "@{little}";
//	private static final String TEMPLATE_PATH = "D://freemarker/template/";
//	private static final String PROJECT_PATH = "D://freemarker/projects/";
	private static String template_path = null;
	private static String project_path = "D://output/";

	private static Configuration cfg;

	public static Configuration getConfiguration(String templatePath) throws Exception {
		if(null == cfg){
			cfg = new Configuration();
			cfg.setDirectoryForTemplateLoading(new File(templatePath));
		}
		return cfg;
	}

    private static FreeMarkerUtils instance;

    public static FreeMarkerUtils getInstance(){
        if(null == instance){
            instance = new FreeMarkerUtils();
        }
        return instance;
    }
	
	public Template getTemplate(String templateName) throws Exception{
        if(null == template_path){
            Class cla = this.getClass();
            ClassLoader cl = cla.getClassLoader();
            URL url = cl.getResource(".");
            template_path = url.getFile()+"template";
            System.out.println(template_path);
        }
		return getConfiguration(template_path).getTemplate(templateName, SYSTEM_ENCODING);
	}

	/**
	 * 生成想要的问题件
	 * @param templateName 模板名
	 * @param module 模块名、表名
	 * @param params 树形Map<String,Object>
	 * @param savePathModel 存储路径模板，包含 @{moduleCode}
	 * @param saveFileModel 存储文件名模板 ，包含@{moduleCode}
	 */
	public void buildTemplate(String templateName, String module, Map<String,Object> params,String savePathModel, String saveFileModel) {
		String savePathName = project_path + "/" + savePathModel.replace(".", "/")
				.replace(MODULE_LARGE_MODEL, StringUtils.getFirstLarge(module))
				.replace(MODULE_LITTLE_MODEL, StringUtils.getFirstLittle(module));
		System.out.println("savePathName is : " + savePathName);
		File savePath = new File(savePathName);
		if (!savePath.exists()) {
			savePath.mkdirs();
		}
		String saveFileName = saveFileModel.replace(MODULE_LARGE_MODEL, StringUtils.getFirstLarge(module))
				.replace(MODULE_LITTLE_MODEL, StringUtils.getFirstLittle(module));
		String realSaveFileName = savePathName + "/" + saveFileName;
		try {
			Writer out = new OutputStreamWriter(new FileOutputStream(realSaveFileName), SYSTEM_ENCODING);
			getTemplate(templateName).process(params, out);
			if(null != out){
				try{
				out.close();
				}catch(Exception e){
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
