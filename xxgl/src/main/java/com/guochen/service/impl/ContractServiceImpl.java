package com.guochen.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.guochen.dao.TContractMapper;
import com.guochen.model.TContract;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.ContractService;


@Service
@Transactional
public class ContractServiceImpl implements ContractService {
	@Resource
	private TContractMapper tcontractmapper;

	@Override
	public TContract selectByPrimeryKey(int id) {
		return tcontractmapper.selectByPrimaryKey(id);
	}
	
	@Override
	public int deleteByPrimaryKey(int id) {
		return tcontractmapper.deleteByPrimaryKey(id);
	}

	@Override
	public TContract selectByProjectId(int proId) {
		return tcontractmapper.selectByProjectId(proId);
	}

	@Override
	public int save(TContract contract) {
		return tcontractmapper.insert(contract);
	}

	@Override
	public List<TContract> selectPageList(Page page, TContract contract) {
		page.setTotalCount(tcontractmapper.selectPageCount(contract));
		return tcontractmapper.selectPageList(PageUtils.createRowBounds(page),contract);
	}

	@Override
	public List<TContract> selectAllList(TContract contract) {
		return tcontractmapper.selectAll(contract);
	}

	@Override
	public void updateTContract(TContract contract) {
		tcontractmapper.updateByPrimaryKey(contract);
	}

	/**
	 * 根据合同ID加载合同文件图片
	 */
	@Override
	public Map<String,Object> getContractImageList(HttpServletRequest request,Integer contractId) {
		TContract contract = tcontractmapper.selectByPrimaryKey(contractId);
		if(contract==null || contract.getFilePath()==null || "".equals(contract.getFilePath())){
			return null;
		}
		Map<String,Object> retMap = new HashMap<String, Object>();
		String fileName = contract.getFilePath().substring(0,contract.getFilePath().indexOf("."));
		String base_path = request.getRealPath("/")+"uploadFile/contract";
		String image_path = base_path+"/image/"+fileName;
		File imageFolder = new File(image_path);
		if(imageFolder.exists() && imageFolder.isDirectory()){
			String [] images = imageFolder.list();
			if(images.length>0){
				List<String> imageList = new ArrayList<String>();
				for (String imageName:images) {
					imageList.add(imageName);
				}
				retMap.put("image_list", imageList);
			}
		}
		String pdf_path = base_path+"/pdf/"+fileName+".pdf";
		File pdfFile = new File(pdf_path);
		if(pdfFile.exists() && pdfFile.isFile()){
			retMap.put("pdf_file", fileName+".pdf"); 
		}
		return retMap;
	}

	@Override
	public List<Map> selectConCountByMouth() {
		return tcontractmapper.selectConCountByMouth();
	}

	@Override
	public int selectCountByMouth() {
		// TODO Auto-generated method stub
		return tcontractmapper.selectCountByMouth();
	}

	@Override
	public long selectConCount() {
		return tcontractmapper.selectConCount();
	}

}
