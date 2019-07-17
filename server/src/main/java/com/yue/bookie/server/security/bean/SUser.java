package com.yue.bookie.server.security.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * 用户名密码信息
 * @author Veiking
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SUser {
	private int id;
	private String name;
	private String password;
	
	public SUser(SUser sUser) {
		this.id = sUser.id;
		this.name = sUser.name;
		this.password = sUser.password;
	}
}
