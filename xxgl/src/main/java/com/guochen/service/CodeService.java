package com.guochen.service;

import java.util.List;

import com.guochen.model.TCode;
import com.guochen.page.Page;

public interface CodeService {
	
	public TCode selectByPrimeryKey(int id);
	public int save(TCode code);
	public int updateByPrimaryKey(TCode code);
	public int delete(int codeId);
	public List<TCode> selectListByType(String type);
	public List<TCode> selectPageList(Page page, TCode code);
}
