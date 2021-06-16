package com.sun.mythyleaf.dao;

import com.baomidou.dynamic.datasource.annotation.DS;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

@DS("db1")
@Mapper
public interface HomeDao {

    @Select("select * from dict_config_scheduler")
    List<Map<String,Object>> getScheduler();
}
