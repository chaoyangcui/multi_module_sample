import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @Author Eric
 * @Date 2017/5/10 11:54
 * @Desc
 */
@Configuration
@Import({AppConfig.class, SecurityConfig.class})
public class CommonConfig {

}
