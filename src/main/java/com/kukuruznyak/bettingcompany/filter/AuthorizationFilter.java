package com.kukuruznyak.bettingcompany.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


public class AuthorizationFilter{
//        implements Filter {
//    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

//    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        if (request.getSession().getAttribute("user") != null) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
        System.out.println("Filter");
            request.getRequestDispatcher("/").forward(servletRequest, servletResponse);
//        }
    }

//    @Override
    public void destroy() {
    }
}
