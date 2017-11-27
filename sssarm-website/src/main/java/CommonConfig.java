import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author Eric
 * @since  2017/5/10 11:54
 */
@Configuration
@Import({AppConfig.class, SecurityConfig.class})
public class CommonConfig {

}
