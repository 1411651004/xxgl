package com.guochen.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guochen.dao.TContractMapper;
import com.guochen.dao.TProjectMapper;
import com.guochen.model.TContract;
import com.guochen.model.TProject;
import com.guochen.service.FrontService;

@Service
@Transactional
public class FrontServiceImpl implements FrontService {
	
	@Resource
	private TProjectMapper tprojectmapper;
	@Resource
	private TContractMapper tcontractmapper;
	@Override
	public List<TProject> selectTenderNotice() {
		List<TProject> list = tprojectmapper.selectlist(null, null, null, "1");
		return list;
	}
	@Override
	public List<TContract> selectBidNotice() {
		List<TContract> list = tcontractmapper.selectBidNotice();
		return list;
	}

}
