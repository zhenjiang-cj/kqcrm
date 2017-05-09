package com.nl.util;

/**
 * <p>Title:加密和解密公共函数 </p>
 * <p>Description: 加密和解密公共函数</p>
 * <p>Copyright: Copyright (c) 2007</p>
 * <p>Company: Fujian Newland Software Engineer Ltd Corp.</p>
 * @author Lampard Lee
 * @date Mar 25th,2007
 * @version 1.0
 */

/*
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
*/

public class CryptTool{
	
	/*
	private String Algorithm = "DES";
    private KeyGenerator keygen;
    private SecretKey deskey;
    private Cipher c;
    private byte[] cipherByte;
    */
    /**
     * 初始化 DES 实例
     */
	/*
    public DesTool() {
          init();
    }
    */

	/*
    public void init() {
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
        try {
               keygen = KeyGenerator.getInstance(Algorithm);
               deskey = keygen.generateKey();
               c = Cipher.getInstance(Algorithm);
         }
         catch(NoSuchAlgorithmException ex){
            ex.printStackTrace();
        }
         catch(NoSuchPaddingException ex){
            ex.printStackTrace();
        }
       }
    */

    /**
     * 对 String 进行加密
     * @param str 要加密的数据
     * @return 返回加密后的 byte 数组
     */
	/*
     public byte[] createEncryptor(String str) {
        try {
             c.init(Cipher.ENCRYPT_MODE, deskey);
             cipherByte = c.doFinal(str.getBytes());
        }
        catch(java.security.InvalidKeyException ex){
            ex.printStackTrace();
        }
        catch(javax.crypto.BadPaddingException ex){
            ex.printStackTrace();
        }
        catch(javax.crypto.IllegalBlockSizeException ex){
            ex.printStackTrace();
        }
        return cipherByte;
     }
    */
    /**
     * 对 Byte 数组进行解密
     * @param buff 要解密的数据
     * @return 返回加密后的 String
     */
	/*
     public String createDecryptor(byte[] buff) {
        try {
           c.init(Cipher.DECRYPT_MODE, deskey);
           cipherByte = c.doFinal(buff);
        }
        catch(java.security.InvalidKeyException ex){
            ex.printStackTrace();
        }
        catch(javax.crypto.BadPaddingException ex){
            ex.printStackTrace();
        }
        catch(javax.crypto.IllegalBlockSizeException ex){
            ex.printStackTrace();
        }
        return (new String(cipherByte));
     }
    */
	
     /**
      * 使用异或进行简单的密码加密
      * @return <code>String[]</code> 加密后字符串
      * @author Administrator
      * @since 1.0 2005/11/28
      */
     public static String setXOREncrypt(String str){
         String sn="ziyu"; //密钥
         int[] snNum=new int[str.length()];
         String result="";
         String temp="";

         for(int i=0,j=0;i<str.length();i++,j++){
             if(j==sn.length())
                 j=0;
             snNum[i]=str.charAt(i)^sn.charAt(j);
         }

         for(int k=0;k<str.length();k++){

             if(snNum[k]<10){
                 temp="00"+snNum[k];
             }else{
                 if(snNum[k]<100){
                     temp="0"+snNum[k];
                 }
             }
             result+=temp;
         }
         return result;
     }

     /**
      * 密码解密,虽然用不到
      * @return <code>String[]</code> 加密后字符串
      * @author Administrator
      * @since 1.0 2005/11/28
      */
     public static String getXOREncrypt(String str){
         String sn="ziyu"; //密钥
         char[] snNum=new char[str.length()/3];
         String result="";

         for(int i=0,j=0;i<str.length()/3;i++,j++){
             if(j==sn.length())
                 j=0;
             int n=Integer.parseInt(str.substring(i*3,i*3+3));
             snNum[i]=(char)((char)n^sn.charAt(j));
         }

         for(int k=0;k<str.length()/3;k++){
             result+=snNum[k];
         }
         return result;
     }
}
