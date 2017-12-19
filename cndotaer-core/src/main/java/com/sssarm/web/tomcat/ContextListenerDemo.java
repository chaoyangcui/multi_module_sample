package com.sssarm.web.tomcat;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by cuiguiyang on 2017/2/19 17:39.
 * Desc
 */
public class ContextListenerDemo implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext context = servletContextEvent.getServletContext();
        String key = "sssarm.cui";
        String val = "sssarm";
        context.setAttribute(key, val);
        contextDestroyed(servletContextEvent);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }


}
