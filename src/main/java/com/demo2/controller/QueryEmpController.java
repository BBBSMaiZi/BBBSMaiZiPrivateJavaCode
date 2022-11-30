package com.demo2.controller;

import com.demo2.bean.Emp;
import com.demo2.bean.Result;
import com.demo2.service.EmpInfoService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: QueryEmpController
 * @author: 赵健钢
 * @explain:
 * @date: 2022-11-27
 **/
@Slf4j

@RestController("/QueryEmpController")
public class QueryEmpController extends BasicController {

    @Resource
    private EmpInfoService empInfoService;

    @GetMapping("/getEmpByEmpNo")
    public Result<Object> getEmpByEmpNo(String empNo) {

        Map<String, Object> map = empInfoService.getEmpByEmpNo(empNo);

        Result<Object> objectResult = new Result<Object>(map);
        log.info("查询中------------------------");

        return objectResult;
    }

    @GetMapping("/getAllEmp")
    public Result<List<Map<String, Object>>> getAllEmp() {
        List<Map<String, Object>> allEmp = empInfoService.getAllEmp();

        return new Result<List<Map<String, Object>>>(allEmp);
    }

    @PostMapping("/addEmp")
    public Result addEmp(@RequestBody Emp emp) {


        System.out.println("emp = " + emp);

        return new Result(true, "新增员工成功");
    }

}
