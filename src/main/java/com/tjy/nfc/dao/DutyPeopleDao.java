package com.tjy.nfc.dao;

import com.tjy.nfc.entity.DutyPeople;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 负责人员信息表(DutyPeople)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-09 21:51:55
 */
@Mapper
public interface DutyPeopleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    DutyPeople queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<DutyPeople> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param dutyPeople 实例对象
     * @return 对象列表
     */
    List<DutyPeople> queryAll(DutyPeople dutyPeople);

    /**
     * 新增数据
     *
     * @param dutyPeople 实例对象
     * @return 影响行数
     */
    int insert(DutyPeople dutyPeople);

    /**
     * 修改数据
     *
     * @param dutyPeople 实例对象
     * @return 影响行数
     */
    int update(DutyPeople dutyPeople);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

}