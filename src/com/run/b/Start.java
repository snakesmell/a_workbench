package com.run.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Start {
	public static void runbat(String batName){
	        Runtime rt = Runtime.getRuntime();
	        Process ps = null;
	        try {
/*	        	String[] script = {"cmd.exe", "/c", "sc", "query", APP_SERVICE_NAME, "|", "find", "/C", "\"RUNNING\""};//to check whether service is running or not
	        	String[] script = {"cmd.exe", "/c", "sc", "start", SERVICE_NAME};//to start service
	        	String[] script = {"cmd.exe", "/c", "sc", "stop", SERVICE_NAME};//to stop service
	        	
*/	        	/////////////////////////////////Redis服务运行状态，开启，关闭/////////////////////////////
//	        	String[] script = {"cmd.exe", "/c", "sc", "query", "Redis", "|", "find", "/C", "\"RUNNING\""};//to check whether service is running or not
	        	String[] script = {"cmd.exe", "/c", "sc", "start", "Redis"};//to start service
//	        	String[] script = {"cmd.exe", "/c", "sc", "stop", "Redis"};//to stop service
	        	
	        	ps = rt.exec(script);
//	            ps = rt.exec("cmd.exe /c start " + batName);
/*	            InputStream in = ps.getInputStream();
	            int c;
	            while ((c = in.read()) != -1) {
	                System.out.print(c);// ����㲻��Ҫ����������п���ע����
	            }
	            in.close();  */         
	        } catch (Exception e1) {
	            e1.printStackTrace();
	        }
	 
	    }
	/**
	 * 开启服务
	 */
	public void startServer(String name){
		Runtime rt = Runtime.getRuntime();
        Process ps = null;
        try {
        	String[] script = {"cmd.exe", "/c", "sc", "start", name};//to start service
        	ps = rt.exec(script);
//            ps = rt.exec("cmd.exe /c start " + batName);
/*	            InputStream in = ps.getInputStream();
            int c;
            while ((c = in.read()) != -1) {
                System.out.print(c);// ����㲻��Ҫ����������п���ע����
            }
            in.close();  */         
        } catch (Exception e1) {
            e1.printStackTrace();
        }
	}
	/**
	 * 关闭服务
	 */
	public void stopServer(String name){
		Runtime rt = Runtime.getRuntime();
        Process ps = null;
        try {
        	String[] script = {"cmd.exe", "/c", "sc", "stop", name};//to stop service
        	ps = rt.exec(script);
/*	            InputStream in = ps.getInputStream();
            int c;
            while ((c = in.read()) != -1) {
                System.out.print(c);// ����㲻��Ҫ����������п���ע����
            }
            in.close();  */         
        } catch (Exception e1) {
            e1.printStackTrace();
        }
	}
	 /**
	  * 启动项环境配置
	  * set "CATALINA_HOME=E:\2.Server\apache-tomcat-7.0.40-windows-x64_2\apache-tomcat-7.0.40"
	  * @param args
	  * @throws InterruptedException
	  */
	    public static void main(String[] args) throws InterruptedException {
	    	
//	        String batName = "E:\\2.Server\\apache-tomcat-7.0.40-windows-x64_2\\apache-tomcat-7.0.40\\bin\\startup.bat";
//	        String batName2 = "E:\\2.Server\\apache-tomcat-7.0.79-windows-x64\\apache-tomcat-7.0.79\\bin\\startup.bat";
//	        runbat(batName);
//	        new Thread(new Start(batName)).start();
//	        new Thread(new Start(batName2)).start();
	        /*callCmd(batName);
	        callCmd(batName2);*/
	    	runbat("");
	        System.out.println("main thread");
	    }
	
}
