package bridge.example;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/8/28 13:36
 * Desc    Setting | Editor | File and Code Templates
 */
public abstract class Abstraction {
    Implementor implementor;

    Abstraction(Implementor implementor) {
        this.implementor = implementor;
    }

    public abstract void operation();
}
