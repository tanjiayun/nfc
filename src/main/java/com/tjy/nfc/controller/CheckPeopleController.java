package com.tjy.nfc.controller;

import com.tjy.nfc.entity.CheckPeople;
import com.tjy.nfc.entity.PlanDetail;
import com.tjy.nfc.service.CheckPeopleService;
import com.tjy.nfc.utils.ResponseCode;
import com.tjy.nfc.utils.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 巡检人员表(CheckPeople)表控制层
 *
 * @author makejava
 * @since 2020-12-02 15:12:55
 */
@Slf4j
@RestController
@RequestMapping("checkPeople")
public class CheckPeopleController {
    /**
     * 服务对象
     */
    @Resource
    private CheckPeopleService checkPeopleService;

    @GetMapping("/getInfo/{companyId}/{checkList}")
    public ResultModel getPlanInfo(@PathVariable String companyId, @PathVariable String checkList) {
        try {
            if(StringUtils.isBlank(companyId) && StringUtils.isBlank(checkList)){
                return ResultModel.failure(ResponseCode.ERROR_PARAMS_VALIDATOR,"参数验证错误");
            }
            List<String> list = Arrays.asList(checkList.split("--"));
            if(list == null || list.size() == 0){
                return ResultModel.success();
            }
            List<CheckPeople> checkPeopleList = new ArrayList<>();
            for (String checkPeopleNo: list) {
                CheckPeople checkPeople = checkPeopleService.getInfo(companyId,checkPeopleNo);
                checkPeopleList.add(checkPeople);
            }
            return ResultModel.success(checkPeopleList);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResultModel.failure(ResponseCode.ERROR_999,"系统异常");
        }
    }


    @GetMapping("/allPeopleList/{companyId}/{nameOrPhone}")
    public ResultModel getAllPeopleList(@PathVariable String companyId,@PathVariable String nameOrPhone) {
        try {
            if(StringUtils.isBlank(companyId) && StringUtils.isBlank(nameOrPhone)){
                return ResultModel.failure(ResponseCode.ERROR_PARAMS_VALIDATOR,"参数验证错误");
            }

            if(StringUtils.equals("all",nameOrPhone)){
                nameOrPhone = "";
            }

            List<CheckPeople> checkPeopleList = checkPeopleService.getAllPeopleList(companyId,nameOrPhone);
            return ResultModel.success(checkPeopleList);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResultModel.failure(ResponseCode.ERROR_999,"系统异常");
        }
    }

}