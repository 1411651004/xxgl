package com.guochen.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guochen.dao.TOfferMapper;
import com.guochen.model.TOffer;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.OfferService;



@Service
@Transactional
public class OfferServiceImpl implements OfferService {
	@Resource
	private TOfferMapper toffermapper;

	@Override
	public TOffer selectByPrimeryKey(int id) {
		return toffermapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(TOffer offer) {
		return toffermapper.insert(offer);
	}
	
	@Override
	public void updateTOffer(TOffer com) {
		toffermapper.updateByPrimaryKey(com);
	}

	@Override
	public List<TOffer> selectPageList(Page page, TOffer offer) {
		page.setTotalCount(toffermapper.selectPageCount(offer));
		return toffermapper.selectPageList(PageUtils.createRowBounds(page),offer);
	}

	@Override
	public List<TOffer> selectAllList(TOffer offer) {
		return toffermapper.selectAll(offer);
	}

	@Override
	public List<Map> selectOfferCountByMouth() {
		return toffermapper.selectOfferCountByMouth();
	}

	@Override
	public TOffer selectByProIdAndUserId(int proId, int userId) {
		return toffermapper.selectByProIdAndUserId(proId, userId);
	}

	@Override
	public int selectOfferCount() {
		return toffermapper.selectOfferCount();
	}

	
	
}
