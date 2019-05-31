package com.guochen.service.impl;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.guochen.dao.TNoticeMapper;
import com.guochen.model.TNotice;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.NoticeService;

@Service
@Transactional
public class NoticeServiceImpl implements NoticeService {
	@Resource
	private TNoticeMapper noticemapper;

	@Override
	public TNotice selectByPrimeryKey(int id) {
		return noticemapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(TNotice notice) {
		return noticemapper.insert(notice);
	}

	@Override
	public int updateByPrimaryKey(TNotice notice) {
		return noticemapper.updateByPrimaryKey(notice);
	}

	@Override
	public List<TNotice> selectPageList(Page page, TNotice notice) {
		page.setTotalCount(noticemapper.selectPageCount(notice));
		return noticemapper.selectPageList(PageUtils.createRowBounds(page), notice);
	}

	@Override
	public List<TNotice> selectList(TNotice notice) {
		return noticemapper.selectList(notice);
	}
	
}
