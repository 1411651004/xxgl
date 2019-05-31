package com.guochen.dao;

import com.guochen.model.TProject;
import com.guochen.model.TUser;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;

public interface TUserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Dec 26 20:05:50 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Dec 26 20:05:50 CST 2016
     */
    int insert(TUser record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Dec 26 20:05:50 CST 2016
     */
    TUser selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Dec 26 20:05:50 CST 2016
     */
    List<TUser> selectAll(TUser user);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_user
     *
     * @mbg.generated Mon Dec 26 20:05:50 CST 2016
     */
    int updateByPrimaryKey(TUser user);
    TUser selectByLoginname(String loginname);
    TUser selectByPhone(String phone);
    
    TUser selectByComId(int comId);
    
    public List<TUser> selectPageList(RowBounds rowBounds, TUser user);
    public Long selectPageCount(TUser user);
    public List<Map> selectUserCountByMouth();
    public int selectUserCount();
}