package com.guochen.service;

import java.util.List;

import com.guochen.model.TConapp;
import com.guochen.model.TProject;
import com.guochen.page.Page;

public interface ConAppService {
	
	public TConapp selectByPrimeryKey(int id);
	public int deleteByContractId(int contractId);
	public int save(TConapp con);
	public List<TConapp> listAll(int contractId);
	public int update(TConapp con);
	public TConapp selectCurrentByContractId(int contractId);
	public List<TConapp> mselectVerifyPageList(Page page,int userId);
	public List<TConapp> selectVerifyPageList(Page page, TProject project);
}
