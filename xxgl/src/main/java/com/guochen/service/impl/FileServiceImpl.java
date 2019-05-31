package com.guochen.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guochen.dao.TFileMapper;
import com.guochen.model.TFile;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.FileService;

@Service
@Transactional
public class FileServiceImpl implements FileService {
	@Resource
	private TFileMapper fileMapper;

	@Override
	public List<TFile> selectPageList(Page page, TFile file) {
		page.setTotalCount(fileMapper.selectPageCount(file));
		return fileMapper.selectPageList(PageUtils.createRowBounds(page), file);
	}

	@Override
	public int add(TFile file) {
		return fileMapper.insert(file);
	}

	@Override
	public int deleteByIds(Integer[] ids) {
		for(int i=0;i<ids.length;i++){
			fileMapper.deleteByPrimaryKey(ids[i]);
		}
		return 0;
	}

	@Override
	public List selectByUseridPage(Page page,int id) {
		List<TFile> list =fileMapper.selectByUseridPageCount(id);
		List returnlist = new ArrayList();
		for(int i=0;i<list.size();i++){
			TFile file = list.get(i);
			String[] str = file.getFileName().split("\\|");
			for(int k=0;k<str.length;k++){
				HashMap hs = new HashMap();
				hs.put("fileName", str[k]);
				hs.put("time", file.getCreateTime());
				returnlist.add(hs);
			}
		}
		page.setTotalCount(returnlist.size());
		int start = ((page.getPageNum()-1)*page.getNumPerPage())+1;
		int end = page.getPageNum()*page.getNumPerPage();
		if(returnlist.size()>page.getNumPerPage()){
			if(end>returnlist.size()){
				returnlist = returnlist.subList(start-1, returnlist.size());
			}else{
				returnlist = returnlist.subList(start-1, end);
			}
		}
		return returnlist;
		
	}
	
}
