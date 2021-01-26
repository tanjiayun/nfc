package com.tjy.nfc.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 任务信息表(TaskInfo)实体类
 *
 * @author makejava
 * @since 2020-12-16 17:24:50
 */
@Data
public class TaskInfo implements Serializable {
//    private static final long serialVersionUID = 942547962765813775L;
    //主键
    private Integer id;
    //公司编号
    private String companyId;
    //计划编号
    private String planNo;
    //计划名称
    private String planName;
    //设备编号
    private String shebeiNo;
    //设备名称
    private String shebeiName;
    //状态
    private Integer status;
    //巡检次数
    private String times;
    //等级 (0:list;1:onlyPeople)
    private Integer level;
    //巡检人员list
    private String checkPeopleList;
    //设备列表
    private String checkPeople;
    //负责人编号
    private String dutyPeopleNo;
    //详细地址
    private String address;
    //开始时间
    private String beginTime;
    //结束时间
    private String endTime;
    //最后更新时间
    private String lastUpdateTime;
    //描述
    private String details;
    //备注信息1
    private String reserve1;
    //备注信息2
    private String reserve2;
    //备注信息3
    private String reserve3;
    //备注信息4
    private String reserve4;
    
    private Integer column_21;

}