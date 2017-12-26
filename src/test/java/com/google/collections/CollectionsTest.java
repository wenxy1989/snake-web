package com.google.collections;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang.StringUtils;
import org.junit.Test;

import java.util.Iterator;
import java.util.List;

/**
 * Created by HP on 2017/3/10.
 */
public class CollectionsTest {

    @Test
    public void ListTest() {
        long readyMis = System.currentTimeMillis();
        StringBuffer sb = new StringBuffer(1);
        for (int i = 0; i < 100000; i++) {
            sb.append(",");
            sb.append(i);
        }
        String str = sb.toString();
        long initMils = System.currentTimeMillis();
        String[] array = str.split(",");
        long split1Mils = System.currentTimeMillis();
        for (String each : array) {
            System.out.println(each);
        }
        long print1Mils = System.currentTimeMillis();
        Splitter splitter = Splitter.on(",");
        Iterable<String> iterable = splitter.split(str);
        Iterator<String> iterator = iterable.iterator();
        long split2Mils = System.currentTimeMillis();
        while (iterator.hasNext()){
            System.out.println(iterator.next());
        }
        long print2Mils = System.currentTimeMillis();
        System.out.println("build string use time is : " + (initMils - readyMis));
        System.out.println("string spilt to array use time is : " + (split1Mils - initMils));
        System.out.println("print array string use time is : " + (print1Mils - split1Mils));
        System.out.println("string spilt to array use time is : " + (split2Mils - print1Mils));
        System.out.println("print iterater string use time is : " + (print2Mils - split2Mils));
    }

    @Test
    public void StringTests() {
        String[] strings = StringUtils.split("one,two,three,four", ",");
        System.out.println(Splitter.on(',').split("one,two,three,four"));
        Splitter splitter = Splitter.on(',').omitEmptyStrings().trimResults();
        System.out.println(splitter.split(",,one,three"));
        System.out.println(splitter.split(",, jdk , jre"));
    }

}
