package com.guochen.service;

import java.util.List;

import com.guochen.model.TFile;
import com.guochen.page.Page;

public interface FileService {
	
	public List<TFile> selectPageList(Page page, TFile file);
	int add(TFile file);
	int deleteByIds(Integer[] ids);
	public List selectByUseridPage(Page page,int id);
}
