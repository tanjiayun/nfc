package com.tjy.nfc.service.impl;

import com.tjy.nfc.entity.DutyPeople;
import com.tjy.nfc.dao.DutyPeopleDao;
import com.tjy.nfc.service.DutyPeopleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 负责人员信息表(DutyPeople)表服务实现类
 *
 * @author makejava
 * @since 2020-12-09 21:51:55
 */
@Service("dutyPeopleService")
public class DutyPeopleServiceImpl implements DutyPeopleService {
    @Resource
    private DutyPeopleDao dutyPeopleDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public DutyPeople queryById(Integer id) {
        return this.dutyPeopleDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<DutyPeople> queryAllByLimit(int offset, int limit) {
        return this.dutyPeopleDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param dutyPeople 实例对象
     * @return 实例对象
     */
    @Override
    public DutyPeople insert(DutyPeople dutyPeople) {
        this.dutyPeopleDao.insert(dutyPeople);
        return dutyPeople;
    }

    /**
     * 修改数据
     *
     * @param dutyPeople 实例对象
     * @return 实例对象
     */
    @Override
    public DutyPeople update(DutyPeople dutyPeople) {
        this.dutyPeopleDao.update(dutyPeople);
        return this.queryById(dutyPeople.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.dutyPeopleDao.deleteById(id) > 0;
    }

    @Override
    public List<DutyPeople> getPlanInfo(String companyId) {
        DutyPeople dutyPeopleSearch = new DutyPeople();
        dutyPeopleSearch.setCompanyId(companyId);
        return dutyPeopleDao.queryAll(dutyPeopleSearch);
    }

    @Override
    public DutyPeople getInfoByNo(String companyId, String dutyPeopleNo) {
        DutyPeople query = new DutyPeople();
        query.setCompanyId(companyId);
        query.setDutyPeopleNo(dutyPeopleNo);
        List<DutyPeople> list = dutyPeopleDao.queryAll(query);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}