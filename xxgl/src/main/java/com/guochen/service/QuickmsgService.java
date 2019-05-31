package com.guochen.service;

import java.util.List;

import com.guochen.model.TQuickMsg;
import com.guochen.page.Page;

public interface QuickmsgService {
	public List<TQuickMsg> selectPageList(Page page, TQuickMsg tquickmsg);
	public TQuickMsg selectByPrimeryKey(int id);
	public int save(TQuickMsg tquickmsg);
	public int updateByPrimaryKey(TQuickMsg tquickmsg);
	public int delete(int quickmsgId);
}
