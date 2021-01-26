package com.tjy.nfc.service;

import com.tjy.nfc.entity.PlanDetail;
import com.tjy.nfc.entity.PlanInfo;
import com.tjy.nfc.entity.TaskInfo;
import com.tjy.nfc.utils.ResultModel;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

/**
 * 设备信息表(PlanInfo)表服务接口
 *
 * @author makejava
 * @since 2020-11-16 23:36:25
 */
public interface PlanInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PlanInfo queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PlanInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param planInfo 实例对象
     * @return 实例对象
     */
    PlanInfo insert(PlanInfo planInfo);

    /**
     * 修改数据
     *
     * @param planInfo 实例对象
     * @return 实例对象
     */
    PlanInfo update(PlanInfo planInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<PlanInfo> getListByCompanyId(String companyId,Integer status);

    PlanDetail getPlanInfo(String companyId, String planNo) throws  ParseException;

    int stop(String companyId, String planNo);

    void insertInfo(PlanInfo planInfo, List<TaskInfo> taskInfoList);

    PlanInfo getInfoByNameOrNo(String companyId, String planNo, String planName);

    ResultModel change(PlanInfo planInfo);
}