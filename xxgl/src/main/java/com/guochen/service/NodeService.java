package com.guochen.service;

import java.util.List;

import com.guochen.model.TNode;

public interface NodeService {
	
	public TNode selectByPrimeryKey(int id);
	public TNode selectByParentId(int id);
	public TNode selectByFrontId(int id);
	public TNode selectStartNode();
	public int save(TNode node);
	public int updateByPrimaryKey(TNode node);
	public List<TNode> listAllNodes(TNode node);
	public List<TNode> listAllNodesWithOrder(TNode node);
	public int del(int id);
	public String delexceptfirst();
}
