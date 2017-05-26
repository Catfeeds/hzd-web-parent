<%@page import="org.apache.maven.model.Model"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>补充信息详情</title>
<link href="${path}/script/easyui/themes/detailstyle.css" rel="stylesheet" type="text/css" />
<%@include file="../common/include.jsp"%>

	<script type="text/javascript">

</script>
</head>
<body>
	<div class="content2">

		<div class="userInfo">
			<h1>补充详情</h1>
			<table cellpadding="0" cellspacing="0">
				<tr>
					<td>补充提交时间 ：<span><fmt:formatDate value="${userApplyInfo.additionalSubmitTime }" pattern="yyyy-MM-dd HH:mm:ss"/></span></td>
				</tr>
				<tr>
					<td>需要补充的内容 ：<span>${userApplyInfo.additionalContent }</span></td>
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
	                    	<div><a href="${userImage.artWork}"><img src="${userImage.artWork}" /></a></div>
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
	                    	<div><a href="${userImage.artWork}"><img src="${userImage.artWork}" /></a></div>
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
	                    	<div><a href="${userImage.artWork}"><img src="${userImage.artWork}" /></a></div>
		                </div>
					</div>
				</c:if>
			</c:forEach>
			</div>
		<div class="userInfoBox">
		<h3>收入证明</h3>
		<c:forEach items="${userImageVOList}" var="userImage" varStatus="s">
			<c:if test = "${userImage.imageType == 'C8'}">
				<div class="gallery">
					<div class="showImage2">
						<div><a href="${userImage.artWork}"><img src="${userImage.artWork}" /></a></div>
					</div>
				</div>
			</c:if>
		</c:forEach>
	</div>

	<div class="userInfoBox">
		<h3>社保/公积金</h3>
		<c:forEach items="${userImageVOList}" var="userImage" varStatus="s">
			<c:if test = "${userImage.imageType == 'C3'}">
				<div class="gallery">
					<div class="showImage2">
						<div><a href="${userImage.artWork}"><img src="${userImage.artWork}" /></a></div>
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
	                    	<div><a href="${userImage.artWork}"><img src="${userImage.artWork}" /></a></div>
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
	                    	<div><a href="${userImage.artWork}"><img src="${userImage.artWork}" /></a></div>
		                </div>
					</div>
				</c:if>
			</c:forEach>											
		</div>
	</div>
	<script src="${path}/script/easyui/js/jquery-2.1.4.min.js"></script>
	<script src="${path}/script/easyui/js/zoom/zoom.min.js"></script>
</body>
</html>
