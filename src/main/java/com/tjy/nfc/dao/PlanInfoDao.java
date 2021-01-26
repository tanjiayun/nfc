package com.tjy.nfc.dao;

import com.tjy.nfc.entity.PlanInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 设备信息表(PlanInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-11-18 17:39:58
 */
@Mapper
public interface PlanInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    PlanInfo queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<PlanInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param planInfo 实例对象
     * @return 对象列表
     */
    List<PlanInfo> queryAll(PlanInfo planInfo);

    /**
     * 新增数据
     *
     * @param planInfo 实例对象
     * @return 影响行数
     */
    int insert(PlanInfo planInfo);

    /**
     * 修改数据
     *
     * @param planInfo 实例对象
     * @return 影响行数
     */
    int update(PlanInfo planInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    int stopPlan(@Param("companyId") String companyId,@Param("planNo")  String planNo);
}