package com.guochen.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.guochen.model.Xsxx;

public interface XsxxDao {

    List<Xsxx> selectByXsxx(Xsxx record);

    int insertSelective(Xsxx record);
    
    int deleteByPrimaryKey(Integer id);
    
    int updateByPrimaryKeySelective(Xsxx record);
    
    int updateByIdSelective(Xsxx record);
    
    public List<Xsxx> selectPageList(RowBounds rowBounds, Xsxx record);
    
    public Long selectPageCount(Xsxx record);
    
    public int updateStaById(String ids);
    
    public int updatestabysfz(Xsxx record);
    
    public List<Xsxx> selectCountByQyxx(Xsxx record);

	int changeStudentStatus(String ids, String status);

	Long selectPageInputSecondCount(Xsxx xsxx);

	List<Xsxx> selectPageInputSecond(RowBounds createRowBounds, Xsxx xsxx);
}