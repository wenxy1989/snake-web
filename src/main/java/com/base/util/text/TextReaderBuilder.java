package com.base.util.text;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

import java.io.*;

/**
 * Created by wenxy on 2017/3/11.
 */
public final class TextReaderBuilder {

    private static String encode = "UTF-8";

    private AfterReadLineListener afterReadLineListener;

    public static TextReaderBuilder newBuilder(){
        return new TextReaderBuilder();
    }

    public TextReaderBuilder afterReadLineListener(AfterReadLineListener afterReadLineListener) {
        Preconditions.checkState(this.afterReadLineListener == null);
        this.afterReadLineListener = Preconditions.checkNotNull(afterReadLineListener);
        return this;
    }

    public AfterReadLineListener getAfterReadLineListener() {
        return Objects.firstNonNull(afterReadLineListener,NullListener.INSTANCE);
    }

    static enum NullListener implements AfterReadLineListener {
        INSTANCE {
            @Override
            public void readLine(String line) {

            }
        }
    }

    private TextReader build(String fileName) throws FileNotFoundException {
        return build(new File(fileName),this.encode);
    }
    private TextReader build(String fileName,String encode) throws FileNotFoundException {
        return build(new File(fileName),encode);
    }

    public TextReader build(File file) throws FileNotFoundException {
        return build(file,encode);
    }

    public TextReader build(File file,String encode) throws FileNotFoundException {
        return new TextReader(this,file,encode);
    }

}
