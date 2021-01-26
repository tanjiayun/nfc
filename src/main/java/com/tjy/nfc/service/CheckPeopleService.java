package com.tjy.nfc.service;

import com.tjy.nfc.entity.CheckPeople;
import java.util.List;

/**
 * 巡检人员表(CheckPeople)表服务接口
 *
 * @author makejava
 * @since 2020-12-02 15:12:55
 */
public interface CheckPeopleService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CheckPeople queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CheckPeople> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param checkPeople 实例对象
     * @return 实例对象
     */
    CheckPeople insert(CheckPeople checkPeople);

    /**
     * 修改数据
     *
     * @param checkPeople 实例对象
     * @return 实例对象
     */
    CheckPeople update(CheckPeople checkPeople);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    CheckPeople getInfo(String companyId, String checkPeopleNo);

    List<CheckPeople> getAllPeopleList( String companyId, String nameOrPhone);
}