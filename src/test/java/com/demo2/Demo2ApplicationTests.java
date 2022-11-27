package com.demo2;

import com.demo2.service.EmpInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

@SpringBootTest
class Demo2ApplicationTests {

    @Resource
    private EmpInfoService empInfoService;

    @Test
    void contextLoads() {
        Map<String, Object> empByEmpNo = empInfoService.getEmpByEmpNo("7369");
        empByEmpNo.forEach((k, v) -> {
            System.out.println(k + "====" + v);
        });

    }

}
