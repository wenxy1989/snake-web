package com.io.test;

import org.junit.Test;

import java.io.File;
import java.net.URL;

public class FileTests {

    @Test
    public void classPathTest(){
        URL resource = getClass().getResource("/");
        System.out.println(new File(resource.getPath()).getParentFile().getParent());
        System.out.println(resource.getPath());
        System.out.println(resource.getFile());
    }

}
