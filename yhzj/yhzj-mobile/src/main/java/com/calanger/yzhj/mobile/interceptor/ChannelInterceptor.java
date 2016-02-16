package com.calanger.yzhj.mobile.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.calanger.common.util.Config;
import com.calanger.common.web.util.CookieUtils;
import com.calanger.yhzj.mobile.constant.Constants;
import com.google.common.base.Strings;

public class ChannelInterceptor extends HandlerInterceptorAdapter {
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if ("GET".equals(request.getMethod())) {
            String iFrom = request.getParameter("ifrom");
            String from = request.getParameter("from");
            String sid = request.getParameter("sid");
            String typeid = request.getParameter("typeid");
            String lx = request.getParameter("lx");

            // 推广链接
            String referrer = request.getParameter("referrer");
            if (!Strings.isNullOrEmpty(referrer)) {
                CookieUtils.addCookie(response, Constants.COOKIE_DOMAIN, Constants.COOKIE_PATH, "_fcj_referrer", referrer, 30 * 24 * 3600);
            }

            String refererUrl = request.getHeader("Referer");
            if (refererUrl != null && !refererUrl.contains(Config.getConfig().get("base.domain"))) {
                CookieUtils.addCookie(response, Constants.COOKIE_DOMAIN, Constants.COOKIE_PATH, "_fcj_referer_url", refererUrl, 30 * 24 * 3600);
            }

            if (!Strings.isNullOrEmpty(iFrom)) {
                from = iFrom;
            }
            if (!Strings.isNullOrEmpty(from)) {
                CookieUtils.addCookie(response, Constants.COOKIE_DOMAIN, Constants.COOKIE_PATH, "_fcj_from", from, 30 * 24 * 3600);
            }
            if (!Strings.isNullOrEmpty(sid)) {
                CookieUtils.addCookie(response, Constants.COOKIE_DOMAIN, Constants.COOKIE_PATH, "_fcj_sid", sid, 30 * 24 * 3600);
            }
            if (!Strings.isNullOrEmpty(typeid)) {
                CookieUtils.addCookie(response, Constants.COOKIE_DOMAIN, Constants.COOKIE_PATH, "_fcj_typeid", typeid, 30 * 24 * 3600);
            }
            if (!Strings.isNullOrEmpty(lx)) {
                CookieUtils.addCookie(response, Constants.COOKIE_DOMAIN, Constants.COOKIE_PATH, "_fcj_lx", lx, 30 * 24 * 3600);
            }
        }
    }
}
