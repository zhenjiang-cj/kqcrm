/*
 * Powered By [rapid-framework]
 * Since 2014 create by dq
 */

package com.nl.portal.dt;


/**
 * @author dq
 * @version 1.0
 * @since 1.0
 */


public class IscServerFile implements java.io.Serializable {
    /**
     * fileId       db_column: FILE_ID 
     */
	private Long fileId;
    /**
     * 公告编号，序列SEQ_WORKWAY_NOTICE_ID       db_column: NOTICE_ID 
     */
	private Long noticeId;
    /**
     * fileNumber       db_column: FILE_NUMBER 
     */
	private String fileName;
    /**
     * 附件存储名       db_column: FILE_SAVE_NAME 
     */
	private String fileSaveName;
    /**
     * filePath       db_column: FILE_PATH 
     */
	private String filePath;
    /**
     * status       db_column: STATUS 
     */
	private Integer createOperId;
    /**
     * changeTime       db_column: CHANGE_TIME 
     */
	//columns END

	public IscServerFile(){
	}

	public IscServerFile(Long fileId){
		this.fileId = fileId;
	}

	public void setFileId(Long value) {
		this.fileId = value;
	}
	
	public Long getFileId() {
		return this.fileId;
	}
	public void setNoticeId(Long value) {
		this.noticeId = value;
	}
	
	public Long getNoticeId() {
		return this.noticeId;
	}
	public void setFileName(String value) {
		this.fileName = value;
	}
	
	public String getFileName() {
		return this.fileName;
	}
	public void setFileSaveName(String value) {
		this.fileSaveName = value;
	}
	
	public String getFileSaveName() {
		return this.fileSaveName;
	}
	public void setFilePath(String value) {
		this.filePath = value;
	}
	
	public String getFilePath() {
		return this.filePath;
	}
	public void setCreateOperId(Integer value) {
		this.createOperId = value;
	}
	
	public Integer getCreateOperId() {
		return this.createOperId;
	}
}

