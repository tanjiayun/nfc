package com.tjy.nfc.entity;

import lombok.Data;

import java.util.Date;
import java.io.Serializable;

/**
 * 设备信息表(ShebeiInfo)实体类
 *
 * @author makejava
 * @since 2021-01-05 13:41:49
 */
@Data
public class ShebeiInfo implements Serializable {
    private static final long serialVersionUID = -39869248714902837L;
    //主键
    private Integer id;
    //设备名
    private String shebeiName;
    //设备编号
    private String shebeiNo;
    //nfc内置编号
    private String nfcNo;
    //状态
    private Integer status;
    //详细地址
    private String address;
    //最后更新时间
    private String lastUpdateTime;
    //公司编号
    private String companyId;
    //备注信息1
    private String reserve1;
    //备注信息2
    private String reserve2;
    //备注信息3
    private String reserve3;
    //备注信息4
    private String reserve4;
}