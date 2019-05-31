package com.guochen.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.guochen.dao.TNodeMapper;
import com.guochen.model.TNode;
import com.guochen.service.NodeService;
@Service
@Transactional
public class NodeServiceImpl implements NodeService {
	@Resource
	private TNodeMapper tnodemapper;

	@Override
	public TNode selectByPrimeryKey(int id) {
		return tnodemapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(TNode node) {
		return tnodemapper.insert(node);
	}

	@Override
	public int updateByPrimaryKey(TNode node) {
		return tnodemapper.updateByPrimaryKey(node);
	}

	@Override
	public List<TNode> listAllNodes(TNode node) {
		return tnodemapper.selectAll(node);
	}

	@Override
	public TNode selectStartNode() {
		return tnodemapper.selectStartNode();
	}

	@Override
	public List<TNode> listAllNodesWithOrder(TNode node) {
		List<TNode> nodeList = new ArrayList<TNode>();
		TNode startNode = tnodemapper.selectStartNode();
		return loadNextNode(nodeList,startNode);
	}
	
	public List<TNode> loadNextNode(List<TNode> nodeList, TNode startNode){
		nodeList.add(startNode);
		if(startNode.getRearNodeId()!=9999){
			//不是结束节点
			TNode nextNode = tnodemapper.selectByPrimaryKey(startNode.getRearNodeId());
			if(nextNode == null){
				return nodeList;
			}
			loadNextNode(nodeList,nextNode);
		}
		return nodeList;
	}

	@Override
	public int del(int id) {
		return tnodemapper.deleteByPrimaryKey(id);
	}

	@Override
	public TNode selectByParentId(int id) {
		return tnodemapper.selectByParentId(id);
	}
	@Override
	public TNode selectByFrontId(int id) {
		return tnodemapper.selectByFrontId(id);
	}

	@Override
	public String delexceptfirst() {
		return tnodemapper.delexceptfirst();
	}
	
}
