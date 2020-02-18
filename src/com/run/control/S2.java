package com.run.control;

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

import com.run.a.Status;

/**
 * Servlet implementation class S2
 */
@WebServlet("/S2")
public class S2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		String result="0";
//		String id = request.getParameter("id");
//		String str="Redis";
//		int flag = Status.callCmd().indexOf(str);
//		if(flag==-1){
//			result="0";
//		}else{
//			result="1";
//		}
//		response.getWriter().append(result);
		
		
		Properties prop = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("server.properties");
		prop.load(in);
		Set keyValue = prop.keySet();
		StringBuffer sb=new StringBuffer();
		for (Iterator it = keyValue.iterator(); it.hasNext();)
		{
			String key = (String) it.next();
			String value=prop.getProperty(key);
			//System.out.println(key+"-"+value);//配置文件值
			
			String result="0";
//			String str="Redis";
			int flag = Status.callCmd().indexOf(value);
			if(flag==-1){
				result="0";
			}else{
				result="1";
			}
			
			
//			Status status = new Status();
//			String result = status.state(value);
//			if(result==null){
//				result="0";
//			}else{
//				result="1";
//			}
			sb.append(key+"-"+result);
			sb.append("*");
		}		
		String v=sb.toString();
		v=v.substring(0, v.length()-1);
		response.getWriter().append(v);
	}

}
