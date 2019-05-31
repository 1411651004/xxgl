package com.guochen.dao;

import com.guochen.model.TOffer;
import com.guochen.model.TProject;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface TOfferMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_offer
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_offer
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    int insert(TOffer record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_offer
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    TOffer selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_offer
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    List<TOffer> selectAll(TOffer offer);
    List<TOffer> selectPageList(RowBounds rowBounds, TOffer offer);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table t_offer
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    int updateByPrimaryKey(TOffer record);
    
    public Long selectPageCount(TOffer record);
    public List<Map> selectOfferCountByMouth();
    public int selectOfferCount();
    public TOffer selectByProIdAndUserId(@Param("proId")int proId, @Param("userId")int userId);
}