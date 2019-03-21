package com.demo.controller;

import com.demo.persistence.dao.TestMapper;
import com.demo.persistence.entity.Scourse;
import com.demo.persistence.entity.Test;
import com.demo.persistence.entity.TestExample;
import com.demo.service.DataScourceService;
import com.demo.utils.DataSourceUtils;
import com.demo.utils.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 *
 */
@RequestMapping("/test")
@Controller
public class TestController {
    @Autowired
    TestMapper testMapper;
    @Resource
    DataScourceService dataScourceService;
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

    @ResponseBody
    @RequestMapping("/test2")
    public int test2(){
        String dbKey ="dataScourse4";
        String url = "jdbc:mysql://127.0.0.1:3306";
        String userName = "root";
        String password = "123456";
        String driver = "com.mysql.jdbc.Driver";
        System.out.println("当前数据源"+DataSourceUtils.getDbKey());
        System.out.println("当前数据源数目"+DynamicDataSource.getDataSourceList());
        dataScourceService.createScourseDataBase(dbKey,driver,url,userName,password);
        System.out.println("=======================创建成功=====================");
        System.out.println("当前数据源数目"+DynamicDataSource.getDataSourceList());
        dataScourceService.deleteScourseDataBase("dataScourse1");
        System.out.println("=======================删除成功=====================");
        System.out.println("当前数据源数目"+DynamicDataSource.getDataSourceList());
        return 0;
    }
}
