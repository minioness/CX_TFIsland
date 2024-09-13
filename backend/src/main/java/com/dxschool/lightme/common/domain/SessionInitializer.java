package com.dxschool.lightme.common.domain;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SessionInitializer implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;

        HttpSession session = httpRequest.getSession(true);

        if (session.getAttribute("userId") == null) {
            session.setAttribute("userId", 1L);  // userId에 1L 설정
        }
        filterChain.doFilter(request, response);
    }
}
