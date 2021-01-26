package com.tjy.nfc.service;

import com.tjy.nfc.entity.CheckLog;
import java.util.List;

/**
 * 巡检日志表(CheckLog)表服务接口
 *
 * @author makejava
 * @since 2020-11-30 16:48:37
 */
public interface CheckLogService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    CheckLog queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<CheckLog> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param checkLog 实例对象
     * @return 实例对象
     */
    CheckLog insert(CheckLog checkLog);

    /**
     * 修改数据
     *
     * @param checkLog 实例对象
     * @return 实例对象
     */
    CheckLog update(CheckLog checkLog);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    List<CheckLog> search(String companyId, String planNo, String shebeiNo, String dataTime);
}