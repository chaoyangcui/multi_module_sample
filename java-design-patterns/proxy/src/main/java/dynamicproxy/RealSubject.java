package dynamicproxy;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/10/19 10:30
 * Desc    Setting | Editor | File and Code Templates
 */
public class RealSubject implements Subject {
    @Override
    public void doSth() {
        System.out.println("do sth...");
    }
}
