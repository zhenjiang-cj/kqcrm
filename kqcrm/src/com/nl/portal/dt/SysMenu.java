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
public class SysMenu implements Serializable
{
	
	private int page_id;
	private String type;
	private String name;
	private String webaddr;
	private String parent_id;
	private String page_desc;
	private String alias;
	private int sysid;
	
	//add by cj 20140925  菜单路径
	private String menu_path;
	
	public String getMenu_path() {
		return menu_path;
	}
	public void setMenu_path(String menu_path) {
		this.menu_path = menu_path;
	}
	public String getPage_desc() {
		return page_desc;
	}
	public void setPage_desc(String page_desc) {
		this.page_desc = page_desc;
	}
	public int getPage_id()
	{
		return page_id;
	}
	public void setPage_id(int page_id)
	{
		this.page_id = page_id;
	}
	public String getType()
	{
		return type;
	}
	public void setType(String type)
	{
		this.type = type;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getWebaddr()
	{
		return webaddr;
	}
	public void setWebaddr(String webaddr)
	{
		this.webaddr = webaddr;
	}
	public String getParent_id()
	{
		return parent_id;
	}
	public void setParent_id(String parent_id)
	{
		this.parent_id = parent_id;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public int getSysid() {
		return sysid;
	}
	public void setSysid(int sysid) {
		this.sysid = sysid;
	}
}
