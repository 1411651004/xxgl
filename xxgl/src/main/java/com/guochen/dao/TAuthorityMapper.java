package com.guochen.dao;

import com.guochen.model.TAuthority;
import java.util.List;

public interface TAuthorityMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_authority
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_authority
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    int insert(TAuthority record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_authority
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    TAuthority selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_authority
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    List<TAuthority> selectAll(TAuthority record);
    List<TAuthority> selectAuthorityByRoleId(int roleId);
    List<TAuthority> selectParentAuthorityByRoleId(int roleId);
    List<TAuthority> selectChildAuthorityByRoleId(int roleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_authority
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    int updateByPrimaryKey(TAuthority record);
}