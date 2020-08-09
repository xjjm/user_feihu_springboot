package com.fh.filter;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration// 将此Filter交给Spring容器管理
@WebFilter(urlPatterns = "/", filterName = "CrossFilter")
@Order(1)// 指定过滤器的执行顺序，值越大越靠后执行
public class CrossFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletResponse resp = (HttpServletResponse)response;
        HttpServletRequest req = (HttpServletRequest)request;
        String origin = req.getHeader("Origin");
        resp.setHeader("Access-Control-Allow-Origin",origin);
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {

    }
}
