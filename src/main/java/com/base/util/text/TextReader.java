package com.base.util.text;

import java.io.*;

/**
 * Created by wenxy on 2017/3/11.
 */
public class TextReader {

    private static String encode = "UTF-8";

    private File file;

    private AfterReadLineListener afterReadLineListener;

    public TextReader(TextReaderBuilder builder,File file) {
        this(builder,file,encode);
    }

    public TextReader(TextReaderBuilder builder,File file,String encode) {
        this.file = file;
        this.afterReadLineListener= builder.getAfterReadLineListener();
        this.encode = encode;
    }

    public void find(String value) {

    }

    public void read() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file),encode));
        String line = null;
        while ((line = reader.readLine()) != null) {
            afterReadLineListener.readLine(line);
        }
        reader.close();
    }
}
