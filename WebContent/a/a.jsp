<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="com.run.*" %>
<%	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"+ request.getServerName() + ":" + request.getServerPort()+ path + "/";%>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="<%=basePath%>js/jquery-3.4.1.min.js"></script>
<link rel="stylesheet" href="<%=basePath%>js/bootstrap-3.3.7-dist/css/bootstrap.min.css">
<link rel="stylesheet" href="<%=basePath%>statics/dist/css/layui.css">
<script src="<%=basePath%>js/bootstrap-3.3.7-dist/js/bootstrap.min.js" ></script>
<script src="<%=basePath%>statics/dist/layui.js"></script>
<style>
	.a3 {
	    height: 200px;
	    background-color: red; /* 不支持线性的时候显示 */
	    background-image: linear-gradient(to left, #1E90FF , #F0F8FF);
	}
	.a1 {
	    height: 200px;
	    background-color: red; /* 不支持线性的时候显示 */
	    background-image: linear-gradient(to left , #F0F8FF, #1E90FF);
	}
</style>
</head>
<body>
<br><br>
<div style="text-align: center;font-size: 30px;">服务控制台</div>
<br>
<div class="row" >
  <div class="col-md-2 a1" ></div>
  <div class="col-md-8" >
  	<table class="table table-condensed" style="background-color: #E1FFFF;">
  		<thead>
  			<tr style="font-size: 20px;">
  				<td>类别</td>
  				<td>名称</td>
  				<td style="text-align: right;">操作&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  				<td>状态</td>
  			</tr>
  		</thead>
  		<tbody id="roadPanel" ></tbody>
	</table>
  </div>
  <div class="col-md-2 a3"></div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	/* $("#roadPanel").html('<tr><td>1</td></tr>'); */
	$("#roadPanel").append(<%=request.getAttribute("roadPanel")%>);
});
</script>
<script type="text/javascript">
//启动-停止tomcat
function tomcat(id,action){
	 var title=""; 
	 if(action==1){
		 title="开启";
	 }
	 if(action==0){
		 title="关闭";
	 }
	 if(confirm("服务"+id+"确定要"+title+"吗？")){
		 $.ajax({
	         url:"<%=basePath%>/C1",
	         type:"POST",
	         data:{ id: id ,action:action},
	         success:function(data){
	  			//console.log(data);
	         }
		});
	 }else{}
	
}
//开启关闭服务

function server(id,action){
	 var title=""; 
	 if(action==1){
		 title="开启";
	 }
	 if(action==0){
		 title="关闭";
	 }
	 if(confirm("服务"+id+"确定要"+title+"吗？")){
		 $.ajax({
		        url:"<%=basePath%>/B1",
		        type:"POST",
		        data:{ id: id ,action:action},
		        success:function(data){
		 			//console.log(data);
		        }
			});
	 }else{}
	 
}

//获取服务运行状态
setInterval(function(){
	 $.ajax({
	       url:"<%=basePath%>/S1",
	       type:"POST",
	       data:{ id: 1 },
	       success:function(data){
				//console.log("SERVER:"+data);
				var sp=data.split("*");
				for(var i=0;i<sp.length;i++){
					//console.log(i);
					var st=sp[i].split("-");
					if(st[1]=='0'){//关闭
						$("#"+st[0]).html("&nbsp;&nbsp;<button style=\"background-color: red; border-radius: 20px;\">&nbsp;</button>");	
					}
					if(st[1]=='1'){//开启
						$("#"+st[0]).html("&nbsp;&nbsp;<button style=\"background-color: green; border-radius: 20px;\">&nbsp;</button>");
					}
					 
				}
	       }
	 });
}, 3000);
//获取服务运行状态
setInterval(function(){
	 $.ajax({
	       url:"<%=basePath%>/S2",
	       type:"POST",
	       data:{ id: 1 },
	       success:function(data){
	    	   var sp=data.split("*");
				for(var i=0;i<sp.length;i++){
					//console.log(i);
					var st=sp[i].split("-");
					if(st[1]=='0'){//关闭
						$("#"+st[0]).html("&nbsp;&nbsp;<button style=\"background-color: red;border-radius: 20px;\">&nbsp;</button>");	
					}
					if(st[1]=='1'){//开启
						$("#"+st[0]).html("&nbsp;&nbsp;<button style=\"background-color: green;border-radius: 20px;\">&nbsp;</button>");
					}
					 
				}
	       }
	 });
}, 3000);
</script>
</html>