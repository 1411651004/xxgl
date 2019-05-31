package com.guochen.service;

import java.util.List;

import com.guochen.model.TTemplate;
import com.guochen.page.Page;

public interface TemplateService {
	
	public TTemplate selectByPrimeryKey(int id);
	public int deleteByPrimaryKey(Integer id);
	public List<TTemplate> listAll();
	public int save(TTemplate template);
	public List<TTemplate> selectPageList(Page page, TTemplate template);
	int updateByPrimaryKey(TTemplate template);
}
