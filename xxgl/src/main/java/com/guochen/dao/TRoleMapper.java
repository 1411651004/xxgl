package com.guochen.dao;

import com.guochen.model.TRole;
import com.guochen.model.TUser;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

public interface TRoleMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    int insert(TRole record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    TRole selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    List<TRole> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_role
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    int updateByPrimaryKey(TRole record);
    
    public List<TRole> selectPageList(RowBounds rowBounds, TRole record);
    public Long selectPageCount(TRole record);
}