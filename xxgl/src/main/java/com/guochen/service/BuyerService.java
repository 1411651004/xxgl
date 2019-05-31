package com.guochen.service;

import java.util.List;
import com.guochen.model.TBuyer;
import com.guochen.page.Page;

public interface BuyerService {
	
	public TBuyer selectByPrimeryKey(int id);
	public int save(TBuyer buyer);
	public int updateByPrimaryKey(TBuyer buyer);
	public List<TBuyer> listAll();
	public List<TBuyer> selectPageList(Page page, TBuyer buyer);
	public int delete(int userId);
}
