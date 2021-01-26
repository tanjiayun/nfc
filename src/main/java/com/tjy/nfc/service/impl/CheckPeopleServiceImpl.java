package com.tjy.nfc.service.impl;

import com.tjy.nfc.entity.CheckPeople;
import com.tjy.nfc.dao.CheckPeopleDao;
import com.tjy.nfc.service.CheckPeopleService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 巡检人员表(CheckPeople)表服务实现类
 *
 * @author makejava
 * @since 2020-12-02 15:12:55
 */
@Service("checkPeopleService")
public class CheckPeopleServiceImpl implements CheckPeopleService {
    @Resource
    private CheckPeopleDao checkPeopleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CheckPeople queryById(Integer id) {
        return this.checkPeopleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<CheckPeople> queryAllByLimit(int offset, int limit) {
        return this.checkPeopleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param checkPeople 实例对象
     * @return 实例对象
     */
    @Override
    public CheckPeople insert(CheckPeople checkPeople) {
        this.checkPeopleDao.insert(checkPeople);
        return checkPeople;
    }

    /**
     * 修改数据
     *
     * @param checkPeople 实例对象
     * @return 实例对象
     */
    @Override
    public CheckPeople update(CheckPeople checkPeople) {
        this.checkPeopleDao.update(checkPeople);
        return this.queryById(checkPeople.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.checkPeopleDao.deleteById(id) > 0;
    }

    @Override
    public CheckPeople getInfo(String companyId, String checkPeopleNo) {
        CheckPeople checkPeopleQuery = new CheckPeople();
        checkPeopleQuery.setCheckPeopleNo(checkPeopleNo);
        checkPeopleQuery.setCompanyId(companyId);
        List<CheckPeople> list = checkPeopleDao.queryAll(checkPeopleQuery);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public List<CheckPeople> getAllPeopleList(String companyId, String nameOrPhone) {
        return checkPeopleDao.queryByNameOrPhone(companyId,nameOrPhone);
    }
}