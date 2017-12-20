package bytecode;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric Cui
 * @date 2017/12/12 17:10
 * Desc    Setting | Editor | File and Code Templates
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        System.out.println(222D);
        System.out.println(127);
        System.out.println(128);
        System.out.println(1299);
        int c = new Main().t1();
    }

    public int t1() {
        int a = 1;
        final int b = 2;
        System.out.println(b);
        return a;
    }
}
