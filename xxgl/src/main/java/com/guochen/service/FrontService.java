package com.guochen.service;

import java.util.List;

import com.guochen.model.TContract;
import com.guochen.model.TNotice;
import com.guochen.model.TProject;

public interface FrontService {
	
	public List<TProject> selectTenderNotice();
	public List<TContract> selectBidNotice();
}
