<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户管理</title>
<%@include file="../common/include.jsp"%> 
<script src="${path}/script/common/dateUtil.js" type="text/javascript"></script>
<script type="text/javascript">
$(function(){
	//获取借款人列表数据
	$('#grid').datagrid({
		url:'${path}/users/page',
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
		   {field:'id',title:'id',width:30,hidden:true},
		   {field:'mobile',title:'手机号',width:150,formatter:function(value){
			   return removeHTMLTag(value);
		   }},
			{field:'name',title:'姓名',width:100,formatter:function(value){
				return removeHTMLTag(value);
			}},
			{field:'idCard',title:'身份证号',width:180,formatter:function(value){
				return removeHTMLTag(value);
			}},
		    {field:'status',title:'用户状态',width:100,formatter:function(value){
		    	if(value=="0"){
		    		return "正常";
		    	}
		    	if(value=="1"){
		    		return "禁用";
		    	}
		    }},
			{field:'createTime',title:'注册时间',width:120,formatter:formatDateBoxFull},
			{field:'applyStatus',title:'进件状态',width:100,formatter:function(value){
				if(value =="0"||value=="2"){
					return "未进件";
				}
				if(value=="1"){
					return "已进件";
				}
			}},
		   {field:'-',title:'操作',width:100,formatter:function(value,row,index){
			   //return "<a href='#' onClick='edit(" + row.id + ")'>修改 </a> <a href='#' onClick='dele(" + row.id + ")'>下线</a> ";
			   // return "<a href='#' onclick='updatePassWord(\""+row.mobile+"\,"+row.name+"\");' >重置密码 </a>&nbsp;<a href='#' onclick='status(\""+row.mobile+"\,"+row.status+"\");'> row.status==0?"禁用":"启用"</a>";   
			  //value
			   return "<a href='javaScript:void(0);' onclick=updatePassWord('"+row.mobile+"','"+row.name+"'); >重置密码 </a>&nbsp;<a href='javaScript:void(0);' onclick=updateStatus('"+row.mobile+"','"+row.status+"');>"+(row.status==0?"禁用":"启用")+"</a>";
		   }}
		]],
		title : '用户列表',
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
	$("#pw1").tooltip({
		content:"密码长度需在8到16位之间",
	});
	$("#pw2").tooltip({
		content:"密码长度需在8到16位之间",
	});
	
	
	
});
//重置密码
function updatePassWord(mobile,name) {
	$("#div").html(mobile);
	if(name == "null"){
		$("#di").text("");
	}
	else{
		$("#di").text(name);
	}
    $("#dd").dialog({
	 closable: false, //右上角的关闭按钮，因为dialog框架关联的是window框架，window框架关联的是panel框架，所以该API是在panel框架中找到的
	 title: "重置密码", //dialog左上角的名称
	 modal: true, //模式化
	 width: 400,
	 height: 300,
	 buttons: [//dialog右下角的按钮，以Json数组形式添加
	    {
	    	text: "提交", //按钮名称
		    handler: function () {//按钮点击之后出发的方
		    	var passWord = $("#pw1").val();
		    	var pw = $("#pw2").val();
		    	if(passWord==null || passWord=="" || pw=="" || pw==null){
		    		$("#passwordRepeatSpan").html("新密码和重复密码均不能为空");
		    		return false;
		    	}
		    	if(passWord.length<8 || passWord.length>16 || pw.length<8 || pw.length>16){
		    		$("#passwordRepeatSpan").html("新密码长度需在8到16位之间");
		    		return false;
		    	}
		    	if(passWord!=pw){
		    		$("#passwordRepeatSpan").html("两次密码不一致");
		    		return false;
		    	}
	    		$("#msg").html("");
	    		$.ajax({
	    			type:"POST",
	    			url: '${path}/users/check/updatePassword',
	    			data:{
	    				"mobile" : mobile,
	    				"passWord" : passWord,
	    			},
	    			success:function(result){
	    				var result2=eval("("+result+")");
	    				$.messager.alert("",result2.message,(result2.code=="1"?"info":"error"),function(){
							window.location = '${path}/users/list';						
						});
	    				return true;
	    			}
	    		});
		    }
		},{
			text:"取消",
			handler: function () {
				window.location = '${path}/users/list';
			}
		}]
    });
}
//修改借款人的“禁用/启用”状态
function updateStatus(mobile,status){
	$.messager.confirm("确认对话框","确定"+(status==0?"禁用":"启用")+"吗？",function(flag){
		if(flag){
			$.ajax({
				type:"POST",
				url: '${path}/users/check/status',
				data:{
					"mobile" : mobile,
					"status" : (status==0?"1":"0")
				},
				success:function(result){
					var result2=eval("("+result+")");
					$.messager.alert("",result2.message,(result2.code=="1"?"info":"error"),function(){
						window.location = '${path}/users/list';						
					});
					return true;
				}
			});
			return true;
		}else{
			return false;
		}
	});
}
function doSearch(){
	$('#grid').datagrid('load',{
		mobile: $('#mobile').val(),
		name: $('#name').val(),
		idCard: $('#idCard').val(),
		//buyRedpackCount: $('#buyRedpackCount').combobox('getValue'),
		startDate: $('#startDate').datebox('getValue'),
		endDate: $('#endDate').datebox('getValue'),
		applyStatus:$("#applyStatus").combobox('getValue')
	});
}
function doExport(){
	//window.location="${path}/member/excel";
	$("#searchForm").attr("action", "${path}/user/excel");
	 $("#searchForm").attr("method", "POST");
	 $("#searchForm").submit();
}
function rest(){
    $('#mobile').val('');
    $('#name').val('');
    $('#idCard').val('');

    $('#startDate').datebox("setValue", '');
    $('#endDate').datebox("setValue", '');
    $("#applyStatus").combobox("setValue", '');
}
</script>

</head>
<body>

<table id="grid" style="width: 520px;height: 550px" ></table>
<div id="toolbar">
	<!-- 查询条件 -->
	<form id="searchForm" >
	<span>用户手机号:</span>
	<input id="mobile" name="mobile" class="easyui-textbox"  style="width:120px;"/>
	<span>用户姓名:</span>
	<input id="name" name="name" class="easyui-textbox"  style="width:120px;"/>
	<span>身份证号:</span>
	<input id="idCard" name="idCard" class="easyui-textbox"  style="width:120px;"/>
	<span>注册日期:</span>
	<input id="startDate" name="startDate" class="easyui-datebox" style="width:120px; line-height:26px;border:1px solid #ccc"/>至
	<input id="endDate" name="endDate" class="easyui-datebox" style="width:120px; line-height:26px;border:1px solid #ccc"/>
	<span>进件状态:</span>
	<select id="applyStatus" name="applyStatus" class="easyui-combobox" data-options="editable:false,panelHeight:'auto'">
    	<option value="">请选择</option>
    	<option value="0">未进件</option>
    	<option value="1">已进件</option>
    </select>
<!-- 	<span>借款状态:</span>
	<select  class="easyui-combobox" style="width:120px;">
		<option value="">请选择</option>
		<option value="1">是</option>
		<option value="0">否</option>  
	</select> 	 -->
	<div class="btnDiv">
		<span align="center"><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search"  onclick="doSearch()">查询</a></span>
		<span align="center"><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-reload" onclick="rest()">重置</a></span>
		<span align="center"><a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-save"  onclick="doExport()">导出Excel</a></span>
	</div>
	</form>
</div>
<div id="dd">
<form id="updatepw" method="psot">
	<table style="width: 300px;height: 200px">
		<tr>
			<th>
				手机号 :
			</th>
			<td>
				<div id="div"></div>
			</td>
		</tr>
		<tr>
			<th>
				用户名 :
			</th>
			<td id="di">
				<input id="userNameRepeat" class="easyui-textbox" prompt="Username" iconWidth="28"/>
			</td>
		</tr>
		<tr>
			<th>
				新密码 :
			</th>
			<td>
				<input id="pw1" type="password"/><br/>
				<span id="passwordRepeatSpan" style="color:red;"></span>
			</td>
		</tr>
		<tr>
			<th>
				确认新密码 :
			</th>
			<td>
				<input id="pw2" type="password"/>
			</td>
		</tr>
<!-- 		<tr>
			<td>
				<div id="msg" style="line-height: 100px; font-size: 1rem; color: red;"></div>
			</td>
		</tr> -->
	</table>
</form>
</div>

</body>
</html>
