package com.nl.portal.dt;

import java.io.Serializable;

/**
 * 
 * Copyright (c) 2011,�´�½����������޹�˾ All rights reserved�� �ļ����ƣ�SysBossCode.java
 * ������ϵͳ����������Ϣʵ����
 * 
 * @author xupeng
 * @date 2011-9-23
 * @version �޸ļ�¼
 */
public class SysBossCode implements Serializable
{
	/* Ա������*/
	private String sno_user;
	/* BOSS_CODE */
	private String boss_code;
	
	
	public String getSno_user()
	{
		return sno_user;
	}
	public void setSno_user(String sno_user)
	{
		this.sno_user = sno_user;
	}
	public String getBoss_code()
	{
		return boss_code;
	}
	public void setBoss_code(String boss_code)
	{
		this.boss_code = boss_code;
	}
}
