package com.demo2.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Result<T> {
    private boolean status;
    private Integer code;
    private String message;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long total;
    private T data;

    /**
     * 普通查询数据
     */
    public Result(T data) {
      //  System.out.println(data);
        if (data==null){
            this.data = (T) "";
        }else {
            this.data = data;
        }
        this.code = 200;
        this.message = "操作成功";
        this.status = true;
    }
    /**
     * 普通查询数据(分页)
     */
    public Result(T data, Long total) {
        this.data = data;
        this.code = 200;
        this.message = "操作成功";
        this.status = true;
        this.total = total;
    }
    /**
     * 对于一些 增删改 返回数据定义
     */
    public Result(boolean bool) {
        if (bool){
            this.message = "操作成功";
        }else {
            this.message = "操作失败";
        }
        this.code = 200;
        this.status = bool;
    }
    /**
     * 对于一些 增删改 返回数据定义
     */
    public Result(boolean bool, String message) {
        this.status = bool;
        this.code = 200;
        this.message = message;
    }
    
}