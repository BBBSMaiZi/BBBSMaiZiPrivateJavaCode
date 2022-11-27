package com.demo2.bean;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @className: Outer
 * @author: 赵健钢
 * @explain:
 * @date: 2022-11-25
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Outer {


    private String operationName;

    private String operationCode;


    private List<Inner> viewData;

}
