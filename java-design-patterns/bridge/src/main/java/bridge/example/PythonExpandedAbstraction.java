package bridge.example;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/8/28 13:43
 * Desc    Setting | Editor | File and Code Templates
 */
public class PythonExpandedAbstraction extends Abstraction {

    private String target;

    public PythonExpandedAbstraction(Implementor implementor) {
        super(implementor);
    }

    PythonExpandedAbstraction(Implementor implementor, String target) {
        super(implementor);
        this.target = target;
    }

    @Override
    public void operation() {
        System.out.println("Language:Python");
        implementor.println();
    }
}
