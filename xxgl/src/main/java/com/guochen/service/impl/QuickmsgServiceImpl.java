package com.guochen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guochen.dao.TCodeMapper;
import com.guochen.dao.TQuickMsgMapper;
import com.guochen.model.TCode;
import com.guochen.model.TQuickMsg;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.QuickmsgService;
@Service
@Transactional
public class QuickmsgServiceImpl implements QuickmsgService {
	@Resource
	private TQuickMsgMapper tquickmsgmapper;
	@Override
	public List<TQuickMsg> selectPageList(Page page, TQuickMsg tquickmsg) {
		page.setTotalCount(tquickmsgmapper.selectPageCount(tquickmsg));
		return tquickmsgmapper.selectPageList(PageUtils.createRowBounds(page), tquickmsg);
	}
	@Override
	public TQuickMsg selectByPrimeryKey(int id) {
		// TODO Auto-generated method stub
		return tquickmsgmapper.selectByPrimaryKey(id);
	}
	@Override
	public int save(TQuickMsg tquickmsg) {
		// TODO Auto-generated method stub
		return tquickmsgmapper.insert(tquickmsg);
	}
	@Override
	public int updateByPrimaryKey(TQuickMsg tquickmsg) {
		// TODO Auto-generated method stub
		return tquickmsgmapper.updateByPrimaryKey(tquickmsg);
	}
	@Override
	public int delete(int quickmsgId) {
		// TODO Auto-generated method stub
		return tquickmsgmapper.deleteByPrimaryKey(quickmsgId);
	}
	
	

}
