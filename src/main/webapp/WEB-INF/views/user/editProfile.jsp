<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/views/common/com-taglibs.jspf" %>
<%@ include file="/WEB-INF/views/common/com-stylesheet.jspf" %>

<!-- ページタイトル用の非表示要素 -->
<div id="pageTitle" style="display:none;">本人情報変更</div>

<div class="container">
    <h2 class="mb-4">本人情報変更</h2>
    <!-- 情報フォーム -->
    <c:url var="updateUrl" value="/user/updateProfile"/>
    <form:form method="post" action="${updateUrl}" modelAttribute="userForm" id="profileForm">
        <div class="row mb-3">
            <form:label path="userId" cssClass="col-sm-2 col-form-label">ユーザーID</form:label>
            <div class="col-sm-10">
                <form:input path="userId" cssClass="form-control" readonly="true"/>
            </div>
        </div>
        <div class="row mb-3">
            <form:label path="username" cssClass="col-sm-2 col-form-label">氏名</form:label>
            <div class="col-sm-10">
                <form:input path="username" cssClass="form-control"/>
            </div>
        </div>
        <div class="row mb-3">
            <form:label path="email" cssClass="col-sm-2 col-form-label">メールアドレス</form:label>
            <div class="col-sm-10">
                <form:input path="email" type="email" cssClass="form-control"/>
            </div>
        </div>
        <div class="row mb-3">
            <form:label path="nowPassword" cssClass="col-sm-2 col-form-label">現パスワード<br/>
                <span style="color: red; font-size: 12px;">※ 必須入力</span></form:label>
            <div class="col-sm-10">
                <form:password path="nowPassword" cssClass="form-control"/>
            </div>
        </div>
        <div class="row mb-3">
            <form:label path="newPassword" cssClass="col-sm-2 col-form-label">新パスワード<br/>
                <span style="color: red; font-size: 12px;">※ 変更の場合のみ入力</span></form:label>
            <div class="col-sm-10">
                <form:password path="newPassword" cssClass="form-control"/>
            </div>
        </div>
        <div class="row mb-3">
            <form:label path="confirmPassword" cssClass="col-sm-2 col-form-label">新パスワード<br/>
                <span style="color: red; font-size: 12px;">※ 変更の場合のみ入力</span></form:label>
            <div class="col-sm-10">
                <form:password path="confirmPassword" cssClass="form-control"/>
            </div>
        </div>
        <div class="d-grid gap-2 col-6 mx-auto">
            <button type="submit" class="btn btn-primary">更　　新</button>
        </div>
    </form:form>
</div>
