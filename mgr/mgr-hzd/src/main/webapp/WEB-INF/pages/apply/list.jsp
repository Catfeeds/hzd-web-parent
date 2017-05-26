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
			{field:'rowNumbers',  
			    title: '序号',  
			    align: 'center',  
			    width: 40,  
			    formatter: function(val,rec,index){  
			       var op = $('#grid').datagrid('options');  
			      return op.pageSize * (op.pageNumber - 1) + (index + 1);  
			    }  
				},
		   {field:'applyId',title:'applyId',width:30,hidden:'true'},
		   {field:'mobile',title:'手机号',width:120,formatter:function(value){
			   return removeHTMLTag(value);
		   }},
			{field:'name',title:'姓名',width:60,formatter:function(value){
				return removeHTMLTag(value);
			}},
			{field:'idCard',title:'身份证号',width:190,formatter:function(value){
				return removeHTMLTag(value);
			}},
		    {field:'loanPurposeOne',title:'借款用途',width:80,formatter:function(value){
				return removeHTMLTag(value);
			}},
			{field:'loanPurposeTwo',title:'借款用途详情',width:100,formatter:function(value){
				return removeHTMLTag(value);
			}},
			{field:'minApplyAmount',title:'申请最低额度',width:100},
			{field:'maxApplyAmount',title:'申请最高额度',width:100},
			{field:'maxMonthlyPayment',title:'月还款最高额度',width:100},
			{field:'applySubmitTime',title:'提交时间',width:100,formatter:formatDatebox},
			{field:'status',title:'进件状态',width:80,formatter:function(value){
				if(value =="2"){
					return "未进件";
				}
				if(value=="1"){
					return "已进件";
				}
			}},

            {field:'additionalSubmitTime',title:'补充提交时间',width:100,formatter:formatDatebox},
            {field:'additionalStatus',title:'补充状态',width:80,formatter:function(value){
                if(value =="0"){
                    return "待补充";
                }
                if(value=="1"){
                    return "已补充";
                }
            }},
            { field: "additionalContent", title: '待补充内容', width:100,halign:'center', formatter: function (value) {
		    	if(value != null){
                    return "<span title='" + value + "'>" + value + "</span>";
				}
            }},

		   {field:'-',title:'操作',width:160,formatter:function(value,row,index){
		   	var html ="";
		    	if(row.additionalStatus == "1"){
					html  +="<a href='#' onclick='detail(\""+row.applyId+"\,"+row.mobile+"\");' > 查看详情  </a> | <a href='#' onclick='adddetail(\""+row.applyId+"\,"+row.mobile+"\");' > 补充详情</a>";
                }else{
					html  +="<a href='#' onclick='detail(\""+row.applyId+"\,"+row.mobile+"\");' > 查看详情   </a>";
                }
				if(row.status == "2" && row.checkStatus == "0"){
					html  +="<a href='#' onclick='anewSubmitApply(\""+row.applyId+"\,"+row.mobile+"\");' > 重新提交   </a>";
				}
				return html;
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
		status:$("#status").combobox('getValue'),

        addStartDate: $('#addStartDate').datebox('getValue'),
        addEndDate: $('#addEndDate').datebox('getValue'),
        additionalStatus:$("#additionalStatus").combobox('getValue')
	});
}

function doExport(){
	//window.location="${path}/member/excel";
	$("#searchForm").attr("action", "${path}/apply/excel");
	 $("#searchForm").attr("method", "POST");
	 $("#searchForm").submit();
}

//查看详情
function detail(det){
	var arr=det.split(",");
	var applyId = arr[0];
	var mobile = arr[1];
	window.location = '${path}/apply/check/detail?applyId='+applyId+"&mobile="+mobile;
}

//补充详情
function adddetail(det){
    var arr=det.split(",");
    var applyId = arr[0];
    var mobile = arr[1];
    window.location = '${path}/apply/check/addDetail?applyId='+applyId+"&mobile="+mobile;
}
//重新提交
function anewSubmitApply(det) {
	var arr=det.split(",");
	var applyId = arr[0];
	var mobile = arr[1];
	var win = $.messager.progress({
		title:'请稍后',
		msg:'数据正在提交'
	});
	$.ajax({
		type:"POST",
		url: '${path}/apply/anewSubmitApply',
		dataType:'json',
		data:{
			"applyId" : applyId,
			"mobile" : mobile,
		},
		success:function(result){
			$.messager.progress('close');
			if(result.success){
				$.messager.alert("提示","提交成功!","info",function(){
					window.location = '${path}/apply/list';
					return null;

				});
			}else{
				$.messager.alert("提示",result.msg,"error",function(){
					window.location = '${path}/apply/list';
					return null;
				});
			}
		}
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
	<input id="mobile" name="mobile" class="easyui-textbox"  style="width:120px;"/>
	<span>用户姓名:</span>
	<input id="name" name="name" class="easyui-textbox"  style="width:120px;"/>
	<span>身份证号:</span>
	<input id="idCard" name="idCard" class="easyui-textbox"  style="width:120px;">
	<span>提交时间:</span>
	<input id="startDate" name="startDate" class="easyui-datebox" style="width:120px; line-height:26px;border:1px solid #ccc"/>至
	<input id="endDate" name="endDate" class="easyui-datebox" style="width:120px; line-height:26px;border:1px solid #ccc"/>
	<span>进件状态:</span>
	<select id="status" name="status" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'">
    	<option value="">请选择</option>
    	<option value="2">未进件</option>
    	<option value="1">已进件</option>
    </select>
	<span><br></span>
	<span>补充提交时间:</span>
	<input id="addStartDate" name="addStartDate" class="easyui-datebox" style="width:120px; line-height:26px;border:1px solid #ccc"/>至
	<input id="addEndDate" name="addEndDate" class="easyui-datebox" style="width:120px; line-height:26px;border:1px solid #ccc"/>
	<span>补充状态:</span>
	<select id="additionalStatus" name="status" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'">
		<option value="">请选择</option>
		<option value="0">待补充</option>
		<option value="1">已补充</option>
	</select>
<!-- 	<span>借款状态:</span>
	<select  class="easyui-combobox" style="width:120px;">
		<option value="">请选择</option>
		<option value="1">是</option>
		<option value="0">否</option>  
	</select>  -->	 
	
	<div class="btnDiv">
		<span align="center"><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search"  onclick="doSearch()">查询</a></span>
		<span align="center"><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save"  onclick="doExport()">导出Excel</a></span>
	</div>
	</form>
</div>
</body>
</html>
