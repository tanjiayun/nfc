package com.tjy.nfc.service;

import com.tjy.nfc.entity.DutyPeople;
import java.util.List;

/**
 * 负责人员信息表(DutyPeople)表服务接口
 *
 * @author makejava
 * @since 2020-12-09 21:51:55
 */
public interface DutyPeopleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DutyPeople queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DutyPeople> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param dutyPeople 实例对象
     * @return 实例对象
     */
    DutyPeople insert(DutyPeople dutyPeople);

    /**
     * 修改数据
     *
     * @param dutyPeople 实例对象
     * @return 实例对象
     */
    DutyPeople update(DutyPeople dutyPeople);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<DutyPeople> getPlanInfo(String companyId);

    DutyPeople getInfoByNo(String companyId, String dutyPeopleNo);
}