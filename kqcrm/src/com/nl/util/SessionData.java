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
 * �޶�����    �޶���    �汾    ����
 * 
 */
public class SessionData 
{
    //��¼������Ϣ
	private  AdmUserFc admUser;
	
	//������Ϣ
	private  HtzjCodeBm htzjCodeBm;
	
	//��ҵ������Ϣ
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
