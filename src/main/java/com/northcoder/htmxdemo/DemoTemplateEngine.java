package com.northcoder.htmxdemo;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

public class DemoTemplateEngine {
    
    public static TemplateEngine configureEngine() {
        // Use the Thymeleaf classloader resolver:
        ClassLoaderTemplateResolver templateResolver
                = new ClassLoaderTemplateResolver(Thread
                        .currentThread().getContextClassLoader());
        templateResolver.setTemplateMode(TemplateMode.HTML);
        templateResolver.setCharacterEncoding("UTF-8");
        templateResolver.setPrefix("/thymeleaf/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheTTLMs(3600000L); // one hour
        templateResolver.setCacheable(true);
        TemplateEngine templateEngine = new TemplateEngine();
        templateEngine.addDialect(new Java8TimeDialect());
        templateEngine.setTemplateResolver(templateResolver);
        return templateEngine;
    }
       
}
