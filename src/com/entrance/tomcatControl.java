package com.entrance;

import java.io.IOException;
import java.io.InputStream;
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
import com.run.util.CommandUtils;
import com.sqliteUtil.Common;

/**
 * Servlet implementation class tomcatControl
 */
@WebServlet("/tomcatControl")
public class tomcatControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tomcatControl() {
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
		String value = request.getParameter("value");
		value=value.replace("\\", "\\\\");
		String action = request.getParameter("action");
		String serverip = request.getParameter("serverip");
		/////begin///// 开启
		StringBuffer sb=new StringBuffer();
		if(Common.SERVER_START.equals(action)){//开启
			String url=value+"\\bin\\startup.bat";
			open(url);	
			System.out.println(url);
		}
		/////end/////
		/////begin///// 关闭
		if(Common.SERVER_STOP.equals(action)){//开启
			int beginIndex = serverip.lastIndexOf(":");
			String v = serverip.substring(beginIndex+1);
			System.out.println(v);
			KillTomcat.killByPort(v);
		}		
		/////end/////
	}
	/**
	 * 开启服务
	 */
	public void open(String url){
	        Thread thread = new Thread(new Start(url));
	        thread.setDaemon(true);
	        thread.start();
	}
	/**
	 * 关闭服务
	 */
	public void stop(String url){
        try {
			CommandUtils.exeCommand(url);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
