package com.base.test.utils.test;

import com.base.util.MD5Util;
import org.junit.Test;

/**
 * Created by HP on 2016/12/8.
 */
public class MD5UtilTest {

    @Test
    public void encodeTest(){
        System.out.println(MD5Util.encode("123456"));
    }

}
