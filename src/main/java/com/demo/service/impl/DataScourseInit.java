package com.demo.service.impl;

import com.demo.persistence.dao.ScourseMapper;
import com.demo.persistence.entity.Scourse;
import com.demo.persistence.entity.ScourseExample;
import com.demo.utils.DynamicDataSource;
import com.demo.utils.PropertiesUtil;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Properties;

public class DataScourseInit implements InitializingBean {
    @Autowired
    ScourseMapper scourseMapper;

    @Override
    public void afterPropertiesSet() throws Exception {
        Scourse defaultSource = new Scourse();
        Properties properties = PropertiesUtil.getProperties("/db.properties");
        defaultSource.setDriver(properties.getProperty("jdbc.driver"));
        defaultSource.setUrl(properties.getProperty("jdbc.url"));
        defaultSource.setUsername(properties.getProperty("jdbc.username"));
        defaultSource.setPassword(properties.getProperty("jdbc.password"));
        DynamicDataSource.addDataSource("default",properties.getProperty("jdbc.url"),properties.getProperty("jdbc.username"),properties.getProperty("jdbc.password"),properties.getProperty("jdbc.driver"));

        List<Scourse> scourses = scourseMapper.selectByExample(new ScourseExample());
        scourses.stream().forEach(s->{
            DynamicDataSource.addDataSource(s.getDbkey(),s.getUrl(),s.getUsername(),s.getPassword(),s.getDriver());
        });

    }
}
