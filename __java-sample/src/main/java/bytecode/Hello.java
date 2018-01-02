package bytecode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric Cui
 * @date 2017/12/21 13:18
 * Desc    Setting | Editor | File and Code Templates
 */
public class Hello {
    public static void main(String[] args) {
        List<Hello> list = new ArrayList<>();
        while (true) {
            list.add(new Hello());
        }
    }

    public void recursion() {
    }

    private byte[] bytes = new byte[1024 * 2];
}
