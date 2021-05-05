package com.hust.labregister.controller.bean;

import java.util.LinkedHashMap;

public class ResponseBean {

    private String errorCode;
    private String msg;
    private LinkedHashMap<String, Object> data;

    public ResponseBean(){
        this.errorCode="1";
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public LinkedHashMap<String, Object> getData() {
        return data;
    }

    public void setData(LinkedHashMap<String, Object> data) {
        this.data = data;
    }
}