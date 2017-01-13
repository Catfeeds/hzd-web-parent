<%@page import="org.apache.maven.model.Model"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>进件信息详情</title>
<link href="${path}/script/easyui/themes/detailstyle.css" rel="stylesheet" type="text/css" />
<%@include file="../common/include.jsp"%>
<script type="text/javascript">

</script>
</head>
<body>
	<div class="content2">
	    <div class="userInfo">
	        <h1>借款需求信息</h1>
	        <table cellpadding="0" cellspacing="0">
				<tr>	
					<td>借款用途：<span>${userApplyInfo.loanPurposeOne}</span></td>
					<td>借款用途详情：<span>${userApplyInfo.loanPurposeTwo}</span></td>
					<td>申请最低额度：<span>${userApplyInfo.minApplyAmount}</span></td>
					<td>申请最高额度：<span>${userApplyInfo.maxApplyAmount}</span></td>
				</tr>
				<tr>
					<td>月还款最高额度：<span>${userApplyInfo.maxMonthlyPayment}</span></td>
					<td>期数（月）：<span>${userApplyInfo.period}</span></td>
				</tr>
			</table>
		</div>
		<div class="userInfo">
	        <h1>个人资料信息 </h1>
	        <table cellpadding="0" cellspacing="0">
				<tr>
					<td>姓名：<span>${user.name}</span></td>
					<td>身份证号：<span>${user.idCard}</span></td>
					<td>证件有效期：<span>${userInfo.idcardValidity}</span></td>
					<td>学历：<span>${userInfo.education}</span></td>
					<td>出生日期：<span><fmt:formatDate value="${user.birthday }" pattern="yyyy-MM-dd"/></span></td>
				</tr>
				<tr>
					<td>户籍所在省：<span>${userInfo.domicileProvince}</span></td>
					<td>户籍所在市：<span>${userInfo.domicileCity}</span></td>
					<td>户籍详细地址：<span>${userInfo.domicileAddress}</span></td>
					<td>婚姻状况：<span>${userInfo.marriageStatus}</span></td>
					<td>性别：<span>${user.gender}</span></td>
				</tr>
				<tr>
					<td>有无子女：<span>${userInfo.childrenStatus}</span></td>
					<td>房产情况：<span>${userInfo.houseStatus}</span></td>
					<td>家庭所在省：<span>${userInfo.residentProvince}</span></td>
					<td>户籍邮政编码：<span>${userInfo.domicilePostCode}</span></td>
				</tr>
				<tr>
					<td>家庭所在市：<span>${userInfo.residentCity}</span></td>
					<td>家庭详细地址：<span>${userInfo.residentAddress}</span></td>
					<td>家庭电话号码：<span>${userInfo.residentTelCode}</span></td>
					<td>电子邮件地址：<span>${userInfo.email}</span></td>
					<td>家庭电话区号：<span>${userInfo.residentTelAreaCode}</span></td>
				</tr>
				<tr>
					<td>个人年收入：<span>${userInfo.annualIncome}</span></td>
					<td>信用卡最高额度：<span>${userInfo.creditCardLimit}</span></td>
					<td>共同居住者：<span>${userInfo.liveTogether}</span></td>
					<td>家庭邮政编码：<span>${userInfo.residentPostCode}</span></td>
				</tr>
				<tr>
					<td>员工编号：<span>${userInfo.staffNo}</span></td>
				</tr>
			</table>
		</div>
        <div class="userInfo">
            <h1>工作信息</h1>
            <table cellpadding="0" cellspacing="0">
				<tr>
					<td>单位名称：<span>${userInfo.orgName}</span></td>
					<td>单位性质：<span>${userInfo.orgType}</span></td>
					<td>单位所在省：<span>${userInfo.orgProvince}</span></td>
					<td>单位所在市：<span>${userInfo.orgCity}</span></td>
				</tr>
				<tr>
					<td>单位详细地址：<span>${userInfo.orgAddress}</span></td>
					<td>单位电话号码：<span>${userInfo.orgTelCode}</span></td>
					<td>单位邮政编码：<span>${userInfo.orgPostCode}</span></td>
					<td>单位电话区号：<span>${userInfo.orgTelAreaCode}</span></td>
				</tr>
			</table>
		</div>
        <div class="userInfo">
            <h1>联系人信息</h1>
            <table cellpadding="0" cellspacing="0">
				<c:forEach items="${userRelationVOList }" var="userRelation" varStatus="s">
					<tr>
						<c:if test = "${userRelation.type == '5'}">
							<th align="left">工作证明人</th>
						</c:if>
						<c:if test = "${userRelation.type == '3'}">
							<th align="left">家庭证明人</th>
						</c:if>
						<c:if test = "${userRelation.type == '4'}">
							<th align="left">紧急联系人</th>
						</c:if>
					</tr>
					<tr>
						<td>姓名：<span>${userRelation.name}</span></td>
						<td>和本人关系：<span>${userRelation.relationType}</span></td>
						<td>手机号：<span>${userRelation.mobile}</span></td>
					</tr>          
				</c:forEach>
			</table>
		</div>
		<div class="userInfo">
            <h1>其他</h1>
            <table cellpadding="0" cellspacing="0">
				<tr>
					<c:if test = "${userInfo.productId == '01'}">
						<td>贷款类型：<span>精英贷1.89</span></td>
					</c:if>
				</tr>
				<tr>
					<c:if test = "${userInfo.isInside == '1'}">
						<td>网内/网外：<span>网内</span></td>
					</c:if>
					<c:if test = "${userInfo.isInside == '0'}">
						<td>网内/网外：<span>网外</span></td>
					</c:if>
				</tr>
			</table>
		</div>
		<div class="userInfoBox">
           	<h1>上传图片展示</h1>
           	<h3>身份证</h3>
			<c:forEach items="${userImageVOList}" var="userImage" varStatus="s">
				<c:if test = "${userImage.imageType == 'B1'}">
					<div class="gallery">
						<div class="imgBox">
	                    	<div><img src="${userImage.artWork}" /></div>
		                </div>
					</div>
				</c:if>
			</c:forEach>
			</div>
			
			<div class="userInfoBox">
			<h3>征信报告</h3>
			<c:forEach items="${userImageVOList}" var="userImage" varStatus="s">
				<c:if test = "${userImage.imageType == 'E1'}">
					<div class="gallery">
						<div class="showImage2">
	                    	<div><img src="${userImage.artWork}" /></div>
		                </div>
					</div>
				</c:if>
			</c:forEach>
			</div>
			<div class="userInfoBox">
			<h3>个人住址证明</h3>
			<c:forEach items="${userImageVOList}" var="userImage" varStatus="s">
				<c:if test = "${userImage.imageType == 'F7'}">
					<div class="gallery">
						<div class="showImage2">
	                    	<div><img src="${userImage.artWork}" /></div>
		                </div>
					</div>
				</c:if>
			</c:forEach>
			</div>
			<div class="userInfoBox">
			<h3>收入证明/社保/公积金</h3>
			<c:forEach items="${userImageVOList}" var="userImage" varStatus="s">
				<c:if test = "${userImage.imageType == 'C8'}">
					<div class="gallery">
						<div class="showImage2">
	                    	<div><img src="${userImage.artWork}" /></div>
		                </div>
					</div>
				</c:if>
			</c:forEach>
			</div>
			<div class="userInfoBox">
			<h3>工作证明</h3>
			<c:forEach items="${userImageVOList}" var="userImage" varStatus="s">
				<c:if test = "${userImage.imageType == 'D8'}">
					<div class="gallery">
						<div class="showImage2">
	                    	<div><img src="${userImage.artWork}" /></div>
		                </div>
					</div>
				</c:if>
			</c:forEach>	
			</div>
			<div class="userInfoBox">
			<h3>其它证明</h3>
			<c:forEach items="${userImageVOList}" var="userImage" varStatus="s">
				<c:if test = "${userImage.imageType == 'L5'}">
					<div class="gallery">
						<div class="showImage2">
	                    	<div><img src="${userImage.artWork}" /></div>
		                </div>
					</div>
				</c:if>
			</c:forEach>											
		</div>
	</div>
</body>
</html>
