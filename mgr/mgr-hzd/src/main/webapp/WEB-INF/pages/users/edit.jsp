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
		sfz();
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
	
	function upload(id) {
        $("#form"+id).ajaxSubmit({
            type : 'POST',
            url:'${path}/users/check/updateImage',
            success : function(data) {
            	if(data == "false"){
            		alert("上传失败");
            		return null;
            	}
                $("#imgDiv"+id).attr("src",data);
                alert("上传成功");
            }
        });

    }
</script>
</head>
<body>
<div id="div" style="line-height: 100px; font-size: 1rem; color: red;"></div>
<form action="${path}/users/check/update" method="post" id="form" >
<table id="table" style="width: 800px;height: 400px"  >
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
	<!-- <tr>
		<td>图片上传信息</td></tr>
	<tr>
		<td>重新上传<input type="file" name="image1"  /></td>
		<td>重新上传<input type="file" name="image2" /></td>
		<td>重新上传<input type="file" name="image3" /></td>
	</tr> -->
	<tr>
		<td>
		<input type="hidden" name="mobile" id="mobile" value=${smsUserInfo.mobile} /> 
		<!-- <input type="button" value="保存" onclick="save()"/>  -->
		</td>
	</tr>
</table>
</form>

<form action="" id="form1" enctype="multipart/form-data">
	<table id="table" style="width: 800px;height: 300px">
	<tr>
		<td>
			<img id="imgDiv1" style="width: 200px;height: 100px" name="url" src="${smsUserInfo.artWorkA}" /><br />
			<input type="file" name="image1"  />
			<input type="button" onclick="upload('1')"value="重新上传"/>
		</td>
	</tr>
	<tr><td>
		<input type="hidden" name="imgId" value=${smsUserInfo.imgIdA} />
		<input type="hidden" name="mobile" id="mobile" value=${smsUserInfo.mobile} />
		</td>
	</tr>
	</table>
</form>

<form action="" id="form2" enctype="multipart/form-data">
	<table id="table" style="width: 800px;height: 300px">
	<tr>
		<td>
			<img id="imgDiv2" style="width: 200px;height: 100px" name="url" src="${smsUserInfo.artWorkB}" /><br />
        	<input type="file" id="image_input" name="file" />
        	<input type="button" onclick="upload('2')"value="重新上传"/>
		</td>
	</tr>
	<tr><td>
		<input type="hidden" name="imgId" value=${smsUserInfo.imgIdB} />
		<input type="hidden" name="mobile" id="mobile" value=${smsUserInfo.mobile} />
		</td>
	</tr>
	</table>
</form>

<form action="" id="form3" enctype="multipart/form-data">
	<table id="table" style="width: 800px;height: 300px">
	<tr>
		<td>
			<img id="imgDiv3" style="width: 200px;height: 100px" name="url" src="${smsUserInfo.artWorkC}" /><br />
        	<input type="file" id="image_input" name="file" />
        	<input type="button" onclick="upload('3')" value="重新上传"/>
		</td>
	</tr>
			
	<tr><td>
		<input type="hidden" name="imgId" value=${smsUserInfo.imgIdC} />
		<input type="hidden" name="mobile" id="mobile" value=${smsUserInfo.mobile} />
		</td>
	</tr>
	</table>
</form>

	<input type="button" value="保存" onclick="save()"/> 
</body>
</html>