package com.ms.blog.common;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.io.Serializable;


/**
 * @Author:         STEEZ
 * @CreateDate:     2022/4/25 21:29
 * 返回值对象
 */
@Data
public class ServerResponse<T> implements Serializable {
    private static final long serialVersionUID = -5368505763231357265L;

    private int code;
    private String msg;
    private T data;

    public ServerResponse() {
    }

    public ServerResponse(int code) {
        this.code = code;
    }

    private ServerResponse(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private ServerResponse(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public ServerResponse(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static<T> ServerResponse<T> Success(){
        return new ServerResponse<>(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase());
    }

    public static<T> ServerResponse<T> SuccessMsg(String msg){
        return new ServerResponse<>(HttpStatus.OK.value(), msg);
    }

    public static<T> ServerResponse<T> Success(T data){
        return new ServerResponse<>(HttpStatus.OK.value(),data);
    }

    public static<T> ServerResponse<T> Success(String msg, T data){
        return new ServerResponse<>(HttpStatus.OK.value(),msg,data);
    }

    public static<T> ServerResponse<T> Error(){
        return new ServerResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

    public static<T> ServerResponse<T> Error(String msg){
        return new ServerResponse<>(HttpStatus.INTERNAL_SERVER_ERROR.value(),msg);
    }


    public static<T> ServerResponse<T> Custom(int code, String msg, T data){
        return new ServerResponse<>(code,msg,data);
    }

    public boolean isSuccess(){
        if(this==null){
            return false;
        }
        return this.code == HttpStatus.OK.value();
    }
}
