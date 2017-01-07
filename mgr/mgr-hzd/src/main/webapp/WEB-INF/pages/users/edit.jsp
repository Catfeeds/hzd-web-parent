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
		xm();
		/* var name = $("#name").val();
		var idCard = $("#idCard").val();
		var mobile = $("#mobile").val(); */
		var msg = $("#div").val();
		if(msg==""){
			document.getElementById("form1").submit();
		}else{
			$("#div").html("校验未通过不能提交");
		}
	}
	function xm(){
		//alert(1111);
		var name = $("#name").val();
		if(name==null||name==""){
			$("#div").html("姓名不能为空");
		}else{
			var reg = /^[\u4E00-\u9FA5]{2,8}$/;
			if(!reg.test(name)){
				$("#div").html("输入的姓名在2~8个汉字");
				return  false; 
			}
			$("#div").html("");
		}
		
	}
	
	function sfz(){
		var idCard = $("#idCard").val();
		if(idCard==null || idCard==""){
			$("#div").html("身份证号不能为空");
		}else{
			 var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
			 if(reg.test(idCard) === false){    
				 $("#div").html("身份证号不合法"); 
			    return  false; 
			 }

			$("#div").html("");
		}
	}
	
</script>
</head>
<body>
<div id="div" style="line-height: 100px; font-size: 1rem; color: red;"></div>
<form action="${path}/users/check/updateUserAndImage" method="post" id="form1" enctype="multipart/form-data">

<table id="table" style="width: 800px;height: 600px"  >
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
		<td>重新上传<input type="file" name="images" /></td>
		<td>重新上传<input type="file" name="images" /></td>
		<td>重新上传<input type="file" name="images" /></td>
	</tr>
	<tr>
		<td>
		<input type="hidden" name="mobile" id="mobile" value=${smsUserInfo.mobile} /> 
		<input type="button" value="保存" onclick="save()"/> </td>
	</tr>
	
</table>
<%-- </form>

<form action="${path}/users/check/smsImgUpload" method="post" id="form2" enctype="multipart/form-data">
<table id="table" style="width: 800px;height: 350px">
</table>

</form> --%>
</body>
</html>