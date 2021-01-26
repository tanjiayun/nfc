package com.tjy.nfc.utils;

public final class ResponseCode {
   public static final String SUCCESS = "N00000";//成功
   public static final String ERROR_999 = "999";// 系统异常
   public static final String ERROR_PARAMS_VALIDATOR="200"; //参数验证错误
   public static final String ERROR_SERVICE_VALIDATOR="300"; //业务验证错误
   public static final String ERROR_DATA_VALIDATOR="400";  //系统数据错误
   public static final String ERROR_LOGIN_USERNAME="E00001";  //登陆名错误
   public static final String ERROR_LOGIN_PWD="E00002";  //登陆密码错误
   public static final String DARA_INSERT_ERROR="E00003";  //添加数据异常
   public static final String DARA_INSERT_EXIST="E00004"; //添加的数据已经存在，请勿重复添加
   public static final String DARA_UPDATE_NOT_EXIST="E00005"; //数据不存在
   public static final String DARA_UPDATE_ERROR="E00006"; //修改数据异常
   public static final String DARA_UPDATE_NOT_CHANGE="E00007"; //数据无变化更新
}
