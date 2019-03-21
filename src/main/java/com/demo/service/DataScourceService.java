package com.demo.service;

import com.demo.persistence.entity.Scourse;

public interface DataScourceService {
    /**
     * 查询所有的数据源
     * @return
     */
    Scourse getAllScourse();
    /**
     * 添加数据源
     */
    int addScourse(Scourse scourse);

    /**
     * 删除数据源
     */
    int deleteScourse(int id);


}
