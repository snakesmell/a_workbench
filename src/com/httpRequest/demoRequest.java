package com.httpRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.sqliteUtil.Common;
import com.sqliteUtil.SQLiteJDBC;

/**
 * Servlet implementation class demoRequest
 */
@WebServlet("/demoRequest")
public class demoRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public demoRequest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//////////////////////////////LEFT PANEL//////////////////////////////////////////	
		String html=(String)request.getServletContext().getAttribute(Common.ContextWindow);
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(html);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//////////////////////////////MAIN PANEL//////////////////////////////////////////
		ServletContext context = request.getServletContext();
		StringBuilder sb=new StringBuilder();
		List<Map> list = (List<Map>)context.getAttribute(Common.Context);
		if(list==null)return;
		for (Map map : list) {
			String remark=String.valueOf(map.get(Common.REMARK));
			String value=String.valueOf(map.get(Common.VALUE));
			String name=String.valueOf(map.get(Common.NAME));
			String id=String.valueOf(map.get(Common.ID));
			String fwip=String.valueOf(map.get(Common.FWIP));
			String serverip=String.valueOf(map.get(Common.SERVERIP));
			String status=String.valueOf(context.getAttribute(id));
			sb.append("<tr>");
			
			sb.append("<td>"+remark+"</td>");
			sb.append("<td>"+name+"-"+serverip+"</td>");
			
			sb.append("<td><label class=\"btn btn-info\" onclick=\"request('"+id+"',0)\">开启</label>");
			sb.append("<label class=\"btn btn-warning\" onclick=\"request('"+id+"',1)\">关闭</label></td>");
			
			
			if(status.equals(Common.SERVER_START)){
//				sb.append("<td>"+status+"</td>");
				sb.append("<td><button style=\"background-color: green; border-radius: 20px;\">&nbsp;</button></td>");
			}else{
				sb.append("<td><button style=\"background-color: red; border-radius: 20px;\">&nbsp;</button></td>");
//				sb.append("<td>"+status+"</td>");
			}
			
			sb.append("</tr>");
		}
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(sb.toString());
		
	}

}
