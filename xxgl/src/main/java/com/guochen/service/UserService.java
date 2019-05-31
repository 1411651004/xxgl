package com.guochen.service;

import java.util.List;
import java.util.Map;

import com.guochen.model.TUser;
import com.guochen.page.Page;

public interface UserService {
	
	public TUser selectByPrimeryKey(int id);
	public int save(TUser user);
	public int updateByPrimaryKey(TUser user);
	public List<TUser> listAllUsers(TUser user);
	public TUser selectByPhone(String phone);
		public List<TUser> selectPageList(Page page, TUser user);
	public int delete(int userId);
	public List<Map> selectUserCountByMouth();
	public int selectUserCount();
	public TUser selectByComId(int comId);
}
