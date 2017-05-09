package com.nl.util;


public class PageRequest {

	private Page page;
	private boolean isPage = false;
	private StringBuffer sortColumns = new StringBuffer();
	
	/**
	 * 
	 * @Title: queryForPage 
	 * @Description: 仅调用此方法会 触发分页搜索
	 * @author dq   
	 * @date 2014-5-29 上午09:42:25 
	 * @version V1.0  
	 * @param @param page    
	 * @return void   
	 * @throws
	 */
	public void queryForPage(Page page){
		this.isPage = true;
		this.page = page;
	}
	
	public String getSortColumns() {
		return this.sortColumns.toString();
	}

	private void checkSortColumnsSqlInjection(String sortColumns) {
		if (sortColumns == null)
			return;
		if ((sortColumns.indexOf("'") >= 0) || (sortColumns.indexOf("\\") >= 0))
			throw new IllegalArgumentException("sortColumns:" + sortColumns
					+ " has SQL Injection risk");
	}
	
	public PageRequest desc(String columnName){
		sortColumns.append(" " + columnName + " desc ");
		return this;
	}
	
	public PageRequest asc(String columnName){
		sortColumns.append(" " + columnName + " asc ");
		return this;
	}
	
	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public boolean getIsPage() {
		return isPage;
	}

	public void setIsPage(boolean isPage) {
		this.isPage = isPage;
	}
}