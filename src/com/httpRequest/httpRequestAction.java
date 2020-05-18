package com.httpRequest;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.sqliteUtil.Common;
import com.sqliteUtil.SQLiteJDBC;
/**
 * Servlet implementation class httpRequestAction
 */
@WebServlet("/httpRequestAction")
public class httpRequestAction extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public httpRequestAction() {
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
		//param1 ID,param2 ACTION
		String id = "";
		String action = "";
		String name="";
		String value="";
		String remark="";
		String fwip="";
		String serverip="";
		//Step1 获取前台页面传来的参数值
		Map<String, String[]> param = request.getParameterMap();
		Iterator<Entry<String, String[]>> set = param.entrySet().iterator();
		List<NameValuePair> params=new ArrayList<NameValuePair>();
		while(set.hasNext()){
			Entry<String, String[]> ss = set.next();
			switch(ss.getKey()){
			case "id":
				params.add(new BasicNameValuePair("id",ss.getValue()[0]));
				id=ss.getValue()[0];
				break;
			case "action":
				params.add(new BasicNameValuePair("action",ss.getValue()[0]));
				break;
			}
		}
		//Step2 根据ID查找该数据的其它参数
		List<Map> list = SQLiteJDBC.query(id);
		for(int i=0;i<list.size();i++){
			Map map=list.get(i);
			name=String.valueOf(map.get("NAME"));
			value=String.valueOf(map.get("VALUE"));
			remark=String.valueOf(map.get("REMARK"));
			fwip=String.valueOf(map.get("FWIP"));
			serverip=String.valueOf(map.get("SERVERIP"));
			
			params.add(new BasicNameValuePair("name",name));
			params.add(new BasicNameValuePair("value",value));
			params.add(new BasicNameValuePair("remark",remark));
			params.add(new BasicNameValuePair("fwip",fwip));
			params.add(new BasicNameValuePair("serverip",serverip));
		}
		//Step3 分类 根据设备ID查找数据库对应的服务
		if(remark.equals(Common.Server)){
			//
			 CloseableHttpClient httpclient = HttpClients.createDefault(); 
			 HttpPost httppost = new HttpPost("http://"+fwip+"/WorkBench/winControl"); 
			//建立一个NameValuePair数组，用于存储欲传送的参数
			 httppost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
			//设置编码
			 HttpResponse res= httpclient.execute(httppost);
			 String responseEntityStr = EntityUtils.toString(res.getEntity());
			 System.out.println(responseEntityStr);
			 //System.out.println(res.getEntity());
		}
		//Step3 分类 根据设备ID查找数据库对应的服务
		if(remark.equals(Common.Tomcat)){
			//
			 CloseableHttpClient httpclient = HttpClients.createDefault(); 
			 HttpPost httppost = new HttpPost("http://"+fwip+"/WorkBench/tomcatControl"); 
			//建立一个NameValuePair数组，用于存储欲传送的参数
			 httppost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
			//设置编码
			 HttpResponse res= httpclient.execute(httppost);
			 String responseEntityStr = EntityUtils.toString(res.getEntity());
			 System.out.println(responseEntityStr);
			 //System.out.println(res.getEntity());
		}
		
	}
//	public static void main(String[] args) {
//		 try {
//			 CloseableHttpClient httpclient = HttpClients.createDefault(); 
//			 HttpPost httppost = new HttpPost("http://192.168.10.223:8081"); 
//			//建立一个NameValuePair数组，用于存储欲传送的参数
//			//设置编码
//			 HttpResponse res= httpclient.execute(httppost);
//			 String responseEntityStr = EntityUtils.toString(res.getEntity());
//			 System.out.println(responseEntityStr);
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
