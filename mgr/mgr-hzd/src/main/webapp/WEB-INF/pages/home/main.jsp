<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>进件二期后台管理系统</title>
<%@include file="../common/include.jsp"%> 

<script>
	function addTab(title, url){
		if ($('#mainTabs').tabs('exists', title)){
			$('#mainTabs').tabs('select', title);
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
</script>


</head>
    <body class="easyui-layout">  
        <!-- 正上方panel -->  
        <div region="north" split="true" border="false" style="overflow: hidden; height: 30px;
        background: url(images/layout-browser-hd-bg.gif) #7f99be repeat-x center 50%;
        line-height: 20px;color: #fff; font-family: Verdana, 微软雅黑,黑体">
        <span style="float:right; padding-right:20px;" class="head">
        <a href="javascript:void(0)" onclick="exit()" style="font-size: 14px; float:right; padding-right:20px;color:#FFF; text-decoration:none;">安全退出</a></span>
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
                <li><span><a href="javascript:void(0)" onclick="addTab('实名认证列表', '#">实名认证列表</a></span></li>  
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
