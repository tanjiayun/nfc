package com.tjy.nfc.service;

import com.tjy.nfc.entity.ShebeiInfo;
import java.util.List;

/**
 * 设备信息表(ShebeiInfo)表服务接口
 *
 * @author makejava
 * @since 2020-11-29 17:08:34
 */
public interface ShebeiInfoService {

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    ShebeiInfo queryById(Integer id);

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    List<ShebeiInfo> queryAllByLimit(int offset, int limit);

    /**
     * 新增数据
     *
     * @param shebeiInfo 实例对象
     * @return 实例对象
     */
    ShebeiInfo insert(ShebeiInfo shebeiInfo);

    /**
     * 修改数据
     *
     * @param shebeiInfo 实例对象
     * @return 实例对象
     */
    ShebeiInfo update(ShebeiInfo shebeiInfo);

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    boolean deleteById(Integer id);

    ShebeiInfo getShebeiInfo(String companyId, String shebeiNo);

    List<ShebeiInfo> getList(String companyId);

    List<ShebeiInfo> search(String companyId, String nameOrNo);
}