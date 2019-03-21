package com.demo.service.impl;

import com.demo.persistence.dao.ScourseMapper;
import com.demo.persistence.entity.Scourse;
import com.demo.persistence.entity.ScourseExample;
import com.demo.service.DataScourceService;
import com.demo.utils.DynamicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class DataScourceServiceImpl implements DataScourceService {
    @Autowired
    ScourseMapper scourseMapper;

    @Override
    public List<Scourse> getAllScourse() {
        return scourseMapper.selectByExample(new ScourseExample());
    }

    @Override
    public int addScourse(String databaseName, String driver, String url, String userName, String password) {
        Scourse scourse = new Scourse();
        scourse.setUsername(userName);
        scourse.setDbkey(databaseName);
        scourse.setUrl(url);
        scourse.setDriver(driver);
        scourse.setPassword(password);
        return scourseMapper.insertSelective(scourse);
    }

    @Override
    public int deleteScourse(String dbKey) {
        ScourseExample scourseExample = new ScourseExample();
        scourseExample.createCriteria().andDbkeyEqualTo(dbKey);
        return scourseMapper.deleteByExample(scourseExample);
    }

    @Override
    public void createDatabases(String databaseName, String driver, String url, String userName, String password) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, password);
            stmt = conn.createStatement();
            String sql = "CREATE DATABASE " + databaseName + " default character set utf8 collate utf8_general_ci";
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    @Override
    public void deleteDatabases(String databaseName, String driver, String url, String userName, String password) {
        Connection conn = null;
        Statement stmt = null;
        try {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, userName, password);
            stmt = conn.createStatement();
            String sql = "DROP  DATABASE " + databaseName;
            stmt.executeUpdate(sql);
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    @Override
    public void createScourseDataBase(String databaseName, String driver, String url, String userName, String password) {
        createDatabases(databaseName, driver, url, userName, password);
        addScourse(databaseName, driver, url+"/"+databaseName, userName, password);
        DynamicDataSource.addDataSource(databaseName, url+"/"+databaseName, userName, password, driver);
    }

    @Override
    public void deleteScourseDataBase(String dbkey) {
        deleteScourse(dbkey);
        Scourse dataSourse = DynamicDataSource.getDataSourse(dbkey);
        DynamicDataSource.deleteDataSource(dbkey);
        deleteDatabases(dataSourse.getDbkey(),dataSourse.getDriver(),dataSourse.getUrl(),dataSourse.getUsername(),dataSourse.getPassword());
    }
}
