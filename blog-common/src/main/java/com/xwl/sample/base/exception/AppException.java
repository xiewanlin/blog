package com.xwl.sample.base.exception;

public class AppException extends Exception {
    private static final long serialVersionUID = 9129725497061673234L;
    private int               code             = 0;
    private Object[]          params           = null;

    public AppException(int code) {
        super();
        this.code = code;
    }

    public AppException(String msg) {
        super(msg);
        this.code = -1;
    }

    public AppException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    public AppException(int code, Object[] params) {
        super();
        this.code = code;
        this.params = params;
    }

    public int getCode() {
        return code;
    }

    public Object[] getParams() {
        return params;
    }

    public void setParams(Object[] params) {
        this.params = params;
    }

    public String toString() {
        return "AppException{\"code\":" + code + ",\"message\":\"" + getMessage() + "\",\"params\":\"" + params + "\"}";
    }
}
