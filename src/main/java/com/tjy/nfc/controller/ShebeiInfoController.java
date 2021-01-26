package com.tjy.nfc.controller;

import com.alibaba.fastjson.JSONObject;
import com.tjy.nfc.entity.PlanDetail;
import com.tjy.nfc.entity.PlanInfo;
import com.tjy.nfc.entity.ShebeiInfo;
import com.tjy.nfc.service.ShebeiInfoService;
import com.tjy.nfc.utils.ResponseCode;
import com.tjy.nfc.utils.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 设备信息表(ShebeiInfo)表控制层
 *
 * @author makejava
 * @since 2020-11-29 17:08:34
 */

@Slf4j
@RestController
@RequestMapping("shebeiInfo")
public class ShebeiInfoController {
    /**
     * 服务对象
     */
    @Resource
    private ShebeiInfoService shebeiInfoService;

    @GetMapping("/getShebeiInfo/{companyId}/{shebeiNo}")
    public ResultModel getPlanInfo(@PathVariable String companyId, @PathVariable String shebeiNo) {
        try {
            if(StringUtils.isBlank(companyId) && StringUtils.isBlank(shebeiNo)){
                return ResultModel.failure(ResponseCode.ERROR_PARAMS_VALIDATOR,"参数验证错误");
            }
            ShebeiInfo ShebeiInfo = shebeiInfoService.getShebeiInfo(companyId,shebeiNo);
            return ResultModel.success(ShebeiInfo);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResultModel.failure(ResponseCode.ERROR_999,"系统异常");
        }
    }

    @GetMapping("/getList/{companyId}")
    public ResultModel getList(@PathVariable String companyId) {
        try {
            List<ShebeiInfo> list = shebeiInfoService.getList(companyId);
            return ResultModel.success(list);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResultModel.failure(ResponseCode.ERROR_999,"系统异常");
        }
    }

    @GetMapping("/search/{companyId}/{nameOrNo}")
    public ResultModel search(@PathVariable String companyId,@PathVariable String nameOrNo) {
        try {
            if(StringUtils.equals("all",nameOrNo)){
                nameOrNo = "";
            }

            List<ShebeiInfo> shebeiInfoList = shebeiInfoService.search(companyId,nameOrNo);
            return ResultModel.success(shebeiInfoList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultModel.failure(ResponseCode.ERROR_999,"系统异常");
        }
    }


    @RequestMapping(method = RequestMethod.POST,value = "/add",produces = "application/json;charset=UTF-8")
    public ResultModel insert(@RequestBody JSONObject shebeiInfoRequest) {
        log.debug("request ShebeiInfo change:"+ shebeiInfoRequest);
        try {
            ShebeiInfo shebeiInfo = new ShebeiInfo();
            shebeiInfo.setCompanyId(shebeiInfoRequest.getString("companyId"));
            shebeiInfo.setShebeiName(shebeiInfoRequest.getString("taskName"));
            shebeiInfo.setShebeiNo(shebeiInfoRequest.getString("shebeiNo"));
            shebeiInfo.setAddress(shebeiInfoRequest.getString("address"));
            shebeiInfo.setStatus(1);
            shebeiInfo.setLastUpdateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            if(StringUtils.isNotBlank(shebeiInfoRequest.getString("nfcNo"))){
                shebeiInfo.setNfcNo(shebeiInfoRequest.getString("nfcNo"));
            }else{
                shebeiInfo.setNfcNo("testNo");
            }


            try{
                return ResultModel.success(shebeiInfoService.insert(shebeiInfo));
            }catch (Exception e) {
                return ResultModel.failure(ResponseCode.DARA_UPDATE_ERROR,"添加数据异常");
            }

        } catch (Exception e) {
            return ResultModel.failure(ResponseCode.ERROR_999,"系统异常");
        }
    }


}