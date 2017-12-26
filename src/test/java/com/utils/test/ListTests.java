package com.utils.test;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wenxy on 2017/3/11.
 */
public class ListTests {

    @Test
    public void subTest() {
        List<String> list = Lists.newArrayList("one", "tow", "three", "four", "five");

        int size = 2;
        int count = 0;
        if (null != list && (count = list.size()) > 0) {
            int page = count / size;
            if (count % size > 0) {
                page++;
            }
            for (int i = 0; i < page; i++) {
                int start = i * size;
                int end = start + size;
                if (end > count) {
                    end = count;
                }
                List<String> each = list.subList(start, end);
                System.out.println(each);
            }
        }
    }

    @Test
    public void addTest() {
//        int count = Integer.MAX_VALUE;
        int count = 10000000;
        long startMils = System.currentTimeMillis();
        List<IntegerObject> list = new ArrayList<IntegerObject>(count);//more quick
//        List<IntegerObject> list = new ArrayList<IntegerObject>();
        long createMils = System.currentTimeMillis();
        for (int i = 0; i < count; i++) {
//            list.add(String.format("number:%d",i));
            list.add(new IntegerObject(i));
        }
        long endMils = System.currentTimeMillis();
        System.out.println("create list use mils is : " + (createMils - startMils));
        System.out.println("add to list use mils is : " + (endMils - createMils));
    }

    public static void main(String args[]) {
        ListTests tests = new ListTests();
        tests.addTest();
    }

}

class IntegerObject {
    private int number;
    private String name;

    public IntegerObject(int number) {
        this.number = number;
        this.name = String.format("number:%d",number);
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
