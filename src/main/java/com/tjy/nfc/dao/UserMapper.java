package com.tjy.nfc.dao;
import com.tjy.nfc.entity.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;


@Mapper
public interface UserMapper {
	
	/**
	 * 通过登录名拿到用户信息
	 * @return
	 */
	public UserEntity getUserEntityByLoginName(@Param("loginName") String loginName);


	
}
