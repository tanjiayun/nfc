package com.tjy.nfc.entity;

import lombok.Data;

@Data
public class UserEntity {
	/**
	 * id
	 */
	private int id;
	/**
	 * 姓名
	 */
	private String loginName;
	/**
	 * 登录名
	 */
	private String name;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 手机号
	 */
	private String phone;
	/**
	 * token
	 */
	private String token;
	/**
	 * 公司编号
	 */
	private String companyId;

	private int type;

	private String peopleNo;
}
