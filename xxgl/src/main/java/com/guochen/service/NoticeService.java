package com.guochen.service;

import java.util.List;
import com.guochen.model.TNotice;
import com.guochen.page.Page;

public interface NoticeService {
	
	public TNotice selectByPrimeryKey(int id);
	public int save(TNotice notice);
	int updateByPrimaryKey(TNotice notice);
	public List<TNotice> selectPageList(Page page, TNotice notice);
	public List<TNotice> selectList(TNotice notice);
}
