package com.guochen.service.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.guochen.dao.XsxxDao;
import com.guochen.model.Xsxx;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.XsxxService;

@Service
public class XsxxServiceImpl implements XsxxService {
    @Autowired
    private XsxxDao xsxxDao;
    @Override
    public List<Xsxx> selectByXsxx(Xsxx record) {
        List<Xsxx> result = xsxxDao.selectByXsxx(record);
        return result;
    }
    		@Override
	public List<Xsxx> selectPageList(Page page, Xsxx record) {
		page.setTotalCount(xsxxDao.selectPageCount(record));
		return xsxxDao.selectPageList(PageUtils.createRowBounds(page), record);
	}
    @Override
    public int insertSelective(Xsxx record) {
         return xsxxDao.insertSelective(record);
    }
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return xsxxDao.deleteByPrimaryKey(id);
    }
    @Override
    public int updateByPrimaryKeySelective(Xsxx record) {
        return xsxxDao.updateByPrimaryKeySelective(record);
    }
	@Override
	public int updateStaById(String ids) {
		return xsxxDao.updateStaById(ids);
	}
	@Override
	public int updatestabysfz(Xsxx record) {
		return xsxxDao.updatestabysfz(record);
	}
	@Override
	public int updateByIdSelective(Xsxx record) {
		return xsxxDao.updateByIdSelective(record);
	}
	@Override
	public List<Xsxx> selectCountByQyxx(Xsxx record) {
		return xsxxDao.selectCountByQyxx(record);
	}
	@Override
	public int changeStudentStatus(String ids, String status) {
		return xsxxDao.changeStudentStatus(ids,status);
	}
	@Override
	public List<Xsxx> selectPageInputSecond(Page page, Xsxx xsxx) {
		page.setTotalCount(xsxxDao.selectPageInputSecondCount(xsxx));
		return xsxxDao.selectPageInputSecond(PageUtils.createRowBounds(page), xsxx);
	}
}