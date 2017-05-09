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


public class PortalDeptInfoVo {
	//bean properties START
	private Integer sno;
	private String deptName;
	private Integer deptCounty;
	private Date createTime;
	private Integer createOperId;
	private Date changeTime;
	private Integer changeOperId;
	private Integer isValid;
	//bean properties END

		
		/**
		 * 
		 * @Title: getSnoString
		 * @Description: getSnoString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getSno() {
			String value;
			if(this.sno == null){
				value = "";
			}else{
				value = this.sno.toString();
			}
			return value;
		}
		/**
		 * 
		 * @Title: setSnoString
		 * @Description: setSnoString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setSno(String value) {
			if(value == null || value.equals("")){
				this.sno = null;
			}else{
				this.sno = Integer.parseInt(value);
			}
		}
	
		/**
		 * 
		 * @Title: setSnoPhysicsType 
		 * @Description:  setSnoPhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setSno(Integer value) {
			this.sno = value;
		}
		
		/**
		 * 
		 * @Title: getSnoPhysicsType
		 * @Description: getSnoPhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Integer getSnoPhysicsType() {
			return this.sno;
		}
		
		
		/**
		 * 
		 * @Title: setDeptName 
		 * @Description:  setDeptName 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param value    
		 * @return void
		 */
		public void setDeptName(String value) {
			this.deptName = value;
		}
		
		/**
		 * 
		 * @Title: getDeptName
		 * @Description: getDeptName 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public String getDeptName() {
			return this.deptName;
		}
		
		/**
		 * 
		 * @Title: getDeptCountyString
		 * @Description: getDeptCountyString 
		 * @author dq   
		 * @version V1.0
		 * @return String
		 */
		public String getDeptCounty() {
			String value;
			if(this.deptCounty == null){
				value = "";
			}else{
				value = this.deptCounty.toString();
			}
			return value;
		}
		/**
		 * 
		 * @Title: setDeptCountyString
		 * @Description: setDeptCountyString 
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param String    
		 * @return void
		 */
		public void setDeptCounty(String value) {
			if(value == null || value.equals("")){
				this.deptCounty = null;
			}else{
				this.deptCounty = Integer.parseInt(value);
			}
		}
	
		/**
		 * 
		 * @Title: setDeptCountyPhysicsType 
		 * @Description:  setDeptCountyPhysicsType 
			 * @author dq   
			 * @date 2014-2-28 上午09:06:20 
			 * @version V1.0  
			 * @param value    
			 * @return void
			 */
		public void setDeptCounty(Integer value) {
			this.deptCounty = value;
		}
		
		/**
		 * 
		 * @Title: getDeptCountyPhysicsType
		 * @Description: getDeptCountyPhysicsType
		 * @author dq   
		 * @date 2014-2-28 上午09:06:20 
		 * @version V1.0  
		 * @param @param value    
		 * @return void   
		 * @throws
		 */
		public Integer getDeptCountyPhysicsType() {
			return this.deptCounty;
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
				this.createOperId = Integer.parseInt(value);
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
		public void setCreateOperId(Integer value) {
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
		public Integer getCreateOperIdPhysicsType() {
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
				this.changeOperId = Integer.parseInt(value);
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
		public void setChangeOperId(Integer value) {
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
		public Integer getChangeOperIdPhysicsType() {
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
		

}

