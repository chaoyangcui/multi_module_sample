package bridge.example;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/8/28 13:46
 * Desc    Setting | Editor | File and Code Templates
 */
public class JavaImplementor implements Implementor {

    private String target;

    public JavaImplementor(String target) {
        this.target = target;
    }

    @Override
    public void println(String target) {
        System.out.println("Language:Java");
        System.out.println(String.format("System.out.println(\"%s\");", target));
    }

    @Override
    public void println() {
        System.out.println(String.format("System.out.println(\"%s\");", target));
    }
}
