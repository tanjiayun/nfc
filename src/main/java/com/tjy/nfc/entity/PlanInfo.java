package com.tjy.nfc.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 计划信息表(PlanInfo)实体类
 *
 * @author makejava
 * @since 2020-12-24 15:36:24
 */
@Data
public class PlanInfo implements Serializable {
    private static final long serialVersionUID = -28026374975487820L;
    //主键
    private Integer id;
    //设备名
    private String planName;
    //设备编号
    private String planNo;
    //状态
    private Integer status;
    //负责人姓名
    private String dutyPeopleName;
    //负责人手机号
    private String dutyPeoplePhone;
    //开始时间
    private String beginTime;
    //结束时间
    private String endTime;
    //最后更新时间
    private String lastUpdateTime;
    //公司id
    private String companyId;
    //设备列表
    private String shebeiList;
    //图片地址
    private String imageUrl;
    //是否终止计划——0：正常，1：终止
    private Integer runnable;
    //备注信息1
    private String reserve1;
    //备注信息2
    private String reserve2;
    //备注信息3
    private String reserve3;
    //备注信息4
    private String reserve4;

}