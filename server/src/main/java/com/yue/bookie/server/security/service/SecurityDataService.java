package com.yue.bookie.server.security.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yue.bookie.server.security.bean.SPermission;
import com.yue.bookie.server.security.bean.SRole;
import com.yue.bookie.server.security.bean.SUser;
import com.yue.bookie.server.security.dao.SPermissionDao;
import com.yue.bookie.server.security.dao.SRoleDao;
import com.yue.bookie.server.security.dao.SUserDao;

/**
 * Security 数据服务
 * 
 * @author Veiking
 */
@Service
public class SecurityDataService {
	@Autowired
	private SUserDao sUserDao;
	@Autowired
	private SRoleDao sRoleDao;
	@Autowired
	private SPermissionDao sPermissionDao;

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
