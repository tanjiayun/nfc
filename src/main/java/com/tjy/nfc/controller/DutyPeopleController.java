package com.tjy.nfc.controller;

import com.tjy.nfc.entity.DutyPeople;
import com.tjy.nfc.service.DutyPeopleService;
import com.tjy.nfc.utils.ResponseCode;
import com.tjy.nfc.utils.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * 负责人员信息表(DutyPeople)表控制层
 *
 * @author makejava
 * @since 2020-12-09 21:51:55
 */
@Slf4j
@RestController
@RequestMapping("dutyPeople")
public class DutyPeopleController {
    /**
     * 服务对象
     */
    @Resource
    private DutyPeopleService dutyPeopleService;


    @GetMapping("/getInfo/{companyId}")
    public ResultModel getPlanInfo(@PathVariable String companyId) {
        try {
            List<DutyPeople> list = dutyPeopleService.getPlanInfo(companyId);
            return ResultModel.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResultModel.failure(ResponseCode.ERROR_999,"系统异常");
        }
    }


    @GetMapping("/getInfoByNo/{companyId}/{dutyPeopleNo}")
    public ResultModel getInfoByNo(@PathVariable String companyId,@PathVariable String dutyPeopleNo) {
        try {
            DutyPeople dutyPeople = dutyPeopleService.getInfoByNo(companyId,dutyPeopleNo);
            return ResultModel.success(dutyPeople);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResultModel.failure(ResponseCode.ERROR_999,"系统异常");
        }
    }
}