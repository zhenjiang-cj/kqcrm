package com.nl.util;

import com.nl.portal.dt.AdmUserFc;
import com.nl.portal.dt.HtzjCodeBm;
import com.nl.portal.dt.KmCompanyUser;



/**
 * @Description: 
 * @author CJ
 * @version 1.0
 * Oct 16, 2016
 * -------------------------------------------
 * @History:
 * 修订日期    修订人    版本    描述
 * 
 */
public class SessionData 
{
    //登录工号信息
	private  AdmUserFc admUser;
	
	//部门信息
	private  HtzjCodeBm htzjCodeBm;
	
	//企业工号信息
	private KmCompanyUser companyUser;

	public AdmUserFc getAdmUser() {
		return admUser;
	}

	public void setAdmUser(AdmUserFc admUser) {
		this.admUser = admUser;
	}

	public HtzjCodeBm getHtzjCodeBm() {
		return htzjCodeBm;
	}

	public void setHtzjCodeBm(HtzjCodeBm htzjCodeBm) {
		this.htzjCodeBm = htzjCodeBm;
	}

	public KmCompanyUser getCompanyUser() {
		return companyUser;
	}

	public void setCompanyUser(KmCompanyUser companyUser) {
		this.companyUser = companyUser;
	}
	
	
}
