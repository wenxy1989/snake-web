package com.utils.test;

import org.junit.Test;

public class StringTests {

    @Test
    public void replaceAllTest(){
        String result = "//pom.xml".replaceAll("//","/");
        System.out.println(result);
    }

}
