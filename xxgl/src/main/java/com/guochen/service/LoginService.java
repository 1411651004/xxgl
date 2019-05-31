package com.guochen.service;

import java.util.List;

import com.guochen.model.TAuthority;
import com.guochen.model.TCom;
import com.guochen.model.TRole;
import com.guochen.model.TUser;

public interface LoginService {
	public String login(TUser user,String iscookie);
	public TUser selectUserByLoginname(String loginname);
	public TRole selectRoleByRoleId(int roleid);
	public TCom selectComByComId(int comid);
	public List<TAuthority> selectAuthorityByRoleId(int roleid);
	public List<TAuthority> selectParentAuthorityByRoleId(int roleid);
	public List<TAuthority> selectChildAuthorityByRoleId(int roleid);
}
