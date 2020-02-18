package com.run.b;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class T1 {
	
	 public static void main(String[] args) {
		 	//String command = "taskkill /f /pid 13564"; 关闭可以
	        String command = "netstat -ano | findstr \"8080\"";
	        cmd(command);
	    }
	    public static boolean cmd(String command){
	        try{  
	        	Process process = Runtime.getRuntime().exec("cmd.exe /C start "+command);  
	        	
	            BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream()));
	            String readLine = br.readLine();
	            StringBuilder builder = new StringBuilder();
	            while (readLine != null) {
	                readLine = br.readLine();
	                System.out.println(readLine);
	                builder.append(readLine);
	            }
//	            System.out.println(readLine.toString());
	        }catch(Exception e){  
	            e.printStackTrace();  
	        } 
	        return false;
	    } 
	
	
	 public static String  callCmd(){
	    	String locationCmd="cmd.exe /C start netstat -ano | findstr \"8080\"";
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
}
