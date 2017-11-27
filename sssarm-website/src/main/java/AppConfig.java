import nz.net.ultraq.thymeleaf.LayoutDialect;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.freemarker.FreeMarkerView;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

/**
 * @author Eric
 * @since  2017/5/9 18:05
 */
@EnableAutoConfiguration
@ComponentScan({"com.sssarm"})
@Configuration(value = "classpath:config/applicationContext.xml")
public class AppConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry
                .addMapping("/**")
                .allowCredentials(true)
                .allowedMethods("GET", "POST")
                .allowedOrigins("*");
        super.addCorsMappings(registry);
    }
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        // registry.addViewController("/").setViewName("home");
        registry.addViewController("/hello").setViewName("hello");
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        if (!registry.hasMappingForPattern("/webjars/**")) {
            registry
                    .addResourceHandler("/webjars/**")
                    .addResourceLocations("classpath:/META-INF/resources/webjars/");
        }
        if (!registry.hasMappingForPattern("/**")) {
            registry
                    .addResourceHandler("/**")
                    .addResourceLocations("classpath:/resources/");
        }
    }

    /*@Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        // ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("classpath:static/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");

        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(templateResolver);

        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(springTemplateEngine);
        viewResolver.setOrder(1);

        registry.viewResolver(viewResolver);
    }*/
    /** This is not really needed, but as i need to add SpringSecurityDialect, i need to override defaults **/
    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        final SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setCacheable(false);
        templateResolver.setPrefix("classpath:/static/");
        templateResolver.setSuffix(".html");
        // templateResolver.setTemplateMode("HTML5");
        // 非严格验证HTML代码,需额外依赖net.sourceforge.nekohtml-nekohtml
        templateResolver.setTemplateMode("LEGACYHTML5");
        return templateResolver;
    }
    @Bean
    public SpringTemplateEngine templateEngine() {
        final SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.addTemplateResolver(templateResolver());
        // springTemplateEngine.addDialect(new SpringStandardDialect());
        springTemplateEngine.addDialect(new LayoutDialect());
        /**
         * @See https://github.com/thymeleaf/thymeleaf-extras-springsecurity
         * springTemplateEngine.addDialect(new SpringSecurityDialect());
         * springTemplateEngine.addDialect(new CmsDialect());
         */
        return springTemplateEngine;
    }
    @Bean
    public ThymeleafViewResolver viewResolver() {
        final ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        viewResolver.setViewNames(new String[]{"*.html", "*.xhtml"});
        return viewResolver;
    }
    @Bean
    public UrlBasedViewResolver urlBasedViewResolver() {
        final UrlBasedViewResolver urlBasedViewResolver = new UrlBasedViewResolver();
        urlBasedViewResolver.setViewNames(".jsp", ".tpl");
        urlBasedViewResolver.setViewClass(FreeMarkerView.class);
        urlBasedViewResolver.setOrder(2);
        return urlBasedViewResolver;
    }
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.viewResolver(viewResolver());
        registry.viewResolver(urlBasedViewResolver());
    }


    /*@Bean
    public TemplateResolver templateResolver() {
        ClassLoaderTemplateResolver templateResolver = new ClassLoaderTemplateResolver();
        // ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
        templateResolver.setPrefix("templates/");
        templateResolver.setSuffix(".html");
        templateResolver.setTemplateMode("HTML5");
        return templateResolver;
    }
    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine springTemplateEngine = new SpringTemplateEngine();
        springTemplateEngine.setTemplateResolver(templateResolver());
        return springTemplateEngine;
    }
    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
        viewResolver.setTemplateEngine(templateEngine());
        viewResolver.setOrder(1);
        return viewResolver;
    }*/

}
