package com.run.test;

import java.io.IOException;
import java.io.InputStream;

public class RuntimeTest {

	public void runbat(String batName){
		Runtime rt = Runtime.getRuntime();
		Process ps = null;
		try {
			ps = rt.exec("cmd.exe /c start " + batName);
			InputStream in = ps.getInputStream();
            int c;
            while ((c = in.read()) != -1) {
                System.out.print(c);// 如果你不需要看输出，这行可以注销掉
            }
            in.close();			
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	public static void main(String[] args) throws InterruptedException {
		RuntimeTest test1 = new RuntimeTest();
		String batName = "E:\\1.Server\\Release_4_apache-tomcat-7.0.79-windows-x64\\apache-tomcat-7.0.79\\bin\\startup.bat";
//		String url2="E:\\2.Server\\apache-tomcat-7.0.79-windows-x64\\apache-tomcat-7.0.79\\bin\\startup.bat";
		test1.runbat(batName);
		System.out.println("main thread");
	}
}
