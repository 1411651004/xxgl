package com.guochen.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.guochen.model.TContract;
import com.guochen.page.Page;

public interface ContractService {
	
	public TContract selectByPrimeryKey(int id);
	public int deleteByPrimaryKey(int id);
	public int selectCountByMouth();
	public TContract selectByProjectId(int proId);
	public int save(TContract contract);
	public List<TContract> selectPageList(Page page, TContract contract);
	public List<TContract> selectAllList(TContract contract);
	public void updateTContract(TContract contract);
	
	public Map<String,Object> getContractImageList(HttpServletRequest request,Integer contractId);
	public List<Map> selectConCountByMouth();
	public long selectConCount();
}
