<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set value="${pageContext.request.contextPath}" var="path" scope="page"/>
<script type="text/javascript">

    function removeHTMLTag(str) {
        if(str == null){
           return '';
        }
        str = str.replace(/<\/?[^>]*>/g,''); //去除HTML tag
       // str = str.replace(/[ | ]*\n/g,'\n'); //去除行尾空白
        //str = str.replace(/\n[\s| | ]*\r/g,'\n'); //去除多余空行
        str=str.replace(/ /ig,'');//去掉
        return str;
    }
</script>

<script src="${path}/script/easyui/js/jquery.min.js" type="text/javascript"></script>
<script src="${path}/script/easyui/js/jquery.easyui.min.js" type="text/javascript"></script>
<link href="${path}/script/easyui/themes/default/easyui.css" rel="stylesheet" type="text/css" />
<link href="${path}/script/easyui/themes/icon.css" rel="stylesheet" type="text/css" />
<link href="${path}/script/easyui/themes/style.css" rel="stylesheet" type="text/css" />
<link href="${path}/script/easyui/themes/detailstyle.css" rel="stylesheet" type="text/css" />
<link href="${path}/script/easyui/css/zoom.css" rel="stylesheet" type="text/css">
<script src="${path}/script/easyui/js/easyui-lang-zh_CN.js" type="text/javascript"></script>
<script src="${path}/script/easyui/js/jquery-form.js" type="text/javascript"></script>

