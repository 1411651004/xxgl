package com.guochen.service;

import java.util.List;

import com.guochen.model.Xsxx;
import com.guochen.page.Page;

public interface XsxxService {

    List<Xsxx> selectByXsxx(Xsxx record);

    int insertSelective(Xsxx record);
    
    int deleteByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(Xsxx record);
    
    int updateByIdSelective(Xsxx record);
    
    public List<Xsxx> selectPageList(Page page, Xsxx record);
    
    int updateStaById(String ids);
    
    int updatestabysfz(Xsxx record);
    
    public List<Xsxx> selectCountByQyxx(Xsxx record);

	int changeStudentStatus(String ids, String status);

	List<Xsxx> selectPageInputSecond(Page page, Xsxx xsxx);
}