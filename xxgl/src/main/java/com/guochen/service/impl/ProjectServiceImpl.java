package com.guochen.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guochen.dao.TBuyerMapper;
import com.guochen.dao.TCodeMapper;
import com.guochen.dao.TProjectMapper;
import com.guochen.model.TBuyer;
import com.guochen.model.TCode;
import com.guochen.model.TProject;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.ProjectService;
@Service
@Transactional
public class ProjectServiceImpl implements ProjectService {
	@Resource
	private TProjectMapper tprojectmapper;
	@Resource
	private TBuyerMapper tbuyermapper;
	@Resource
	private TCodeMapper codemapper;
	@Override
	public int selectCountByDate(String starttime, String endtime) {
		List<TProject> list = tprojectmapper.selectByDate(starttime,endtime);
		return list==null?0:list.size();
	}
	@Override
	public TProject selectByPrimeryKey(int id) {
		
		return tprojectmapper.selectByPrimaryKey(id);
	}
	@Override
	public TBuyer selectBuyerById(int id) {
		TBuyer tbuyer = tbuyermapper.selectByPrimaryKey(id);
		return tbuyer;
	}
	@Override
	public List<TCode> selectSeme() {
		return codemapper.selectAllByCodeType("seme");
	}
	@Override
	public String save(TProject project) {
		project.setProStatus("0");
		project.setOfferNum("0");
		tprojectmapper.insert(project);
		return "保存成功！！！";
	}
	@Override
	public List<TProject> listAllProject() {
		return tprojectmapper.selectAll();
	}
	@Override
	public List<TProject> selectPageList(Page page, TProject project) {
		page.setTotalCount(tprojectmapper.selectPageCount(project));
		return tprojectmapper.selectPageList(PageUtils.createRowBounds(page), project);
	}
	@Override
	public List<TProject> selectAccProjectPageList(Page page, Integer comId,String proName) {
		page.setTotalCount(tprojectmapper.selectAccProjectPageCount(comId,proName));
		return tprojectmapper.selectAccProjectPageList(PageUtils.createRowBounds(page), comId,proName);
	}
	@Override
	public int updateByPrimaryKey(TProject record) {
		return tprojectmapper.updateByPrimaryKey(record);
	}
	@Override
	public List<TProject> selectToSelectComPageList(Page page, TProject project) {
		page.setTotalCount(tprojectmapper.selectPageCountToSelectCom(project));
		return tprojectmapper.selectToSelectComPageList(PageUtils.createRowBounds(page),project);
	}
	@Override
	public List<TProject> selectToVerfyPageList(Page page,TProject project) {
		page.setTotalCount(tprojectmapper.selectToVerfyPageCount(project));
		return tprojectmapper.selectToVerfyPageList(PageUtils.createRowBounds(page),project);
	}
	@Override
	public List<TProject> selectToVerfyPageList1(Page page,TProject project) {
		page.setTotalCount(tprojectmapper.selectToVerfyPageCount1(project));
		return tprojectmapper.selectToVerfyPageList1(PageUtils.createRowBounds(page),project);
	}
			@Override
	public List<HashMap<String,String>> selectAllOnPage(int pageno,String status) {
		//int startNo,int endNo,String status
		HashMap<String,Object> hs = new HashMap<String,Object>();
		hs.put("startNo", (pageno-1)*10);
		hs.put("endNo",pageno*10);
		hs.put("status",status);
		List<HashMap<String,String>> list = new ArrayList<HashMap<String,String>>();
		
		List<TProject> listproject = tprojectmapper.selectAllOnPage(hs);
		for(int i=0;i<listproject.size();i++){
			TProject project = (TProject)listproject.get(i);
			TBuyer tbuyer = tbuyermapper.selectByPrimaryKey(project.getBuyerId());
			HashMap<String,String> rehs = new HashMap<String,String>();
			rehs.put("proName", project.getProName());
			rehs.put("proCode", project.getProCode());
			rehs.put("conName", project.getConName());
			rehs.put("buyerName", tbuyer==null?"":tbuyer.getBuyerName());
			rehs.put("starttime", project.getProStarttime());
			rehs.put("status", project.getProStatus());
			rehs.put("endtime", project.getProEndtime());
			rehs.put("phone", project.getConPhone());
			list.add(rehs);
		}
		return list;
	}
	@Override
	public List<TProject> getlist(String starttime, String endtime,
			String proName, String proStatus) {
		return tprojectmapper.selectlist(starttime,endtime,proName,proStatus);
	}
	@Override
	public boolean addofferNum(int proId) {
		// TODO Auto-generated method stub
		boolean b = false;
		TProject pro = tprojectmapper.selectByPrimaryKey(proId);
		pro.setOfferNum((Integer.parseInt(pro.getOfferNum())+1)+"");
		if(tprojectmapper.updateByPrimaryKey(pro)==1){
			b = true;
		}
		return b;
	}
	@Override
	public List<TProject> mselectToVerfyPageList(Page page,TProject project) {
		page.setTotalCount(tprojectmapper.selectToVerfyPageCount(project));
		return tprojectmapper.selectToVerfyPageList(PageUtils.createRowBounds(page),project);
	}
	@Override
	public List<Map> selectProCountByMouth() {
		
		return tprojectmapper.selectProCountByMouth();
	}
	@Override
	public int selectProCount() {
		return tprojectmapper.selectProCount();
	}

}
