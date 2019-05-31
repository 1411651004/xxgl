package com.guochen.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.guochen.dao.TCodeMapper;
import com.guochen.model.TCode;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.CodeService;
@Service
@Transactional
public class CodeServiceImpl implements CodeService {
	@Resource
	private TCodeMapper codemapper;

	@Override
	public TCode selectByPrimeryKey(int id) {
		return codemapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(TCode code) {
		return codemapper.insert(code);
	}

	@Override
	public int updateByPrimaryKey(TCode code) {
		return codemapper.updateByPrimaryKey(code);
	}

	@Override
	public int delete(int codeId) {
		return codemapper.deleteByPrimaryKey(codeId);
	}

	@Override
	public List<TCode> selectListByType(String codeType) {
		return codemapper.selectAllByCodeType(codeType);
	}

	@Override
	public List<TCode> selectPageList(Page page, TCode code) {
		page.setTotalCount(codemapper.selectPageCount(code));
		return codemapper.selectPageList(PageUtils.createRowBounds(page), code);
	}

}
