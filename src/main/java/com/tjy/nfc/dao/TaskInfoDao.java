package com.tjy.nfc.dao;

import com.tjy.nfc.entity.TaskInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 * 任务信息表(TaskInfo)表数据库访问层
 *
 * @author makejava
 * @since 2020-12-16 17:24:50
 */
@Mapper
public interface TaskInfoDao {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TaskInfo queryById(Integer id);

    /**
     * 查询指定行数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TaskInfo> queryAllByLimit(@Param("offset") int offset, @Param("limit") int limit);


    /**
     * 通过实体作为筛选条件查询
     *
     * @param taskInfo 实例对象
     * @return 对象列表
     */
    List<TaskInfo> queryAll(TaskInfo taskInfo);

    /**
     * 新增数据
     *
     * @param taskInfo 实例对象
     * @return 影响行数
     */
    int insert(TaskInfo taskInfo);

    /**
     * 修改数据
     *
     * @param taskInfo 实例对象
     * @return 影响行数
     */
    int update(TaskInfo taskInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 影响行数
     */
    int deleteById(Integer id);

    void changeTime(@Param("companyId") String companyId,@Param("planNo")  String planNo,
                    @Param("beginTime") String beginTime,@Param("endTime")  String endTime);
}
