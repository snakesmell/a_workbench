package com.ThreadServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

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
import org.dom4j.Document;
import org.dom4j.DocumentHelper;

import com.run.a.Status;
import com.sqliteUtil.Common;
import com.sqliteUtil.SQLiteJDBC;

public class DemonServer implements Runnable{
	private ServletContextEvent arg0;
	public DemonServer(ServletContextEvent arg0) {
		// TODO Auto-generated constructor stub
		this.arg0=arg0;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try {
				
				ServletContext context = arg0.getServletContext();
				
				Properties prop = new Properties();
				InputStream in = DemonServer.class.getClassLoader().getResourceAsStream("config.properties");
				prop.load(in);
				String sleep=prop.getProperty("sleep");
				Thread.sleep(Integer.parseInt(sleep));//服务循环PING
				/////////////////////////////////MAIN PANEL//////////////////////////////////
				List<Map> list = SQLiteJDBC.query();
				context.setAttribute(Common.Context,list);
				//System.out.println(list);
				for (Map map : list) {
						try {
							Thread.sleep(500);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					
						String remark=String.valueOf(map.get(Common.REMARK));
						String value=String.valueOf(map.get(Common.VALUE));
						String name=String.valueOf(map.get(Common.NAME));
						String id=String.valueOf(map.get(Common.ID));
						String fwip=String.valueOf(map.get(Common.FWIP));
						String serverip=String.valueOf(map.get(Common.SERVERIP));
						if(remark.equals(Common.Server)) {//server状态获取
							try {
								String cmd = httpRequest(fwip,Common.winServer,null);
								int flag = cmd.indexOf(name);
								if(flag==-1){
									context.setAttribute(id, Common.SERVER_STOP);
//								String sql = " UPDATE WORKBENCH set STATUS = '"+Common.SERVER_STOP+"' where ID='"+id+"' ";
//								SQLiteJDBC.commonUpdate(sql);
								}else{
									context.setAttribute(id, Common.SERVER_START);
//								String sql = " UPDATE WORKBENCH set STATUS = '"+Common.SERVER_START+"' where ID='"+id+"' ";
//								SQLiteJDBC.commonUpdate(sql);
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								context.setAttribute(id, Common.SERVER_STOP);
							}
						}
						
						if(remark.equals(Common.Tomcat)) {//tomcat状态获取
							try {
								String result = httpRequest(fwip,Common.tomcatServer,serverip);
								context.setAttribute(id, Common.SERVER_STOP);
								if(result.equals(Common.SERVER_STOP)){
									context.setAttribute(id, Common.SERVER_STOP);
//									String sql = " UPDATE WORKBENCH set STATUS = '"+Common.SERVER_STOP+"' where ID='"+id+"' ";
//									SQLiteJDBC.commonUpdate(sql);
								}
								if(result.equals(Common.SERVER_START)){
									context.setAttribute(id, Common.SERVER_START);
//									String sql = " UPDATE WORKBENCH set STATUS = '"+Common.SERVER_START+"' where ID='"+id+"' ";
//									SQLiteJDBC.commonUpdate(sql);
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								//e.printStackTrace();
								context.setAttribute(id, Common.SERVER_STOP);
							}
						}
				}
				/////////////////////////////////LEFT PANEL//////////////////////////////////
//				SQLiteJDBC.clearAllStatus();//Step1 清空
				List<Map> list2 = SQLiteJDBC.queryAllFWIP();//Step2  查询
				StringBuilder sb=new StringBuilder();
				for (Map map : list2) {
//					Thread.sleep(100);
					String value=String.valueOf(map.get(Common.FWIP));
					String result = httpRequest(value,Common.computerStatus,null);
					sb.append(value+"</br>");
					sb.append(result+"</br>");
//					Thread.sleep(100);
//					SQLiteJDBC.insertStatus(result);//Step3 保存
				}
				context.setAttribute(Common.ContextWindow, sb.toString());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	/**
	 * 获取远程服务器当前运行服务状态
	 * @param ip
	 * @return
	 */
	public static String httpRequest(String value,String method,String port){
		try {
//			Properties prop = new Properties();
//			InputStream in = DemonServer.class.getClassLoader().getResourceAsStream("config.properties");
//			prop.load(in);
//			String port=prop.getProperty("port");winServer
			CloseableHttpClient httpclient = HttpClients.createDefault(); 
			HttpPost httppost = new HttpPost("http://"+value+"/WorkBench/"+method); 
			if(port!=null){
				List<NameValuePair> params=new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("port",port));
				httppost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));
			}
			//设置编码
			HttpResponse res= httpclient.execute(httppost);
			String responseEntityStr = EntityUtils.toString(res.getEntity());
			//System.out.println(responseEntityStr);
			return responseEntityStr;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}
	
	 /**
	  * STEP 1
     * 查看Tomcat服务运行状态1
     * @param url
     */
    public static String state(String url) {
        String result = null;
        Document document = null;
        try {
        	Properties prop = new Properties();
    		InputStream in = DemonServer.class.getClassLoader().getResourceAsStream("config.properties");
    		prop.load(in);
    		String user=String.valueOf(prop.get("user"));
    		String password=String.valueOf(prop.get("password"));
            result =  getHtmlContext("http://"+url+"/manager/status?XML=true", user, password);
            //System.out.println(result);
            document = DocumentHelper.parseText(result);
            //System.out.println(result);
        } catch (Exception e) {
            //e.printStackTrace();
            return result;
        }
        //System.out.println(document.asXML());
        return result;
    }
    /**
     * STEP 2
     * 分析Tomcat服务运行状态2
     * @param url
     */
	public static String getHtmlContext(String tempurl, String username, String password) throws IOException {
        URL url = null;
        BufferedReader breader = null;
        InputStream is = null;
        StringBuffer resultBuffer = new StringBuffer();
        try {
            url = new URL(tempurl);
            String userPassword = username + ":" + password;
            String encoding = new sun.misc.BASE64Encoder().encode (userPassword.getBytes());
 
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            //conn.setRequestProperty("Authorization", "Basic " + encoding);
            is = conn.getInputStream();
            breader = new BufferedReader(new InputStreamReader(is));
            String line = "";
            while ((line = breader.readLine()) != null) {
                resultBuffer.append(line);
            }
        } catch (MalformedURLException e) {
           // e.printStackTrace();
        } finally {
            if(breader != null)
                breader.close();
            if(is != null)
                is.close();
        }
        return resultBuffer.toString();
    }
	
	
	public static void main(String[] args) {
		 URL url = null;
	     BufferedReader breader = null;
	     InputStream is = null;
	     StringBuffer resultBuffer = new StringBuffer();
		 try {
			 	url = new URL("http://192.168.10.65:8080/");
	            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	            is = conn.getInputStream();
	            breader = new BufferedReader(new InputStreamReader(is));
	            String line = "";
	            while ((line = breader.readLine()) != null) {
	                resultBuffer.append(line);
	            }
	            System.out.println(line);
	        } catch (Exception e) {
	           // e.printStackTrace();
	        } finally {
	        }
	}
	
}
