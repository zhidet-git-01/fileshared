package com.calanger.yhzj.mobile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.calanger.common.util.Config;
import com.calanger.common.util.UrlUtils;
import com.calanger.common.web.util.RequestUtils;

public abstract class BaseController {
    @ModelAttribute("baseDomain")
    public String getBaseDomain() {
     return Config.getConfig().get("base.domain");
    }

    @ModelAttribute("staticDomain")
    public String getStaticDomain() {
        return Config.getConfig().get("static.domain");
    }

    @ModelAttribute("homeDomain")
    public String getHomeDomain() {
        return Config.getConfig().get("home.domain");
    }

    @ModelAttribute("returnUrl")
    public String getReturnUrl() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return RequestUtils.getRequestURIWithQueryString(request);
    }

    @ModelAttribute("encodedReturnUrl")
    public String getEncodedReturnUrl() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return UrlUtils.encode(RequestUtils.getRequestURIWithQueryString(request));
    }
}
