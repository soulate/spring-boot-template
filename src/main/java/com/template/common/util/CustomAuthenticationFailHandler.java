package com.template.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomAuthenticationFailHandler implements AuthenticationFailureHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {

        logger.info("onAuthenticationFailure ::: " + request.getParameter("email"));

        //Locale 을 변경해도 정상적으로 안내려와서 화면단에서 처리함.
        String message = "login.failed.msg";

        logger.info("message :::: " + "login.failed.msg");

        request.getSession().setAttribute("message", message);
        response.sendRedirect("/login");
    }
}
