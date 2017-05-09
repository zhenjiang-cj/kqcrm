package com.nl.util;

/**
 * <p>Title: </p>
 * <p>Description: 用MD5算法对密码进行加密保存 </p>
 * <p>the format is table name_column name_constant name</p>
 * <p>Copyright: Copyright (c) 2004/10/18 </p>
 * <p>Company: TrustWay </p>
 * @author Lampard Lee
 * @version 1.0
 */

import java.security.MessageDigest;

public class OneWayHash
{

	public OneWayHash()
	{
	}

	public static String encrypt(String message)
		throws Exception
	{
		return encrypt(message, "MD5");
	}

	public static String encrypt(String message, String algorithm)
		throws Exception
	{
		if(message == null)
			throw new Exception("message is null.");
		if(!"MD5".equals(algorithm) && !"SHA-1".equals(algorithm))
			throw new Exception("algorithm must be MD5 or SHA-1.");
		byte buffer[] = message.getBytes();
		MessageDigest md = MessageDigest.getInstance(algorithm);
		md.reset();
		md.update(buffer);
		byte digest[] = md.digest();
		StringBuffer hexString = new StringBuffer();
		String sHexBit = null;
		for(int i = 0; i < digest.length; i++)
		{
			sHexBit = Integer.toHexString(0xff & digest[i]);
			if(sHexBit.length() == 1)
				sHexBit = "0".concat(String.valueOf(String.valueOf(sHexBit)));
			hexString.append(sHexBit);
		}

		return hexString.toString();
	}

	public static void main(String args[])
		throws Exception
	{
		String plainText = "汉字-1234567890-abcdefghijklmnopqrstuvwxyz-everythingelse";
		System.out.println("plain text: ".concat(String.valueOf(String.valueOf(plainText))));
		String cryptograph = encrypt(plainText);
		System.out.println("cryptograph: ".concat(String.valueOf(String.valueOf(cryptograph))));
	}
}
