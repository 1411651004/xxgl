package com.guochen.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guochen.dao.TBuScopeMapper;
import com.guochen.model.TBuScope;
import com.guochen.service.ScopeService;

@Service
@Transactional
public class ScopeServiceImpl implements ScopeService {
	@Resource
	private TBuScopeMapper scopemapper;

	@Override
	public TBuScope selectByPrimeryKey(int id) {
		return scopemapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(TBuScope authority) {
		return scopemapper.insert(authority);
	}

	@Override
	public int updateByPrimaryKey(TBuScope scope) {
		return scopemapper.updateByPrimaryKey(scope);
	}

	@Override
	public List<TBuScope> getTree() {
		TBuScope scope = new TBuScope();
		scope.setParentId(0);
		List<TBuScope> parentList = scopemapper.selectAll(scope);
		List<TBuScope> allList = scopemapper.selectAll(new TBuScope());
		if(parentList!=null && allList!=null){
			for(TBuScope au:parentList){
				List<TBuScope> childrenList = new ArrayList<TBuScope>();
				for(TBuScope al:allList){
					if(al.getParentId()==au.getId()){
						childrenList.add(al);
					}
				}
				au.setChildrenList(childrenList);
			}
		}
		return parentList;
	}

	@Override
	public List<TBuScope> selectList(TBuScope scope) {
		return scopemapper.selectAll(scope);
	}

}
