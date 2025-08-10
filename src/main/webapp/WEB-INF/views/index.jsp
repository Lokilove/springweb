<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/com-taglibs.jspf" %>
<%@ include file="/WEB-INF/views/common/com-stylesheet.jspf" %>
<%@ include file="/WEB-INF/views/common/com-script.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <title>ホーム</title>
    <meta charset="UTF-8">
</head>
<body>
<!-- ヘッダー -->
<%@ include file="/WEB-INF/views/component/header.jsp" %>
<!-- レイアウト -->
<%--<div class="d-flex">--%>
<div id="wrapper">
    <!-- 左ナビ -->
    <%@ include file="/WEB-INF/views/component/navigator.jsp" %>
    <!-- メインエリア -->
    <iframe id="main-content" src="<c:url value='/main' />"></iframe>
</div>
</body>
</html>