package com.demo.controller;

import com.demo.persistence.dao.TestMapper;
import com.demo.persistence.entity.Test;
import com.demo.persistence.entity.TestExample;
import com.demo.utils.DataSourceUtils;
import com.demo.utils.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 *
 */
@RequestMapping("/test")
@Controller
public class TestController {
    @Autowired
    TestMapper testMapper;
    @ResponseBody
    @RequestMapping("/test")
    public int test(){
        System.out.println("当前数据源"+DataSourceUtils.getDbKey());
        System.out.println("=======================切换数据源=====================");
        DataSourceUtils.setDbKey("a");
        System.out.println("当前数据源"+DataSourceUtils.getDbKey());
        System.out.println(testMapper.selectByExample(new TestExample()));
        DataSourceUtils.setDbKey("b");
        System.out.println("当前数据源"+DataSourceUtils.getDbKey());
        System.out.println(testMapper.selectByExample(new TestExample()));
        return 0;
    }
}
