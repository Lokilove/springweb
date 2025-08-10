package com.mvc.web.security.handler;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

        // エラーメッセージをリクエスト属性に設定
        request.setAttribute("loginError", "ユーザー名またはパスワードが無効です");

        // JSPにforward（リダイレクトではないのでURLは変わらない）
        request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response);
    }
}
