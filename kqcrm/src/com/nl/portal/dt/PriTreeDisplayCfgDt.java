package com.nl.portal.dt;
/**
 * Ȩ����չ������
 * @author daihb
 * @creatdate Jan 22, 2013
 */
public class PriTreeDisplayCfgDt {
    // �����
	private int treeId;
	// ��Լ���
	private int treeLevel;
	// ʵ�ַ�ʽ
	private int type;
	// չ����������
	private int displayDesc;
	// չ�ַ�ʽ
	private int displayType;
	// �Ƿ���Ч
	private int isValid;
	// ������Ա
	private int oper;
	// ����ʱ��
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
