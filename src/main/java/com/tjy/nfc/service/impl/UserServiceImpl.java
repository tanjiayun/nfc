package com.tjy.nfc.service.impl;

import com.tjy.nfc.dao.UserMapper;
import com.tjy.nfc.entity.UserEntity;
import com.tjy.nfc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserMapper userMapper;

	@Override
	public UserEntity getUserEntityByLoginName(String loginName) {
		UserEntity userEntity = userMapper.getUserEntityByLoginName(loginName);
		return userEntity;
	}


}
