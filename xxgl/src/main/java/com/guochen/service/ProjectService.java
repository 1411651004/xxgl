package com.guochen.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.guochen.model.TBuyer;
import com.guochen.model.TCode;
import com.guochen.model.TProject;
import com.guochen.page.Page;

public interface ProjectService {
	
	public int selectCountByDate(String starttime,String endtime);
	public TProject selectByPrimeryKey(int id);
	public TBuyer selectBuyerById(int id);
	public List<TCode> selectSeme();
	public List<TProject> listAllProject();
	public String save(TProject project);
	public List<TProject> selectPageList(Page page, TProject project);
	public List<TProject> selectToVerfyPageList(Page page,TProject project);
	public List<TProject> selectToVerfyPageList1(Page page,TProject project);
	public List<TProject> mselectToVerfyPageList(Page page,TProject project);
	public List<TProject> selectAccProjectPageList(Page page, Integer comId,String proName);
	int updateByPrimaryKey(TProject record);
	public List<TProject> selectToSelectComPageList(Page page, TProject project);
	public List<HashMap<String,String>> selectAllOnPage(int pageno,String status);
	public List<TProject> getlist(String starttime,String endtime,String proName,String proStatus);
	public boolean addofferNum(int proId);
	public List<Map> selectProCountByMouth();
	public int selectProCount();
}
