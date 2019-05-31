package com.guochen.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guochen.dao.TTemplateMapper;
import com.guochen.model.TTemplate;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.TemplateService;
import com.guochen.utils.CommonUtils;
@Service
@Transactional
public class TemplateServiceImpl implements TemplateService {
	@Resource
	private TTemplateMapper templtemapper;

	@Override
	public TTemplate selectByPrimeryKey(int id) {
		return templtemapper.selectByPrimaryKey(id);
	}

	@Override
	public List<TTemplate> listAll() {
		return templtemapper.selectAll();
	}

	@Override
	public int save(TTemplate template) {
		if(template.getCreateTime()==null || "".equals(template.getCreateTime())){
			template.setCreateTime(CommonUtils.date2Str(new Date()));
		}
		template.setUpdateTime(CommonUtils.date2Str(new Date()));
		return templtemapper.insert(template);
	}

	@Override
	public List<TTemplate> selectPageList(Page page, TTemplate template) {
		page.setTotalCount(templtemapper.selectPageCount(template));
		return templtemapper.selectPageList(PageUtils.createRowBounds(page), template);
	}

	@Override
	public int updateByPrimaryKey(TTemplate template) {
		if(template.getCreateTime()==null || "".equals(template.getCreateTime())){
			template.setCreateTime(CommonUtils.date2Str(new Date()));
		}
		template.setUpdateTime(CommonUtils.date2Str(new Date()));
		return templtemapper.updateByPrimaryKey(template);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return templtemapper.deleteByPrimaryKey(id);
	}

}
