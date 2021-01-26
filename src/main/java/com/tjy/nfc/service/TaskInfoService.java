package com.tjy.nfc.service;

import com.tjy.nfc.entity.TaskInfo;
import java.util.List;

/**
 * 任务信息表(TaskInfo)表服务接口
 *
 * @author makejava
 * @since 2020-12-14 14:13:05
 */
public interface TaskInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    TaskInfo queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<TaskInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param taskInfo 实例对象
     * @return 实例对象
     */
    TaskInfo insert(TaskInfo taskInfo);

    /**
     * 修改数据
     *
     * @param taskInfo 实例对象
     * @return 实例对象
     */
    TaskInfo update(TaskInfo taskInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<TaskInfo> getList(String companyId, String checkPeople, int status);

}