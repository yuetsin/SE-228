package com.yue.bookie.server.lib.security;

import java.util.List;
import com.yue.bookie.server.lib.bean.SPermission;
import com.yue.bookie.server.lib.bean.SRole;
import com.yue.bookie.server.lib.bean.SUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
/**
 * 提供用户信息封装服务
 * @author Veiking
 */
@Service
public class VUserDetailsService implements UserDetailsService {

    @Autowired
    SecurityDataService securityDataService;
    /**
     * 根据用户输入的用户名返回数据源中用户信息的封装，返回一个UserDetails
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SUser sUser = securityDataService.findSUserByName(username);
        //用户角色列表
        List<SRole> sRoleList = securityDataService.findSRoleListBySUserId(sUser.id);
        //用户资源权限列表
        List<SPermission> sPermissionList = securityDataService.findSPermissionListBySUserId(sUser.id);
        return new VUserDetails(sUser, sRoleList, sPermissionList);
    }

}