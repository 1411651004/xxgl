package com.guochen.model;

import java.util.List;

public class TBuScope {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bu_scope.id
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bu_scope.node_name
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String nodeName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bu_scope.parent_id
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private Integer parentId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bu_scope.create_time
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bu_scope.update_time
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bu_scope.alternate_field1
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String alternateField1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_bu_scope.alternate_field2
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String alternateField2;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bu_scope.id
     *
     * @return the value of t_bu_scope.id
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bu_scope.id
     *
     * @param id the value for t_bu_scope.id
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bu_scope.node_name
     *
     * @return the value of t_bu_scope.node_name
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getNodeName() {
        return nodeName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bu_scope.node_name
     *
     * @param nodeName the value for t_bu_scope.node_name
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setNodeName(String nodeName) {
        this.nodeName = nodeName == null ? null : nodeName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bu_scope.parent_id
     *
     * @return the value of t_bu_scope.parent_id
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public Integer getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bu_scope.parent_id
     *
     * @param parentId the value for t_bu_scope.parent_id
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bu_scope.create_time
     *
     * @return the value of t_bu_scope.create_time
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bu_scope.create_time
     *
     * @param createTime the value for t_bu_scope.create_time
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bu_scope.update_time
     *
     * @return the value of t_bu_scope.update_time
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bu_scope.update_time
     *
     * @param updateTime the value for t_bu_scope.update_time
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bu_scope.alternate_field1
     *
     * @return the value of t_bu_scope.alternate_field1
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getAlternateField1() {
        return alternateField1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bu_scope.alternate_field1
     *
     * @param alternateField1 the value for t_bu_scope.alternate_field1
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setAlternateField1(String alternateField1) {
        this.alternateField1 = alternateField1 == null ? null : alternateField1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_bu_scope.alternate_field2
     *
     * @return the value of t_bu_scope.alternate_field2
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getAlternateField2() {
        return alternateField2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_bu_scope.alternate_field2
     *
     * @param alternateField2 the value for t_bu_scope.alternate_field2
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setAlternateField2(String alternateField2) {
        this.alternateField2 = alternateField2 == null ? null : alternateField2.trim();
    }
    private List<TBuScope> childrenList;

	public List<TBuScope> getChildrenList() {
		return childrenList;
	}

	public void setChildrenList(List<TBuScope> childrenList) {
		this.childrenList = childrenList;
	}

    private int isCheck;

	public int getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(int isCheck) {
		this.isCheck = isCheck;
	}
    
}