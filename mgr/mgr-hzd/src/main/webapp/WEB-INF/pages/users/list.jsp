<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<%@include file="../common/include.jsp"%> 

<script type="text/javascript">
$(function(){
	$('#grid').datagrid({
		url:'${path}/product/productList',
		columns:[[
		   {field:'id',title:'id',width:30,hidden:true},
		   {field:'mobile',title:'保险名称',width:180},
			{field:'name',title:'副标题',width:60},
			{field:'userName',title:'红包名称',width:60},
		    {field:'password',title:'保额',width:60},
			{field:'idCard',title:'保期',width:80},
			{field:'status',title:'保险公司',width:80},
			{field:'checkStatus',title:'投保年龄',width:80},
			{field:'nopassCause',title:'生效日期',width:100},
			{field:'createTime',title:'保单类型',width:50},
		   {field:'-',title:'操作',width:100,formatter:function(value,row,index){
			   //return "<a href='#' onClick='edit(" + row.id + ")'>修改 </a> <a href='#' onClick='dele(" + row.id + ")'>下线</a> ";
			   return "<a href='#' onclick='edit(\""+row.id+"\");' >修改</a>&nbsp;<a href='#' onclick='dele(\""+row.id+"\");' >下线</a>";   
		   }}
		  
		]],
		title : '用户列表',
		iconCls : 'icon-ok',
		fitColumns:true,
		fit:true,
		singleSelect:true,
		pagination:true,
		pageSize:20,
		collapsible:true,
		toolbar: [{
			iconCls: 'icon-add',
			text:'增加',
			handler: function(){
				
				$("#addForm .easyui-validatebox").val("")
				$('#addWindow').window('open');//打开窗口
			}
		}]
	});
});

</script>

</head>
<body>

<table id="grid" style="width: 520px;height: 550px"></table>


</body>
</html>
