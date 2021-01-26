package com.tjy.nfc.service.impl;

import com.tjy.nfc.entity.TaskInfo;
import com.tjy.nfc.dao.TaskInfoDao;
import com.tjy.nfc.service.TaskInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 任务信息表(TaskInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-12-14 14:13:05
 */
@Service("taskInfoService")
public class TaskInfoServiceImpl implements TaskInfoService {
    @Resource
    private TaskInfoDao taskInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public TaskInfo queryById(Integer id) {
        return this.taskInfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TaskInfo> queryAllByLimit(int offset, int limit) {
        return this.taskInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param taskInfo 实例对象
     * @return 实例对象
     */
    @Override
    public TaskInfo insert(TaskInfo taskInfo) {
        this.taskInfoDao.insert(taskInfo);
        return taskInfo;
    }

    /**
     * 修改数据
     *
     * @param taskInfo 实例对象
     * @return 实例对象
     */
    @Override
    public TaskInfo update(TaskInfo taskInfo) {
        this.taskInfoDao.update(taskInfo);
        return this.queryById(taskInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.taskInfoDao.deleteById(id) > 0;
    }

    @Override
    public List<TaskInfo> getList(String companyId, String checkPeople, int status) {

        TaskInfo query = new TaskInfo();
        query.setCompanyId(companyId);
        if(status == 1 || status == 2 || status == 3){
            query.setStatus(status);
        }
        query.setCheckPeople(checkPeople);
        return  taskInfoDao.queryAll(query);
    }

}