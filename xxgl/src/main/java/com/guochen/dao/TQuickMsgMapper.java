package com.guochen.dao;

import java.util.List;

import org.apache.ibatis.session.RowBounds;

import com.guochen.model.TCode;
import com.guochen.model.TQuickMsg;

public interface TQuickMsgMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_quick_msg
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_quick_msg
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    int insert(TQuickMsg record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_quick_msg
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    TQuickMsg selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_quick_msg
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    List<TQuickMsg> selectAll();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_quick_msg
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    int updateByPrimaryKey(TQuickMsg record);
    public List<TQuickMsg> selectPageList(RowBounds rowBounds, TQuickMsg record);
    public Long selectPageCount(TQuickMsg record);
}