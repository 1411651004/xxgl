package com.guochen.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guochen.dao.BfglDao;
import com.guochen.dao.QyxxDao;
import com.guochen.model.Bfgl;
import com.guochen.model.Qyxx;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.QyxxService;

@Service
public class QyxxServiceImpl implements QyxxService {
    @Autowired
    private QyxxDao qyxxDao;
    @Autowired
    private BfglDao bfglDao;
    @Override
    public List<Qyxx> selectByQyxx(Qyxx record) {
        List<Qyxx> result = qyxxDao.selectByQyxx(record);
        return result;
    }
    		@Override
	public List<Qyxx> selectPageList(Page page, Qyxx record) {
		page.setTotalCount(qyxxDao.selectPageCount(record));
		return qyxxDao.selectPageList(PageUtils.createRowBounds(page), record);
	}
    @Override
    public int insertSelective(Qyxx record) {
         return qyxxDao.insertSelective(record);
    }
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return qyxxDao.deleteByPrimaryKey(id);
    }
    @Override
    public int updateByPrimaryKeySelective(Qyxx record) {
        return qyxxDao.updateByPrimaryKeySelective(record);
    }
	@Override
	public List<Bfgl> selectBfgl(Bfgl bfgl) {
		return bfglDao.selectByBfgl(bfgl);
	}
	@Override
	public int updateBfglByPrimaryKey(Bfgl bfgl) {
		return bfglDao.updateByPrimaryKey(bfgl);
	}
	@Override
	public int insertBfgl(Bfgl bfgl) {
		return bfglDao.insertSelective(bfgl);
	}
	@Override
	public List<Qyxx> selectByIds(String[] ids) {
		return qyxxDao.selectByIds(ids);
	}
}