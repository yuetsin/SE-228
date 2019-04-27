package com.yue.bookie.server.lib.security;

import java.util.List;

import com.yue.bookie.server.lib.bean.SPermission;
import com.yue.bookie.server.lib.bean.SRole;
import com.yue.bookie.server.lib.bean.SUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * Security 数据服务
 *
 * @author Veiking
 */
@Service
public class SecurityDataService {
    @Autowired
    private com.yue.bookie.server.lib.user.sUserDao sUserDao;
    @Autowired
    private com.yue.bookie.server.lib.user.sRoleDao sRoleDao;
    @Autowired
    private com.yue.bookie.server.lib.user.sPermissionDao sPermissionDao;

    public SUser findSUserByName(String name) {
        return sUserDao.findSUserByName(name);
    }

    public List<SRole> findSRoleListBySUserId(int sUserId) {
        return sRoleDao.findSRoleListBySUserId(sUserId);
    }

    public List<SRole> findSRoleListBySPermissionUrl(String sPermissionUrl) {
        return sRoleDao.findSRoleListBySPermissionUrl(sPermissionUrl);
    }

    public List<SPermission> findSPermissionListBySUserId(int sUserId) {
        return sPermissionDao.findSPermissionListBySUserId(sUserId);
    }

    public List<SPermission> findSPermissionListBySPermissionUrl(String sPermissionUrl) {
        return sPermissionDao.findSPermissionListBySPermissionUrl(sPermissionUrl);
    }
}