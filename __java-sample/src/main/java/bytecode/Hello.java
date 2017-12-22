package bytecode;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric Cui
 * @date 2017/12/21 13:18
 * Desc    Setting | Editor | File and Code Templates
 */
public class Hello {
    public static void main(String[] args) throws ArrayIndexOutOfBoundsException, UnsupportedEncodingException {
        System.out.println(Arrays.toString("中".getBytes("UTF-8")));
    }
}
