package com.tjy.nfc.controller;

import com.tjy.nfc.utils.ResponseCode;
import com.tjy.nfc.utils.ResultModel;
import com.tjy.nfc.entity.UserEntity;
import com.tjy.nfc.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


@RestController
//@Controller
/*@PreAuthorize("hasRole('ADMI')")*/
public class UserController {

	private Logger log = LoggerFactory.getLogger(UserController.class);

	@Resource(name = "userServiceImpl")
	private UserService userService;

	@GetMapping("/user/{loginName}")
	public UserEntity userGet(@PathVariable String loginName) {
		UserEntity userEntity = userService.getUserEntityByLoginName(loginName);
		log.debug("The method is ending");
		return userEntity;
	}


    @RequestMapping(value = "/login",method = RequestMethod.POST)
	public ResultModel login(@RequestBody Map<String, Object> userEntity) {
        log.debug("login method is beiginning");
        try {
            String loginName =(String) userEntity.get("loginName");
            String pwd = (String)userEntity.get("password");
            if(StringUtils.isBlank(loginName) && StringUtils.isBlank(pwd)){
                return ResultModel.failure(ResponseCode.ERROR_PARAMS_VALIDATOR,"参数验证错误");
            }
            UserEntity userEntityResult = userService.getUserEntityByLoginName(loginName);
            if(userEntityResult != null){
                if(StringUtils.equals(pwd, userEntityResult.getPassword())){
                    return ResultModel.success(userEntityResult.getPhone()+"_"
                            +userEntityResult.getCompanyId()+"_"+userEntityResult.getType()
                            +"_"+userEntityResult.getPeopleNo()
                    );
                }else{
                    return ResultModel.failure(ResponseCode.ERROR_LOGIN_PWD,"登陆密码错误");
                }
            }else{
                return ResultModel.failure(ResponseCode.ERROR_LOGIN_USERNAME,"登陆名错误");
            }
        } catch (Exception e) {
            return ResultModel.failure(ResponseCode.ERROR_999,"系统异常");
        }
	}

}
