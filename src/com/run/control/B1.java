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

import com.run.b.Start;

/**
 * Servlet implementation class B1
 */
@WebServlet("/B1")
public class B1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public B1() {
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
		
		Start start = new Start();
		
		
		
		
		/////begin/////
		Properties prop = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("server.properties");
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
					start.startServer(value);	
				}
				if("0".equals(action)){//关闭
					start.stopServer(value);
				}
			}
		}		
		/////end/////
		
		
		doGet(request, response);
	}

}
