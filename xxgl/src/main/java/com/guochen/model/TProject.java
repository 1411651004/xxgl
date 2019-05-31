package com.guochen.model;

import java.util.Date;

import com.guochen.utils.CommonUtils;

public class TProject {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.id
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private Integer id;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.pro_code
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String proCode;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.pro_name
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String proName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.file_id
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String fileId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.con_name
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String conName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.con_phone
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String conPhone;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.pro_starttime
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String proStarttime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.pro_endtime
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String proEndtime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.se_me
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String seMe;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.pro_profile
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String proProfile;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.bi_an
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String biAn;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.pro_status
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String proStatus;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.offer_num
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String offerNum;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.buyer_id
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private Integer buyerId;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.create_time
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String createTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.update_time
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String updateTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.alternate_field1
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String alternateField1;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column t_project.alternate_field2
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    private String alternateField2;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.id
     *
     * @return the value of t_project.id
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public Integer getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.id
     *
     * @param id the value for t_project.id
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.pro_code
     *
     * @return the value of t_project.pro_code
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getProCode() {
        return proCode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.pro_code
     *
     * @param proCode the value for t_project.pro_code
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setProCode(String proCode) {
        this.proCode = proCode == null ? null : proCode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.pro_name
     *
     * @return the value of t_project.pro_name
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getProName() {
        return proName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.pro_name
     *
     * @param proName the value for t_project.pro_name
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setProName(String proName) {
        this.proName = proName == null ? null : proName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.file_id
     *
     * @return the value of t_project.file_id
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getFileId() {
        return fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.file_id
     *
     * @param fileId the value for t_project.file_id
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.con_name
     *
     * @return the value of t_project.con_name
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getConName() {
        return conName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.con_name
     *
     * @param conName the value for t_project.con_name
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setConName(String conName) {
        this.conName = conName == null ? null : conName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.con_phone
     *
     * @return the value of t_project.con_phone
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getConPhone() {
        return conPhone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.con_phone
     *
     * @param conPhone the value for t_project.con_phone
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setConPhone(String conPhone) {
        this.conPhone = conPhone == null ? null : conPhone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.pro_starttime
     *
     * @return the value of t_project.pro_starttime
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getProStarttime() {
        return proStarttime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.pro_starttime
     *
     * @param proStarttime the value for t_project.pro_starttime
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setProStarttime(String proStarttime) {
        this.proStarttime = proStarttime == null ? null : proStarttime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.pro_endtime
     *
     * @return the value of t_project.pro_endtime
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getProEndtime() {
        return proEndtime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.pro_endtime
     *
     * @param proEndtime the value for t_project.pro_endtime
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setProEndtime(String proEndtime) {
        this.proEndtime = proEndtime == null ? null : proEndtime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.se_me
     *
     * @return the value of t_project.se_me
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getSeMe() {
        return seMe;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.se_me
     *
     * @param seMe the value for t_project.se_me
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setSeMe(String seMe) {
        this.seMe = seMe == null ? null : seMe.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.pro_profile
     *
     * @return the value of t_project.pro_profile
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getProProfile() {
        return proProfile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.pro_profile
     *
     * @param proProfile the value for t_project.pro_profile
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setProProfile(String proProfile) {
        this.proProfile = proProfile == null ? null : proProfile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.bi_an
     *
     * @return the value of t_project.bi_an
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getBiAn() {
        return biAn;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.bi_an
     *
     * @param biAn the value for t_project.bi_an
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setBiAn(String biAn) {
        this.biAn = biAn == null ? null : biAn.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.pro_status
     *
     * @return the value of t_project.pro_status
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getProStatus() {
        return proStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.pro_status
     *
     * @param proStatus the value for t_project.pro_status
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setProStatus(String proStatus) {
        this.proStatus = proStatus == null ? null : proStatus.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.offer_num
     *
     * @return the value of t_project.offer_num
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getOfferNum() {
        return offerNum;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.offer_num
     *
     * @param offerNum the value for t_project.offer_num
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setOfferNum(String offerNum) {
        this.offerNum = offerNum == null ? null : offerNum.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.buyer_id
     *
     * @return the value of t_project.buyer_id
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public Integer getBuyerId() {
        return buyerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.buyer_id
     *
     * @param buyerId the value for t_project.buyer_id
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setBuyerId(Integer buyerId) {
        this.buyerId = buyerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.create_time
     *
     * @return the value of t_project.create_time
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.create_time
     *
     * @param createTime the value for t_project.create_time
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setCreateTime(String createTime) {
        this.createTime = createTime == null ? null : createTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.update_time
     *
     * @return the value of t_project.update_time
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.update_time
     *
     * @param updateTime the value for t_project.update_time
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime == null ? null : updateTime.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.alternate_field1
     *
     * @return the value of t_project.alternate_field1
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getAlternateField1() {
        return alternateField1;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.alternate_field1
     *
     * @param alternateField1 the value for t_project.alternate_field1
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setAlternateField1(String alternateField1) {
        this.alternateField1 = alternateField1 == null ? null : alternateField1.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column t_project.alternate_field2
     *
     * @return the value of t_project.alternate_field2
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public String getAlternateField2() {
        return alternateField2;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column t_project.alternate_field2
     *
     * @param alternateField2 the value for t_project.alternate_field2
     *
     * @mbg.generated Mon Dec 26 15:46:15 CST 2016
     */
    public void setAlternateField2(String alternateField2) {
        this.alternateField2 = alternateField2 == null ? null : alternateField2.trim();
    }
    
    private String queryDate1;//查询时间1
    private String queryDate2;//查询时间2

	public String getQueryDate1() {
		return queryDate1;
	}

	public void setQueryDate1(String queryDate1) {
		this.queryDate1 = queryDate1;
	}

	public String getQueryDate2() {
		return queryDate2;
	}

	public void setQueryDate2(String queryDate2) {
		this.queryDate2 = queryDate2;
	}
    
	private String isBeforeEnd;
	private String projectNo;

	public String getIsBeforeEnd() {
		if(isBeforeEnd==null || "".equals(isBeforeEnd)){
			if(proEndtime!=null && !"".equals(proEndtime) && proEndtime.length()==19){
				return CommonUtils.dateCompare(CommonUtils.date2Str(new Date()),proEndtime)+"";
			}
		}else{
			return isBeforeEnd;
		}
		return "";
	}

	public void setIsBeforeEnd(String isBeforeEnd) {
		this.isBeforeEnd = isBeforeEnd;
	}
	
	private TBuyer buyer;

	public TBuyer getBuyer() {
		return buyer;
	}

	public void setBuyer(TBuyer buyer) {
		this.buyer = buyer;
	}
	
	private Integer userId;//用户ID，用户查询

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getProjectNo() {
		return projectNo;
	}

	public void setProjectNo(String projectNo) {
		this.projectNo = projectNo;
	}
	
}