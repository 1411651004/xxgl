package com.guochen.service;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.guochen.model.TCom;
import com.guochen.model.TUser;
import com.guochen.page.Page;

public interface ComService {
	
	public TCom selectByPrimeryKey(int id);
	public TCom selectByUserId(int id);
	public int save(TCom com);
	public List<TCom> selectPageList(Page page, TCom com);
	public void deleteByIds(String[] ids);
	public int deleteByPrimaryKey(Integer id);
	public int blackByTUser(TUser user);
	public int whiteByTUser(TUser user);
	public void updateTcom(TCom com);
	public HSSFWorkbook exp(List<TCom> list);
	public List<TCom> selectList(TCom com);
}
