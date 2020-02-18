<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";%>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script src="<%=basePath%>js/jquery-3.4.1.min.js"></script>
	<link rel="stylesheet" href="<%=basePath%>js/bootstrap-3.3.7-dist/css/bootstrap.min.css">
	<script src="<%=basePath%>js/bootstrap-3.3.7-dist/js/bootstrap.min.js" ></script>
</head>
<body>
	<br><br><br><br>
	<div class="row">
	  <div class="col-md-4"></div>
	  <div class="col-md-4">
	  		<form action="<%=basePath%>/Login" method="post" class="form-inline">
			  <div class="form-group">
			    <label for="exampleInputName2">账号</label>
			    <input type="text" class="form-control" id="exampleInputName2"  placeholder="admin">
			  </div>
			  <div class="form-group">
			    <label for="exampleInputEmail2">密码</label>
			    <input type="password" class="form-control" id="exampleInputEmail2" placeholder="admin">
			  </div>
			  <br>
			  	 &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   			  	 <button type="submit" class="btn btn-default">登录</button>
			</form>
	  </div>
	  <div class="col-md-4"></div>
	</div>
</body>
<script type="text/javascript">
<%-- function login(){
	 $.ajax({
        url:"<%=basePath%>/Login",
        type:"POST",
        /* data:{ id: id ,action:action}, */
        success:function(data){
 			console.log(data);
        }
	});
} --%>
</script>
</html>