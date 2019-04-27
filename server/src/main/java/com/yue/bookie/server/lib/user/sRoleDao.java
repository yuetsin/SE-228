package com.yue.bookie.server.lib.user;

import java.util.List;

import com.yue.bookie.server.lib.bean.SRole;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
/**
 * 角色信息查询
 * @author Veiking
 */
@Mapper
public interface sRoleDao {

    @Select(value=" SELECT sr.* FROM s_role sr " +
            " LEFT JOIN s_user_role sur ON sr.id = sur.fk_role_id " +
            " LEFT JOIN s_user su ON sur.fk_user_id = su.id " +
            " WHERE su.id = #{sUserId} ")
    public List<SRole> findSRoleListBySUserId(int sUserId);

    @Select(value=" SELECT sr.* FROM s_role sr " +
            " LEFT JOIN s_role_permission srp ON sr.id = srp.fk_role_id " +
            " LEFT JOIN s_permission sp ON srp.fk_permission_id = sp.id " +
            " WHERE sp.url = #{sPermissionUrl} ")
    public List<SRole> findSRoleListBySPermissionUrl(String sPermissionUrl);
}