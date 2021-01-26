package com.tjy.nfc.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 负责人员信息表(DutyPeople)实体类
 *
 * @author makejava
 * @since 2020-12-09 21:51:55
 */
@Data
public class DutyPeople implements Serializable {
    private static final long serialVersionUID = -73570319776380625L;
    //主键
    private Integer id;
    //负责人编号
    private String dutyPeopleNo;
    //负责人姓名
    private String dutyPeopleName;
    //负责人手机号
    private String dutyPeoplePhone;
    //公司id
    private String companyId;
    //部门
    private String department;
    //职位
    private String duty;
    //性别：1-男；2-女
    private Integer gender;
    //创建时间
    private Date createTime;
    //最后更新时间
    private Date lastUpdateTime;
    //图片地址
    private String imageUrl;
    //备注信息1
    private String reserve1;
    //备注信息2
    private String reserve2;
    //备注信息3
    private String reserve3;
    //备注信息4
    private String reserve4;


}