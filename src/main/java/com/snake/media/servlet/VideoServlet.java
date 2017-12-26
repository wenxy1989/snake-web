package com.snake.media.servlet;

import com.base.exception.ServiceException;
import com.snake.media.model.Image;
import com.snake.media.model.Video;
import com.snake.media.service.IImageService;
import com.snake.media.service.IVideoService;
import com.snake.system.model.Attachment;
import com.snake.system.service.IAttachmentService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * Created by wen on 2015/5/1.
 */
public class VideoServlet extends HttpServlet {
    protected Logger logger = Logger.getLogger(this.getClass());
    private ApplicationContext ctx = null;
    private IVideoService videoService = null;
    private IAttachmentService attachmentService = null;
    private IImageService imageService = null;

    public VideoServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        if (ctx == null) {
            ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        }
        videoService = (IVideoService) ctx.getBean("videoService");
        attachmentService = (IAttachmentService) ctx.getBean("attachmentService");
        imageService = (IImageService) ctx.getBean("imageService");
    }

    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String type = request.getParameter("type");
        String videoId = request.getParameter("videoId");
        if(StringUtils.isNotBlank(videoId)){
            video(request,response);
        }else if("image".equals(type)){
            image(request,response);
        }else{
            attachment(request,response);
        }
    }

    public void video(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String videoId = request.getParameter("videoId");
            String referer = request.getHeader("Referer");
            videoId = referer.substring(referer.lastIndexOf("/")+1,referer.lastIndexOf("."));
            Video video = videoService.getObject(new Long(videoId));
            File file = new File(video.getFilePath());
            if (file.exists()) {// 如果文件存在,就打开文件
                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                response.setContentType("text/plain;charset=utf-8");
                response.setHeader("Content-Type", "application/ms-excel");
                response.setHeader("Content-Length", String.valueOf(file.length()));
                response.setHeader("Content-Disposition", "application/x-mpegurl");//ios和android支持
                //response.setHeader("Content-Disposition","application/vnd.apple.mpegurl");//ios支持，android不支持

                BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
                byte[] input = new byte[1024];
                boolean eof = false;
                while (!eof) {
                    int length = bis.read(input);
                    if (length == -1) {
                        eof = true;
                    } else {
                        bos.write(input, 0, length);
                    }
                }
                bos.flush();
                bis.close();
                bos.close();
            }
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }

    public void attachment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String fileId = request.getParameter("fileId");
            if(StringUtils.isNotBlank(fileId)){
                Long id = new Long(fileId);// 获取附件id
                Attachment attachment = attachmentService.getObject(id);
                String fileName = attachment.getFilePath()+attachment.getFileName()+"."+attachment.getFileExt();
                if ("jpg".equals(attachment.getFileExt())) {// 如果是图片，则显示
                    FileInputStream is = new FileInputStream(fileName);
                    int i = is.available(); // 得到文件大小
                    byte data[] = new byte[i];
                    is.read(data); // 读数据
                    is.close();
                    response.setContentType("image/*"); // 设置返回的文件类型
                    OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
                    toClient.write(data); // 输出数据
                    toClient.close();
                }else{// 如果是打开模式,则直接打开附件
                    File file = new File(fileName);
                    if (file.exists()) {// 如果文件存在,就打开文件
                        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                        String fileType = attachment.getFileExt();
                        response.setContentType("text/plain;charset=utf-8");

                        if (fileType.toLowerCase().equals("pdf")) {
                            response.setHeader("Content-Type", "application/pdf");
                        } else if (fileType.toLowerCase().equals("doc") || fileType.toLowerCase().equals("docx")) {
                            response.setHeader("Content-Type","application/ms-word");
                        } else if (fileType.toLowerCase().equals("xls") || fileType.toLowerCase().equals("xlsx")) {
                            response.setHeader("Content-Type","application/ms-excel");
                        }

                        response.setHeader("Content-Length", String.valueOf(file.length()));
                        response.setHeader("Content-Disposition","attachment;filename="+attachment.getFileName());

                        BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
                        byte[] input = new byte[1024];
                        boolean eof = false;
                        while (!eof) {
                            int length = bis.read(input);
                            if (length == -1) {
                                eof = true;
                            } else {
                                bos.write(input, 0, length);
                            }
                        }
                        bos.flush();
                        bis.close();
                        bos.close();
                    }
                }
            }
        }catch(EOFException e){
            logger.error("连接已中断",e);
        }catch (Exception e) {
            logger.error("显示文件失败", e);
        }
    }

    public void image(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageId = request.getParameter("imageId");
        try {
            Image image = imageService.getObject(new Long(imageId));
            File file = new File(image.getFilePath());
            FileInputStream is = new FileInputStream(file);
            int i = is.available(); // 得到文件大小
            byte data[] = new byte[i];
            is.read(data); // 读数据
            is.close();
            response.setContentType("image/*"); // 设置返回的文件类型
            OutputStream toClient = response.getOutputStream(); // 得到向客户端输出二进制数据的对象
            toClient.write(data); // 输出数据
            toClient.close();
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
