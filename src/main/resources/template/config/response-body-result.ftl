package com.web.${application.code}.config;

import java.io.Serializable;

/**
* Created by wenxy on ${now}
*/
public class ResponseBodyResult implements Serializable {
private static final String message_success = "success";
private static final String message_error = "error";
public static final int ERROR_JSON_PARSE = 2;
public static final int SUCCESS = 0;

public static final ResponseBodyResult SUCCESSFUL = new ResponseBodyResult(SUCCESS, message_success);
private int code = 0;
private String message;
private Object data;

public static final ResponseBodyResult success(Object data) {
return new ResponseBodyResult(data);
}

public static final ResponseBodyResult error(int code, String message) {
return new ResponseBodyResult(code, message);
}

private ResponseBodyResult() {

}

public ResponseBodyResult(int code, String message, Object data) {
this.code = code;
this.message = message;
this.data = data;
}

public ResponseBodyResult(int code, String message) {
this(code, message, null);
}

public ResponseBodyResult(int code) {
this(code, message_error);
}

public ResponseBodyResult(Object data) {
this(0, message_success, data);
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
