<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进件管理</title>
<%@include file="../common/include.jsp"%> 
<script src="${path}/script/common/dateUtil.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	$('#grid').datagrid({
		url:'${path}/apply/page',
		columns:[[
		   {field:'applyId',title:'applyId',width:230},
		   {field:'mobile',title:'手机号',width:150},
			{field:'name',title:'姓名',width:150},
			{field:'idCard',title:'身份证号',width:180},
		    {field:'loanPurposeOne',title:'借款用途',width:100},
			{field:'loanPurposeTwo',title:'借款用途详情',width:120},
			{field:'minApplyAmount',title:'申请最低额度',width:100},
			{field:'maxApplyAmount',title:'申请最高额度',width:100},
			{field:'maxMonthlyPayment',title:'月还款最高额度',width:100},
			{field:'applySubmitTime',title:'提交时间',width:200,formatter:formatDateBoxFull},//引用外部JS
			{field:'status',title:'进件状态',width:100,formatter:function(value){
				if(value =="0"){
					return value+"未进件";
				}
				if(value=="1"){
					return value+"已进件";
				}
				if(value=="2"){
					return value+"待审核";
				}
			}},
		   {field:'-',title:'操作',width:100,formatter:function(value,row,index){
			   return "<a href='#' onclick='detail(\""+row.applyId+"\,"+row.mobile+"\");' > 查看详情   </a>";   
		   }}
		  
		]],
		title : '进件列表',
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


function doSearch(){
	$('#grid').datagrid('load',{
		mobile: $('#mobile').val(),
		name: $('#name').val(),
		idCard: $('#idCard').val(),
		//buyRedpackCount: $('#buyRedpackCount').combobox('getValue'),
		startDate: $('#startDate').datebox('getValue'),
		endDate: $('#endDate').datebox('getValue'),
		status:$("#status").combobox('getValue')
	});
}

function doExport(){
	//window.location="${path}/member/excel";
	$("#searchForm").attr("action", "${path}/apply/excel");
	 $("#searchForm").attr("method", "POST");
	 $("#searchForm").submit();
}

function detail(det){
	var arr=det.split(",");
	var applyId = arr[0];
	var mobile = arr[1];
	window.location = '${path}/apply/check/detail?applyId='+applyId+"&mobile="+mobile;
	
}
</script>

</head>
<body>

<table id="grid" style="width: 520px;height: 550px"></table>

<div id="toolbar">
	<!-- 查询条件 -->
	<form id="searchForm" >
	<span>用户手机号:</span>
	<input id="mobile" name="mobile" class="easyui-textbox"  style="width:120px;"/>
	<span>用户姓名:</span>
	<input id="name" name="name" class="easyui-textbox"  style="width:120px;"/>
	<!-- <span>身份证号:</span>
	<input id="idCard" name="idCard" class="easyui-textbox"  style="width:120px;"> -->
	<span>提交时间:</span>
	<input id="startDate" name="startDate" class="easyui-datebox" style="width:120px; line-height:26px;border:1px solid #ccc"/>至
	<input id="endDate" name="endDate" class="easyui-datebox" style="width:120px; line-height:26px;border:1px solid #ccc"/>
	<span>进件状态:</span>
	<select id="status" name="status" class="easyui-combobox" style="width:120px;" data-options="editable:false,panelHeight:'auto'">
		<option value="">请选择</option>
		<option value="0">待进件</option>
		<option value="1">已进件</option>  
	</select>
	
	<div class="btnDiv">
		<span align="center"><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search"  onclick="doSearch()">查询</a></span>
		<span align="center"><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save"  onclick="doExport()">导出Excel</a></span>
	</div>
	</form>
</div>
</body>
</html>
