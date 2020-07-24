package com.wzx.wzx_test1.utils;

import java.io.Serializable;

public class CommonResult<T> implements Serializable {

    private static final long serialVersionUID = 8081585371266115332L;

    private boolean success = true;
    private Integer code;
    private String message;
    private T data;
    private Integer total;

    public static <T> CommonResult<T> successReturn(T data) {
        return successReturn(data, null, null);
    }

    public static <T> CommonResult<T> successReturn(T data, Integer total) {
        return successReturn(data, total, null);
    }

    public static <T> CommonResult<T> successReturn(T data, String message) {
        return successReturn(data, null, message);
    }

    public static <T> CommonResult<T> successReturn(T data, Integer total, String message) {
        CommonResult<T> result = new CommonResult<T>();
        result.setSuccess(true);
        result.setCode(0);
        result.setMessage(message);
        result.setData(data);
        result.setTotal(total);
        return result;
    }

    public static <T> CommonResult<T> errorReturn(String message) {
        return errorReturn(null, message);
    }

    public static <T> CommonResult<T> errorReturn(Integer code, String message) {
        CommonResult<T> result = new CommonResult<T>();
        result.setSuccess(false);
        result.setCode(code);
        result.setMessage(message);
        result.setData(null);
        result.setTotal(null);
        return result;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}