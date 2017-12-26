package com.io.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.zip.GZIPOutputStream;

/**
 * Created by wenxy on 2017/8/26.
 */
public class StreamTests {

    public void InputStreamTest() throws IOException {
        String filePath = null;
        new GZIPOutputStream(new FileOutputStream(filePath));

        new ObjectInputStream(new FileInputStream(filePath));

    }
}
