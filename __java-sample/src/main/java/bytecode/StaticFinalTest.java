package bytecode;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric Cui
 * @date 2017/12/20 12:25
 * Desc    Setting | Editor | File and Code Templates
 */
public class StaticFinalTest {
    public static final StaticFinalTest instance = new StaticFinalTest();
    public static /*final*/ int b = 2;

    static {
        // b = 1;
        // System.out.println("static block: " + b);
    }

    public StaticFinalTest() {
        System.out.println("constructor : " + b);
    }

    public static void main(String[] args) {
        System.out.println(StaticFinalTest.instance);
        StaticFinalTest staticFinalTest = new StaticFinalTest();
        System.out.println("main        : " + staticFinalTest.b);
    }
}
