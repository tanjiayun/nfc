package com.tjy.nfc.service.impl;

import com.tjy.nfc.entity.CheckLog;
import com.tjy.nfc.dao.CheckLogDao;
import com.tjy.nfc.service.CheckLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;

/**
 * 巡检日志表(CheckLog)表服务实现类
 *
 * @author makejava
 * @since 2020-11-30 16:48:37
 */
@Service("checkLogService")
public class CheckLogServiceImpl implements CheckLogService {
    @Resource
    private CheckLogDao checkLogDao;

    /**
     * 通过ID查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public CheckLog queryById(Integer id) {
        return this.checkLogDao.queryById(id);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<CheckLog> queryAllByLimit(int offset, int limit) {
        return this.checkLogDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param checkLog 实例对象
     * @return 实例对象
     */
    @Override
    public CheckLog insert(CheckLog checkLog) {
        this.checkLogDao.insert(checkLog);
        return checkLog;
    }

    /**
     * 修改数据
     *
     * @param checkLog 实例对象
     * @return 实例对象
     */
    @Override
    public CheckLog update(CheckLog checkLog) {
        this.checkLogDao.update(checkLog);
        return this.queryById(checkLog.getId());
    }

    /**
     * 通过主键删除数据
     *
     * @param id 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(Integer id) {
        return this.checkLogDao.deleteById(id) > 0;
    }

    @Override
    public List<CheckLog> search(String companyId, String planNo, String shebeiNo, String dataTime) {
        CheckLog query = new CheckLog();
        query.setCompanyId(companyId);
        query.setShebeiNo(shebeiNo);
        query.setPlanNo(planNo);
        if(!StringUtils.equals("all",dataTime)){
            query.setDateStr(dataTime);
        }
        List<CheckLog> list = checkLogDao.queryAll(query);
        list.sort(Comparator.comparing(CheckLog::getCreateTime));
        return list;
    }
}