package com.google.collections;

import org.junit.Test;

import java.util.Objects;

/**
 * Created by HP on 2017/3/10.
 */
public class ObjectsTest {

    @Test
    public void equalsTest(){
        System.out.println(Objects.equals("a","a"));
        System.out.println(Objects.equals("a",null));
        System.out.println(Objects.equals(null,"a"));
        System.out.println(Objects.equals(null,null));
    }


    @Test
    public void hasCodeTest(){

    }

}
