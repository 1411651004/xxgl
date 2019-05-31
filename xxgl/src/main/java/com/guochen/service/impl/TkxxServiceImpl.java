package com.guochen.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guochen.dao.TkxxDao;
import com.guochen.model.Tkxx;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.TkxxService;

@Service
public class TkxxServiceImpl implements TkxxService {
    @Autowired
    private TkxxDao tkxxDao;
    @Override
    public List<Tkxx> selectByTkxx(Tkxx record) {
        List<Tkxx> result = tkxxDao.selectByTkxx(record);
        return result;
    }
    		@Override
	public List<Tkxx> selectPageList(Page page, Tkxx record) {
		page.setTotalCount(tkxxDao.selectPageCount(record));
		return tkxxDao.selectPageList(PageUtils.createRowBounds(page), record);
	}
    @Override
    public int insertSelective(Tkxx record) {
         return tkxxDao.insertSelective(record);
    }
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tkxxDao.deleteByPrimaryKey(id);
    }
    @Override
    public int updateByPrimaryKeySelective(Tkxx record) {
        return tkxxDao.updateByPrimaryKeySelective(record);
    }
	@Override
	public List<Tkxx> selectAll() {
		return tkxxDao.selectAll();
	}
}