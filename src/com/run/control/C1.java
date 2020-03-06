package com.run.control;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.run.a.KillTomcat;
import com.run.a.Start;
import com.run.a.Status;
import com.run.a.Stop;
import com.run.util.CommandUtils;

/**
 * Servlet implementation class C1
 */
@WebServlet("/C1")
public class C1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public C1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		String action = request.getParameter("action");
		System.out.println(id);
		
		/////begin///// 开启
		Properties prop = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("tomcat.properties");
		prop.load(in);
		Set keyValue = prop.keySet();
		StringBuffer sb=new StringBuffer();
		for (Iterator it = keyValue.iterator(); it.hasNext();)
		{
			String key = (String) it.next();
			if(key.equals(id)){
				String value=prop.getProperty(key);
				//System.out.println(key+"-"+value);//配置文件值
				if("1".equals(action)){//开启
					String url=value+"\\bin\\startup.bat";
					open(url);	
					System.out.println(url);
				}
//				if("0".equals(action)){//关闭
//					String url=value+"\\bin\\shutdown.bat";
//					stop(url);
//				}
			}
		}		
		/////end/////
		/////begin///// 关闭
			Properties prop2 = new Properties();
			InputStream in2 = this.getClass().getClassLoader().getResourceAsStream("tomcatState.properties");
			prop2.load(in2);
			Set keyValue2 = prop2.keySet();
			StringBuffer sb2=new StringBuffer();
			for (Iterator it = keyValue2.iterator(); it.hasNext();)
			{
				String key = (String) it.next();
				if(key.equals(id)){
					String value=prop2.getProperty(key);
					int beginIndex = value.lastIndexOf(":");
					String v = value.substring(beginIndex+1);
					System.out.println(v);
					KillTomcat.killByPort(v);
					//System.out.println(key+"-"+value);//配置文件值
//					if("1".equals(action)){//开启
//						String url=value+"\\bin\\startup.bat";
//						open(url);	
//					}
//					if("0".equals(action)){//关闭
//						String url=value+"\\bin\\shutdown.bat";
//						stop(url);
//					}
				}
			}		
			/////end/////
	
		doGet(request, response);
	}
	/**
	 * 开启服务
	 */
	public void open(String url){
//		   String batName2 = "E:\\2.Server\\apache-tomcat-7.0.79-windows-x64\\apache-tomcat-7.0.79\\bin\\startup.bat";
//	        runbat(batName);
//	        Thread thread = new Thread(new Start(url));
//	        thread.setDaemon(true);
//	        thread.start();
	        try {
				CommandUtils.exeCommand(url);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
	}
	/**
	 * 关闭服务
	 */
	public void stop(String url){
//		   String batName2 = "E:\\2.Server\\apache-tomcat-7.0.79-windows-x64\\apache-tomcat-7.0.79\\bin\\startup.bat";
//	        runbat(batName);
//	        Thread thread = new Thread(new Stop(url));
//	        thread.setDaemon(true);
//	        thread.start();
        try {
			CommandUtils.exeCommand(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
