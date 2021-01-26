package com.tjy.nfc.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tjy.nfc.dao.CheckLogDao;
import com.tjy.nfc.dao.ShebeiInfoDao;
import com.tjy.nfc.dao.TaskInfoDao;
import com.tjy.nfc.entity.*;
import com.tjy.nfc.dao.PlanInfoDao;
import com.tjy.nfc.service.PlanInfoService;
import com.tjy.nfc.utils.AnalyzeUtil;
import com.tjy.nfc.utils.ResponseCode;
import com.tjy.nfc.utils.ResultModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 设备信息表(PlanInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-11-16 23:36:25
 */
@Service("planInfoService")
public class PlanInfoServiceImpl implements PlanInfoService {
    @Resource
    private PlanInfoDao planInfoDao;
    @Resource
    private ShebeiInfoDao shebeiInfoDao;
    @Resource
    private CheckLogDao checkLogDao;
    @Resource
    private TaskInfoDao taskInfoDao;
    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public PlanInfo queryById(Integer id) {
        return this.planInfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<PlanInfo> queryAllByLimit(int offset, int limit) {
        return this.planInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param planInfo 实例对象
     * @return 实例对象
     */
    @Override
    public PlanInfo insert(PlanInfo planInfo) {
        this.planInfoDao.insert(planInfo);
        return planInfo;
    }

    /**
     * 修改数据
     *
     * @param planInfo 实例对象
     * @return 实例对象
     */
    @Override
    public PlanInfo update(PlanInfo planInfo) {
        this.planInfoDao.update(planInfo);
        return this.queryById(planInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.planInfoDao.deleteById(id) > 0;
    }

    @Override
    public List<PlanInfo> getListByCompanyId(String companyId,Integer status) {
        PlanInfo planInfo = new PlanInfo();
        planInfo.setCompanyId(companyId);
//        planInfo.setRunnable(0);
        if(status == 1 || status == 2 || status == 3){
            planInfo.setStatus(status);
        }
        return planInfoDao.queryAll(planInfo);
    }

    @Override
    public PlanDetail getPlanInfo(String companyId, String planNo) throws ParseException {
        //计划详情
        PlanInfo planInfo = new PlanInfo();
        planInfo.setCompanyId(companyId);
        planInfo.setPlanNo(planNo);
        List<PlanInfo> listPlanInfo= planInfoDao.queryAll(planInfo);
        if(listPlanInfo == null || listPlanInfo.size()  == 0){
            return null;
        }
        PlanInfo planInfoResult = listPlanInfo.get(0);
        String beginTime = planInfoResult.getBeginTime();
        String endTime = planInfoResult.getEndTime();
        Long days = AnalyzeUtil.getDays(beginTime,endTime);
        //设备列表详情
        String shebeiList = planInfoResult.getShebeiList();
        String[] shebeis = shebeiList.split(";");
        List<TaskInfo> taskInfoList = new ArrayList<>();
        Map<String,Long> unFinishTimesMap = new HashMap<>();
        Map<String,Long> finishTimesMap = new HashMap<>();

        for (String shebeiNo: shebeis ) {
            TaskInfo taskInfoQuery = new TaskInfo();
            taskInfoQuery.setShebeiNo(shebeiNo);
            taskInfoQuery.setPlanNo(planNo);
            taskInfoQuery.setCompanyId(companyId);
            taskInfoQuery.setLevel(0);
            List<TaskInfo> listTaskInfo = taskInfoDao.queryAll(taskInfoQuery);
            TaskInfo taskInfoResult = new TaskInfo();
            if(listTaskInfo != null && listTaskInfo.size() > 0){
                taskInfoResult = listTaskInfo.get(0);
                taskInfoList.add(taskInfoResult);
            }

            //查询对应的设备巡检次数(只统计'已完成'和'执行中')
            if(planInfoResult.getStatus() == 1 || planInfoResult.getStatus() == 2){
                Long num = checkLogDao.queryMaxNum(companyId,planNo,shebeiNo);
                if(num == null) num = 0L;
                finishTimesMap.put(taskInfoResult.getShebeiName(),num);
                Long times = AnalyzeUtil.getTotalTimes(days,taskInfoResult.getTimes());
                unFinishTimesMap.put(taskInfoResult.getShebeiName(),times-num);
            }
        }
        //封装
        PlanDetail planDetail = new PlanDetail();
        planDetail.setPlanInfo(planInfoResult);
        planDetail.setTaskInfoList(taskInfoList);
        planDetail.setFinishTimesMap(finishTimesMap);
        planDetail.setUnFinishTimesMap(unFinishTimesMap);
        return planDetail;
    }

    @Override
    public int stop(String companyId, String planNo) {
        return planInfoDao.stopPlan(companyId,planNo);
    }


    @Override
    public void insertInfo(PlanInfo planInfo, List<TaskInfo> taskInfoList) {
        int i = planInfoDao.insert(planInfo);
        if(i== 1){
            for (TaskInfo taskInfo:taskInfoList) {
                taskInfoDao.insert(taskInfo);
            }
        }
    }

    @Override
    public PlanInfo getInfoByNameOrNo(String companyId, String planNo, String planName) {
        PlanInfo query = new PlanInfo();
        query.setCompanyId(companyId);
        query.setPlanNo(planNo);
        List<PlanInfo> list = planInfoDao.queryAll(query);
        if(list != null && list.size() > 0 ){
            return list.get(0);
        }else{
            query.setPlanNo(null);
            query.setPlanName(planName);
            list = planInfoDao.queryAll(query);
            if(list != null && list.size() > 0 ){
                return list.get(0);
            }
        }
        return null;
    }

    @Override
    public ResultModel change(PlanInfo planInfo) {
        PlanInfo query = new PlanInfo();
        query.setCompanyId(planInfo.getCompanyId());
        query.setPlanNo(planInfo.getPlanNo());
        List<PlanInfo> list = planInfoDao.queryAll(query);
        if(list == null && list.size() == 0 ){
            return ResultModel.failure(ResponseCode.DARA_UPDATE_NOT_EXIST,"数据不存在");
        }
        PlanInfo planInfoNew = list.get(0);
        if(StringUtils.equals(planInfoNew.getDutyPeoplePhone(),planInfo.getDutyPeoplePhone())
                &&StringUtils.equals(planInfoNew.getDutyPeopleName(),planInfo.getDutyPeopleName())
                &&StringUtils.equals(planInfoNew.getBeginTime().substring(0,10),planInfo.getBeginTime().substring(0,10))
                &&StringUtils.equals(planInfoNew.getEndTime().substring(0,10),planInfo.getEndTime().substring(0,10))) {
            return ResultModel.failure(ResponseCode.DARA_UPDATE_NOT_CHANGE,"数据无变化更新");
        }
        planInfoNew.setDutyPeoplePhone(planInfo.getDutyPeoplePhone());
        planInfoNew.setDutyPeopleName(planInfo.getDutyPeopleName());
        planInfoNew.setBeginTime(planInfo.getBeginTime());
        planInfoNew.setEndTime(planInfo.getEndTime());
        String nowDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        planInfoNew.setLastUpdateTime(nowDateTime);
        try{
            planInfoDao.update(planInfoNew);
            if(StringUtils.equals(planInfoNew.getBeginTime().substring(0,10),planInfo.getBeginTime().substring(0,10))
               &&StringUtils.equals(planInfoNew.getEndTime().substring(0,10),planInfo.getEndTime().substring(0,10))) {
                taskInfoDao.changeTime(planInfo.getCompanyId(),planInfo.getPlanNo(),planInfoNew.getBeginTime(),planInfoNew.getEndTime());
            }

        }catch (Exception e){
            return ResultModel.failure(ResponseCode.DARA_UPDATE_ERROR,"修改数据异常");
        }
        return ResultModel.success();
    }


}