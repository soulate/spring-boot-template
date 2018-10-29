package com.template.common.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RequestBodyXSSFileter implements Filter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String[] acceptUrls;

    public RequestBodyXSSFileter(String[] acceptUrls){

        this.acceptUrls = acceptUrls;

    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {}

    @Override
    public void destroy() {}

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response = (HttpServletResponse)res;

        if(excludeUrls(request)){
            chain.doFilter(request, response); //걸러내는 URI일 경우 요청값 그대로 처리

        }else{

            RequestWrapper requestWrapper = null;
            try{
                requestWrapper = new RequestWrapper(request);
            }catch(Exception e){
                e.printStackTrace();
            }

            chain.doFilter(requestWrapper, response);
        }
    }

    private boolean excludeUrls(ServletRequest request) {

        String uri = ((HttpServletRequest) request).getRequestURI().trim();

        logger.info("RequestBodyXSSFileter Uri : {}", uri);

        boolean returnValue = false;

        for(String url : this.acceptUrls) {

            if(uri.startsWith(url)){

                returnValue = true;

            }

        }

        return returnValue;

    }
}
