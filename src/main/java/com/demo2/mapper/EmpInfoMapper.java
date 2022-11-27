package com.demo2.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @className: EmpInfoMapper
 * @author: 赵健钢
 * @explain:
 * @date: 2022-11-27
 **/
@Mapper
public interface EmpInfoMapper {

    Map<String, Object> getEmpByEmpNo(String empNo);

    List<Map<String, Object>> getAllEmp();
}
