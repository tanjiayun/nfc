package com.tjy.nfc.dao;

import com.tjy.nfc.entity.CheckLog;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 巡检日志表(CheckLog)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-18 14:03:49
 */
@Mapper
public interface CheckLogDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CheckLog queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CheckLog> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param checkLog 实例对象
     * @return 对象列表
     */
    List<CheckLog> queryAll(CheckLog checkLog);

    /**
     * 新增数据
     *
     * @param checkLog 实例对象
     * @return 影响行数
     */
    int insert(CheckLog checkLog);

    /**
     * 修改数据
     *
     * @param checkLog 实例对象
     * @return 影响行数
     */
    int update(CheckLog checkLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    Long queryMaxNum(@Param("companyId") String companyId,@Param("planNo") String planNo,@Param("shebeiNo") String shebeiNo);
}