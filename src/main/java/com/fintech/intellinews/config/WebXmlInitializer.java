package com.fintech.intellinews.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

/**
 * @author wanghao
 * create 2017-12-02 22:59
 **/
public class WebXmlInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        //添加ServletContext参数
        servletContext.setInitParameter("spring.profiles.active", "develop");
        super.onStartup(servletContext);
        super.onStartup(servletContext);
    }

    @Override
    protected FilterRegistration.Dynamic registerServletFilter(ServletContext servletContext, Filter filter) {

        return super.registerServletFilter(servletContext, filter);
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[0];
    }

    @Override
    protected String[] getServletMappings() {
        // 配置DispatcherServlet映射路径
        return new String[]{"/"};
    }
}
