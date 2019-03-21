package com.demo.service;

import com.demo.persistence.entity.Scourse;

import java.util.List;

public interface DataScourceService {
    /**
     * 查询所有的数据源
     * @return
     */
    List<Scourse> getAllScourse();

    /**
     *  添加数据源
     * @param databaseName
     * @param driver
     * @param url
     * @param userName
     * @param password
     * @return
     */
    int addScourse(String databaseName, String driver, String url, String userName, String password);

    /**
     * 删除数据源
     *
     * @param dbKey
     * @return
     */
    int deleteScourse(String dbKey);

    /**
     *  创建数据库
     * @param databaseName
     * @param driver
     * @param url
     * @param userName
     * @param password
     */
    void createDatabases(String databaseName, String driver, String url, String userName, String password);

    /**
     *  删除数据库
     * @param databaseName
     * @param driver
     * @param url
     * @param userName
     * @param password
     */
    void deleteDatabases(String databaseName, String driver, String url, String userName, String password);

    /**
     *  动态创建一个数据源并且建库
     * @param databaseName
     * @param driver
     * @param url
     * @param userName
     * @param password
     */
    void createScourseDataBase(String databaseName, String driver, String url, String userName, String password);

    /**
     *  删除数据源信息及数据库
     * @param dbkey
     */
    void deleteScourseDataBase(String dbkey);

}
