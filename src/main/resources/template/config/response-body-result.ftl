package com.web.${app.code}.config;

import java.io.Serializable;

/**
* Created by wenxy on ${now}
*/
public class ResponseBodyResult implements Serializable {

    public enum Error {
        json_parse("json_parse_error"),
        parameter("parameter_error");

        private String value;
        Error(String message){
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }

    public static final ResponseBodyResult SUCCESSFUL = success(null);
    public static final ResponseBodyResult FAILURE = error(null);

    private int code;
    private String message;
    private Object data;

    public static final ResponseBodyResult success(Object data) {
        return new ResponseBodyResult(data);
    }

    public static final ResponseBodyResult error(String message) {
        return new ResponseBodyResult(1, message);
    }

    public static final ResponseBodyResult error(int code, String message) {
        return new ResponseBodyResult(code, message);
    }

    public ResponseBodyResult(int code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseBodyResult(int code, String message) {
        this(code, message, null);
    }


    public ResponseBodyResult(Object data) {
        this(0, "success", data);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
