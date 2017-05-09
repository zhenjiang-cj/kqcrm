package com.nl.base;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import org.apache.log4j.Logger;


public class HttpURLConnHelper{
	public static final Logger log = Logger.getLogger(HttpURLConnHelper.class);
    private static int CONNECTTIMEOUT = 5000; // ���������ĳ�ʱʱ�䣨��λ�����룩
    private static int READTIMEOUT = 5000; // ��������ȡ���ݵĳ�ʱʱ�䣨��λ�����룩

    /**
     * @description ����Http����
     * @author 
     * @return
     * @throws IOException
     */
    public static String execute(String serverUrl, String message) throws IOException
    {
        java.net.URL connURL = new java.net.URL(serverUrl);
        HttpURLConnection httpCon = (HttpURLConnection) connURL.openConnection();

        // ����http�����ͷ��
        httpCon.setUseCaches(false);// Post ������ʹ�û���
        httpCon.setDoOutput(true); // http�����ڣ������Ҫ��Ϊtrue, Ĭ���������false;
        httpCon.setDoInput(true); // �����Ƿ��httpUrlConnection���룬Ĭ���������true;

        // �趨���͵����������ǿ����л���java����
        httpCon.setRequestProperty("Content-type", "text/xml; charset=UTF-8");
        httpCon.setRequestMethod("POST"); // �趨����ķ���Ϊ"POST"��Ĭ����GET
        httpCon.setConnectTimeout(CONNECTTIMEOUT); // ���������ĳ�ʱʱ�䣨��λ�����룩
        httpCon.setReadTimeout(READTIMEOUT); // ��������ȡ���ݵĳ�ʱʱ�䣨��λ�����룩

        // д��http���������
        DataOutputStream dataOutputStream = new DataOutputStream(httpCon.getOutputStream());
        dataOutputStream.write(message.getBytes("UTF-8"));
        dataOutputStream.flush();
        dataOutputStream.close();
        int responseCode = httpCon.getResponseCode();
        if(responseCode == HttpURLConnection.HTTP_OK) // ��������ȷ
        {
            // ���ڴ滺�����з�װ�õ�������HTTP������ķ��͵�����ˡ�
            BufferedReader bufReader = new BufferedReader(new InputStreamReader(httpCon.getInputStream(), "UTF-8"));
            StringBuffer sb = new StringBuffer();
            String line = "";
            while((line = bufReader.readLine()) != null)
            {
                sb.append(line);
            }
            bufReader.close();
            return sb.toString();
        }
        else    // ������������磺404
        {
        	log.debug("����Http�������쳣��������Ϊ��" + responseCode);
            return "";
        }
    }

}
