package com.how2java.exception;

/**
 * com.how2java.exception
 *
 * @author jh
 * @date 2018/7/21 21:13
 * description:
 */

public class CustomException  extends Exception{
    //异常信息
    public String message;


    public CustomException(String message){
        super();
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
