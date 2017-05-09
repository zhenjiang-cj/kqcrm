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
     * ��������       db_column: NOTICE_ID 
     */
	private Long noticeId;
    /**
     * �������       db_column: NOTICE_TITLE 
     */
	private String noticeTitle;
    /**
     * ��������       db_column: NOTICE_TEXT 
     */
	private String noticeText;
    /**
     * ���ͷ�ʽ,1Ϊ���淽ʽ��2Ϊ���ŷ�ʽ       db_column: NOTICE_TYPE 
     */
	private Integer noticeType;
    /**
     * ���Ϳ�ʼʱ��       db_column: START_DATE 
     */
	private Date startDate;
    /**
     * ���ͽ���ʱ��       db_column: END_DATE 
     */
	private Date endDate;
    /**
     * ������       db_column: PUBLISH_OPERATOR_ID 
     */
	private Integer publishOperatorId;
    /**
     * ����ʱ��       db_column: PUBLISH_DATE 
     */
	private Date publishDate;
    /**
     * �޸���       db_column: CHANGER_ID 
     */
	private Integer changerId;
    /**
     * �޸�ʱ��       db_column: CHANGE_DATE 
     */
	private Date changeDate;
	
    /**
     * �������ʹ���      db_column: NOTICE_INFO_BIG_TYPE 
     */
	private Integer noticeInfoBigType;
    /**
     * ��������С��      db_column: NOTICE_INFO_TYPE 
     */
	private Integer noticeInfoType;
    /**
     * ���ͷ�Χ       db_column: PUBLISH_REGION 
     */
	private String publishRegion;
    /**
     * ���͸�λ       db_column: PUBLISH_POST 
     */
	private String publishPost;
    /**
     * �ļ�����       db_column: FILE_SEQ 
     */
	private Long fileSeq;
    /**
     * �Ƿ����֪ͨ       db_column: ISSMS 
     */
	private Integer issms;
    /**
     * �Ƿ����֪ͨ       db_column: ISSMS 
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

