<%@page import="org.apache.maven.model.Model"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>实名认证管理详情</title>
<%@include file="../common/include.jsp"%> 
<script type="text/javascript">
	$(function(){
		//alert("1111")
		var status = $("#hid").val();
		//alert(status);
		if(status==2){
			$("#statu").show();
			$("#sh").show();
		}
			
	});
	
	function rad(obj){
		if(obj.value==1){
			$("#text").show();
		}else{
			$("#text").hide();
		}
		
	}
	function butt(){
		//alert(1111)
		var status = $("#hid").val();
		
		if(status==1||status==0){
			window.location = '${path}/users/check/list';
		}  
		if(status==2){
			var checkStatus = $('input:radio[name=radioCheck]:checked').val();
			var mobile = $("#hid1").val();
			var nopassCause = $("#mytext").val();
			// alert(nopassCause);
			window.location = '${path}/users/check/updateStatus?mobile='+mobile+ "&checkStatus="+checkStatus+"&nopassCause="+nopassCause;
		}
		
	}
	
</script>
</head>
<body>
<form action="${path}/users/check/updateStatus" method="post" id="form">
<table id="grid" style="width: 800px;height: 550px">
	<tr>
		<td>用户信息</td>
	</tr>
	<tr>	
		<td>手机号:</td>
		<td>${smsUserInfo.mobile}</td>
		<td>姓名:</td>
		<td>${smsUserInfo.name}</td>
		
	</tr>
	<tr>
		<td>身份证号:</td>
		<td>${smsUserInfo.idCard }</td>
	</tr>
	<tr>
		<td>审核状态:</td>
		<td>${smsUserInfo.statusInfo}</td>
		<td>注册时间:</td>
		<td>${smsUserInfo.createTime}</td>
	</tr>
	<tr>
		<td>图片上传信息</td>
		<td><img style="width: 200px;height: 100px"  src= ${smsUserInfo.artWorkA} /></td>
		<!-- <td><img style="width: 200px;height: 100px"  src= "../../images/accept.png"/></td> -->
		<td><img style="width: 200px;height: 100px"  src= ${smsUserInfo.artWorkB} /></td>
		<td><img style="width: 200px;height: 100px"  src= ${smsUserInfo.artWorkC} /></td>
	</tr>
	<tr style="display: none " id="sh">
		<td>审核意见</td>
	</tr>
	<tr id="statu" style="display: none ">
		<td><input type="radio" name="radioCheck" id="yes" value="0" checked onclick="rad(this)"/>通过 </td>
		<td><input type="radio" name="radioCheck" id="no" value="1" onclick="rad(this)"/>不通过 </td>
		<td style="display: none " id="text">
			<textarea id="mytext">
			</textarea>
		</td>
	</tr>
	
	<tr>
		<td>
			<input type="hidden"  value=${smsUserInfo.checkStatus} id="hid"/>
			<input type="hidden"  value=${smsUserInfo.mobile} id="hid1"/>
			<input type="button" value=${smsUserInfo.butt} id="button" onclick="butt()"/>
		</td>
	</tr>
</table>
</form>
</body>
</html>