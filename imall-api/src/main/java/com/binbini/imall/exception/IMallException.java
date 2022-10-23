package com.binbini.imall.exception;

/**
 * @Author: BinBin
 * @Date: 2022/09/28/17:10
 * @Description:
 */
public class IMallException extends RuntimeException {

    private String msg;

    public IMallException(String msg){
        super(msg);
        this.msg=msg;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

}
