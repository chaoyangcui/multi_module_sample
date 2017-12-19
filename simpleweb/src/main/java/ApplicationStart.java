import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * Created by cuiguiyang on 2017/3/11 13:33.
 * Desc
 */
@SpringBootApplication
@Configuration("classpath:config/application.properties")
@ComponentScan({"com.sssarm.web", "com.sssarm.service", "com.sssarm.domain", "com.sssarm"})
@Import({WebMvcConfig.class, WebSecurityConfig.class, DataSourceConfig.class})
public class ApplicationStart {

    public static void main(String[] args) {
        SpringApplication.run(ApplicationStart.class, args);
    }

}
