package com.guochen.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guochen.dao.XtglDao;
import com.guochen.model.Xtgl;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.XtglService;

@Service
public class XtglServiceImpl implements XtglService {
    @Autowired
    private XtglDao xtglDao;
    @Override
    public List<Xtgl> selectByXtgl(Xtgl record) {
        List<Xtgl> result = xtglDao.selectByXtgl(record);
        return result;
    }
    		@Override
	public List<Xtgl> selectPageList(Page page, Xtgl record) {
		page.setTotalCount(xtglDao.selectPageCount(record));
		return xtglDao.selectPageList(PageUtils.createRowBounds(page), record);
	}
    @Override
    public int insertSelective(Xtgl record) {
         return xtglDao.insertSelective(record);
    }
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return xtglDao.deleteByPrimaryKey(id);
    }
    @Override
    public int updateByPrimaryKeySelective(Xtgl record) {
        return xtglDao.updateByPrimaryKeySelective(record);
    }
	@Override
	public List<Xtgl> getAllQuarters() {
		return xtglDao.getAllQuarters();
	}
	@Override
	public List<Xtgl> getQuarterByName(String codeValue) {
		return xtglDao.getQuarterByName(codeValue);
	}
}