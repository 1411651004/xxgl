package com.guochen.service;

import java.util.List;

import com.guochen.model.TMsg;
import com.guochen.model.TQuickMsg;
import com.guochen.model.TRole;
import com.guochen.model.TUser;
import com.guochen.page.Page;

public interface MsgService {
	
	public List<TQuickMsg> selectAll();
	
	public List<TRole> selectAllRole();
	
	public List<TUser> selectAllUser();
	
	public int save(TMsg tmsg);
	
	public List<TMsg> selectPageList(Page page, TMsg msg);
}
