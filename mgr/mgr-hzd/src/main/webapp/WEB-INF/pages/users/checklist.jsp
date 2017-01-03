<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>实名认证管理</title>
<%@include file="../common/include.jsp"%> 

<script type="text/javascript">
$(function(){
	$('#grid').datagrid({
		url:'${path}/users/check/page',
		columns:[[
		   {field:'id',title:'id',width:30,hidden:true},
		   {field:'mobile',title:'手机号',width:150},
			{field:'name',title:'姓名',width:150},
			{field:'idCard',title:'身份证号',width:180},
			{field:'submitTime',title:'提交时间',width:120},
			{field:'checkStatus',title:'审核状态',width:100},
			{field:'nopassCause',title:'不通过原因',width:100},
		   {field:'-',title:'操作',width:100,formatter:function(value,row,index){
			   //return "<a href='#' onClick='edit(" + row.id + ")'>修改 </a> <a href='#' onClick='dele(" + row.id + ")'>下线</a> ";
			   return "<a href='#' onclick='edit(\""+row.id+"\");' >查看详情 </a>&nbsp;<a href='#' onclick='dele(\""+row.id+"\");' >修改</a>";   
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
		toolbar: '#toolbar'
	});
});

function doSearch(){
	$('#grid').datagrid('load',{
		mobile: $('#mobile').val(),
		name: $('#name').val(),
		idCard: $('#idCard').val(),
		//buyRedpackCount: $('#buyRedpackCount').combobox('getValue'),
		subStartDate: $('#subStartDate').datebox('getValue'),
		subEndDate: $('#subEndDate').datebox('getValue'),
		checkStatus: $('#checkStatus').datebox('getValue')
	});
}
</script>

</head>
<body>

<table id="grid" style="width: 520px;height: 550px"></table>

<div id="toolbar">
	<!-- 查询条件 -->
	<form id="searchForm" >
	<span>用户手机号:</span>
	<input id="mobile" name="mobile" class="easyui-textbox"  style="width:120px;">
	<span>用户姓名:</span>
	<input id="name" name="name" class="easyui-textbox"  style="width:120px;">
	<span>身份证号:</span>
	<input id="idCard" name="idCard" class="easyui-textbox"  style="width:120px;">
	<span>提交日期:</span>
	<input id="subStartDate" name="subStartDate" class="easyui-datebox" style="width:120px; line-height:26px;border:1px solid #ccc">至
	<input id="subEndDate" name="subEndDate" class="easyui-datebox" style="width:120px; line-height:26px;border:1px solid #ccc">
 	<span>审核状态:</span>
	<select id="checkStatus" name="checkStatus" class="easyui-combobox" style="width:120px;">
		<option value="">请选择</option>
		<option value="2">待审核</option>
		<option value="0">通过</option>
		<option value="1">不通过</option>  
	</select> 	
	
	<div class="btnDiv">
		<span align="center"><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search"  onclick="doSearch()">查询</a></span>
		<span align="center"><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save"  onclick="doExport()">导出Excel</a></span>
	</div>
	</form>
</div>
</body>
</html>
