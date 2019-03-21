package com.demo.utils;

import org.springframework.web.filter.OncePerRequestFilter;
import org.apache.commons.lang3.StringUtils;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestPathFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {

        String prefix = FilterUtil.getSchoolPrefix(httpServletRequest);
        if (StringUtils.isNotBlank(prefix)) { // 数据源
            prefix = prefix.toLowerCase();
            DataSourceUtils.setDbKey(prefix);
        } else {
            DataSourceUtils.setDbKey("default");
        }

        filterChain.doFilter(httpServletRequest, httpServletResponse);
    }

    @Override
    public void destroy() {

    }
}
