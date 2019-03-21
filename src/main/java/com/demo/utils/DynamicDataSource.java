package com.demo.utils;

import com.demo.persistence.entity.Scourse;
import com.jolbox.bonecp.BoneCPDataSource;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

/**
 * @author admin
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    //    存放数据源   备注 { 机构标识，数据源}
    private static Map<String, DataSource> dataSourceMap = new HashMap<String, DataSource>();
    //    存放实体类标识  {实体类，机构标识}
    private final static Map<Scourse, String> dseMap = new HashMap<>();

    public static DataSource dataSource(String key) {
        return dataSourceMap.get(key);
    }

    //spring注入需要set方法，不是必要的，可以注入也可以不注入
    public static void setDataSourceMap(Map<String, DataSource> dataSourceMap) {
        DynamicDataSource.dataSourceMap = dataSourceMap;

    }

    //获取数据源信息集合
    public static Map<Scourse, String> getDsemap() {
        return dseMap;
    }

    //检查是否包含指定id的数据源
    public static boolean checkDbKey(String dbKey) {
        if (dataSourceMap.get(dbKey) != null) return true;
        return false;
    }

    /**
     *     抽象方法，必须重写，用来判断使用哪个数据源
     * @return
     */
    @Override
    protected String determineCurrentLookupKey() {
        return DataSourceUtils.getDbKey();
    }

    /**
     * 对数据源的初始化方法，由于这里已经将数据源集合放在本类中，如果不重写将会由于父类参数为null而抛出异常。
     */
    @Override
    public void afterPropertiesSet() {

    }

    /**
     * 确定使用哪一个数据源
     * 如果为null读取配置文件因此配置文件必须有参数
     */
    @Override
    protected DataSource determineTargetDataSource() {
        String dsKey = determineCurrentLookupKey();
        DataSource dds = dataSourceMap.get(dsKey);
        if (dds == null) {
            return dataSourceMap.get("default");
        } else {
            return dds;
        }
    }

    /**
     * 添加数据源
     * 为了防止多线程添加同一个数据源，这里采用同步,同时会判断是否已存在
     *
     * @param dbkey
     * @param username
     * @param password
     * @return String 新建数据源对应的key，如果已经存在，则返回之前的key。
     */
    public static synchronized String addDataSource(String dbkey, String jdbcurl,
                                                    String username, String password, String driver) {
        Scourse d1 = new Scourse(jdbcurl, username);
        String value = dseMap.get(d1);
        if (dseMap.get(d1) != null) {
            return value;//已存在则返回该数据源的id
        }

        DataSource ds = createDataSource(jdbcurl, username, password, driver);
        dataSourceMap.put(dbkey, ds);//存储数据源集合
        dseMap.put(d1, dbkey);//保存已经存储了哪些数据源

        return dbkey;
    }

    /**
     * 更新数据源
     *
     * @param dbkey
     * @param jdbcurl
     * @param username
     * @param password
     * @param driver
     * @return
     */
    public static String updataDataSource(String dbkey, String jdbcurl,
                                          String username, String password, String driver) {
        Scourse d1 = new Scourse(jdbcurl, username);
        DataSource ds = createDataSource(jdbcurl, username, password, driver);
        //存储数据源集合
        dataSourceMap.put(dbkey, ds);
        //保存已经存储了哪些数据源
        dseMap.put(d1, dbkey);
        return dbkey;
    }

    /**
     * 创建一个数据源
     *
     * @param username
     * @param password
     * @return
     */
    private static DataSource createDataSource(String jdbcurl, String username, String password, String driver) {
        BoneCPDataSource dds = new BoneCPDataSource();
        dds.setDriverClass(driver);
        dds.setJdbcUrl(jdbcurl);
        dds.setUsername(username);
        dds.setPassword(password);

        Properties properties = PropertiesUtil.getProperties("db.properties");
        dds.setIdleConnectionTestPeriodInSeconds(Long.parseLong(properties.getProperty("jdbc.idleConnectionPeriodInSeconds", "240")));
        dds.setIdleMaxAgeInSeconds(Long.parseLong(properties.getProperty("jdbc.idleMaxAgeInSeconds", "6000")));
        dds.setMaxConnectionsPerPartition(Integer.parseInt(properties.getProperty("jdbc.maxConnectionsPerPartition", "20")));
        dds.setMinConnectionsPerPartition(Integer.parseInt(properties.getProperty("jdbc.minConnectionsPerPartition", "10")));
        dds.setPartitionCount(Integer.parseInt(properties.getProperty("jdbc.partitionCount", "2")));
        dds.setAcquireIncrement(Integer.parseInt(properties.getProperty("jdbc.acquireIncrement", "2")));
        dds.setStatementsCacheSize(Integer.parseInt(properties.getProperty("jdbc.statementsCacheSize", "20")));

        return dds;
    }

    /**
     * 删除数据源
     * 删除三个封装map里面的key
     *
     * @param dbkey
     */
    public static void deleteDataSource(String dbkey) {
        Iterator<String> iter = dataSourceMap.keySet().iterator();
        while (iter.hasNext()) {
            String key = iter.next();
            if (dbkey.equals(key)) {
                iter.remove();
            }
        }
        Iterator<String> valueIter = dseMap.values().iterator();
        while (valueIter.hasNext()) {
            String value = iter.next();
            if (dbkey.equals(value)) {
                iter.remove();
            }
        }
    }
}