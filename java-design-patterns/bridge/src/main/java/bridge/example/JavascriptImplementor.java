package bridge.example;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/8/28 13:48
 * Desc    Setting | Editor | File and Code Templates
 */
public class JavascriptImplementor implements Implementor {

    private String target;

    public JavascriptImplementor(String target) {
        this.target = target;
    }

    @Override
    public void println(String target) {
        System.out.println("Language:Javascript");
        System.out.println(String.format("console.info(\"%s\");", target));
    }

    @Override
    public void println() {
        System.out.println(String.format("console.info(\"%s\");", target));
    }
}
