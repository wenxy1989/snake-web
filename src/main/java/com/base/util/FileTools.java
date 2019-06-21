package com.base.util;

import com.snake.freemarker.TypeProperties;
import org.apache.commons.lang.StringUtils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileTools {

    public static List<String> fileListTree(String folder) {
        if (null != folder && StringUtils.isNotBlank(folder)) {
            File folderFile = new File(folder);
            if (folderFile.exists() && folderFile.isDirectory()) {
                File[] files = folderFile.listFiles();
                if(null != files && files.length > 0) {
                    List<String> fileList = new ArrayList<String>();
                    for (File file : files) {
                        if (file.isDirectory()) {
                            if ("true".equals(TypeProperties.getValue("template", "folder", "ignore", "folder", file.getName()))) {
                                continue;//todo
                            }
                            List<String> list = fileListTree(file.getAbsolutePath());
                            if (null != list) {
                                fileList.addAll(list);
                            }
                        } else {
                            if ("true".equals(TypeProperties.getValue("template", "folder", "ignore", "file", file.getName()))) {
                                continue;//todo
                            }
                            fileList.add(file.getAbsolutePath());
                        }
                    }
                    return fileList;
                }
            }
        }
        return null;
    }
}
