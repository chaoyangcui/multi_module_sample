package __java9;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/27 16:39
 * Desc    Setting | Editor | File and Code Templates
 */
public class DesktopExample {

    private static final Desktop DESKTOP = Desktop.getDesktop();

    public static void main(String[] args) {
        try {
            // Desktop.getDesktop().mail();
            DESKTOP.browse(new URI("https://www.google.com.hk"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }

}
