package com.nl.base;

import java.text.SimpleDateFormat;
import java.util.*;

public class WriteLog {
//	≈‰÷√≤Œ ˝
	private static boolean bDebugInfo = true;
	  
	public static void debugInfo(Object obj) 
	{
		Calendar rightNow = Calendar.getInstance();
		SimpleDateFormat df = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss z");
	    if (bDebugInfo)
	      System.out.println(df.format(rightNow.getTime()) + " " +obj.toString());
	}


	public static boolean getDebugFlag() {
		return bDebugInfo;
	}


	public static void setDebugFlag(boolean debugInfo) {
		bDebugInfo = debugInfo;
	}
}
