package com.guochen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guochen.dao.TBuyerMapper;
import com.guochen.model.TBuyer;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.BuyerService;
@Service
@Transactional
public class BuyerServiceImpl implements BuyerService {
	@Resource
	private TBuyerMapper buyermapper;

	@Override
	public TBuyer selectByPrimeryKey(int id) {
		return buyermapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(TBuyer buyer) {
		return buyermapper.insert(buyer);
	}

	@Override
	public int updateByPrimaryKey(TBuyer buyer) {
		return buyermapper.updateByPrimaryKey(buyer);
	}

	@Override
	public List<TBuyer> listAll() {
		return buyermapper.selectAll();
	}

	@Override
	public List<TBuyer> selectPageList(Page page, TBuyer buyer) {
		page.setTotalCount(buyermapper.selectPageCount(buyer));
		return buyermapper.selectPageList(PageUtils.createRowBounds(page), buyer);
	}

	@Override
	public int delete(int id) {
		return buyermapper.deleteByPrimaryKey(id);
	}

	
}
