package com.guochen.dao;

import com.guochen.model.TNode;
import java.util.List;

public interface TNodeMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_node
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_node
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    int insert(TNode record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_node
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    TNode selectByPrimaryKey(Integer id);

    TNode selectStartNode();

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_node
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    List<TNode> selectAll(TNode node);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_node
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    int updateByPrimaryKey(TNode record);
    TNode selectByParentId(int id);
    TNode selectByFrontId(int id);
    public Long selectTotalCount();
    public String delexceptfirst();
}