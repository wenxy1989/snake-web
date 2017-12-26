package com.snake.system.model;

/**
* @ClassName: Attachment
* @Description: 附件信息表
* @author: wenxy
* @date: 2014-07-15 13:32:29
*/
public class Attachment{

    private Long id;
    private String oldFileName;
    private String fileName;
    private String filePath;
    private Long fileSize;
    private String fileExt;
    private Long creatorId;
    private String createdTime;

    public void setId(Long id) {//主键
        this.id= id;
    }
    public Long getId() {//主键
        return id;
    }
    public void setOldFileName(String oldFileName) {//原文件名
        this.oldFileName= oldFileName;
    }
    public String getOldFileName() {//原文件名
        return oldFileName;
    }
    public void setFileName(String fileName) {//文件名
        this.fileName= fileName;
    }
    public String getFileName() {//文件名
        return fileName;
    }
    public void setFilePath(String filePath) {//文件路径
        this.filePath= filePath;
    }
    public String getFilePath() {//文件路径
        return filePath;
    }
    public void setFileSize(Long fileSize) {//文件大小
        this.fileSize= fileSize;
    }
    public Long getFileSize() {//文件大小
        return fileSize;
    }
    public void setFileExt(String fileExt) {//扩展名(后缀名)
        this.fileExt= fileExt;
    }
    public String getFileExt() {//扩展名(后缀名)
        return fileExt;
    }
    public void setCreatorId(Long creatorId) {//上传人
        this.creatorId= creatorId;
    }
    public Long getCreatorId() {//上传人
        return creatorId;
    }
    public void setCreatedTime(String createdTime) {//上传时间
        this.createdTime= createdTime;
    }
    public String getCreatedTime() {//上传时间
        return createdTime;
    }
}
