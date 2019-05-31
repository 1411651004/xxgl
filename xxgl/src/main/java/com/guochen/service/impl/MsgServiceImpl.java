package com.guochen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guochen.dao.TMsgMapper;
import com.guochen.dao.TQuickMsgMapper;
import com.guochen.dao.TRoleMapper;
import com.guochen.dao.TUserMapper;
import com.guochen.model.TMsg;
import com.guochen.model.TQuickMsg;
import com.guochen.model.TRole;
import com.guochen.model.TUser;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.MsgService;

@Service
@Transactional
public class MsgServiceImpl implements MsgService {
	private static Logger log = Logger.getLogger(MsgServiceImpl.class);
	
	@Resource
	private TQuickMsgMapper tquickmsgmapper;
	@Resource
	private TRoleMapper trolemapper;
	@Resource
	private TUserMapper tusermapper;
	@Resource
	private TMsgMapper tmsgmapper;
	
	@Override
	public List<TQuickMsg> selectAll() {
		// TODO Auto-generated method stub
		return tquickmsgmapper.selectAll();
	}

	@Override
	public List<TRole> selectAllRole() {
		// TODO Auto-generated method stub
		return trolemapper.selectAll();
	}

	@Override
	public List<TUser> selectAllUser() {
		TUser user = new TUser();
		return tusermapper.selectAll(user);
	}

	@Override
	public int save(TMsg tmsg) {
		// TODO Auto-generated method stub
		return tmsgmapper.insert(tmsg);
	}

	@Override
	public List<TMsg> selectPageList(Page page, TMsg msg) {
		page.setTotalCount(tmsgmapper.selectPageCount(msg));
		return tmsgmapper.selectPageList(PageUtils.createRowBounds(page), msg);
	}
	

}
