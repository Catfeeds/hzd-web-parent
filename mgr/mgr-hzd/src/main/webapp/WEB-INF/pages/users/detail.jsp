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
		var status = $("#hid").val();
		if(status==2){
			$("#statu").show();
			//$("#sh").show();
		}
			
	});
	
	function rad(obj){
		if(obj.value==1){
			$("#center").show();
		}else{
			$("#center").hide();
		}
		
	}
	function butt(){
		var status = $("#hid").val();
		if(status==1||status==0){
			window.location = '${path}/users/check/list';
		}  
		if(status==2){
			var checkStatus = $('input:radio[name=radioCheck]:checked').val();
			var mobile = $("#hid1").val();
			var nopassCause = $("#mytext").val();
			$.ajax({
				type:"POST",
				url: '${path}/users/check/updateStatus',
				data:{
					"mobile" : mobile,
					"checkStatus" : checkStatus,
					"nopassCause" : nopassCause,
				},
				success:function(result){
					if(result){
						alert("恭喜您,提交成功!");
						window.location = '${path}/users/check/list';
						return null;
					}else{
						alert("修改失败");
						return false;
					} 
				}
			});
		}
	}
</script>
</head>
<body>
    <div class="content2">
        <div class="userInfo">
            <h1>用户信息</h1>
            <table cellpadding="0" cellspacing="0">
                <tr>
                    <td>手机号：<span>${smsUserInfo.mobile}</span></td>
                    <td>姓名：<span>${smsUserInfo.name}</span></td>
                    <td>身份证号：<span>${smsUserInfo.idCard }</span></td>
                </tr>
                <tr>
                    <td>审核状态：<span>${smsUserInfo.statusInfo}</span></td>
                    <td>注册时间：<span>${smsUserInfo.createTime}</span></td>
                </tr>
            </table>
        </div>
        <div class="uploadPic">
            <h1>图片上传信息</h1>
            <div class="gallery">
                <div class="showImage2">
                    <div><img src="${smsUserInfo.artWorkA}" /></div>
                </div>
                <div class="showImage2">
                    <div><img src= "${smsUserInfo.artWorkB}" /></div>
                </div>
                <div class="showImage2">
                    <div><img src= "${smsUserInfo.artWorkC}" /></div>
                </div>
            </div>
            <div class="clear"></div>
            <hr />
            <div class="radioBox" id="statu" style="display: none ">
                <h1>审核意见</h1>
                <div class="selectBox" >
                    <div class="lL One"><input type="radio" name="radioCheck" id="yes" value="0" checked onclick="rad(this)"/>通过</div>
                    <div class="lL Two"><input type="radio" name="radioCheck" id="no" value="1" onclick="rad(this)"/>不通过 </div>
                </div>
                <div class="clear"></div>
                <div class="center" id="center" style="display: none ">
                    <textarea name="" placeholder="请填写不通过原因" id="" cols="30" rows="10"></textarea>
                </div>
            </div>
            <br />
            <div class="ui-button" style="text-align: left;margin: 20px 0 0" >
            	<input type="hidden"  value="${smsUserInfo.checkStatus}" id="hid"/>
				<input type="hidden"  value="${smsUserInfo.mobile}" id="hid1"/>
                <input type="button" value="${smsUserInfo.butt}" class="submitBtn" id="button" onclick="butt()" />
            </div>
        </div>
    </div>
</body>
</html>