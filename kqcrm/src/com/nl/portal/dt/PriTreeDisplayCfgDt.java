package com.nl.portal.dt;
/**
 * 权限树展现配置
 * @author daihb
 * @creatdate Jan 22, 2013
 */
public class PriTreeDisplayCfgDt {
    // 树编号
	private int treeId;
	// 针对级别
	private int treeLevel;
	// 实现方式
	private int type;
	// 展现文字描述
	private int displayDesc;
	// 展现方式
	private int displayType;
	// 是否有效
	private int isValid;
	// 操作人员
	private int oper;
	// 操作时间
	private String operTime;
	public int getTreeLevel() {
		return treeLevel;
	}
	public void setTreeLevel(int treeLevel) {
		this.treeLevel = treeLevel;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getDisplayDesc() {
		return displayDesc;
	}
	public void setDisplayDesc(int displayDesc) {
		this.displayDesc = displayDesc;
	}
	public int getTreeId() {
		return treeId;
	}
	public void setTreeId(int treeId) {
		this.treeId = treeId;
	}
	public int getDisplayType() {
		return displayType;
	}
	public void setDisplayType(int displayType) {
		this.displayType = displayType;
	}
	public int getIsValid() {
		return isValid;
	}
	public void setIsValid(int isValid) {
		this.isValid = isValid;
	}
	public int getOper() {
		return oper;
	}
	public void setOper(int oper) {
		this.oper = oper;
	}
	public String getOperTime() {
		return operTime;
	}
	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}
	
}
