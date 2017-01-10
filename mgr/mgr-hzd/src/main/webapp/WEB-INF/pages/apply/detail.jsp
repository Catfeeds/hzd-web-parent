<%@page import="org.apache.maven.model.Model"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进件信息详情</title>
<%@include file="../common/include.jsp"%> 
<script type="text/javascript">

</script>
</head>
<body>
	<table id="applyId" align="center" style="width: 2000px;height: 550px">
		<tr>
			<th align="left">借款需求信息</th>
		</tr>
			<tr>	
				<td align="left">借款用途：${userApplyInfo.loanPurposeOne}</td>
				<td align="left">借款用途详情：${userApplyInfo.loanPurposeTwo}</td>
				<td align="left">申请最低额度：${userApplyInfo.minApplyAmount}</td>
				<td align="left">申请最高额度：${userApplyInfo.maxApplyAmount}</td>
			</tr>
			<tr>
				<td align="left">月还款最高额度：${userApplyInfo.maxMonthlyPayment}</td>
				<td align="left">期数（月）：${userApplyInfo.period}</td>
			</tr>
		<tr></tr>
		<tr></tr>
		<tr></tr>
		<tr>
			<th align="left">个人资料信息</th>
		</tr>
			<tr>
				<td align="left">姓名：${user.name}</td>
				<td align="left">身份证号：${user.idCard}</td>
				<td align="left">证件有效期：${userInfo.idcardValidity}</td>
				<td align="left">学历${userInfo.education}</td>
				<td align="left">出生日期：${user.birthday}</td>
			</tr>
			<tr>
				<td align="left">户籍所在省：${userInfo.domicileProvince}</td>
				<td align="left">户籍所在市：${userInfo.domicileCity}</td>
				<td align="left">户籍详细地址：${userInfo.domicileAddress}</td>
				<td align="left">婚姻状况：${userInfo.education}</td>
				<td align="left">性别：${user.gender}</td>
			</tr>
			<tr>
				<td align="left">有无子女：${userInfo.childrenStatus}</td>
				<td align="left">房产情况：${userInfo.houseStatus}</td>
				<td align="left">家庭所在省：${userInfo.residentProvince}</td>
				<td align="left">户籍邮政编码：${userInfo.domicilePostCode}</td>
			</tr>
			<tr>
				<td align="left">家庭所在市：${userInfo.residentCity}</td>
				<td align="left">家庭详细地址：${userInfo.residentAddress}</td>
				<td align="left">家庭电话号码：${userInfo.residentTelCode}</td>
				<td align="left">电子邮件地址：${userInfo.email}</td>
				<td align="left">家庭电话区号：${userInfo.residentTelAreaCode}</td>
			</tr>
			<tr>
				<td align="left">个人年收入：${userInfo.annualIncome}</td>
				<td align="left">信用卡最高额度：${userInfo.creditCardLimit}</td>
				<td align="left">共同居住者：${userInfo.liveTogether}</td>
				<td align="left">家庭邮政编码${userInfo.residentPostCode}</td>
			</tr>
			<tr>
				<td align="left">员工编号：${userInfo.staffNo}</td>
			</tr>
		<tr></tr>
		<tr></tr>
		<tr></tr>
		<tr>
			<th align="left">工作信息</th>
		</tr>
			<tr>
				<td align="left">单位名称：${userInfo.orgName}</td>
				<td align="left">单位性质：${userInfo.orgType}</td>
				<td align="left">单位所在省：${userInfo.orgProvince}</td>
				<td align="left">单位所在市：${userInfo.orgCity}</td>
			</tr>
			<tr>
				<td align="left">单位详细地址：${userInfo.orgAddress}</td>
				<td align="left">单位电话号码：${userInfo.orgTelCode}</td>
				<td align="left">单位邮政编码：${userInfo.orgPostCode}</td>
				<td align="left">单位电话区号：${userInfo.orgTelAreaCode}</td>
			</tr>
		<tr></tr>
		<tr></tr>
		<tr></tr>	
		<tr>
			<th align="left">联系人信息</th>
		</tr>
			<c:forEach items="${userRelationVOList }" var="userRelation" varStatus="s">
				<tr>
					<c:if test = "${userRelation.type == 0}">
						<td align="left"><div id="userRelationtype">工作证明人</div></td>
					</c:if>
					<c:if test = "${userRelation.type == 1}">
						<td align="left"><div id="userRelationtype">家庭证明人</div></td>
					</c:if>
					<c:if test = "${userRelation.type == 2}">
						<td align="left"><div id="userRelationtype">紧急联系人</div></td>
					</c:if>
				</tr>
				<tr>
					<td align="left">姓名：${userRelation.name}</td>
					<td align="left">和本人关系：${userRelation.relationType}</td>
					<td align="left">手机号：${userRelation.mobile}</td>
				</tr>          
			</c:forEach>
		<tr></tr>
		<tr></tr>
		<tr></tr>		
		<tr>
			<th align="left">其他</th>
		</tr>
			<tr>
				<td align="left">贷款类型：${userInfo.productId}</td>
			</tr>
			<tr>
				<td align="left">网内/网外：
					<c:if test = "${userInfo.isInside == 0}">
						<td align="left"><div id="isinside">网外</div></td>
					</c:if>
					<c:if test = "${userInfo.isInside == 1}">
						<td align="left"><div id="isinside">网内</div></td>
					</c:if>
				</td>
			</tr>
		<tr></tr>
		<tr></tr>
		<tr></tr>
		<tr>
			<th align="left">上传图片展示</th>
		</tr>
			<c:forEach items="${userImageVOList}" var="userImage" varStatus="s">
				<tr>
					<c:if test = "${userImage.type == 0}">
						<td align="left"><div id="userRelationtype">身份证</div></td>
					</c:if>
					<c:if test = "${userImage.type == 1}">
						<td align="left"><div id="userRelationtype">征信报告</div></td>
					</c:if>
					<c:if test = "${userImage.type == 2}">
						<td align="left"><div id="userRelationtype">个人住址证明</div></td>
					</c:if>
					<c:if test = "${userImage.type == 3}">
						<td align="left"><div id="userRelationtype">收入证明</div></td>
					</c:if>
					<c:if test = "${userImage.type == 4}">
						<td align="left"><div id="userRelationtype">实名认证图片</div></td>
					</c:if>
					<c:if test = "${userImage.type == 5}">
						<td align="left"><div id="userRelationtype">工作证明</div></td>
					</c:if>
					<c:if test = "${userImage.type == 6}">
						<td align="left"><div id="userRelationtype">社保/公积金</div></td>
					</c:if>
					<c:if test = "${userImage.type == 7}">
						<td align="left"><div id="userRelationtype">其它</div></td>
					</c:if>
				</tr>
				<tr>
					<td><img style="width: 200px;height: 100px" src="${userImage.artWork}"></td>
				</tr>
			</c:forEach>
			
	</table>
</body>
</html>
