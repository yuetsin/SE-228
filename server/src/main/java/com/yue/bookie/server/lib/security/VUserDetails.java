package com.yue.bookie.server.lib.security;

import java.util.Collection;
import java.util.List;

import com.yue.bookie.server.lib.bean.SPermission;
import com.yue.bookie.server.lib.bean.SRole;
import com.yue.bookie.server.lib.bean.SUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;

import com.google.gson.Gson;

public class VUserDetails extends SUser implements UserDetails {

    private static final long serialVersionUID = 1L;
    Gson gson = new Gson();
    Logger logger = LoggerFactory.getLogger(this.getClass());
    //用户角色列表
    private List<SRole> sRoleList;
    //用户资源权限列表
    private List<SPermission> sPermissionList;

    public VUserDetails(SUser sUser, List<SRole> sRoleList, List<SPermission> sPermissionList) {
        super(sUser);
        this.sRoleList = sRoleList;
        this.sPermissionList = sPermissionList;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        StringBuilder authoritiesBuilder = new StringBuilder();
        List<SRole> tempRoleList = this.getsRoleList();
        if (null != tempRoleList) {
            for (SRole role : tempRoleList) {
                authoritiesBuilder.append(",").append(role.role);
            }
        }
        List<SPermission> tempPermissionList = this.getsPermissionList();
        if (null != tempPermissionList) {
            for (SPermission permission : tempPermissionList) {
                authoritiesBuilder.append(",").append(permission.permission);
            }
        }
        String authoritiesStr = "";
        if(authoritiesBuilder.length()>0) {
            authoritiesStr = authoritiesBuilder.deleteCharAt(0).toString();
        }
        logger.info("VUserDetails getAuthorities [authoritiesStr={} ", authoritiesStr);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesStr);
    }
    @Override
    public String getPassword() {
        return super.password;
    }

    @Override
    public String getUsername() {
        return super.name;
    }

    /**
     * 判断账号是否已经过期，默认没有过期
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * 判断账号是否被锁定，默认没有锁定
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * 判断信用凭证是否过期，默认没有过期
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * 判断账号是否可用，默认可用
     */
    @Override
    public boolean isEnabled() {
        return true;
    }


    public List<SRole> getsRoleList() {
        return sRoleList;
    }

    public void setsRoleList(List<SRole> sRoleList) {
        this.sRoleList = sRoleList;
    }

    public List<SPermission> getsPermissionList() {
        return sPermissionList;
    }

    public void setsPermissionList(List<SPermission> sPermissionList) {
        this.sPermissionList = sPermissionList;
    }
}