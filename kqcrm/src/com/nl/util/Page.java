package com.nl.util;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;

public class Page {
	public final static int PAGESIZE = 15;
	public static int PAGESIZE_10 = 10;
    private int pageSize = PAGESIZE;
    private List items;
    private int totalCount = 0;
    private int startIndex = -99;
    private int endIndex = -99;
    private int curPageNo=1;
    private String url="";
    private String jsonItems;
    
	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Page(int curPageNo) {   
        setPageSize(PAGESIZE);
        setCurPageNo(curPageNo);
    }
	
	public Page(int curPageNo, int pageSize) {   
        setPageSize(pageSize);
        setCurPageNo(curPageNo);
    }

	public List getItems() {   
        return items;   
    }   
  
    public void setItems(List items) {   
        this.items = items;   
    }   
  
    public int getPageSize() {   
        return pageSize;   
    }   
  
    public void setPageSize(int pageSize) {   
        this.pageSize = pageSize;   
    }   
  
    public int getTotalCount() {   
        return totalCount;   
    }   
  
    public void setTotalCount(int totalCount) {   
        this.totalCount = totalCount;
    }   
  
    public int getStartIndex() {
    	if (startIndex != -99) {
			return this.startIndex;
		}
    	if (pageSize > 0 && curPageNo > 0) {
    		this.startIndex = (curPageNo - 1) * pageSize + 1;
		}else{
	    	this.startIndex = 0;
		}
        return this.startIndex;
    }

    public void setStartIndex(int startIndex) {
    	this.startIndex = startIndex;
    }
    
    public int getEndIndex() {
    	if (endIndex != -99) {
			return this.endIndex;
		}
    	if (pageSize > 0 && curPageNo > 0) {
    		this.endIndex = curPageNo * pageSize;
		}else{
	    	this.endIndex = 0;
		}
		return this.endIndex;
    }

    public void setEndIndex(int endIndex) {
    	this.endIndex = endIndex;
    }

	public int getCurPageNo() {
		return curPageNo;
	}

	public void setCurPageNo(int curPageNo) {
		this.curPageNo = curPageNo;
	}
	
	public boolean isEmpty(){
		if (this.items == null || this.items.size() == 0) {
			return true;
		}else{
			return false;
		}
	}

	public String getJsonItems() {
		if (StringUtils.isEmpty(this.jsonItems)) {
			if (isEmpty()) {
				this.jsonItems = "[]";
			} else {
				this.jsonItems =  JSONArray.fromObject(this.items).toString();
			}
		}
		return this.jsonItems;
	}
	
}

