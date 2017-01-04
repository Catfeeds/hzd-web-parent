<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>实名认证管理详情</title>
<%@include file="../common/include.jsp"%> 
<script type="text/javascript">
	function save(){
		var name = $("#name").val();
		var idCard = $("#idCard").val();
		
		document.getElementById("form1").submit();
	}
	function xm(){
		alert(1111);
		var name = $("#name").val();
		if(name==null||name==""){
			$("#div").html("姓名不能为空");
		}
		
	}
	
	function sfz(){}
	
	function Aclick(){
		window.location = '${path}/users/check/list';
	}
	function Aclick(){
		window.location = '${path}/users/check/list';	
	}
	function Aclick(){
		window.location = '${path}/users/check/list';
	}
</script>
</head>
<body>
<form action="${path}/users/check/update" method="post" id="form1">

<table id="table" style="width: 520px;height: 550px"  >
	<tr id="div" style="display: none "></tr>
	<tr>
		<h>用户信息</h>
		
	</tr>
	<tr>	
		<td>手机号:</td>
		<td>${smsUserInfo.mobile}</td>
		<td>姓名:</td>
		<td><input type="text" id="name" name="name" onblur="xm()" value=${smsUserInfo.name}> </td>
		
	</tr>
	<tr><td>身份证号:</td>
		<td><input type="text" id="idCard" name="idCard" onblur="sfz()" value=${smsUserInfo.idCard} > </td></tr>
	
	<tr>
		<td>审核状态:</td>
		<td>${smsUserInfo.statusInfo}</td>
		<td>注册时间:</td>
		<td>${smsUserInfo.createTime}</td>
		
	</tr>
	<tr>
		<td>图片上传信息</td></tr>
	<tr>
		<td><input type="button" onclick="Aclick();" value="重新上传"/></td>
		<td><input type="button" onclick="Bclick();" value="重新上传"/></td>
		<td><input type="button" onclick="Cclick();" value="重新上传"/></td>
	</tr>
	
	<tr>
		<td>
		<input type="hidden" name="mobile" value=${smsUserInfo.mobile} /> 
		<input type="button" value="保存" onclick="save()"/> </td>
	</tr>
</table>
</form>
</body>
</html>