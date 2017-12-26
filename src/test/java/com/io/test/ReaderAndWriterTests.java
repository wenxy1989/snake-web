package com.io.test;

import java.io.*;

/**
 * Created by wenxy on 2017/8/26.
 */
public class ReaderAndWriterTests {

    public void WriterTest() throws IOException {
        String filePath = "";
        new BufferedWriter(new FileWriter(filePath));

        new BufferedReader(new FileReader(filePath));

    }

}
