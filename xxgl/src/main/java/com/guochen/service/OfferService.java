package com.guochen.service;

import java.util.List;
import java.util.Map;

import com.guochen.model.TOffer;
import com.guochen.page.Page;

public interface OfferService {
	
	public TOffer selectByPrimeryKey(int id);
	public int save(TOffer offer);
	public List<TOffer> selectPageList(Page page, TOffer com);
	public List<TOffer> selectAllList(TOffer com);
	public void updateTOffer(TOffer com);
	public List<Map> selectOfferCountByMouth();
	public int selectOfferCount();
	public TOffer selectByProIdAndUserId(int proId, int userId);
}
