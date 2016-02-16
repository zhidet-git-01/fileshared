package com.calanger.yzhj.mobile.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.calanger.yhzj.mobile.controller.BaseController;

public class CommonInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = ((HandlerMethod) handler);
            if (handlerMethod.getBean() instanceof BaseController && modelAndView != null) {
                modelAndView.addObject("action", handlerMethod.getBean());
            }
        }
    }
}
