package com.yue.bookie.server.lib.user;

import com.yue.bookie.server.lib.bean.SUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface sUserDao {

    @Select(value = " SELECT bookie.* FROM s_user su WHERE su.name = #{name} ")
    public SUser findSUserByName(String name);
}