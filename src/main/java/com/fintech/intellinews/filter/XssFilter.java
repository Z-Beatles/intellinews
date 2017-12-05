package com.fintech.intellinews.filter;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @author waynechu
 * Created 2017-12-05 15:54
 */
public class XssFilter implements Filter {

    @Override
    public void init(FilterConfig arg0) throws ServletException {
        // Do nothing here
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        chain.doFilter(new XssRequestWrapper((HttpServletRequest) request), response);
    }

    @Override
    public void destroy() {
        // Do nothing here
    }
}
