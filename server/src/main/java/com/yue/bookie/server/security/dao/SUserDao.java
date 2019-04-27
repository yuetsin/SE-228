package com.yue.bookie.server.security.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.yue.bookie.server.security.bean.SUser;

/**
 * 用户信息查询
 * @author Veiking
 */
@Mapper
public interface SUserDao {
	/**
	 * 根据用户名获取用户
	 *
	 * @param name
	 * @return
	 */
	@Select(value = " SELECT su.* FROM s_user su WHERE su.name = #{name} ")
	public SUser findSUserByName(String name);

}