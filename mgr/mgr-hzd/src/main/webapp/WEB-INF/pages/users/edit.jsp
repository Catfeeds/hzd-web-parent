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
		// alert(111111111);
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

<div class="content2">
    <div class="userInfo">
        <h1>用户信息</h1>
        <table cellpadding="0" cellspacing="0">
            <tr>
                <td>手机号：<span>${smsUserInfo.mobile}</span></td>
                <td>姓名:</td>
				<td><input type="text" id="name" name="name" onblur="xm()" value="${smsUserInfo.name}" /> </td>
				<td>身份证号:</td>
				<td><input type="text" id="idCard" name="idCard" onblur="xm()" value="${smsUserInfo.idCard}" /> </td>
            </tr>
            <tr>
                <td>审核状态:</td>
				<td>${smsUserInfo.statusInfo}</td>
				<td>注册时间:</td>
				<td>${smsUserInfo.createTime}</td>
            </tr>
        </table>
    </div>
    <div class="uploadPic">
        <h1>图片上传信息</h1>
        <ul>
            <li>
                <form action="" id="form1" enctype="multipart/form-data">
                	<div id="imgdiv" class="showImage"><img id="imgDiv1" src="${smsUserInfo.artWorkA}" alt="身份证正面（人像面）"/></div>
	                <a href="javascript:;" class="file"><span>选择图片</span>
	                    <input type="file" class="selector_file fl" id="image_input1" />
	                </a>
	                <a href="javascript:;" class="file"  onclick="upload('1')" ><span>上传</span>
	                    <input type="button" class="selector_file fl"/>
	                </a>
	                <input type="hidden" name="imgId" value="${smsUserInfo.imgIdA}" />
					<input type="hidden" name="mobile" id="mobile" value="${smsUserInfo.mobile}" />
                </form>
            </li>
            <li>
                <form action="" id="form2" enctype="multipart/form-data">
                	<div id="imgdiv1" class="showImage"><img id="imgDiv2" src="${smsUserInfo.artWorkB}" alt="身份证反面（国徽面）"/></div>
                	<a href="javascript:;" class="file"><span>选择图片</span>
	                    <input type="file" class="selector_file fl" id="image_input2" />
	                </a>
	                <a href="javascript:;" class="file"  onclick="upload('2')"><span>上传</span>
	                    <input type="button" class="selector_file fl" />
	                </a>
	                <input type="hidden" name="imgId" value="${smsUserInfo.imgIdB}" />
					<input type="hidden" name="mobile" id="mobile" value="${smsUserInfo.mobile}" />
                </form>
            </li>
            <li>
                <form action="" id="form3" enctype="multipart/form-data">
                	<div id="imgdiv2" class="showImage"><img id="imgDiv3" src="${smsUserInfo.artWorkC}" alt="个人近照"/></div>
	                <a href="javascript:;" class="file"><span>选择图片</span>
	                    <input type="file" class="selector_file fl" id="image_input3" />
	                </a>
	                <a href="javascript:;" class="file" onclick="upload('3')"><span>上传</span>
	                    <input type="button" class="selector_file fl"/>
	                </a>
	                <input type="hidden" name="imgId" value="${smsUserInfo.imgIdC}" />
					<input type="hidden" name="mobile" id="mobile" value="${smsUserInfo.mobile}" />
                </form>
            </li>
        </ul>
        <div class="clear"></div>
        <div class="ui-button">
            <!-- <a href="RealNameAuthentication.html" class="submitBtn" onclick="save()">保存</a> -->
            <input type="button" id="submitBtn" class="submitBtn" onclick="save()" value="保存"/>
        </div>
    </div>
</div>

</body>
</html>