package com.tjy.nfc.entity;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 设备信息表(PlanDetail)实体类
 *
 * @author makejava
 * @since 2020-11-18 17:39:56
 */
@Data
public class PlanDetail implements Serializable {
    private PlanInfo planInfo;
    private List<TaskInfo> taskInfoList;
    private Map<String,Long> unFinishTimesMap;
    private  Map<String,Long> finishTimesMap;
}