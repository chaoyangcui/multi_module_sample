package __java9;


import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/27 17:57
 * Desc    Setting | Editor | File and Code Templates
 */
public class HttpExample {
    public static void main(String[] args) {
        URI httpURI = null;
        try {
            httpURI = new URI("https://www.baidu.com");

        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
