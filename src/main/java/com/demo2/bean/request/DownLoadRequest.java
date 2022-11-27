package com.demo2.bean.request;

import lombok.Data;

import java.util.List;

/**
 * @className: DownLoadRequest
 * @author: 赵健钢
 * @explain:
 * @date: 2022-11-25
 **/
@Data
public class DownLoadRequest {

    private List<String> yearMonthList;
    private String itemCode;


}
