<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top px-4" style="height: 56px;">
    <a class="navbar-brand" href="javascript:void(0)" onclick="loadPage('<c:url value='/main'/>')">ホームページ</a>
    <div class="ms-auto dropdown">
        <a href="#" class="d-flex align-items-center text-white text-decoration-none dropdown-toggle" id="userDropdown"
           data-bs-toggle="dropdown" aria-expanded="false">
            <img src="<c:url value='/static/img/user.png'/>" alt="User" width="32" height="32"
                 class="rounded-circle me-2"/>
            <strong><sec:authentication property="name"/></strong>
        </a>
        <ul class="dropdown-menu dropdown-menu-end dropdown-menu-dark text-small shadow" aria-labelledby="userDropdown">
            <!-- 表示専用項目（クリック不可） -->
            <li class="dropdown-item text-white" style="pointer-events: none;">
                ログイン時刻：${loginTime}
            </li>
            <li class="dropdown-item text-white" style="pointer-events: none;">
                ユーザー名：<sec:authentication property="name"/>
            </li>
            <li class="dropdown-item text-white" style="pointer-events: none;">
                権限：<sec:authentication property="authorities"/>
            </li>
            <!-- 区切り線 -->
            <li>
                <hr class="dropdown-divider">
            </li>
            <!-- クリック可能な項目 -->
            <li>
                <a href="javascript:void(0)" class="dropdown-item menu-button"
                   onclick="loadPage('<c:url value='/user/editProfile'/>')">本人情報変更</a>
            </li>
            <li>
                <!-- フォームを埋め込む -->
                <form id="logoutForm" action="<c:url value='/logout'/>" method="post" style="display: none;">
                    <sec:csrfInput/>
                </form>
                <a href="javascript:void(0)" class="dropdown-item" id="logoutBtn"
                   onclick="document.getElementById('logoutForm').submit();">ログアウト</a>
            </li>
        </ul>
    </div>
</nav>
