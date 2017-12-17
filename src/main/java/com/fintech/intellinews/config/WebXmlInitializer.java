package com.fintech.intellinews.config;


import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.filter.DelegatingFilterProxy;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.*;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wanghao
 * create 2017-12-02 22:59
 **/
public class WebXmlInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException {
        FilterRegistration.Dynamic druid = servletContext.addFilter("DruidWebStatFilter", WebStatFilter.class);
        druid.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
        Map<String, String> druidMap = new HashMap<>(3);
        druidMap.put("exclusions", "*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");
        druidMap.put("profileEnable", "true");
        druidMap.put("principalSessionName", "sessionId");
        druid.setInitParameters(druidMap);

        FilterRegistration.Dynamic encoding = servletContext.addFilter("encodingFilter", WebStatFilter.class);
        encoding.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), false, "/*");
        Map<String, String> encodingMap = new HashMap<>(2);
        encodingMap.put("encoding", "UTF-8");
        encodingMap.put("forceEncoding", "true");
        encoding.setInitParameters(encodingMap);

        FilterRegistration.Dynamic shiroFilter = servletContext.addFilter("shiroFilter", DelegatingFilterProxy.class);
        shiroFilter.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/*");
        Map<String, String> shiroMap = new HashMap<>(1);
        shiroMap.put("targetFilterLifecycle", "true");
        shiroFilter.setAsyncSupported(true);
        shiroFilter.setInitParameters(shiroMap);

        ServletRegistration.Dynamic druidStatView = servletContext.addServlet("DruidStatView", StatViewServlet.class);
        druidStatView.addMapping("/druid/*");
        super.onStartup(servletContext);
    }

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{SpringAppConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{SpringWebConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        // 配置DispatcherServlet映射路径
        return new String[]{"/"};
    }
}
