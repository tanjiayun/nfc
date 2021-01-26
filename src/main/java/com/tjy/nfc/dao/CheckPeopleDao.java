package com.tjy.nfc.dao;

import com.tjy.nfc.entity.CheckPeople;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 巡检人员表(CheckPeople)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-07 14:04:21
 */
@Mapper
public interface CheckPeopleDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CheckPeople queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CheckPeople> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param checkPeople 实例对象
     * @return 对象列表
     */
    List<CheckPeople> queryAll(CheckPeople checkPeople);

    /**
     * 新增数据
     *
     * @param checkPeople 实例对象
     * @return 影响行数
     */
    int insert(CheckPeople checkPeople);

    /**
     * 修改数据
     *
     * @param checkPeople 实例对象
     * @return 影响行数
     */
    int update(CheckPeople checkPeople);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<CheckPeople> queryByNameOrPhone(@Param("companyId") String companyId, @Param("nameOrPhone") String nameOrPhone);
}