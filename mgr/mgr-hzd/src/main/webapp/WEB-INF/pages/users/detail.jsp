<%@page import="org.apache.maven.model.Model"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>实名认证管理详情</title>
<%@include file="../common/include.jsp"%> 
</head>
<script type="text/javascript">

</script>
<body>
<table id="grid" style="width: 520px;height: 550px">
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
		<td></td>
		<td></td>
		<td></td>
	</tr>
	<tr>
		<td>审核意见</td>
	</tr>
	<tr>
		<td><input type="radio" name="radioCheck" id="yes" value="0" checked/>通过 </td>
		<td><input type="radio" name="radioCheck" id="no" value="1"/>不通过 </td>
	</tr>
	<tr>
		<td>
			<input type="hidden" id="hid" value=${smsUserInfo.checkStatus}/>
			<input type="button" value=${smsUserInfo.butt} id="button"/>
		</td>
	</tr>
</table>

</body>
</html>