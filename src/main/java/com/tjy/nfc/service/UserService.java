package com.tjy.nfc.service;

import com.tjy.nfc.entity.UserEntity;

import java.util.List;

public interface UserService {
	/**
	 * 通过登录名得到用户信息
	 * @param loginName
	 * @return
	 */
	public UserEntity getUserEntityByLoginName(String loginName);

}
