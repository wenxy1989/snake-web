package com.snake.book;

import com.google.common.cache.Cache;
import com.snake.chinese.utils.MainLogicInstants;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * Created by HP on 2017/1/10.
 */
public class ChineseTextUtilsTests {

    //    private static String paragraph = "“哦，原来是……”陈剑雄恍然大悟道，不过他随即笑了起来，暗想道：“我现在这一副乡下农民样子，和当年还真是有点差距，呵呵呵…….”";
    private static String paragraph = "。。。。。。。。。。。";

    private static String fileName = "D:\\workspace\\snake-web\\book\\novel-text\\强者天下.txt";

    @Test
    public void readChineseMapTest() throws IOException {
        long startMils = System.currentTimeMillis();
        Map<String, Integer> result = MainLogicInstants.getInstant().getChineseCountMap(new File(fileName));
        long readMils = System.currentTimeMillis();
        Iterator<Map.Entry<String,Integer>> iterator = result.entrySet().iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().getKey());
        }
        long endMils1 = System.currentTimeMillis();
        for (String each : result.keySet()) {
            System.out.println(each);
        }
        long endMils2 = System.currentTimeMillis();
        System.out.println("read book use time is : " + (readMils - startMils));
        System.out.println("print iterator time is : " + (endMils1 - readMils));
        System.out.println("print for use time is : " + (endMils2 - endMils1));
    }

    @Test
    public void readChineseCacheTest() throws IOException {
        long startMils = System.currentTimeMillis();
        Cache<String, Integer> result = MainLogicInstants.getInstant().getChineseCountCache(new File(fileName));
        long readMils = System.currentTimeMillis();
        for (String each : result.asMap().keySet()) {
            /*if(each.startsWith("更新时间")) {
                continue;
            }*/
            System.out.println(each);
        }
        long endMils = System.currentTimeMillis();
        System.out.println("read book use time is : " + (readMils - startMils));
        System.out.println("print cache use time is : " + (endMils - readMils));
    }

    @Test
    public void getStatementListTest() {
        List<String> list = new ArrayList<String>();
        ChineseTextUtils.getParagraphList(paragraph, list);
        for (String statement : list) {
            System.out.println(statement);
        }
    }

}
