package com.io.test;

import com.base.util.FileTools;
import org.junit.Test;

import java.io.File;
import java.net.URL;
import java.util.List;

public class FileTests {

    @Test
    public void classPathTest(){
        URL resource = getClass().getResource("/");
        System.out.println(new File(resource.getPath()).getParentFile().getParent());
        System.out.println(resource.getPath());
        System.out.println(resource.getFile());
    }

    @Test
    public void listTest(){
        String folder = "/workspace/workspace-snake/snake-web/target/snake-web/output/system/src/";
        List<String> list = FileTools.fileListTree(folder);
        for(String file : list) {
            System.out.println(file);
        }
    }

}
