package com.template.common.filter;

import com.navercorp.lucy.security.xss.servletfilter.XssEscapeFilter;
import com.navercorp.lucy.security.xss.servletfilter.XssEscapeServletFilterWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class XssServletFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String[] acceptUrls;

    public XssServletFilter(String[] acceptUrls){

        this.acceptUrls = acceptUrls;

    }

    private XssEscapeFilter xssEscapeFilter = XssEscapeFilter.getInstance();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        if(excludeUrls(request)){
            chain.doFilter(request, response); //걸러내는 URI일 경우 요청값 그대로 처리

        }else{
            chain.doFilter(new XssEscapeServletFilterWrapper(request, xssEscapeFilter), response);
        }
    }



    private boolean excludeUrls(ServletRequest request) {

        String uri = ((HttpServletRequest) request).getRequestURI().trim();

        logger.info("XssServletFilter Uri : {}", uri);

        boolean returnValue = false;

        for(String url : this.acceptUrls) {

            if(uri.startsWith(url)){

                returnValue = true;

            }

        }

        return returnValue;

    }

    @Override
    public void destroy() {

    }
}
