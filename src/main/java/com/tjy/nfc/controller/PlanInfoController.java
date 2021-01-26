package com.tjy.nfc.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.tjy.nfc.entity.PlanDetail;
import com.tjy.nfc.entity.PlanInfo;
import com.tjy.nfc.entity.ShebeiInfo;
import com.tjy.nfc.entity.TaskInfo;
import com.tjy.nfc.service.PlanInfoService;
import com.tjy.nfc.service.ShebeiInfoService;
import com.tjy.nfc.utils.ResponseCode;
import com.tjy.nfc.utils.ResultModel;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 设备信息表(PlanInfo)表控制层
 *
 * @author makejava
 * @since 2020-11-16 23:36:25
 */
@Slf4j
@RestController
@RequestMapping("planInfo")
public class PlanInfoController {
    /**
     * 服务对象
     */
    @Resource
    private PlanInfoService planInfoService;
    @Resource
    private ShebeiInfoService shebeiInfoService;


    @GetMapping("/getPlanList/{companyId}/{status}")
    public ResultModel getListByCompanyId(@PathVariable String companyId,@PathVariable String status) {
        try {
            if(StringUtils.isBlank(companyId) && StringUtils.isBlank(status)){
                return ResultModel.failure(ResponseCode.ERROR_PARAMS_VALIDATOR,"参数验证错误");
            }
            List<PlanInfo> list = planInfoService.getListByCompanyId(companyId,Integer.parseInt(status));
            return ResultModel.success(list);
        } catch (Exception e) {
            return ResultModel.failure(ResponseCode.ERROR_999,"系统异常");
        }
    }

    @GetMapping("/getPlanInfo/{companyId}/{planNo}")
    public ResultModel getPlanInfo(@PathVariable String companyId,@PathVariable String planNo) {
        try {
            if(StringUtils.isBlank(companyId) && StringUtils.isBlank(planNo)){
                return ResultModel.failure(ResponseCode.ERROR_PARAMS_VALIDATOR,"参数验证错误");
            }
            PlanDetail planDetail = planInfoService.getPlanInfo(companyId,planNo);
            return ResultModel.success(planDetail);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResultModel.failure(ResponseCode.ERROR_999,"系统异常");
        }
    }

    @GetMapping("/stop/{companyId}/{planNo}")
    public ResultModel stop(@PathVariable String companyId,@PathVariable String planNo) {
        try {
            int row = planInfoService.stop(companyId,planNo);
            return ResultModel.success(row);
        } catch (Exception e) {
            e.printStackTrace();
            log.debug(e.getMessage());
            return ResultModel.failure(ResponseCode.ERROR_999,"系统异常");
        }
    }

    @RequestMapping(method = RequestMethod.POST,value = "/add",produces = "application/json;charset=UTF-8")
    public ResultModel add(@RequestBody JSONObject planRequest) {
        log.debug("request planInfo add:"+ planRequest);
        try {
            String nowDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
            PlanInfo planInfo = new PlanInfo();
            planInfo.setCompanyId(planRequest.getString("companyId"));
            String planNo = planRequest.getString("planNo");
            String planName = planRequest.getString("planName");

            if(planInfoService.getInfoByNameOrNo(planRequest.getString("companyId"),planNo,planName) != null  ){
                return ResultModel.failure(ResponseCode.DARA_INSERT_EXIST,"添加的数据(计划名称或编号)已经存在，请勿重复添加");
            };

            planInfo.setPlanNo(planNo);
            planInfo.setPlanName(planName);
            planInfo.setBeginTime(planRequest.getString("beginTime"));
            planInfo.setEndTime(planRequest.getString("endTime"));
            planInfo.setLastUpdateTime(nowDateTime);
            if(StringUtils.isBlank(planRequest.getString("imageUrl"))){
                planInfo.setImageUrl("images/plan/test.png"); //默认图片地址
            }
            String[] dutypeople = planRequest.getString("dutypeople")
                    .replace("(","/").replace(")","/").split("/");
            planInfo.setDutyPeopleName(dutypeople[0]);
            planInfo.setDutyPeoplePhone(dutypeople[2]);
            planInfo.setRunnable(0);
            planInfo.setStatus(3);
            String shebeiList = "";
            JSONArray taskOrders = planRequest.getJSONArray("taskOrders");
            List<TaskInfo>  taskInfoList = new ArrayList<>();
            for (Object object : taskOrders) {
                JSONObject taskOrder = (JSONObject) object;
                JSONObject shebeiOrder = taskOrder.getJSONObject("shebeiOrder");
                shebeiList += shebeiOrder.getString("shebeiNo") + ";";
                TaskInfo taskInfo = new TaskInfo();
                taskInfo.setShebeiNo(shebeiOrder.getString("shebeiNo"));
                taskInfo.setShebeiName(shebeiOrder.getString("shebeiName"));
                taskInfo.setCompanyId(planRequest.getString("companyId"));
                taskInfo.setAddress(shebeiOrder.getString("address"));
                taskInfo.setDetails(taskOrder.getString("details"));
                taskInfo.setPlanNo(planNo);
                taskInfo.setPlanName(planName);
                taskInfo.setBeginTime(planRequest.getString("beginTime"));
                taskInfo.setEndTime(planRequest.getString("endTime"));
                taskInfo.setStatus(3);
                taskInfo.setTimes(taskOrder.getString("times"));
                taskInfo.setLastUpdateTime(nowDateTime);
                taskInfo.setDutyPeopleNo(dutypeople[1]);
                JSONArray peopleJSONArray = taskOrder.getJSONArray("peopleOrders");

                ShebeiInfo shebeiInfo = shebeiInfoService.getShebeiInfo(planRequest.getString("companyId"),shebeiOrder.getString("shebeiNo"));
                shebeiInfo.setStatus(2);
                shebeiInfoService.update(shebeiInfo);

                if(peopleJSONArray.size() > 1){
                    List<String> peopleList = new ArrayList<>();
                    String peopleListStr = "";
                    for (Object peopleObject : peopleJSONArray) {
                        JSONObject peopleOrder = (JSONObject) peopleObject;
                        peopleList.add(peopleOrder.getString("checkPeopleNo"));
                        peopleListStr += peopleOrder.getString("checkPeopleNo") + ";";
                    }
                    peopleListStr = peopleListStr.substring(0,peopleListStr.length()-1);
                    for (String peopleNo:peopleList) {
                        TaskInfo tt = new TaskInfo();
                        BeanUtils.copyProperties(taskInfo,tt);
                        tt.setLevel(1);
                        tt.setCheckPeople(peopleNo);
                        tt.setCheckPeopleList(peopleListStr);
                        taskInfoList.add(tt);
                    }

                    taskInfo.setLevel(0);
                    taskInfo.setCheckPeople("");
                    taskInfo.setCheckPeopleList(peopleListStr);
                    taskInfoList.add(taskInfo);

                }else if(peopleJSONArray.size() == 1){
                    JSONObject peopleOrder =(JSONObject)peopleJSONArray.get(0);
                    taskInfo.setLevel(0);
                    taskInfo.setCheckPeople(peopleOrder.getString("checkPeopleNo"));
                    taskInfo.setCheckPeopleList(peopleOrder.getString("checkPeopleNo"));
                    taskInfoList.add(taskInfo);
                }

                //将设备变成已经使用过了

            }
            planInfo.setShebeiList(shebeiList.substring(0,shebeiList.length()-1));

            try{
                planInfoService.insertInfo(planInfo,taskInfoList);

            }catch (Exception e) {
                return ResultModel.failure(ResponseCode.DARA_INSERT_ERROR,"添加数据异常");
            }

        } catch (Exception e) {
            return ResultModel.failure(ResponseCode.ERROR_999,"系统异常");
        }
        return ResultModel.success();
    }


    @RequestMapping(method = RequestMethod.POST,value = "/change",produces = "application/json;charset=UTF-8")
    public ResultModel change(@RequestBody JSONObject planRequest) {
        log.debug("request planInfo change:"+ planRequest);
        try {
            PlanInfo planInfo = new PlanInfo();
            planInfo.setCompanyId(planRequest.getString("companyId"));
            planInfo.setPlanNo(planRequest.getString("planNo"));
            planInfo.setBeginTime(planRequest.getString("beginTime"));
            planInfo.setEndTime(planRequest.getString("endTime"));
            planInfo.setDutyPeopleName(planRequest.getString("dutyPeopleName"));
            planInfo.setDutyPeoplePhone(planRequest.getString("dutyPeoplePhone"));

            try{
                return planInfoService.change(planInfo);
            }catch (Exception e) {
                return ResultModel.failure(ResponseCode.DARA_UPDATE_ERROR,"添加数据异常");
            }

        } catch (Exception e) {
            return ResultModel.failure(ResponseCode.ERROR_999,"系统异常");
        }
    }


}