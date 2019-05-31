package com.guochen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guochen.dao.TAuthorityMapper;
import com.guochen.dao.TComMapper;
import com.guochen.dao.TRoleMapper;
import com.guochen.dao.TUserMapper;
import com.guochen.model.TAuthority;
import com.guochen.model.TCom;
import com.guochen.model.TRole;
import com.guochen.model.TUser;
import com.guochen.service.LoginService;
import com.guochen.utils.Md5Util;

@Service
@Transactional
public class LoginServiceImpl implements LoginService{
	private static Logger log = Logger.getLogger(LoginServiceImpl.class);
	
	@Resource
	private TUserMapper usermapper;
	@Resource
	private TRoleMapper rolemapper;
	@Resource
	private TAuthorityMapper authoritymapper;
	@Resource
	private TComMapper commapper;
	
	public String login(TUser user,String iscookie) {
		TUser user2 = usermapper.selectByLoginname(user.getLoginName());
		String pwd = "";
		if(iscookie.equals("1")){
			pwd=user.getLoginPwd();
		}else{
			pwd=Md5Util.getMd5(user.getLoginPwd());
		}
		if(user2==null){
			return "用户不存在";
		}else if(user2.getLoginPwd().equals(pwd)){
			if("0".equals(user2.getUserStatus())){
				return "";
			}else{
				return "用户账户被禁用";
			}
		}else{
			return "密码错误";
		}
	}
	
	public TUser selectUserByLoginname(String loginname){
		TUser user = usermapper.selectByLoginname(loginname);
		return user;
	}
	
	public TRole selectRoleByRoleId(int roleid){
		TRole role = rolemapper.selectByPrimaryKey(roleid);
		return role;
	}
	public List<TAuthority> selectAuthorityByRoleId(int roleid){
		return authoritymapper.selectAuthorityByRoleId(roleid);
	}
	public List<TAuthority> selectParentAuthorityByRoleId(int roleid){
		return authoritymapper.selectParentAuthorityByRoleId(roleid);
	}
	public List<TAuthority> selectChildAuthorityByRoleId(int roleid){
		return authoritymapper.selectChildAuthorityByRoleId(roleid);
	}
	public TCom selectComByComId(int comid){
		return commapper.selectByPrimaryKey(comid);
	}
}
