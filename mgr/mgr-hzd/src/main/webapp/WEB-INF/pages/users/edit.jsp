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
		var mobile = $("#mobile1").val()
		var name = $("#name").val();
		if(name==null||name==""){
			$("#div").html("请输入姓名!");
			return ;
		}else{
			var reg = /^[\u4E00-\u9FA5]{2,6}$/;
			if(!reg.test(name)){
				$("#div").html("姓名限2~6位汉字！");
				return  false; 
			}
			$("#div").html("");
		}
		var idCard = $("#idCard").val();
		if(idCard==null || idCard==""){
			$("#div").html("请输入身份证号！");
			return ;
		}else{
			 var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
			 if(reg.test(idCard) === false){    
				 $("#div").html("请输入正确的身份证号"); 
			    return  false; 
			 }
			$("#div").html("");
		}
		var msg = $("#div").val();
		if(msg==""){
			$.ajax({
				type:"POST",
				url: '${path}/users/check/update',
				data:{
					"mobile" : mobile,
					"name" : name,
					"idCard":idCard
				},
				success:function(result){
					if(result){
						alert("恭喜您,保存成功!");
						window.location = '${path}/users/check/list';
						return null;
					}else{
						alert("修改失败");
						return false;
					} 
				}
			});
			//document.getElementById("form").submit();
		}else{
			$("#div").html("校验未通过不能提交");
		}
	}
	function xm(){
		//alert(1111);
		var name = $("#name").val();
		if(name==null||name==""){
			$("#div").html("请输入姓名!");
			return ;
		}else{
			var reg = /^[\u4E00-\u9FA5]{2,6}$/;
			if(!reg.test(name)){
				$("#div").html("姓名限2~6位汉字！");
				return  false; 
			}
			$("#div").html("");
		}
		var idCard = $("#idCard").val();
		if(idCard==null || idCard==""){
			$("#div").html("请输入身份证号！");
			return ;
		}else{
			 var reg = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;  
			 if(reg.test(idCard) === false){    
				 $("#div").html("请输入正确的身份证号"); 
			    return  false; 
			 }
			$("#div").html("");

		}
		
	}
	
	function upload(id) {
		var multipart = $("#image_input"+id).val();
		//alert(multipart);
		if(multipart==""||multipart==null){
			alert("请先选择图片!");
			return ;
		}
        $("#form"+id).ajaxSubmit({
            type : 'POST',
            url:'${path}/users/check/updateImage',
            success : function(data) {
            	if(data == "false"){
            		alert("上传失败,请重新上传!");
            		return null;
            	}
                $("#imgDiv"+id).attr("src",data);
                alert("恭喜您,上传成功!");
            }
        });

    }
	
</script>
</head>
<body>
<div id="div" style="line-height: 100px; font-size: 1rem; color: red;"></div>
<table id="table" style="width:800px; height:100px;">

	<tr id="div" style="display: none "></tr>
	<tr>
		<td>用户信息</td>
	</tr>
	<tr>	
		<td>手机号:</td>
		<td>${smsUserInfo.mobile}</td>
		<td>姓名:</td>
		<td><input type="text" id="name" name="name" onblur="xm()" value=${smsUserInfo.name} /> </td>
		<td>身份证号:</td>
		<td><input type="text" id="idCard" name="idCard" onblur="xm()" value=${smsUserInfo.idCard} /> </td>
	</tr>
	
	<tr style="background:#cccccc;">
		<td>审核状态:</td>
		<td>${smsUserInfo.statusInfo}</td>
		<td>注册时间:</td>
		<td>${smsUserInfo.createTime}</td>
		
	</tr>
	<tr>
		<td>
		<input type="hidden" name="mobile" id="mobile1" value=${smsUserInfo.mobile} /> 
		</td>
	</tr>
</table>

<form action="" id="form1" enctype="multipart/form-data">
	<table id="table" style="width: 800px;">
	<tr>
		<td>
			<img id="imgDiv1" style="width: 200px;height: 100px" src="${smsUserInfo.artWorkA}" /><br />
			<input type="file" id="image_input1"  name="file" />
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
	<table id="table" style="width: 800px;">
	<tr>
		<td>
			<img id="imgDiv2" style="width: 200px;height: 100px" src="${smsUserInfo.artWorkB}" /><br />
        	<input type="file" id="image_input2" name="file" />
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
	<table id="table" style="width: 800px;">
	<tr>
		<td>
			<img id="imgDiv3" style="width: 200px;height: 100px" src="${smsUserInfo.artWorkC}" /><br />
        	<input type="file" id="image_input3" name="file" />
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