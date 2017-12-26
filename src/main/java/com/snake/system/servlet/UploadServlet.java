package com.snake.system.servlet;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.base.util.DateTimeUtils;
import com.base.util.PropertiesUtils;
import com.base.util.Thumb;
import com.snake.system.model.Attachment;
import com.snake.system.service.IAttachmentService;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;


/**
 * 处理文件上传
 * 
 * @author suxl
 * @date 2013/05/09
 */
@SuppressWarnings("all")
public class UploadServlet extends HttpServlet {
	protected Log log = LogFactory.getLog(this.getClass());
	
	private ApplicationContext ctx = null;
	private IAttachmentService attachmentService = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		if (ctx == null) {
			ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
		}
		attachmentService = (IAttachmentService) ctx.getBean("attachmentService");
	}

	public UploadServlet() {
		super();
	}

	/*public void destroy() {
		super.destroy();
	}*/

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
            String userId = request.getParameter("userId");
			ServletFileUpload servletFileUpload = new ServletFileUpload(new DiskFileItemFactory());
			servletFileUpload.setHeaderEncoding("utf-8");
			List fileList = servletFileUpload.parseRequest(request);
            // 记录文件存储路径
            String filePath = PropertiesUtils.getInstance().getProperty("upload.url","d:\\sam_xj_upload") + "\\" + DateTimeUtils.getDateStr();// 设置默认存储的路径

            String fileUrl = null;//记录图片缩略图的路径
            Iterator<FileItem> it = fileList.iterator();
            String oldName = null;
            // 组织附件信息
            Attachment attachment = new Attachment();
			while (it.hasNext()) {
				// 得到文件对象
				FileItem fileItem = (FileItem) it.next();
				//如果不是表单提交才执行
				if (!fileItem.isFormField()) {
					// 统一linux和windows的路径分隔符
                    oldName = fileItem.getName().replaceAll("/", "\\");
					String ext = oldName.substring(oldName.lastIndexOf('.') + 1, oldName.length());// 文件后缀名

					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssS");
					String dateName = sdf.format(new Date()); // 以当前日期作为文件名
					String fileName = dateName + "." + ext;

					File uploadFile = new File(filePath, fileName);// 也可不用自己写实现方法直接使用,fileItem.write(uploadFile);
					uploadFile.getParentFile().mkdirs();// 首先要确认路径是否存在
					// 检查文件是否已经存在
					if (!uploadFile.exists()) {
						// 建立文件
						uploadFile.createNewFile();
					}

					fileItem.write(uploadFile);//写入文件

					if (PropertiesUtils.getInstance().getProperty("thumb.ext","gif,png,bmp,jpeg,jpg").contains(ext.toLowerCase())) {// 判断上传的文件是否支持生成缩略图
						String thumbPath = filePath + "\\thumb";// 缩略图的路径
						File thumbFile = new File(thumbPath, fileName);// 也可不用自己写实现方法直接使用,fileItem.write(uploadFile);
						thumbFile.getParentFile().mkdirs();// 首先要确认路径是否存在
						// 检查文件是否已经存在
						if (!thumbFile.exists()) {
							// 建立文件
							thumbFile.createNewFile();
						}

						fileItem.write(thumbFile);//写入缩略图

						Thumb.saveImageAsJpg(filePath + "\\" + fileName,thumbPath + "\\" + fileName,
                                Integer.valueOf(PropertiesUtils.getInstance().getProperty("thumb.width","150")),
                                Integer.valueOf(PropertiesUtils.getInstance().getProperty("thumb.hight","120")));// 生成缩略图
						fileUrl = thumbFile.getAbsolutePath();//获得图片的缩略图路径
					}else{
						fileUrl = uploadFile.getAbsolutePath();
					}
                    int lastIndex = oldName.lastIndexOf("\\");

                    attachment.setOldFileName(oldName.substring(lastIndex+1, oldName.lastIndexOf(".")));// 存入原文件名
                    attachment.setFileName(dateName);
                    attachment.setFilePath(filePath.replace("\\", "/") + "/");
                    attachment.setFileExt(ext);
                    attachment.setFileSize(fileItem.getSize());
                    attachment.setCreatorId(Long.valueOf(userId));
                    attachment.setCreatedTime(DateTimeUtils.getNowDateTime());
					attachmentService.create(attachment);
				}
			}
			response.getWriter().print(attachment.getId()+","+fileUrl+","+oldName);
		} catch (Exception e) {
			log.error("上传附件操作失败!", e);
		}
	}

}
