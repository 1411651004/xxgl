package com.guochen.service.impl;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.guochen.dao.TComMapper;
import com.guochen.dao.TUserMapper;
import com.guochen.model.TCom;
import com.guochen.model.TUser;
import com.guochen.page.Page;
import com.guochen.page.PageUtils;
import com.guochen.service.ComService;
import com.guochen.utils.CommonUtils;
@Service
@Transactional
public class ComServiceImpl implements ComService {
	@Resource
	private TComMapper tcommapper;
	@Resource
	private TUserMapper tusermapper;

	@Override
	public TCom selectByPrimeryKey(int id) {
		return tcommapper.selectByPrimaryKey(id);
	}

	@Override
	public int save(TCom com) {
		if(com.getId()==null){
			com.setCreateTime(CommonUtils.date2Str(new Date()));
			com.setUpdateTime(CommonUtils.date2Str(new Date()));
		}else{
			com.setUpdateTime(CommonUtils.date2Str(new Date()));
		}
		return tcommapper.insert(com);
	}

	@Override
	public List<TCom> selectPageList(Page page, TCom com) {
		List<TCom> comList1 = new ArrayList<TCom>();
		page.setTotalCount(tcommapper.selectPageCount(com));
		for(TCom com1 : tcommapper.selectPageList(PageUtils.createRowBounds(page), com)){
			com1.setUser(tusermapper.selectByComId(com1.getId()));
			comList1.add(com1);
		}
		return comList1;
	}
	
	@Override
	public List<TCom> selectList(TCom com){
		List<TCom> comList1 = new ArrayList<TCom>();
		for(TCom com1 : tcommapper.selectList(com)){
			com1.setUser(tusermapper.selectByComId(com1.getId()));
			comList1.add(com1);
		}
		return comList1;
	}
	@Override
	public void deleteByIds(String[] ids) {
		tcommapper.deleteByIds(ids);
	}

	@Override
	public int deleteByPrimaryKey(Integer id) {
		return tcommapper.deleteByPrimaryKey(id);
	}

	@Override
	public int blackByTUser(TUser user) {
		return tusermapper.updateByPrimaryKey(user);
	}

	@Override
	public int whiteByTUser(TUser user) {
		return tusermapper.updateByPrimaryKey(user);
	}

	@Override
	public void updateTcom(TCom com) {
		tcommapper.updateByPrimaryKey(com);
	}

	@Override
	public TCom selectByUserId(int id) {
		return tcommapper.selectByUserId(id);
	}
	
	@Override
	public HSSFWorkbook exp(List<TCom> list){
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("合作供应商");
		HSSFRow row = sheet.createRow(0);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue("供应商名称");
		cell=row.createCell(1);
		cell.setCellValue("用户名");
		cell=row.createCell(2);
		cell.setCellValue("经营范围");
		cell=row.createCell(3);
		cell.setCellValue("开户银行");
		cell=row.createCell(4);
		cell.setCellValue("公司地址");
		cell=row.createCell(5);
		cell.setCellValue("联系人");
		cell=row.createCell(6);
		cell.setCellValue("联系人电话");
		cell=row.createCell(7);
		cell.setCellValue("办公电话");
		for(int i=0;i<list.size();i++){
			TCom com = list.get(i);
			row = sheet.createRow(i+1);
			cell = row.createCell(0);
			cell.setCellValue(com.getComName());
			cell = row.createCell(1);
			cell.setCellValue(com.getUser().getLoginName());
			cell = row.createCell(2);
			cell.setCellValue(com.getAlternateField1());
			cell = row.createCell(3);
			cell.setCellValue(com.getBankAccount());
			cell = row.createCell(4);
			cell.setCellValue(com.getComAddr());
			cell = row.createCell(5);
			cell.setCellValue(com.getConName());
			cell = row.createCell(6);
			cell.setCellValue(com.getConPhone());
			cell = row.createCell(7);
			cell.setCellValue(com.getComPhone());
		}
		return wb;
	}
	
}
