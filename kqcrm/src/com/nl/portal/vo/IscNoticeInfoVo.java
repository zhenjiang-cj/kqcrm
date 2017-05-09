/*
 * Powered By [rapid-framework]
 * Since 2014 create by dq
 */

package com.nl.portal.vo;

import java.util.Date;

import org.apache.struts.upload.FormFile;

import com.nl.util.DateTime;

/**
 * @author dq
 * @version 1.0
 * @since 1.0
 */

public class IscNoticeInfoVo {
	
	//bean properties START
	private Long noticeId;
	private String noticeTitle;
	private String noticeText;
	private Integer noticeType;
	private String noticeTypeText;
	private Date startDate;
	private Date endDate;
	private Integer publishOperatorId;
	private String publishOperatorName;
	private Integer loginOperId;//��¼��Ա���
	private String loginOperName;//��¼��Ա���
	private Date publishDate;
	private Integer noticeInfoBigType;
	private String noticeInfoBigTypeText;
	private Integer noticeInfoType;
	private String noticeInfoTypeText;
	private String publishRegion;
	private String publishRegionText;
	private String publishPost;
	private String publishPostText;
	private Long fileSeq;
	private String fileName;
	private String fileSaveName;
	private FormFile noticeFile;
	private Integer issms;
	private String issmsText;
	private Integer regionType;
	private boolean ifModify;
	//bean properties END

		
		/**
		 * 
		 * @Title: getNoticeIdString
		 * @Description: getNoticeIdString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getNoticeId() {
			String value;
			if(this.noticeId == null){
				value = "";
			}else{
				value = this.noticeId.toString();
			}
			return value;
		}
		/**
		 * 
		 * @Title: setNoticeIdString
		 * @Description: setNoticeIdString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setNoticeId(String value) {
			if(value == null || value.equals("")){
				this.noticeId = null;
			}else{
				this.noticeId = Long.parseLong(value);
			}
		}
	
		/**
		 * 
		 * @Title: setNoticeIdPhysicsType 
		 * @Description:  setNoticeIdPhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setNoticeId(Long value) {
			this.noticeId = value;
		}
		
		/**
		 * 
		 * @Title: getNoticeIdPhysicsType
		 * @Description: getNoticeIdPhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Long getNoticeIdPhysicsType() {
			return this.noticeId;
		}
		
		
		/**
		 * 
		 * @Title: setNoticeTitle 
		 * @Description:  setNoticeTitle 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param value    
		 * @return void
		 */
		public void setNoticeTitle(String value) {
			this.noticeTitle = value;
		}
		
		/**
		 * 
		 * @Title: getNoticeTitle
		 * @Description: getNoticeTitle 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public String getNoticeTitle() {
			return this.noticeTitle;
		}
		
		/**
		 * 
		 * @Title: setNoticeText 
		 * @Description:  setNoticeText 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param value    
		 * @return void
		 */
		public void setNoticeText(String value) {
			this.noticeText = value;
		}
		
		/**
		 * 
		 * @Title: getNoticeText
		 * @Description: getNoticeText 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public String getNoticeText() {
			return this.noticeText;
		}
		
		/**
		 * 
		 * @Title: getNoticeTypeString
		 * @Description: getNoticeTypeString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getNoticeType() {
			String value;
			if(this.noticeType == null){
				value = "";
			}else{
				value = String.valueOf(this.noticeType);
			}
			return value;
		}
		/**
		 * 
		 * @Title: setNoticeTypeString
		 * @Description: setNoticeTypeString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setNoticeType(String value) {
			if(value == null || value.equals("")){
				this.noticeType = null;
			}else{
				this.noticeType = Integer.parseInt(value);
			}
		}
	
		/**
		 * 
		 * @Title: setNoticeTypePhysicsType 
		 * @Description:  setNoticeTypePhysicsType 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param value    
		 * @return void
		 */
		public void setNoticeType(Integer value) {
			this.noticeType = value;
		}
		
		/**
		 * 
		 * @Title: getNoticeTypePhysicsType
		 * @Description: getNoticeTypePhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Integer getNoticeTypePhysicsType() {
			return this.noticeType;
		}
		
		
		/**
		 * 
		 * @Title: getStartDateString
		 * @Description: getStartDateString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getStartDate() {
			String value;
			if(this.startDate == null){
				value = "";
			}else{
				value = DateTime.format("yyyy-MM-dd", this.startDate);
			}
			return value;
		}
		/**
		 * 
		 * @Title: setStartDateString
		 * @Description: setStartDateString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setStartDate(String value) {
			if(value == null || value.equals("")){
				this.startDate = null;
			}else{
				this.startDate = DateTime.getStandDate("yyyy-MM-dd", value);
			}
		}
	
		/**
		 * 
		 * @Title: setStartDatePhysicsType 
		 * @Description:  setStartDatePhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setStartDate(Date value) {
			this.startDate = value;
		}
		
		/**
		 * 
		 * @Title: getStartDatePhysicsType
		 * @Description: getStartDatePhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Date getStartDatePhysicsType() {
			return this.startDate;
		}
		
		
		/**
		 * 
		 * @Title: getEndDateString
		 * @Description: getEndDateString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getEndDate() {
			String value;
			if(this.endDate == null){
				value = "";
			}else{
				value = DateTime.format("yyyy-MM-dd", this.endDate);
			}
			return value;
		}
		/**
		 * 
		 * @Title: setEndDateString
		 * @Description: setEndDateString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setEndDate(String value) {
			if(value == null || value.equals("")){
				this.endDate = null;
			}else{
				this.endDate = DateTime.getStandDate("yyyy-MM-dd", value);
			}
		}
	
		/**
		 * 
		 * @Title: setEndDatePhysicsType 
		 * @Description:  setEndDatePhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setEndDate(Date value) {
			this.endDate = value;
		}
		
		/**
		 * 
		 * @Title: getEndDatePhysicsType
		 * @Description: getEndDatePhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Date getEndDatePhysicsType() {
			return this.endDate;
		}
		
		
		/**
		 * 
		 * @Title: getPublishOperatorIdString
		 * @Description: getPublishOperatorIdString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getPublishOperatorId() {
			String value;
			if(this.publishOperatorId == null){
				value = "";
			}else{
				value = this.publishOperatorId.toString();
			}
			return value;
		}
		/**
		 * 
		 * @Title: setPublishOperatorIdString
		 * @Description: setPublishOperatorIdString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setPublishOperatorId(String value) {
			if(value == null || value.equals("")){
				this.publishOperatorId = null;
			}else{
				this.publishOperatorId = Integer.parseInt(value);
			}
		}
	
		/**
		 * 
		 * @Title: setPublishOperatorIdPhysicsType 
		 * @Description:  setPublishOperatorIdPhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setPublishOperatorId(Integer value) {
			this.publishOperatorId = value;
		}
		
		/**
		 * 
		 * @Title: getPublishOperatorIdPhysicsType
		 * @Description: getPublishOperatorIdPhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Integer getPublishOperatorIdPhysicsType() {
			return this.publishOperatorId;
		}
		
		/**
		 * 
		 * @Title: getPublishOperatorIdString
		 * @Description: getPublishOperatorIdString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public Integer getLoginOperId() {
			return this.loginOperId;
		}
		/**
		 * 
		 * @Title: setPublishOperatorIdString
		 * @Description: setPublishOperatorIdString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setLoginOperId(String value) {
			if(value == null || value.equals("")){
				this.loginOperId = null;
			}else{
				this.loginOperId = Integer.parseInt(value);
			}
		}
	
		/**
		 * 
		 * @Title: setPublishOperatorIdPhysicsType 
		 * @Description:  setPublishOperatorIdPhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setLoginOperId(Integer value) {
			this.loginOperId = value;
		}
		
		
		/**
		 * 
		 * @Title: getPublishDateString
		 * @Description: getPublishDateString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getPublishDate() {
			String value;
			if(this.publishDate == null){
				value = "";
			}else{
				value = DateTime.format("yyyy-MM-dd", this.publishDate);
			}
			return value;
		}
		/**
		 * 
		 * @Title: setPublishDateString
		 * @Description: setPublishDateString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setPublishDate(String value) {
			if(value == null || value.equals("")){
				this.publishDate = null;
			}else{
				this.publishDate = DateTime.getStandDate("yyyy-MM-dd", value);
			}
		}
	
		/**
		 * 
		 * @Title: setPublishDatePhysicsType 
		 * @Description:  setPublishDatePhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setPublishDate(Date value) {
			this.publishDate = value;
		}
		
		/**
		 * 
		 * @Title: getPublishDatePhysicsType
		 * @Description: getPublishDatePhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Date getPublishDatePhysicsType() {
			return this.publishDate;
		}
		
		/**
		 * 
		 * @Title: getNoticeInfoTypeString
		 * @Description: getNoticeInfoTypeString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getNoticeInfoType() {
			String value;
			if(this.noticeInfoType == null){
				value = "";
			}else{
				value = this.noticeInfoType.toString();
			}
			return value;
		}
		/**
		 * 
		 * @Title: setNoticeInfoTypeString
		 * @Description: setNoticeInfoTypeString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setNoticeInfoType(String value) {
			if(value == null || value.equals("")){
				this.noticeInfoType = null;
			}else{
				this.noticeInfoType = Integer.parseInt(value);
			}
		}
	
		/**
		 * 
		 * @Title: setNoticeInfoTypePhysicsType 
		 * @Description:  setNoticeInfoTypePhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setNoticeInfoType(Integer value) {
			this.noticeInfoType = value;
		}
		
		/**
		 * 
		 * @Title: getNoticeInfoTypePhysicsType
		 * @Description: getNoticeInfoTypePhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Integer getNoticeInfoTypePhysicsType() {
			return this.noticeInfoType;
		}
		
		
		/**
		 * 
		 * @Title: setPublishRegion 
		 * @Description:  setPublishRegion 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param value    
		 * @return void
		 */
		public void setPublishRegion(String value) {
			this.publishRegion = value;
		}
		
		/**
		 * 
		 * @Title: getPublishRegion
		 * @Description: getPublishRegion 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public String getPublishRegion() {
			return this.publishRegion;
		}
		
		/**
		 * 
		 * @Title: setPublishPost 
		 * @Description:  setPublishPost 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param value    
		 * @return void
		 */
		public void setPublishPost(String value) {
			this.publishPost = value;
		}
		
		/**
		 * 
		 * @Title: getPublishPost
		 * @Description: getPublishPost 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public String getPublishPost() {
			return this.publishPost;
		}
		
		/**
		 * 
		 * @Title: getFileSeqString
		 * @Description: getFileSeqString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getFileSeq() {
			String value;
			if(this.fileSeq == null){
				value = "";
			}else{
				value = this.fileSeq.toString();
			}
			return value;
		}
		/**
		 * 
		 * @Title: setFileSeqString
		 * @Description: setFileSeqString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setFileSeq(String value) {
			if(value == null || value.equals("")){
				this.fileSeq = null;
			}else{
				this.fileSeq = Long.parseLong(value);
			}
		}
	
		/**
		 * 
		 * @Title: setFileSeqPhysicsType 
		 * @Description:  setFileSeqPhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setFileSeq(Long value) {
			this.fileSeq = value;
		}
		
		/**
		 * 
		 * @Title: getFileSeqPhysicsType
		 * @Description: getFileSeqPhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Long getFileSeqPhysicsType() {
			return this.fileSeq;
		}
		
		
		/**
		 * 
		 * @Title: getIssmsString
		 * @Description: getIssmsString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getIssms() {
			String value;
			if(this.issms == null){
				value = "";
			}else{
				value = this.issms.toString();
			}
			return value;
		}
		/**
		 * 
		 * @Title: setIssmsString
		 * @Description: setIssmsString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setIssms(String value) {
			if(value == null || value.equals("")){
				this.issms = null;
			}else{
				this.issms = Integer.parseInt(value);
			}
		}
	
		/**
		 * 
		 * @Title: setIssmsPhysicsType 
		 * @Description:  setIssmsPhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setIssms(Integer value) {
			this.issms = value;
		}
		
		/**
		 * 
		 * @Title: getIssmsPhysicsType
		 * @Description: getIssmsPhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Integer getIssmsPhysicsType() {
			return this.issms;
		}
		public FormFile getNoticeFile() {
			return noticeFile;
		}
		public void setNoticeFile(FormFile noticeFile) {
			this.noticeFile = noticeFile;
		}
		public String getLoginOperName() {
			return loginOperName;
		}
		public void setLoginOperName(String loginOperName) {
			this.loginOperName = loginOperName;
		}
		public String getNoticeTypeText() {
			return noticeTypeText;
		}
		public void setNoticeTypeText(String noticeTypeText) {
			this.noticeTypeText = noticeTypeText;
		}
		public String getPublishOperatorName() {
			return publishOperatorName;
		}
		public void setPublishOperatorName(String publishOperatorName) {
			this.publishOperatorName = publishOperatorName;
		}
		public String getNoticeInfoTypeText() {
			return noticeInfoTypeText;
		}
		public void setNoticeInfoTypeText(String noticeInfoTypeText) {
			this.noticeInfoTypeText = noticeInfoTypeText;
		}
		public String getPublishRegionText() {
			return publishRegionText;
		}
		public void setPublishRegionText(String publishRegionText) {
			this.publishRegionText = publishRegionText;
		}
		public String getPublishPostText() {
			return publishPostText;
		}
		public void setPublishPostText(String publishPostText) {
			this.publishPostText = publishPostText;
		}
		public String getIssmsText() {
			return issmsText;
		}
		public void setIssmsText(String issmsText) {
			this.issmsText = issmsText;
		}
		public String getNoticeInfoBigType() {
			String value;
			if(this.noticeInfoBigType == null){
				value = "";
			}else{
				value = this.noticeInfoBigType.toString();
			}
			return value;
		}
		
		public void setNoticeInfoBigType(String value) {
			if(value == null || value.equals("")){
				this.noticeInfoBigType = null;
			}else{
				this.noticeInfoBigType = Integer.parseInt(value);
			}
		}
		
		public Integer getNoticeInfoBigTypePhysicsType() {
			return this.noticeInfoBigType;
		}
		
		public void setNoticeInfoBigType(Integer noticeInfoBigType) {
			this.noticeInfoBigType = noticeInfoBigType;
		}
		
		public String getNoticeInfoBigTypeText() {
			return noticeInfoBigTypeText;
		}
		
		public void setNoticeInfoBigTypeText(String noticeInfoBigTypeText) {
			this.noticeInfoBigTypeText = noticeInfoBigTypeText;
		}
		
		public String getFileName() {
			return fileName;
		}
		
		public void setFileName(String fileName) {
			this.fileName = fileName;
		}
		
		public String getFileSaveName() {
			return fileSaveName;
		}
		
		public void setFileSaveName(String fileSaveName) {
			this.fileSaveName = fileSaveName;
		}
		public boolean getIfModify() {
			return ifModify;
		}
		public void setIfModify(boolean ifModify) {
			this.ifModify = ifModify;
		}
		public Integer getRegionTypePhysicsType() {
			return regionType;
		}
		public String getRegionType() {
			String value;
			if(this.regionType == null){
				value = "";
			}else{
				value = this.regionType.toString();
			}
			return value;
		}
		public void setRegionType(Integer regionType) {
			this.regionType = regionType;
		}
		public void setRegionType(String value) {
			if(value == null || value.equals("")){
				this.regionType = null;
			}else{
				this.regionType = Integer.parseInt(value);
			}
		}

}

