package com.snake.system.servlet;

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
 * 显示/打开文件
 *
 * @author suxl
 * @date 2013/05/10
 */
public class ShowFileServlet extends HttpServlet {
    protected Logger logger = Logger.getLogger(this.getClass());
    private static final long serialVersionUID = -201305101619880815L;
    private ApplicationContext ctx = null;
    private IAttachmentService attachmentService = null;

    public ShowFileServlet() {
        super();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        if (ctx == null) {
            ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(this.getServletContext());
        }
        attachmentService = (IAttachmentService) ctx.getBean("attachmentService");
    }

    public void destroy() {
        super.destroy();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.doPost(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String fileId = request.getParameter("fileId");
            if (StringUtils.isNotBlank(fileId)) {
                Long id = new Long(fileId);// 获取附件id
                Attachment attachment = attachmentService.getObject(id);
                String fileName = attachment.getFilePath() + attachment.getFileName() + "." + attachment.getFileExt();
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
                } else {// 如果是打开模式,则直接打开附件
                    File file = new File(fileName);
                    if (file.exists()) {// 如果文件存在,就打开文件
                        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
                        String fileType = attachment.getFileExt();
                        response.setContentType("text/plain;charset=utf-8");

                        if (fileType.toLowerCase().equals("pdf")) {
                            response.setHeader("Content-Type", "application/pdf");
                        } else if (fileType.toLowerCase().equals("doc") || fileType.toLowerCase().equals("docx")) {
                            response.setHeader("Content-Type", "application/ms-word");
                        } else if (fileType.toLowerCase().equals("xls") || fileType.toLowerCase().equals("xlsx")) {
                            response.setHeader("Content-Type", "application/ms-excel");
                        }

                        response.setHeader("Content-Length", String.valueOf(file.length()));
                        response.setHeader("Content-Disposition", "attachment;filename=" + attachment.getFileName());

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
        } catch (EOFException e) {
            logger.error("connection break", e);
        } catch (Exception e) {
            logger.error("show file failed", e);
        }
    }

}
