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
	//功能权限编号
	private String functionId = "";
	//系统编号
	private String systemId = "";
	//当前操作员工号
	private String operatorId = "";
	//操作类型
	private String operationType="";
	//操作标志
	private int flag = 0;
	//当前页码
	private int currentPage = 1;
	//每页显示的条数,默认每页显示10条 
	private int pageDisplayNum = 10;
	//开始记录数
	private int startNum = 0;
	//结束记录数
	private int endNum = 10;
	
	//是否需要迭代 0代表不需要迭代，1代表需要迭代；迭代指迭代树形结构传入节点的子节点 
	private int is_recursive = 0; 
	//不需要迭代的时候，根据此字段判断取某一层数据 
	private int data_level = 1;
	//月份
	private String month = "";
	
	//是否验证
	private String is_check="0";
	
	//当前页码
	private int pageNum = 1;
	//每页显示的条数,默认每页显示20条 
	private int numPerPage = 20;
	//记录总数
	private int totalCount;
	
	// 排序字段
    public String orderField;
    // 排序顺序
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
