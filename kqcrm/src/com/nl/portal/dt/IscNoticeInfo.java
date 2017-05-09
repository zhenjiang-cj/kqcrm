/*
 * Powered By [rapid-framework]
 * Since 2014 create by dq
 */

package com.nl.portal.dt;

import java.util.Date;


/**
 * @author dq
 * @version 1.0
 * @since 1.0
 */


public class IscNoticeInfo implements java.io.Serializable {
	
	//columns START
    /**
     * 公告序列       db_column: NOTICE_ID 
     */
	private Long noticeId;
    /**
     * 公告标题       db_column: NOTICE_TITLE 
     */
	private String noticeTitle;
    /**
     * 公告内容       db_column: NOTICE_TEXT 
     */
	private String noticeText;
    /**
     * 推送方式,1为界面方式，2为短信方式       db_column: NOTICE_TYPE 
     */
	private Integer noticeType;
    /**
     * 推送开始时间       db_column: START_DATE 
     */
	private Date startDate;
    /**
     * 推送结束时间       db_column: END_DATE 
     */
	private Date endDate;
    /**
     * 发布人       db_column: PUBLISH_OPERATOR_ID 
     */
	private Integer publishOperatorId;
    /**
     * 发布时间       db_column: PUBLISH_DATE 
     */
	private Date publishDate;
    /**
     * 修改人       db_column: CHANGER_ID 
     */
	private Integer changerId;
    /**
     * 修改时间       db_column: CHANGE_DATE 
     */
	private Date changeDate;
	
    /**
     * 公告类型大类      db_column: NOTICE_INFO_BIG_TYPE 
     */
	private Integer noticeInfoBigType;
    /**
     * 公告类型小类      db_column: NOTICE_INFO_TYPE 
     */
	private Integer noticeInfoType;
    /**
     * 发送范围       db_column: PUBLISH_REGION 
     */
	private String publishRegion;
    /**
     * 发送岗位       db_column: PUBLISH_POST 
     */
	private String publishPost;
    /**
     * 文件序列       db_column: FILE_SEQ 
     */
	private Long fileSeq;
    /**
     * 是否短信通知       db_column: ISSMS 
     */
	private Integer issms;
    /**
     * 是否短信通知       db_column: ISSMS 
     */
	private Integer regionType;
	//columns END

	public IscNoticeInfo(){
	}

	public IscNoticeInfo(Long noticeId){
		this.noticeId = noticeId;
	}

	public void setNoticeId(Long value) {
		this.noticeId = value;
	}
	
	public Long getNoticeId() {
		return this.noticeId;
	}
	public void setNoticeTitle(String value) {
		this.noticeTitle = value;
	}
	
	public String getNoticeTitle() {
		return this.noticeTitle;
	}
	public void setNoticeText(String value) {
		this.noticeText = value;
	}
	
	public String getNoticeText() {
		return this.noticeText;
	}
	public void setNoticeType(Integer value) {
		this.noticeType = value;
	}
	
	public Integer getNoticeType() {
		return this.noticeType;
	}
	public void setStartDate(Date value) {
		this.startDate = value;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	public void setEndDate(Date value) {
		this.endDate = value;
	}
	
	public Date getEndDate() {
		return this.endDate;
	}
	public void setPublishOperatorId(Integer value) {
		this.publishOperatorId = value;
	}
	
	public Integer getPublishOperatorId() {
		return this.publishOperatorId;
	}
	public void setPublishDate(Date value) {
		this.publishDate = value;
	}
	
	public Date getPublishDate() {
		return this.publishDate;
	}
	public void setChangerId(Integer value) {
		this.changerId = value;
	}
	
	public Integer getChangerId() {
		return this.changerId;
	}
	public void setChangeDate(Date value) {
		this.changeDate = value;
	}
	
	public Date getChangeDate() {
		return this.changeDate;
	}
	public void setNoticeInfoType(Integer value) {
		this.noticeInfoType = value;
	}
	
	public Integer getNoticeInfoType() {
		return this.noticeInfoType;
	}
	public void setPublishRegion(String value) {
		this.publishRegion = value;
	}
	
	public String getPublishRegion() {
		return this.publishRegion;
	}
	public void setPublishPost(String value) {
		this.publishPost = value;
	}
	
	public String getPublishPost() {
		return this.publishPost;
	}
	public void setFileSeq(Long value) {
		this.fileSeq = value;
	}
	
	public Long getFileSeq() {
		return this.fileSeq;
	}
	public void setIssms(Integer value) {
		this.issms = value;
	}
	
	public Integer getIssms() {
		return this.issms;
	}

	public Integer getNoticeInfoBigType() {
		return noticeInfoBigType;
	}

	public void setNoticeInfoBigType(Integer noticeInfoBigType) {
		this.noticeInfoBigType = noticeInfoBigType;
	}

	public Integer getRegionType() {
		return regionType;
	}

	public void setRegionType(Integer regionType) {
		this.regionType = regionType;
	}

}

