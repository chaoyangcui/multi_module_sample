package _exception._1;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import java.util.Properties;

/**
 * Created by cuiguiyang on 2017/4/25 21:22.
 * Desc
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"com.sssarm"})
public class WebMVCConfig extends WebMvcConfigurerAdapter {
    @Bean
    public SimpleMappingExceptionResolver simpleMappingExceptionResolver() {
        SimpleMappingExceptionResolver b = new SimpleMappingExceptionResolver();
        Properties mappings = new Properties();
        mappings.put("org.springframework.web.servlet.PageNotFound", "page-404");
        mappings.put("org.springframework.dao.DataAccessException", "data-access");
        mappings.put("org.springframework.transaction.TransactionException", "transaction-Failure");
        b.setExceptionMappings(mappings);
        return b;
    }
}
