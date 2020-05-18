package com.entrance;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.sqliteUtil.Common;

/**
 * Servlet implementation class requestAction
 */
@WebServlet("/tomcatServer")
public class tomcatServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public tomcatServer() {
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
		String port=request.getParameter("port");
		String result=ConnectionTest(port);
		response.getWriter().append(result);
	}
	/**
	 * Invalid
	 * @param port
	 * @return
	 */
	public static String PortTest(int port) {
        try {
			Process p = Runtime.getRuntime().exec("cmd.exe /c netstat -ano|findstr "+port);
			//Process p = Runtime.getRuntime().exec("javac");
			InputStream is = p.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuilder sb=new StringBuilder();
			while((line = reader.readLine())!= null){
				sb.append(line);
			}
			p.waitFor();
			is.close();
			reader.close();
			p.destroy();
			//System.out.println(sb.toString());
			return sb.toString();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}     
	}
	
	public static String ConnectionTest(String port){
		try {
			 CloseableHttpClient httpclient = HttpClients.createDefault(); 
			 HttpPost httppost = new HttpPost("http://"+port); 
			//建立一个NameValuePair数组，用于存储欲传送的参数
			//设置编码
			 HttpResponse res= httpclient.execute(httppost);
			 String responseEntityStr = EntityUtils.toString(res.getEntity());
			 System.out.println(responseEntityStr);
			 return Common.SERVER_START;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			 return Common.SERVER_STOP;
		}
	}
	
}
