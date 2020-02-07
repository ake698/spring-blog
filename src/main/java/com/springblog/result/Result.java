package com.springblog.result;

import lombok.Data;

@Data
public class Result {
    //响应吗
    private int code;

    public Result(int code){
        this.code = code;
    }
}
