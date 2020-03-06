package com.run.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.run.a.Start;

public class test {
	 public void runbat() {
		 String url="E:\\1.Server\\Release_1_apache-tomcat-7.0.40-windows-x64\\apache-tomcat-7.0.40\\bin\\startup.bat";
		 String url2="E:\\2.Server\\apache-tomcat-7.0.79-windows-x64\\apache-tomcat-7.0.79\\bin\\startup.bat";
		 String cmd = "cmd /c start "+url;
		 Thread thread = new Thread(new Start(cmd));
        thread.setDaemon(true);
        thread.start();
        
//        try {
//			Runtime.getRuntime().exec("cmd /c start "+url);
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	 }
	 
	 public static void main(String[] args) {
		 test  test1 = new test ();     
		 try {
			test1.runbat();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 //停止
	//如不能运行，请正确配置tomcat环境
	public void close() throws IOException
	{
		Process process = Runtime.getRuntime().exec("cmd /c D:\\apache-tomcat-7.0.16\\bin\\shutdown.bat"); // 调用外部程序
		final InputStream in = process.getInputStream();
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		StringBuilder buf = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null)
		buf.append(line);
	}
	//启动
	public void start() throws IOException
	{
		String url="E:\\1.Server\\Release_1_apache-tomcat-7.0.40-windows-x64\\apache-tomcat-7.0.40\\bin\\startup.bat";
		Process process = Runtime.getRuntime().exec("cmd /c "+url); // 调用外部程序
		final InputStream in = process.getInputStream();
		BufferedReader br=new BufferedReader(new InputStreamReader(in));
		StringBuilder buf = new StringBuilder();
		String line = null;
		while((line = br.readLine()) != null)
		buf.append(line);
	}
}
