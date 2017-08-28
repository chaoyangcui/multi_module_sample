package bridge.example;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/8/28 14:10
 * Desc    Setting | Editor | File and Code Templates
 */
public class JavaExpandedAbstraction extends Abstraction {

    private String target;

    public JavaExpandedAbstraction(Implementor implementor) {
        super(implementor);
    }

    public JavaExpandedAbstraction(Implementor implementor, String target) {
        super(implementor);
        this.target = target;
    }

    @Override
    public void operation() {
        System.out.println("Language:Java");
        implementor.println();
    }
}
