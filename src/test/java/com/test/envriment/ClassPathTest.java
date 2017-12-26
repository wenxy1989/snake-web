package com.test.envriment;

import com.base.utils.FreeMarkerUtils;
import freemarker.template.Template;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * Created by wenxy on 2016/11/19.
 */
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath*:**applicationContext.xml")
public class ClassPathTest {

    @BeforeClass
    public static void setUp(){

    }

    @Test
    public void getClassPathTest(){
        String path = getClass().getClassLoader().getResource("").getPath();
        System.out.println(path);
    }

    @Test
    public void getConfig(){
        try {
            Template template = FreeMarkerUtils.getConfiguration("/D:/workspace/base-game/src/main/webapp/jsp/config/template").getTemplate("java/model.java.ftl");
            System.out.println(template.getName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
