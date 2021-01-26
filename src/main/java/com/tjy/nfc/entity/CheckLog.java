package com.tjy.nfc.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 巡检日志表(CheckLog)实体类
 *
 * @author makejava
 * @since 2020-12-18 14:03:47
 */
@Data
public class CheckLog implements Serializable {
    private static final long serialVersionUID = -39932027638657971L;
    //主键
    private Integer id;
    //计划编号
    private String planNo;
    //设备编号
    private String shebeiNo;
    //公司id
    private String companyId;
    //检查结果 1：正常;2：异常;
    private Integer result;
    //打卡日期
    private String dateStr;
    //巡检人姓名
    private String checkPeopleName;
    //巡检人手机号
    private String checkPeoplePhone;
    //创建时间
    private Date createTime;
    //最后更新时间
    private Date lastUpdateTime;
    //第几次巡检
    private Long checkNum;
    //备注信息1
    private String reserve1;
    //备注信息2
    private String reserve2;
    //备注信息3
    private String reserve3;
    //备注信息4
    private String reserve4;


}