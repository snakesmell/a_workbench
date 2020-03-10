package com.run.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

public class KillTomcat {
	
	 public static Boolean killByPort(String port){
		 try {
			String back=execCMD("cmd /c netstat -ano | findstr \""+port+"\"");
			execCMD("cmd /c taskkill /f /pid "+back);
			return true;
		} catch (Exception e) {
			return false;
		} 
		 
	 }
	 public static String execCMD(String command) throws Exception{
//	        StringBuilder sb =new StringBuilder();
	            Process process=Runtime.getRuntime().exec(command);
	            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(process.getInputStream(),Charset.forName("GBK")));
	            String line;
	            while((line=bufferedReader.readLine())!=null)
	            {
	            	//System.out.println(line);
	            	int flag = line.indexOf("LISTENING");
	            	if(flag!=-1){
	            		String value = line.substring(flag);
	            		value=value.substring(9);
	            		value=value.trim();//端口号对应pid
	            		return value;
	            	}
	            }
	            return "";
	    }
	 
	 public static void main(String[] args) {
		// TODO Auto-generated method stub
		 try {
			Process process=Runtime.getRuntime().exec("E:\\3servertest\\apache-tomcat-7.0.40\\bin\\startup.bat");
			BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(process.getInputStream(),Charset.forName("GBK")));
			String line;
            while((line=bufferedReader.readLine())!=null)
            {
            	//System.out.println(line);
            	int flag = line.indexOf("LISTENING");
            	if(flag!=-1){
            		String value = line.substring(flag);
            		value=value.substring(9);
            		value=value.trim();//端口号对应pid
            	}
            }
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
