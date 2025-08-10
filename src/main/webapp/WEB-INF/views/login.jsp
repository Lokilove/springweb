<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/com-taglibs.jspf" %>
<%@ include file="/WEB-INF/views/common/com-stylesheet.jspf" %>
<%@ include file="/WEB-INF/views/common/com-script.jspf" %>
<!DOCTYPE html>
<html>
<head>
    <title>ログイン</title>
</head>
<body class="bg-light">
<div class="container">
    <div class="row justify-content-center mt-5">
        <div class="col-md-4">
            <div class="card shadow-sm">
                <div class="card-header text-center">
                    <h4>ログイン</h4>
                </div>
                <c:if test="${not empty requestScope.loginError}">
                    <div class="alert alert-danger mb-0 rounded-0 text-center">
                            ${requestScope.loginError}
                    </div>
                </c:if>
                <div class="card-body">
                    <form method="post" action="<c:url value='/login' />">
                        <sec:csrfInput/>
                        <div class="mb-3">
                            <label for="username" class="form-label">ユーザー名</label>
                            <input type="text" class="form-control" name="username" id="username" required>
                        </div>
                        <div class="mb-3">
                            <label for="password" class="form-label">パスワード</label>
                            <input type="password" class="form-control" name="password" id="password" required>
                        </div>
                        <button type="submit" class="btn btn-primary w-100">ログイン</button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
