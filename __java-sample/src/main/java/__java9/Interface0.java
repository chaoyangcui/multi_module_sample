package __java9;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/22 15:24
 * Desc    private method in interface
 */
public interface Interface0 {

    private static void interfaceInnerMethod() {
        System.out.println("private method in interface0.");
    }
    private void interfaceInnerMethod1() {
        System.out.println("private method in interface0.");
    }

    default void t() {
        interfaceInnerMethod();
        interfaceInnerMethod1();
    }

    default void t1() {
        interfaceInnerMethod1();
    }

}
