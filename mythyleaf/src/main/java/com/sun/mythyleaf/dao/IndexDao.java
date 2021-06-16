package com.sun.mythyleaf.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;
import java.util.Map;

@DS("db2")
@Mapper
public interface IndexDao {

    @Select("select * from dict_dept")
    List<Map<String,Object>> getDept();
}
