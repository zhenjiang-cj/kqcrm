package com.nl.util;

public enum AppSystem {
	SYS_CUSTMGR_FLOW("13"),
	SYS_PORTAL("99");
	private String sysId = "";
	private AppSystem(String sysId){
		this.sysId = sysId;
	}
	@Override
	public String toString() {
		return this.sysId;
	}
}
