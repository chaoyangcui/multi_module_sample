package bridge.example;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/8/28 13:47
 * Desc    Setting | Editor | File and Code Templates
 */
public class PythonImplementor implements Implementor {

    private String target;

    public PythonImplementor(String target) {
        this.target = target;
    }

    @Override
    public void println(String target) {
        System.out.println("Language:Python");
        System.out.println(String.format("print('%s')", target));
    }

    @Override
    public void println() {
        System.out.println(String.format("print('%s')", target));
    }
}
