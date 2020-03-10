package com.run.util;

public class CallCmd {
	public native int intMethod(String str);
	public static void main(String[] args) {
		String x2 = System.getProperty("java.library.path");
		System.out.println(x2);
		System.loadLibrary("b1");
		CallCmd cal = new CallCmd();
		int x = cal.intMethod("");
		System.out.println("return:"+x);
	}
	
	public void begin(){
		String x2 = System.getProperty("java.library.path");
		System.out.println("PATH:"+x2);
		System.loadLibrary("b1");
		CallCmd cal = new CallCmd();
		int x = cal.intMethod("E:\\3servertest\\apache-tomcat-7.0.40-windows-x64\\apache-tomcat-7.0.40\\bin\\startup.bat");
		System.out.println(11111);
		System.out.println("RETURN:"+x);
	}
}
