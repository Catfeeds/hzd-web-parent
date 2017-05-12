<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">  
<%@include file="../common/include.jsp"%> 
<html>
	<head>
		<meta charset="utf-8" />
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>汇中财富管理系统</title>
		<link href="<%=basePath%>images/favicon.ico" rel="shortcut icon"	type="image/x-icon" />
		<link href="${path}/css/login.css" type=text/css rel=stylesheet>
		<script type="text/javascript"	src="<%=basePath%>/script/easyui/js/jquery.min.js"></script>
		<script type="text/javascript">
			var root = "<%=basePath%>";
			$(document).ready(function() {
				$("body").keydown(function(event){
					if(event.keyCode==13){
						$("#submit").click();
						
					}
				});
				
				$("#submit").bind("click", function() {
					if (valid()) {
						var irand = $("#irand").val();
						var password = $("#password").val();
						var username = $("#username").val();
						$.ajax( {
							type : "POST",
							url : root+"sys/login.do",
							data :{
								irand : irand,
								username : username,
								password : password
								},
							dataType:'json',
							success : function(data) {
								var dataObj = eval("data");
								if (dataObj.msg == 'ok') {
									// 1. 交互系统提交参数
									try{
										var sysType = dataObj.sysType;
										console.log(sysType);
										if(sysType == null ||sysType == "redpack" || sysType == ""){
									 		var pageurl=dataObj.interactiveUrl+"?user_id="+username+"";
										}
									}catch(e){}
									// 2.系统功能列表界面
									window.location.href = root + "sys/main";
								} else {
									$("#message").html(dataObj.resultContents.toString());
									 changeImge();
								}
							},
 							error : function(XMLHttpRequest, textStatus, errorThrown) {
								// 通常 textStatus 和 errorThrown 之中
								// 只有一个会包含信息
								if(XMLHttpRequestStatus == 403){
									$("#message").html('连接失败！');
								}
							}
 						});
					}
				});
			});
			
			//输入信息验证   
			function valid() {
				if ($("#username").val() == ''
						|| $("#password").val() == '') {
					$("#message").html('用户名或密码不能为空!');
					return false;
				}
				if ($("#irand").val() == '') {
				$("#message").html('验证码不能为空!');
				return false;
			}
				$("#message").html("");
				return true;
			};
			
			//更换验证码
			function changeImge(){
				document.getElementById("randImage").src = "<%=basePath %>/images/yanzhengma.jsp?k="+Math.random();
			}
		</script>
	</head>
	<body>
		<div id=div1>
			<table id=login height="100%" cellspacing=0 cellpadding=0 width=800
				align=center>
				<tbody>
					<tr id=main>
						<td>
							<table height="100%" cellspacing=0 cellpadding=0 width="100%" border=0>
								<tbody>
									<tr>
										<td colspan=4>
											&nbsp;
										</td>
									</tr>
									<tr height=50>
										<td colspan=5>
											<span
												style="font-size: 25px; font-weight: bold; float: right; padding-right: 170px; font-family: Verdana, 微软雅黑, 黑体">汇中财富管理系统</span>
										</td>
									</tr>
									<tr height=10>
										<td width=380>
											&nbsp;
										</td>
										<td colspan=4 align="center">
											<span id="message" style="color:red;font-weight:bold"></span>
										</td>
									</tr>
									<tr height=40>
										<td rowspan=4>
											&nbsp;
										</td>
										<td width=245 align=right>
											<img src="<%=basePath%>/images/user.png" align="absmiddle" />
												<span style="font-size: 14px; font-family: Verdana, 微软雅黑, 黑体">用户名：</span>
										</td>
										<td>
											<input class=textbox type="text" id="username" value="" name="username">
										</td>
										<td width=120>
											&nbsp;
										</td>
									</tr>
									<tr height=40>
										<td width=245 align=right>
											<img src="<%=basePath%>/images/password.png" align="absmiddle" />
                                            <span style="font-size: 14px; font-family: Verdana, 微软雅黑, 黑体">密&nbsp;&nbsp;&nbsp;码：</span>
										</td>
										<td>
											<input class="textbox" id="password" type="password" value="" name="password">
										</td>
										<td width=120>
											&nbsp;
										</td>
									</tr>
									<tr height=40>
										<td width=245 align=right>
											<img src="<%=basePath%>/images/pencil.png" align="absmiddle" />
                                            <span style="font-size: 14px; font-family: Verdana, 微软雅黑, 黑体">验&nbsp;&nbsp;&nbsp;证&nbsp;&nbsp;&nbsp;码：</span>
										</td>
										<td class="bg_text small">
											<input type="text" id="irand" name="irand" maxLength="7"/>
										</td>
										<td>
											<img src="<%=basePath %>/images/yanzhengma.jsp" id="randImage" alt="换一张" />
											<a href="javascript:void(0);" onclick="changeImge()" title="换一张">换一张</a>
										</td>
										<td width=120>
											&nbsp;
										</td>
									</tr>
									<tr height=40>
										<td></td>
										<td align=right>
											<input id="submit" style="cursor: pointer;" type="button" value=" 登 录 " name=btnlogin>
										</td>
										<td width=215>
											&nbsp;
										</td>
									</tr>
									<tr height=110>
										<td colspan=4>
											&nbsp;
										</td>
									</tr>
								</tbody>
							</table>
						</td>
					</tr>
					<tr id=root height=104>
						<td>
							&nbsp;
						</td>
					</tr>
				</tbody>
			</table>
		</div>
	</body>
</html>