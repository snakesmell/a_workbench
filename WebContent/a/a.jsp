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
<%-- <link rel="stylesheet" href="<%=basePath%>statics/dist/css/layui.css"> --%>
<script src="<%=basePath%>js/bootstrap-3.3.7-dist/js/bootstrap.min.js" ></script>
<%-- <script src="<%=basePath%>statics/dist/layui.js"></script> --%>
<style>
	.a3 {
		border-radius: 5px;
	    height: 200px;
	    background-color: #00BFFF; /* 不支持线性的时候显示 */
	    /* background-image: linear-gradient(to left, #1E90FF , #F0F8FF); */
	}
	.a1 {
		border-radius: 5px;
	    height: 200px;
	    background-color: #00BFFF; /* 不支持线性的时候显示 */
	    /* background-image: linear-gradient(to left , #F0F8FF, #1E90FF); */
	}
</style>
</head>
<body>
<br><br>
<div style="text-align: center;font-size: 30px;">交通运维服务控制台</div>
&nbsp;&nbsp;<label style="cursor: pointer;" onclick="readMe()">操作手册</label>
<br>
<div class="row" >
  <div class="col-md-2 a1" style="font-size: 14px;color: white;height: 100%;padding-left: 30px;" id="leftPanel">
  </div>
  
  <div class="col-md-8" >
  	<table class="table table-condensed" style="background-color:	#E1FFFF;border-radius: 10px;">
  		<thead>
  			<tr style="font-size: 20px;">
  				<td>类别</td>
  				<td>名称</td>
  				<td>操作</td>
  				<td>状态</td>
  			</tr>
  		</thead>
  		<tbody id="roadPanel" >
  		</tbody>
	</table>
  </div>
  
  <div class="col-md-2 a3" style="font-size: 14px;color: white;text-align: center;">
  	<ul>
  		<li><label style="cursor: pointer;" onclick="openWindow()">设备添加</label></li>
  	</ul>
  	<ul	id="sc">
  	
  	</ul>
  	<label st></label>
  </div>
</div>
</body>
<script type="text/javascript">
$(document).ready(function(){
	/* $("#roadPanel").html('<tr><td>1</td></tr>'); */
});
</script>
<script type="text/javascript">

//打开新的窗口
function openWindow(){
	var path="<%=basePath%>";
	var ph=path.replace('WorkBench','Top7');
	window.open(ph+"page/qd.jsp","_blank","toolbar=no, location=no, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, width=800, height=800");
}
function openDoc(fwip){
	var path="http://"+fwip+"/DocManage/Show/c.jsp";
	window.open(path,"_blank","toolbar=no, location=no, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, width=1600, height=800");

}
function readMe(){
	var path="<%=basePath%>";
	window.open(path+"a/readme.jsp","_blank","toolbar=no, location=no, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, width=1600, height=800");
}
function openTomcat(serverip,name){
	window.open("http://"+serverip+"/"+name,"_blank","toolbar=no, location=no, directories=no, status=no, menubar=yes, scrollbars=yes, resizable=no, copyhistory=yes, width=1600, height=800");
}
//下发命令
function request(id,action){
	var title=""; 
	 if(action==1){
		 title="关闭";
	 }
	 if(action==0){
		 title="开启";
	 }
	 if(confirm("确定要"+title+"吗？")){
		 $.ajax({
		       url:"<%=basePath%>/httpRequestAction",
		       type:"POST",
		       data:{ id: id,action:action },
		       success:function(data){
					//console.log("SERVER:"+data);
		    	   //$("#roadPanel").html(data);
		       }
		 });
	 }else{}
}
//获取服务运行状态 MAIN
setInterval(function(){
	 $.ajax({
	       url:"<%=basePath%>/demoRequest",
	       type:"POST",
	       //data:{ id: 0 },
	       success:function(data){
				//console.log("SERVER:"+data);
	    	   $("#roadPanel").html(data);
	       }
	 });
}, 5000);
//获取服务运行状态 LEFT
setInterval(function(){
	 $.ajax({
	       url:"<%=basePath%>/demoRequest",
	       type:"GET",
	       data:{ position: 'left' },
	       success:function(data){
				//console.log("SERVER:"+data);
	    	   $("#leftPanel").html(data);
	       }
	 });
}, 5000);
//获取服务运行状态 RIGHT
setInterval(function(){
	 $.ajax({
	       url:"<%=basePath%>/demoRequest",
	       type:"GET",
	       data:{ position: 'right' },
	       success:function(data){
				//console.log("SERVER:"+data);
	    	   $("#sc").html(data);
	       }
	 });
}, 5000);
</script>
</html>