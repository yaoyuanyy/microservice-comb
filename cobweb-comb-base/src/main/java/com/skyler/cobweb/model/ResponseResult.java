package com.skyler.cobweb.model;

import java.io.Serializable;

/**
 * Description:
 * <p></p>
 * <pre></pre>
 * NB.
 * Created by skyler on 2018/1/15 at 下午3:40
 */
public class ResponseResult<T> implements Serializable {

    private int code;
    private String msg;
    private T data;

    private static final ResponseResult ok = new ResponseResult(0, "success", null);
    private static final ResponseResult fail = new ResponseResult(1, "fail", "system exception");

    public static ResponseResult ok() {
        return ok;
    }

    public static <T> ResponseResult ok(T data) {
        ok.setData(data);
        return ok;
    }

    public static ResponseResult fail() {
        return fail;
    }

    public static ResponseResult fail(String msg) {
        return fail(1, msg);
    }

    public static ResponseResult fail(int code, String msg) {
        fail.setCode(code);
        fail.setMsg(msg);
        return fail;
    }

    public ResponseResult() {
    }

    public ResponseResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
