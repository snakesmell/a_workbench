package com.run.test;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class a2 {
		public static void callCommand(String command) throws Exception{
			Runtime runtime = Runtime.getRuntime();//返回与当前的Java应用相关的运行时对象
			Process process = runtime.exec(command);
//			runtime.gc();//运行垃圾回收器
			String line = null;
			String content = "";
			BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
			while((line = br.readLine()) != null) {
				content += line + "\r\n";
			}
		}
		public static void main(String[] args) throws Exception {
			String command = "E:\\1.Server\\Release_4_apache-tomcat-7.0.79-windows-x64\\apache-tomcat-7.0.79\\bin\\startup.bat";//关闭tomcat命令
			callCommand(command);	
		}
		
	}		
