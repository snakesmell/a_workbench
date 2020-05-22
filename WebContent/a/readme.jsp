<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.run.*" %>
<%	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ReadMe</title>
</head>
<body style="text-align: center;"><br>
<label>一、Tomcat环境配置</label>
<label>共两个文件：catalina.bat 和 startup.bat</label>
<br>1.catalina.bat文件配置&nbsp;&nbsp;&nbsp;&nbsp;注：JDK如果用系统默认的可以不用配置<br>
<img alt="" src="<%=basePath%>a/img/catalina.png">
<br>2.startup.bat文件配置<br>
<img alt="" src="<%=basePath%>a/img/startup1.png">
<br><br>
<label>二、服务添加 点击主页右侧"设备添加"按钮 进入配置页面</label>
<br>2-1 Windows服务添加
<br>查找服务名称<br>
<img alt="" src="<%=basePath%>a/img/server.png">
<br>
点击"新增按钮"，按格式要求将服务配置到系统
<br>
<img alt="" src="<%=basePath%>a/img/222.png">
<br>
<img alt="" src="<%=basePath%>a/img/33.png">
<br>2-2 Tomcat服务添加<br>
<img alt="" src="<%=basePath%>a/img/Tomcat.png">
<br>
<img alt="" src="<%=basePath%>a/img/111.png">
<br><br>
<label>三、配置完成后系统会自动刷新服务页面</label><br>
Thank You!
</body>
</html>