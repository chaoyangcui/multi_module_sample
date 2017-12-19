package _exception._2;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by cuiguiyang on 2017/4/25 21:24.
 * Desc
 */
public class WebSpringMvcConfig extends WebMvcConfigurerAdapter {
    @Bean
    public GlobalHandlerExceptionResolver globalHandlerExceptionResolver() {
        return new GlobalHandlerExceptionResolver();
    }
}
