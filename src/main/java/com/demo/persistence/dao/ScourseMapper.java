package com.demo.persistence.dao;

import com.demo.persistence.entity.Scourse;
import com.demo.persistence.entity.ScourseExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ScourseMapper {
    int deleteByExample(ScourseExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Scourse record);

    int insertSelective(Scourse record);

    List<Scourse> selectByExample(ScourseExample example);

    Scourse selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Scourse record, @Param("example") ScourseExample example);

    int updateByExample(@Param("record") Scourse record, @Param("example") ScourseExample example);

    int updateByPrimaryKeySelective(Scourse record);

    int updateByPrimaryKey(Scourse record);
}