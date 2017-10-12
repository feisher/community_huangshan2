package com.yusong.community.api;

/**
 * Created by quaner on 17/1/3.
 *
 * 统一返回数据模板
 */

public class HttpResult<T>{
    public int code;
    public String message;
    public T data;


}
