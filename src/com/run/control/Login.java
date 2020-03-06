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

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
//		String p1=request.getParameter("p1");//x轴像素比例
//		String p2=request.getParameter("p2");//y轴像素比例
		String html="";
		try {
			html+=this.getTomcat();
			html+=this.getServer();
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("roadPanel", "'"+html+"'");
		request.getRequestDispatcher("/a/a.jsp").forward(request,response);
		doGet(request, response);
	}
	
	public String getTomcat() throws Exception{
		Properties prop2 = new Properties();
		InputStream in2 = this.getClass().getClassLoader().getResourceAsStream("tomcatState.properties");
		prop2.load(in2);
		/////TOMCAT/////
		Properties prop = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("tomcatName.properties");
		prop.load(in);
		Set keyValue = prop.keySet();
		StringBuffer roadPanel=new StringBuffer();
		int i=1;
		for (Iterator it = keyValue.iterator(); it.hasNext();)
		{
			String key = (String) it.next();
			String value=prop.getProperty(key);
			String value2=prop2.getProperty(key);
			value=new String(value.getBytes("iso-8859-1"),"UTF-8");
			
			roadPanel.append("<tr>");
			roadPanel.append("<td>Tomcat</td>");
			roadPanel.append("<td>"+value+"-"+value2+"</td>");
			roadPanel.append("<td style=\"text-align: right;\">");
			roadPanel.append("<button class=\"btn btn-info\"  onclick=tomcat(\""+key+"\",1)>开启</button>");
			roadPanel.append("<button class=\"btn btn-warning\" onclick=tomcat(\""+key+"\",0)>关闭</button>");
			roadPanel.append("</td>");
			roadPanel.append("<td id=\""+key+"\"></td>");
			roadPanel.append("</tr>");
			
			i++;
		}		
		return roadPanel.toString();
		/////TOMCAT/////
	}
	public String getServer() throws Exception{
		/////SERVER/////
		Properties prop = new Properties();
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("server.properties");
		prop.load(in);
		Set keyValue = prop.keySet();
		StringBuffer roadPanel=new StringBuffer();
		int i=1;
		for (Iterator it = keyValue.iterator(); it.hasNext();)
		{
			String key = (String) it.next();
			String value=prop.getProperty(key);

			roadPanel.append("<tr>");
			roadPanel.append("<td>Server</td>");
			roadPanel.append("<td>"+value+"</td>");
			roadPanel.append("<td style=\"text-align: right;\">");
			roadPanel.append("<button class=\"btn btn-info\" onclick=server(\""+key+"\",1)>开启</button>");
			roadPanel.append("<button class=\"btn btn-warning\" onclick=server(\""+key+"\",0)>关闭</button>");
			roadPanel.append("</td>");
			roadPanel.append("<td id=\""+key+"\"></td>");
			roadPanel.append("</tr>");  	
			i++;
		}
		return roadPanel.toString();
		/////SERVER/////
	}

}
