package com.tjy.nfc.service.impl;

import com.tjy.nfc.entity.ShebeiInfo;
import com.tjy.nfc.dao.ShebeiInfoDao;
import com.tjy.nfc.service.ShebeiInfoService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 设备信息表(ShebeiInfo)表服务实现类
 *
 * @author makejava
 * @since 2020-11-29 17:08:34
 */
@Service("shebeiInfoService")
public class ShebeiInfoServiceImpl implements ShebeiInfoService {
    @Resource
    private ShebeiInfoDao shebeiInfoDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public ShebeiInfo queryById(Integer id) {
        return this.shebeiInfoDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<ShebeiInfo> queryAllByLimit(int offset, int limit) {
        return this.shebeiInfoDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param shebeiInfo 实例对象
     * @return 实例对象
     */
    @Override
    public ShebeiInfo insert(ShebeiInfo shebeiInfo) {
        this.shebeiInfoDao.insert(shebeiInfo);
        return shebeiInfo;
    }

    /**
     * 修改数据
     *
     * @param shebeiInfo 实例对象
     * @return 实例对象
     */
    @Override
    public ShebeiInfo update(ShebeiInfo shebeiInfo) {
        this.shebeiInfoDao.update(shebeiInfo);
        return this.queryById(shebeiInfo.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.shebeiInfoDao.deleteById(id) > 0;
    }

    @Override
    public ShebeiInfo getShebeiInfo(String companyId, String shebeiNo) {
        ShebeiInfo query = new ShebeiInfo();
        query.setCompanyId(companyId);
        query.setShebeiNo(shebeiNo);

        List<ShebeiInfo> shebeiInfoList = shebeiInfoDao.queryAll(query);
        if(shebeiInfoList != null && shebeiInfoList.size() > 0){
            return shebeiInfoList.get(0);
        }
        return null;
    }

    @Override
    public List<ShebeiInfo> getList(String companyId) {
        ShebeiInfo query = new ShebeiInfo();
        query.setCompanyId(companyId);
        return shebeiInfoDao.queryAll(query);
    }

    @Override
    public List<ShebeiInfo> search(String companyId, String nameOrNo) {
        return shebeiInfoDao.queryByNameOrNo(companyId,nameOrNo);
    }
}