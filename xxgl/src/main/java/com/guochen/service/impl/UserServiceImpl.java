package com.guochen.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guochen.dao.TUserMapper;
import com.guochen.model.TUser;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.UserService;
@Service
@Transactional
public class UserServiceImpl implements UserService {
	@Resource
	private TUserMapper tusermapper;

	@Override
	public TUser selectByPrimeryKey(int id) {
		return tusermapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(TUser user) {
		return tusermapper.insert(user);
	}

	@Override
	public int updateByPrimaryKey(TUser user) {
		return tusermapper.updateByPrimaryKey(user);
	}

	@Override
	public List<TUser> listAllUsers(TUser user) {
		return tusermapper.selectAll(user);
	}

	@Override
	public TUser selectByPhone(String phone) {
		return tusermapper.selectByPhone(phone);
	}
		@Override
	public List<TUser> selectPageList(Page page, TUser user) {
		page.setTotalCount(tusermapper.selectPageCount(user));
		return tusermapper.selectPageList(PageUtils.createRowBounds(page), user);
	}

	@Override
	public int delete(int userId) {
		return tusermapper.deleteByPrimaryKey(userId);
	}

	@Override
	public List<Map> selectUserCountByMouth() {
		return tusermapper.selectUserCountByMouth();
	}

	@Override
	public TUser selectByComId(int comId) {
		return tusermapper.selectByComId(comId);
	}

	@Override
	public int selectUserCount() {
		return tusermapper.selectUserCount();
	}
	
}
