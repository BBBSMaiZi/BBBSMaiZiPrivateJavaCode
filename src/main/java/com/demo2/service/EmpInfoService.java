package com.demo2.service;

import com.demo2.mapper.EmpInfoMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @className: EmpInfoService
 * @author: 赵健钢
 * @explain:
 * @date: 2022-11-27
 **/
@Service
public class EmpInfoService {

    @Resource
    private EmpInfoMapper empInfoMapper;

    public Map<String, Object> getEmpByEmpNo(String empNo) {
        return empInfoMapper.getEmpByEmpNo(empNo);
    }

    public List<Map<String, Object>> getAllEmp() {
        return empInfoMapper.getAllEmp();
    }


}
