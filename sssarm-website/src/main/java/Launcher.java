import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Eric
 * @since  2017/5/9 18:03
 */
@SpringBootApplication
public class Launcher {

    public static void main(String[] args) {
        SpringApplication.run(CommonConfig.class, args);
    }

}
