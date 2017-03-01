<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>重置密码 </title>
	<%@include file="../common/include.jsp"%>
	<script src="${path}/script/artDialog/jquery.artDialog.js?skin=blue" type="text/javascript"></script>
	<script src="${path}/script/artDialog/plugins/iframeTools.js" type="text/javascript"></script>
    <style type="text/css">
 	    .warnClass{
 	    	color:red;
 	    }
/* 		table{ */
/* 		   border-spacing:200%;  */
/* 		}  */
    </style>
</head>
<body>
<!--     <div class="easyui-panel" style="width:700px;height:370px;padding:50px 10px 40px 10px;margin:50px 10px 40px 10px;"> -->
<!--         <div> -->
<!--         	<label>原始密码:</label> -->
<!--             <input id="password" name="password" class="easyui-passwordbox" prompt="Password" iconWidth="28" style="width:80%;height:20px;padding:10px"> -->
<!--             &nbsp;<span id="passwordSpan" class="warnClass"></span> -->
<!--         </div> -->
<!--         <div> -->
<!--         	<label>新密码:</label> -->
<!--             <input id="passwordNew" name="passwordNew" class="easyui-passwordbox" prompt="Password" iconWidth="28" style="width:80%;height:20px;padding:10px"> -->
<!--             &nbsp;<span id="passwordNewSpan" class="warnClass"></span> -->
<!--         </div> -->
<!--         <div> -->
<!--         	<label>重复密码:</label> -->
<!--             <input id="passwordNewRepeat" name="passwordNewRepeat" class="easyui-passwordbox" prompt="Password" iconWidth="28" style="width:80%;height:20px;padding:10px"> -->
<!--             &nbsp;<span id="passwordNewRepeatSpan" class="warnClass"></span> -->
<!--         </div> -->
<!-- 		<div class="aui_buttons"> -->
<!-- 			<a id="doSure" href="javascript:void(0)" class="button button-end" >确定</a> -->
<!-- 			<a id="doCancel" href="javascript:void(0)" class="button button-end" >取消</a> -->
<!-- 		</div> -->
<!--     </div>  style="width:150%;height:100%;"-->
    <table>
        <tr>
        	<th>原始密码:</th>
        	<td>
            <input id="password" name="password">
            <span id="passwordSpan" class="warnClass"></span>
            </td>
        </tr>
        <tr>
        	<th>新密码:</th>
        	<td>
            <input id="passwordNew" name="passwordNew">
            <span id="passwordNewSpan" class="warnClass"></span>
            </td>
        </tr>
        <tr>
        	<th>重复密码:</th>
        	<td>
            <input id="passwordNewRepeat" name="passwordNewRepeat">
            <span id="passwordNewRepeatSpan" class="warnClass"></span>
            </td>
        </tr>
		<tr>
			<th></th>
			<td>
			<a id="doSure" href="javascript:void(0);">确定</a>
			<a id="doCancel" href="javascript:void(0);">取消</a>
			</td>
		</tr>
    </table>
</body>
<script type="text/javascript">
$(document).ready(function(){
	/**数据校验*/
	$("#password").blur(function(){
		vPassword();
	}).focus(function(){
		$("#passwordSpan").html("");
	});
	$("#passwordNew").blur(function(){
		vPasswordNew();
	}).focus(function(){
		$("#passwordNewSpan").html("");
	});
	$("#passwordNewRepeat").blur(function(){
		vPasswordNewRepeat();
	}).focus(function(){
		$("#passwordNewRepeatSpan").html("");
	});
	/**提交数据*/
	$("#doSure").click(function(){
		doSure();
	});
	$("#doCancel").click(function(){
		doCancel();
	});
});
//
function vPassword(){
	var password=$("#password").val();
	if(password.length<=0){
		$("#passwordSpan").html("原始密码不能为空");
		return false;
	}
	$("#passwordSpan").html("");
	return true;
}
function vPasswordNew(){
	var passwordNew=$("#passwordNew").val();
	var passwordNewRepeat=$("#passwordNewRepeat").val();
	if(passwordNew.length<=0){
		$("#passwordNewSpan").html("新密码不能为空");
		return false;
	}
	if(passwordNewRepeat.length<=0){
		$("#passwordNewRepeatSpan").html("重复密码不能为空");
		return false;
	}
	if(passwordNew!=passwordNewRepeat){
		$("#passwordNewSpan").html("两次输入的新密码不一致");
		return false;
	}
	$("#passwordNewSpan").html("");
	$("#passwordNewRepeatSpan").html("");
	return true;
}
function vPasswordNewRepeat(){
	var passwordNew=$("#passwordNew").val();
	var passwordNewRepeat=$("#passwordNewRepeat").val();
	if(passwordNewRepeat.length<=0){
		$("#passwordNewRepeatSpan").html("重复密码不能为空");
		return false;
	}
	if(passwordNew!=passwordNewRepeat){
		$("#passwordNewSpan").html("两次输入的新密码不一致");
		return false;
	}
	$("#passwordNewSpan").html("");
	$("#passwordNewRepeatSpan").html("");
	return true;
}
//提交数据
function doSure(){
	if((vPassword() && vPasswordNew() && vPasswordNewRepeat())!=true){return false;};
	$.ajax({
		url : "${path}/sys/resetpassword",
		type : "POST",
		data : {
			"password":$("#password").val(),
			"passwordNew":$("#passwordNew").val(),
			"passwordNewRepeat":$("#passwordNewRepeat").val()
		},
		dataType : "json",
 		async : false,
		success : function(data) {
		    if (data.code == "1") {
		    	art.dialog.alert(data.message, function() {
		    		//获取上一级页面，并让上一个页面重新加载
		    		var win = art.dialog.open.origin;
		    		win.location.reload();
		    	});
		    } else {
				art.dialog.alert(data.message, function() {
					//获取上一级页面，并让上一个页面重新加载
		    		var win = art.dialog.open.origin;
		    		win.location.reload();
		    	});
		    }
	    },
	    error : function(data) {
	    	art.dialog.alert(data.message, function() {
	    		//获取上一级页面，并让上一个页面重新加载
	    		var win = art.dialog.open.origin;
	    		win.location.reload();
	    	});
	    }
	});
}
function doCancel(){
	art.dialog.close();
}
</script>
</html>