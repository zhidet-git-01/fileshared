package com.calanger.yhzj.mobile.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.calanger.common.web.util.RequestUtils;

public class PaginationController extends BaseController {
    public String getPaginationUrl(int pageNumber) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String url = RequestUtils.getRequestURIWithQueryString(request);
        return RequestUtils.replaceParamValue(url, "pageNumber", pageNumber == 1 ? null : String.valueOf(pageNumber));
    }

    public String getPaginationUrl(int pageSize, int pageNumber) {
        String url = getPaginationUrl(pageNumber);
        return RequestUtils.replaceParamValue(url, "pageSize", String.valueOf(pageSize));
    }
}
