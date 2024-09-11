package com.dxschool.lightme.caseuser.controller;

import jakarta.security.auth.message.AuthException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Optional;

@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        boolean isLoginUserAnnotation = parameter.
                getParameterAnnotation(User.class) != null;
        boolean isUserClass = Long.class.
                equals(parameter.getParameterType());
        return isLoginUserAnnotation&&isUserClass;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {

        HttpServletRequest request = Optional.ofNullable(webRequest.getNativeRequest(HttpServletRequest.class))
                .orElseThrow(() -> new AuthException("세션 정보가 없습니다."));

        HttpSession session = request.getSession(false);  // 세션이 없으면 null을 반환
        if (session == null) {
            System.out.println("세션이 존재하지 않습니다.");
        } else {
            Object userId = session.getAttribute("userId");
            System.out.println("세션에서 userId: " + userId);
        }

        Object userIdFromContext = RequestContextHolder.getRequestAttributes().getAttribute("userId", RequestAttributes.SCOPE_SESSION);
        System.out.println("RequestContextHolder에서 추출한 userId: " + userIdFromContext);
        throw new AuthException("wrong session id");
    }
}
