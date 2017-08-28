package bridge.example;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/8/28 13:49
 * Desc    Setting | Editor | File and Code Templates
 */
public class _Client {
    public static void main(String[] args) {
        Abstraction java = new JavaExpandedAbstraction(new JavaImplementor("Java"));
        java.operation();

        Abstraction python = new PythonExpandedAbstraction(new PythonImplementor("Python"));
        python.operation();
    }
}
