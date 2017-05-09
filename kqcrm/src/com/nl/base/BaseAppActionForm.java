package com.nl.base;

import java.io.Serializable;

import org.apache.struts.action.ActionForm;
/**
*
* @author sanjing
* @creatdate 2011-08-31
*/
public class BaseAppActionForm extends ActionForm implements Serializable
{
	//����Ȩ�ޱ��
	private String functionId = "";
	//ϵͳ���
	private String systemId = "";
	//��ǰ����Ա����
	private String operatorId = "";
	//��������
	private String operationType="";
	//������־
	private int flag = 0;
	//��ǰҳ��
	private int currentPage = 1;
	//ÿҳ��ʾ������,Ĭ��ÿҳ��ʾ10�� 
	private int pageDisplayNum = 10;
	//��ʼ��¼��
	private int startNum = 0;
	//������¼��
	private int endNum = 10;
	
	//�Ƿ���Ҫ���� 0������Ҫ������1������Ҫ����������ָ�������νṹ����ڵ���ӽڵ� 
	private int is_recursive = 0; 
	//����Ҫ������ʱ�򣬸��ݴ��ֶ��ж�ȡĳһ������ 
	private int data_level = 1;
	//�·�
	private String month = "";
	
	//�Ƿ���֤
	private String is_check="0";
	
	//��ǰҳ��
	private int pageNum = 1;
	//ÿҳ��ʾ������,Ĭ��ÿҳ��ʾ20�� 
	private int numPerPage = 20;
	//��¼����
	private int totalCount;
	
	// �����ֶ�
    public String orderField;
    // ����˳��
    public String orderDirection;
	
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getNumPerPage() {
		return numPerPage;
	}
	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
	}
	public void setIs_check(String is_check) {
		this.is_check = is_check;
	}
	public String getIs_check() {
		return is_check;
	}
	public void setIsc_check(String is_check) {
		this.is_check = is_check;
	}
	public BaseAppActionForm()
	{
		super();
	}
	public String getFunctionId()
	{
		return functionId;
	}
	public void setFunctionId(String functionId)
	{
		this.functionId = functionId;
	}
	public String getSystemId()
	{
		return systemId;
	}
	public void setSystemId(String systemId)
	{
		this.systemId = systemId;
	}
	public int getCurrentPage()
	{
		return currentPage;
	}
	public void setCurrentPage(int currentPage)
	{
		this.currentPage = currentPage;
	}
	public int getStartNum()
	{
		return startNum;
	}
	public void setStartNum(int startNum)
	{
		this.startNum = startNum;
	}
	public int getEndNum()
	{
		return endNum;
	}
	public void setEndNum(int endNum)
	{
		this.endNum = endNum;
	}
	public String getOperatorId()
	{
		return operatorId;
	}
	public void setOperatorId(String operatorId)
	{
		this.operatorId = operatorId;
	}
	public int getFlag()
	{
		return flag;
	}
	public void setFlag(int flag)
	{
		this.flag = flag;
	}
	public String getOperationType()
	{
		return operationType;
	}
	public void setOperationType(String operationType)
	{
		this.operationType = operationType;
	}
	public int getPageDisplayNum()
	{
		return pageDisplayNum;
	}
	public void setPageDisplayNum(int pageDisplayNum)
	{
		this.pageDisplayNum = pageDisplayNum;
	}
	public void countNum()
	{
		this.startNum = (this.currentPage-1)*this.pageDisplayNum;
		this.endNum = this.currentPage*this.pageDisplayNum;
	}
	public int getIs_recursive() {
		return is_recursive;
	}
	public void setIs_recursive(int is_recursive) {
		this.is_recursive = is_recursive;
	}
	public int getData_level() {
		return data_level;
	}
	public void setData_level(int data_level) {
		this.data_level = data_level;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public String getOrderField() {
		return orderField;
	}
	public void setOrderField(String orderField) {
		this.orderField = orderField;
	}
	public String getOrderDirection() {
		return orderDirection;
	}
	public void setOrderDirection(String orderDirection) {
		this.orderDirection = orderDirection;
	}
	public int getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}
	
}
