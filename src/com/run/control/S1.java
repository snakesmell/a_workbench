package com.run.control;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.run.a.Status;

/**
 * Servlet implementation class S1
 */
@WebServlet("/S1")
public class S1 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public S1() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		Properties prop = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("tomcatState.properties");
		prop.load(in);
		Set keyValue = prop.keySet();
		StringBuffer sb=new StringBuffer();
		for (Iterator it = keyValue.iterator(); it.hasNext();)
		{
			String key = (String) it.next();
			String value=prop.getProperty(key);
			//System.out.println(key+"-"+value);//配置文件值
			
			//String id = request.getParameter("id");
			//String url="http://localhost:8191";
			Status status = new Status();
			String result = status.state(value);
			//System.out.println(key+"-"+result);
			if(result==null){
				result="0";
			}else{
				result="1";
			}
			sb.append(key+"-"+result);
			sb.append("*");
		}		
		String v=sb.toString();
		v=v.substring(0, v.length()-1);
		response.getWriter().append(v);
	}

}
