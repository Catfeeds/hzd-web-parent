<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>系统日志</title>
<%@include file="../common/include.jsp"%> 
<script src="${path}/script/common/dateUtil.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$('#grid').datagrid({
		url:'${path}/systemLoglist',
		columns:[[
		{field:'rowNumbers',  
		    title: '序号',  
		    align: 'center',  
		    width: 30,  
		    formatter: function(val,rec,index){  
		       var op = $('#grid').datagrid('options');  
		      return op.pageSize * (op.pageNumber - 1) + (index + 1);  
		    }  
			},
		   {field:'logId',title:'logId',width:30,hidden:true},
		   {field:'applyId',title:'申请单号',width:150},
			{field:'idCard',title:'身份证号',width:100},
			{field:'returnContent',title:'信息反馈',width:100},
			{field:'returnTime',title:'提交时间',width:120,formatter:formatDateBoxFull},
			{field:'applyType',title:'类型',width:100,formatter:function(value){
		    	if(value=="0"){
		    		return "通过";
		    	}
		    	if(value=="1"){
		    		return "不通过";
		    	}
		    	if(value=="2"){
		    		return "待审核";
		    	}
			}}/*,

		   {field:'-',title:'操作',width:100,formatter:function(value,row,index){
			   //return "<a href='#' onClick='edit(" + row.id + ")'>修改 </a> <a href='#' onClick='dele(" + row.id + ")'>下线</a> ";
			   if(row.checkStatus==0){
				   	return "<a href='#' onclick='detail(\""+row.mobile+"\");' >查看详情 </a>&nbsp;<a href='#' onclick='edit(\""+row.mobile+"\");' >修改</a>";   
				}else{
					return "<a href='#' onclick='detail(\""+row.mobile+"\");' >查看详情</a>";
				}   
		   }}*/
		  
		]],
		title : '日志列表',
		iconCls : 'icon-ok',
		fitColumns:true,
		fit:true,
		singleSelect:true,
		pagination:true,//开启分页
		//pageSize:1,//默认每页大小。暂时设置为1，方便调试
		//pageList:[1,2,3,4,5,10,20,30,40,50],//可以设定的每页大小
		collapsible:true,
		toolbar: '#toolbar'
	});
});

function detail(mobile){
	//$('#grid').datagrid({
		//url:'${path}/users/check/detail?mobile='+mobile,
		//mobile:mobile
	//});;
	window.location = '${path}/users/check/detail?mobile='+mobile;
}

function edit(mobile){
	 window.location = '${path}/users/check/edit?mobile='+mobile;
}
function doSearch(){
	$('#grid').datagrid('load',{
		applyId: $('#applyId').val(),

		idCard: $('#idCard').val()

	});
}

function doExport(){
	//window.location="${path}/member/excel";
	$("#searchForm").attr("action", "${path}/checkuser/excel");
	 $("#searchForm").attr("method", "POST");
	 $("#searchForm").submit();
}
</script>

</head>
<body>

<table id="grid" style="width: 520px;height: 550px"></table>

<div id="toolbar">
	<!-- 查询条件 -->
	<form id="searchForm" >
	<span>申请单号:</span>
	<input id="applyId" name="applyId" class="easyui-textbox"  style="width:120px;">
	<span>身份证号:</span>
	<input id="idCard" name="idCard" class="easyui-textbox"  style="width:120px;">

	<div class="btnDiv">
		<span align="center"><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search"  onclick="doSearch()">查询</a></span>
	</div>
	</form>
</div>
</body>
</html>
