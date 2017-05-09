package com.nl.portal.dt;

import java.io.Serializable;

/**
 * 
 * Copyright (c) 2011,新大陆软件工程有限公司 All rights reserved。 文件名称：SysBossCode.java
 * 描述：系统操作工号信息实体类
 * 
 * @author xupeng
 * @date 2011-9-23
 * @version 修改记录
 */
public class SysBossCode implements Serializable
{
	/* 员工编码*/
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
