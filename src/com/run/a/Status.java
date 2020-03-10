package com.run.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;

public class Status {
	 
	 public static void test1() throws IOException {
	        URL url = null;
	        InputStream is = null;
	        StringBuffer resultBuffer = new StringBuffer();
	        BufferedReader breader = null;
	        try {
	            url = new URL("http://admin:tomcat@localhost:8080/manager/status?XML=true");
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            is = conn.getInputStream();
	 
	            breader = new BufferedReader(new InputStreamReader(is));
	            String line = "";
	            while ((line = breader.readLine()) != null) {
	                resultBuffer.append(line);
	            }
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } finally {
	            if (breader != null)
	                breader.close();
	            if (is != null)
	                is.close();
	        }
	        System.out.println(resultBuffer.toString());
	}
	 
    public static String getHtmlContext(String tempurl, String username, String password) throws IOException {
        URL url = null;
        BufferedReader breader = null;
        InputStream is = null;
        StringBuffer resultBuffer = new StringBuffer();
        try {
            url = new URL(tempurl);
            String userPassword = username + ":" + password;
            String encoding = new sun.misc.BASE64Encoder().encode (userPassword.getBytes());
 
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Authorization", "Basic " + encoding);
            is = conn.getInputStream();
            breader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = breader.readLine()) != null) {
                resultBuffer.append(line);
            }
        } catch (MalformedURLException e) {
           // e.printStackTrace();
        } finally {
            if(breader != null)
                breader.close();
            if(is != null)
                is.close();
        }
        return resultBuffer.toString();
    }
    public static void test() {
        String result = "";
        Document document = null;
        try {
            result =  getHtmlContext("http://localhost:8080/manager/status?XML=true", "znjt", "1");
            document = DocumentHelper.parseText(result);
            System.out.println(result);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        System.out.println(document.asXML());
    }
    /**
     * 查看Tomcat服务运行状态
     * @param url
     */
    public String state(String url) {
        String result = null;
        Document document = null;
        try {
        	
        	Properties prop = new Properties();
    		InputStream in = this.getClass().getClassLoader().getResourceAsStream("tomcatAccount.properties");
    		prop.load(in);
    		String user=String.valueOf(prop.get("user"));
    		String password=String.valueOf(prop.get("password"));
            result =  getHtmlContext(url+"/manager/status?XML=true", user, password);
            //System.out.println(result);
            document = DocumentHelper.parseText(result);
            //System.out.println(result);
        } catch (Exception e) {
            //e.printStackTrace();
            return result;
        }
        //System.out.println(document.asXML());
        return result;
    }
    /**
     * cmd 命令
     * @param locationCmd
     */
    public static String  callCmd(){
    	String locationCmd="net start";
        StringBuilder sb = new StringBuilder();
        try {
            Process child = Runtime.getRuntime().exec(locationCmd);
            InputStream in = child.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
            String line;
            while((line=bufferedReader.readLine())!=null)
            {
                sb.append(line + "\n");
            }
               in.close();
            try {
                child.waitFor();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            return sb.toString();
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
     }
    
    public static void main(String[] args) {
		String sb = callCmd();
		System.out.println(sb);
		int flag = sb.indexOf("Windows Audio");
		System.out.println(flag);
	}
}
