/*
 * Powered By [rapid-framework]
 * Since 2014 create by dq
 */

package com.nl.portal.vo;

import java.util.Date;

import com.nl.util.DateTime;

/**
 * @author dq
 * @version 1.0
 * @since 1.0
 */


public class PortalUserInfoVo {
	//bean properties START
	private Integer hrSno;
	private String userName;
	private Long msisdn;
	private Integer shortCode;
	private String email;
	private Date createTime;
	private Long createOperId;
	private Date changeTime;
	private Long changeOperId;
	private Integer isValid;
	private Date borthDate;
	private Integer sex;
	private Integer workType;
	private Integer sno;
	private String userId;
	//bean properties END

		
		/**
		 * 
		 * @Title: getHrSnoString
		 * @Description: getHrSnoString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getHrSno() {
			String value;
			if(this.hrSno == null){
				value = "";
			}else{
				value = this.hrSno.toString();
			}
			return value;
		}
		/**
		 * 
		 * @Title: setHrSnoString
		 * @Description: setHrSnoString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setHrSno(String value) {
			if(value == null || value.equals("")){
				this.hrSno = null;
			}else{
				this.hrSno = Integer.parseInt(value);
			}
		}
	
		/**
		 * 
		 * @Title: setHrSnoPhysicsType 
		 * @Description:  setHrSnoPhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setHrSno(Integer value) {
			this.hrSno = value;
		}
		
		/**
		 * 
		 * @Title: getHrSnoPhysicsType
		 * @Description: getHrSnoPhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Integer getHrSnoPhysicsType() {
			return this.hrSno;
		}
		
		
		/**
		 * 
		 * @Title: setUserName 
		 * @Description:  setUserName 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param value    
		 * @return void
		 */
		public void setUserName(String value) {
			this.userName = value;
		}
		
		/**
		 * 
		 * @Title: getUserName
		 * @Description: getUserName 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public String getUserName() {
			return this.userName;
		}
		
		/**
		 * 
		 * @Title: getMsisdnString
		 * @Description: getMsisdnString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getMsisdn() {
			String value;
			if(this.msisdn == null){
				value = "";
			}else{
				value = this.msisdn.toString();
			}
			return value;
		}
		/**
		 * 
		 * @Title: setMsisdnString
		 * @Description: setMsisdnString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setMsisdn(String value) {
			if(value == null || value.equals("")){
				this.msisdn = null;
			}else{
				this.msisdn = Long.parseLong(value);
			}
		}
	
		/**
		 * 
		 * @Title: setMsisdnPhysicsType 
		 * @Description:  setMsisdnPhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setMsisdn(Long value) {
			this.msisdn = value;
		}
		
		/**
		 * 
		 * @Title: getMsisdnPhysicsType
		 * @Description: getMsisdnPhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Long getMsisdnPhysicsType() {
			return this.msisdn;
		}
		
		
		/**
		 * 
		 * @Title: getShortCodeString
		 * @Description: getShortCodeString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getShortCode() {
			String value;
			if(this.shortCode == null){
				value = "";
			}else{
				value = this.shortCode.toString();
			}
			return value;
		}
		/**
		 * 
		 * @Title: setShortCodeString
		 * @Description: setShortCodeString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setShortCode(String value) {
			if(value == null || value.equals("")){
				this.shortCode = null;
			}else{
				this.shortCode = Integer.parseInt(value);
			}
		}
	
		/**
		 * 
		 * @Title: setShortCodePhysicsType 
		 * @Description:  setShortCodePhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setShortCode(Integer value) {
			this.shortCode = value;
		}
		
		/**
		 * 
		 * @Title: getShortCodePhysicsType
		 * @Description: getShortCodePhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Integer getShortCodePhysicsType() {
			return this.shortCode;
		}
		
		
		/**
		 * 
		 * @Title: setEmail 
		 * @Description:  setEmail 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param value    
		 * @return void
		 */
		public void setEmail(String value) {
			this.email = value;
		}
		
		/**
		 * 
		 * @Title: getEmail
		 * @Description: getEmail 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public String getEmail() {
			return this.email;
		}
		
		/**
		 * 
		 * @Title: getCreateTimeString
		 * @Description: getCreateTimeString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getCreateTime() {
			String value;
			if(this.createTime == null){
				value = "";
			}else{
				value = DateTime.format("yyyyMMdd", this.createTime);
			}
			return value;
		}
		/**
		 * 
		 * @Title: setCreateTimeString
		 * @Description: setCreateTimeString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setCreateTime(String value) {
			if(value == null || value.equals("")){
				this.createTime = null;
			}else{
				this.createTime = DateTime.getStandDate("yyyyMMdd", value);
			}
		}
	
		/**
		 * 
		 * @Title: setCreateTimePhysicsType 
		 * @Description:  setCreateTimePhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setCreateTime(Date value) {
			this.createTime = value;
		}
		
		/**
		 * 
		 * @Title: getCreateTimePhysicsType
		 * @Description: getCreateTimePhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Date getCreateTimePhysicsType() {
			return this.createTime;
		}
		
		
		/**
		 * 
		 * @Title: getCreateOperIdString
		 * @Description: getCreateOperIdString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getCreateOperId() {
			String value;
			if(this.createOperId == null){
				value = "";
			}else{
				value = this.createOperId.toString();
			}
			return value;
		}
		/**
		 * 
		 * @Title: setCreateOperIdString
		 * @Description: setCreateOperIdString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setCreateOperId(String value) {
			if(value == null || value.equals("")){
				this.createOperId = null;
			}else{
				this.createOperId = Long.parseLong(value);
			}
		}
	
		/**
		 * 
		 * @Title: setCreateOperIdPhysicsType 
		 * @Description:  setCreateOperIdPhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setCreateOperId(Long value) {
			this.createOperId = value;
		}
		
		/**
		 * 
		 * @Title: getCreateOperIdPhysicsType
		 * @Description: getCreateOperIdPhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Long getCreateOperIdPhysicsType() {
			return this.createOperId;
		}
		
		
		/**
		 * 
		 * @Title: getChangeTimeString
		 * @Description: getChangeTimeString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getChangeTime() {
			String value;
			if(this.changeTime == null){
				value = "";
			}else{
				value = DateTime.format("yyyyMMdd", this.changeTime);
			}
			return value;
		}
		/**
		 * 
		 * @Title: setChangeTimeString
		 * @Description: setChangeTimeString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setChangeTime(String value) {
			if(value == null || value.equals("")){
				this.changeTime = null;
			}else{
				this.changeTime = DateTime.getStandDate("yyyyMMdd", value);
			}
		}
	
		/**
		 * 
		 * @Title: setChangeTimePhysicsType 
		 * @Description:  setChangeTimePhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setChangeTime(Date value) {
			this.changeTime = value;
		}
		
		/**
		 * 
		 * @Title: getChangeTimePhysicsType
		 * @Description: getChangeTimePhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Date getChangeTimePhysicsType() {
			return this.changeTime;
		}
		
		
		/**
		 * 
		 * @Title: getChangeOperIdString
		 * @Description: getChangeOperIdString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getChangeOperId() {
			String value;
			if(this.changeOperId == null){
				value = "";
			}else{
				value = this.changeOperId.toString();
			}
			return value;
		}
		/**
		 * 
		 * @Title: setChangeOperIdString
		 * @Description: setChangeOperIdString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setChangeOperId(String value) {
			if(value == null || value.equals("")){
				this.changeOperId = null;
			}else{
				this.changeOperId = Long.parseLong(value);
			}
		}
	
		/**
		 * 
		 * @Title: setChangeOperIdPhysicsType 
		 * @Description:  setChangeOperIdPhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setChangeOperId(Long value) {
			this.changeOperId = value;
		}
		
		/**
		 * 
		 * @Title: getChangeOperIdPhysicsType
		 * @Description: getChangeOperIdPhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Long getChangeOperIdPhysicsType() {
			return this.changeOperId;
		}
		
		
		/**
		 * 
		 * @Title: getIsValidString
		 * @Description: getIsValidString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getIsValid() {
			String value;
			if(this.isValid == null){
				value = "";
			}else{
				value = this.isValid.toString();
			}
			return value;
		}
		/**
		 * 
		 * @Title: setIsValidString
		 * @Description: setIsValidString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setIsValid(String value) {
			if(value == null || value.equals("")){
				this.isValid = null;
			}else{
				this.isValid = Integer.parseInt(value);
			}
		}
	
		/**
		 * 
		 * @Title: setIsValidPhysicsType 
		 * @Description:  setIsValidPhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setIsValid(Integer value) {
			this.isValid = value;
		}
		
		/**
		 * 
		 * @Title: getIsValidPhysicsType
		 * @Description: getIsValidPhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Integer getIsValidPhysicsType() {
			return this.isValid;
		}
		
		
		/**
		 * 
		 * @Title: getBorthDateString
		 * @Description: getBorthDateString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getBorthDate() {
			String value;
			if(this.borthDate == null){
				value = "";
			}else{
				value = DateTime.format("yyyyMMdd", this.borthDate);
			}
			return value;
		}
		/**
		 * 
		 * @Title: setBorthDateString
		 * @Description: setBorthDateString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setBorthDate(String value) {
			if(value == null || value.equals("")){
				this.borthDate = null;
			}else{
				this.borthDate = DateTime.getStandDate("yyyyMMdd", value);
			}
		}
	
		/**
		 * 
		 * @Title: setBorthDatePhysicsType 
		 * @Description:  setBorthDatePhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setBorthDate(Date value) {
			this.borthDate = value;
		}
		
		/**
		 * 
		 * @Title: getBorthDatePhysicsType
		 * @Description: getBorthDatePhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Date getBorthDatePhysicsType() {
			return this.borthDate;
		}
		
		
		/**
		 * 
		 * @Title: getSexString
		 * @Description: getSexString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getSex() {
			String value;
			if(this.sex == null){
				value = "";
			}else{
				value = this.sex.toString();
			}
			return value;
		}
		/**
		 * 
		 * @Title: setSexString
		 * @Description: setSexString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setSex(String value) {
			if(value == null || value.equals("")){
				this.sex = null;
			}else{
				this.sex = Integer.parseInt(value);
			}
		}
	
		/**
		 * 
		 * @Title: setSexPhysicsType 
		 * @Description:  setSexPhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setSex(Integer value) {
			this.sex = value;
		}
		
		/**
		 * 
		 * @Title: getSexPhysicsType
		 * @Description: getSexPhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Integer getSexPhysicsType() {
			return this.sex;
		}
		
		
		/**
		 * 
		 * @Title: getWorkTypeString
		 * @Description: getWorkTypeString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getWorkType() {
			String value;
			if(this.workType == null){
				value = "";
			}else{
				value = this.workType.toString();
			}
			return value;
		}
		/**
		 * 
		 * @Title: setWorkTypeString
		 * @Description: setWorkTypeString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setWorkType(String value) {
			if(value == null || value.equals("")){
				this.workType = null;
			}else{
				this.workType = Integer.parseInt(value);
			}
		}
	
		/**
		 * 
		 * @Title: setWorkTypePhysicsType 
		 * @Description:  setWorkTypePhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setWorkType(Integer value) {
			this.workType = value;
		}
		
		/**
		 * 
		 * @Title: getWorkTypePhysicsType
		 * @Description: getWorkTypePhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Integer getWorkTypePhysicsType() {
			return this.workType;
		}
		
		
		public String getSno() {
			String value;
			if(this.sno == null){
				value = "";
			}else{
				value = this.sno.toString();
			}
			return value;
		}
		
		public Integer getSnoPhysicsType() {
			return sno;
		}
		public void setSno(Integer sno) {
			this.sno = sno;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}

		

}

