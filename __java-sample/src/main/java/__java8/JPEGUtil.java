package __java8;

import sun.security.action.GetPropertyAction;

import java.security.AccessController;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric Cui
 * @date 2017/12/5 14:42
 * Desc    Setting | Editor | File and Code Templates
 */
public class JPEGUtil {

    public static void main(String[] args) {
        GetPropertyAction a = new GetPropertyAction("java.io.tmpdir");
        System.out.println(AccessController.doPrivileged(a));
    }

}
