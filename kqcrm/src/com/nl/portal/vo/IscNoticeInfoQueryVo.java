/*
 * Powered By [rapid-framework]
 * Since 2014 create by dq
 */

package com.nl.portal.vo;

import java.util.Date;

import org.apache.commons.lang.StringUtils;

import com.nl.util.DateTime;
import com.nl.util.GlobalConst;
import com.nl.util.PageRequest;

/**
 * @author dq
 * @version 1.0
 * @since 1.0
 */


public class IscNoticeInfoQueryVo extends PageRequest{

	public static String NOTICE_ID = "NOTICE_ID";
	public static String NOTICE_TITLE = "NOTICE_TITLE";
	public static String NOTICE_TEXT = "NOTICE_TEXT";
	public static String NOTICE_TYPE = "NOTICE_TYPE";
	public static String START_DATE = "START_DATE";
	public static String END_DATE = "END_DATE";
	public static String PUBLISH_OPERATOR_ID = "PUBLISH_OPERATOR_ID";
	public static String PUBLISH_DATE = "PUBLISH_DATE";
	public static String CHANGER_ID = "CHANGER_ID";
	public static String CHANGE_DATE = "CHANGE_DATE";
	public static String NOTICE_INFO_TYPE = "NOTICE_INFO_TYPE";
	public static String PUBLISH_REGION = "PUBLISH_REGION";
	public static String PUBLISH_POST = "PUBLISH_POST";
	public static String FILE_SEQ = "FILE_SEQ";
	public static String ISSMS = "ISSMS";
	
	private String noticeTitle;
	private Integer noticeType;
	private Date publishDateBegin;
	private Date publishDateEnd;
	private Integer loginOperId;
	private Integer loginHrSno;
	private boolean isManage;
	private Integer noticeInfoBigType;
	private Integer noticeInfoType;
	
	/*******在推送时间内*******/
	private Date startDateEnd;//推送开始时间（结束）
	private Date endDateBegin;//推送结束时间（开始）
	
	private boolean queryMy;//
	private boolean queryAll;//

	public String getNoticeTitle() {
		return this.noticeTitle;
	}
	
	public void setNoticeTitle(String value) {
		this.noticeTitle = value;
	}
	
	
	public Integer getNoticeType() {
		return this.noticeType;
	}

	public void setNoticeType(String value) {
		if (StringUtils.isEmpty(value)
				|| value.equals(GlobalConst.GLOBLA_SELECT_VALUE)) {
			this.noticeType = null;
		}else {
			this.noticeType = Integer.parseInt(value);
		}
	}
	
	public void setNoticeType(Integer value) {
		this.noticeType = value;
	}
	
	public Date getPublishDateBegin() {
		return this.publishDateBegin;
	}
	
	public void setPublishDateBegin(Date value) {
		this.publishDateBegin = value;
	}	

	public void setPublishDateBegin(String value) {
		if(StringUtils.isEmpty(value)){
			this.publishDateBegin = null;
		}else{
			this.publishDateBegin = DateTime.getStandDate("yyyy-MM-dd", value);
		}
	}
	
	public Date getPublishDateEnd() {
		return this.publishDateEnd;
	}
	
	public void setPublishDateEnd(String value) {
		if(StringUtils.isEmpty(value)){
			this.publishDateEnd = null;
		}else{
			this.publishDateEnd = DateTime.addDate(DateTime.getStandDate("yyyy-MM-dd", value), 24) ;
		}
	}

	public void setPublishDateEnd(Date value) {
		this.publishDateEnd = value;
	}
	

	public Date getStartDateEnd() {
		return this.startDateEnd;
	}
	
	public void setStartDateEnd(Date value) {
		this.startDateEnd = DateTime.getStandDate("yyyyMMdd", DateTime.format("yyyyMMdd", value));
	}	

	public void setStartDateEnd(String value) {
		if(StringUtils.isEmpty(value)){
			this.startDateEnd = null;
		}else{
			this.startDateEnd = DateTime.getStandDate("yyyy-MM-dd", value);
		}
	}
	
	public Date getEndDateBegin() {
		return this.endDateBegin;
	}
	
	public void setEndDateBegin(String value) {
		if(StringUtils.isEmpty(value)){
			this.endDateBegin = null;
		}else{
			this.endDateBegin = DateTime.getStandDate("yyyy-MM-dd", value);
		}
	}

	public void setEndDateBegin(Date value) {
		this.endDateBegin = DateTime.getStandDate("yyyyMMdd", DateTime.format("yyyyMMdd", value));
	}
	 
	
	/************公告类型大类************/
	public Integer getNoticeInfoBigType() {
		return noticeInfoBigType;
	}

	public void setNoticeInfoBigType(Integer noticeInfoBigType) {
		this.noticeInfoBigType = noticeInfoBigType;
	}

	public void setNoticeInfoBigType(String value) {
		if (StringUtils.isEmpty(value)
				|| value.equals(GlobalConst.GLOBLA_SELECT_VALUE)) {
			this.noticeInfoBigType = null;
		}else {
			this.noticeInfoBigType = Integer.parseInt(value);
		}
	}

	/********公告类型小类*********/
	public Integer getNoticeInfoType() {
		return this.noticeInfoType;
	}

	public void setNoticeInfoType(String value) {
		if (StringUtils.isEmpty(value)
				|| value.equals(GlobalConst.GLOBLA_SELECT_VALUE)) {
			this.noticeInfoType = null;
		}else {
			this.noticeInfoType = Integer.parseInt(value);
		}
	}
	
	public void setNoticeInfoType(Integer value) {
		this.noticeInfoType = value;
	}

	public Integer getLoginOperId() {
		return loginOperId;
	}

	public void setLoginOperId(Integer loginOperId) {
		this.loginOperId = loginOperId;
	}

	public void setLoginOperId(String value) {
		if (StringUtils.isEmpty(value)) {
			this.loginOperId = null;
		}else {
			this.loginOperId = Integer.parseInt(value);
		}
	}

	public boolean getIsManage() {
		return isManage;
	}

	public void setManage(boolean isManage) {
		this.isManage = isManage;
	}

	public void queryMy(int operId, int loginHrSno) {
		this.loginHrSno = loginHrSno;
		this.loginOperId = operId;
		this.queryMy = true;
	}

	public void queryAll() {
		this.queryAll = true;
	}

	public boolean getQueryMy() {
		return queryMy;
	}

	public boolean getQueryAll() {
		return queryAll;
	}

	public Integer getLoginHrSno() {
		return loginHrSno;
	}

}

