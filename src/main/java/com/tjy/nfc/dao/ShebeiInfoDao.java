package com.tjy.nfc.dao;

import com.tjy.nfc.entity.ShebeiInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 设备信息表(ShebeiInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-16 17:06:53
 */
@Mapper
public interface ShebeiInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ShebeiInfo queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ShebeiInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param shebeiInfo 实例对象
     * @return 对象列表
     */
    List<ShebeiInfo> queryAll(ShebeiInfo shebeiInfo);

    /**
     * 新增数据
     *
     * @param shebeiInfo 实例对象
     * @return 影响行数
     */
    int insert(ShebeiInfo shebeiInfo);

    /**
     * 修改数据
     *
     * @param shebeiInfo 实例对象
     * @return 影响行数
     */
    int update(ShebeiInfo shebeiInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    List<ShebeiInfo> queryByNameOrNo(@Param("companyId")String companyId,@Param("nameOrNo") String nameOrNo);

}