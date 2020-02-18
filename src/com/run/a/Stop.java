package com.run.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Stop  implements Runnable{
	public Stop(String locationCmd) {
		// TODO Auto-generated constructor stub
		this.locationCmd=locationCmd;
	}
	String locationCmd;
	public String getLocationCmd() {
		return locationCmd;
	}
	public void setLocationCmd(String locationCmd) {
		this.locationCmd = locationCmd;
	}
	public static void runbat(String batName){
	        Runtime rt = Runtime.getRuntime();
	        Process ps = null;
	        try {
	            ps = rt.exec("cmd.exe /c start " + batName);
	            /*InputStream in = ps.getInputStream();
	            int c;
	            while ((c = in.read()) != -1) {
	                System.out.print(c);// ����㲻��Ҫ����������п���ע����
	            }
	            in.close();  */         
	        } catch (IOException e1) {
	            e1.printStackTrace();
	        }
	 
	    }
	 private static void  callCmd(String locationCmd){
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
	            System.out.println("sb:" + sb.toString());
	            System.out.println("callCmd execute finished");           
	        } catch (IOException e) {
	            System.out.println(e);
	        }
	     }
	 /**
	  * 启动项环境配置
	  * set "CATALINA_HOME=E:\2.Server\apache-tomcat-7.0.40-windows-x64_2\apache-tomcat-7.0.40"
	  * @param args
	  * @throws InterruptedException
	  */
    public static void main(String[] args) throws InterruptedException {
        String batName = "E:\\2.Server\\apache-tomcat-7.0.40-windows-x64_2\\apache-tomcat-7.0.40\\bin\\shutdown.bat";
        String batName2 = "E:\\2.Server\\apache-tomcat-7.0.79-windows-x64\\apache-tomcat-7.0.79\\bin\\shutdown.bat";
//	        runbat(batName);
        new Thread(new Start(batName)).start();
        new Thread(new Start(batName2)).start();
        /*callCmd(batName);
        callCmd(batName2);*/
        System.out.println("main thread");
    }
	@Override
	public void run() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
        try {
            Process child = Runtime.getRuntime().exec(locationCmd);
            InputStream in = child.getInputStream();
            BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(in));
            String line;
            /*while((line=bufferedReader.readLine())!=null)
            {
                sb.append(line + "\n");
            }*/
               in.close();
            try {
                child.waitFor();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            System.out.println("sb:" + sb.toString());
            System.out.println("callCmd execute finished");           
        } catch (IOException e) {
            System.out.println(e);
        }
	}
}
