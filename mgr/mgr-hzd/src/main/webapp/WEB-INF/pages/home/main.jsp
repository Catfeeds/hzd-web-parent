<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>进件二期后台管理系统</title>
<%@include file="../common/include.jsp"%>
<script src="${path}/script/artDialog/jquery.artDialog.js?skin=blue" type="text/javascript"></script>
<script src="${path}/script/artDialog/plugins/iframeTools.js" type="text/javascript"></script>
<script>
/*  	$.ajaxSetup({
		error: function (XMLHttpRequest, textStatus, errorThrown){
			if(XMLHttpRequest.status==403){
				alert('您没有权限访问此资源或进行此操作');
				return false;
			}
		},
		complete:function(XMLHttpRequest,textStatus){
			var sessionstatus=XMLHttpRequest.getResponseHeader("sessionstatus");//通过XMLHttpRequest取得响应头,sessionstatus
			if(sessionstatus=='timeout'){//如果超时就处理 ，指定要跳转的页面
				var top = getTopWinow(); //获取当前页面的顶层窗口对象
				alert('登录超时, 请重新登录.');
				top.location.href="mgr-hzd/sys/tologin"; //跳转到登陆页面
			}
		}
	});  */

	function addTab(title, url){
		var centerTabsObj = $('#mainTabs');
		if ($('#mainTabs').tabs('exists', title)){
			$('#mainTabs').tabs('select', title);
			var tab = centerTabsObj.tabs('getSelected');
			centerTabsObj.tabs('update',{
				tab:tab,
				options:{
					content:'<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>'
				}
			});
		} else {
			var content = '<iframe scrolling="auto" frameborder="0"  src="'+url+'" style="width:100%;height:100%;"></iframe>';
			$('#mainTabs').tabs('add',{
				title:title,
				content:content,
				closable:true
			});
		}
	}
	function exit(){
		if(confirm('确定退出吗 ？')){
			window.location="${path}/sys/logout";
		}
	}
	//重置密码
	function resetPassword(){
		art.dialog.open("${path}/sys/toresetpassword.do", {
			   title: '重置密码',
			   id: 'resetPassword',
			   width:'800px',
			   height:'500px',
			   fixed: true,
			   resize:true,
			   drag:true,
			   lock:true,
			   init:function(){
				   this.content="数据加载中...";
			   },/*
			   okVal:'确定',
			   ok:function(){
				   return true;
			   },
			   cancelVal:'取消',
			   cancel: function(){
				   return true;
			   },*/
			   close:function(){
				   return true;
			   }
			},false);
	}
</script>
</head>
    <body class="easyui-layout">  
        <!-- 正上方panel -->  
        <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="font-size: 14px;float:right; padding-right:20px;" class="head">
        	您好，${sessionScope.userName }&nbsp;&nbsp;&nbsp;
        	<a href="javascript:void(0)" onclick="exit()" style="padding-right:20px;color:#FFF; text-decoration:none;">安全退出</a>
        	<a href="javascript:void(0)" onclick="resetPassword();" style="padding-right:20px;color:#FFF; text-decoration:none;">修改密码</a>
        </span>
        <span style="padding-left:10px; font-size: 16px; ">
        	<img src="${path}/images/blocks.gif" width="20" height="20" align="absmiddle" >
        	进件二期后台管理系统</span>
        </div>  
        
		<!-- 左侧菜单 -->
        <div data-options="region:'west',href:''" title="导航" style="width: 200px; padding: 10px;">
			<ul id="mainMenu" class="easyui-tree">  
        <li>  
            <span>线上进件</span>  
            <ul>  
                <li><span><a href="javascript:void(0)" onclick="addTab('进件列表', '${path}/apply/list')">进件列表</a></span></li>  
            </ul>  
        </li>  
        
        <li>  
            <span>用户管理</span>  
            <ul>  
                <li><span><a href="javascript:void(0)" onclick="addTab('用户列表', '${path}/users/list')">用户列表</a></span></li>  
            </ul>  
        </li> 

        <li>  
            <span>实名认证管理</span>  
            <ul>  
                <li><span><a href="javascript:void(0)" onclick="addTab('实名认证列表', '${path}/users/check/list')">实名认证列表</a></span></li>  
            </ul>  
        </li> 

    </ul>    
		</div>
		
        <!-- 正中间panel -->  
        <div region="center" title="功能区" >  
            <div class="easyui-tabs" id="mainTabs" fit="true" border="false">  
                <div title="欢迎页" style="padding:20px;overflow:hidden;">   
                    <div style="margin-top:20px;">  
                        <h3>你好，欢迎.</h3>  
                    </div>  
                </div>  
            </div>  
        </div>  
        
        <!-- 正下方panel -->  
        <div region="south" style="height:30px;" align="center">  
            <label>
                	<br/>  
            </label>  
        </div>  
    </body>
</html>
