package dynamicproxy;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/10/19 10:31
 * Desc    Setting | Editor | File and Code Templates
 */
public class Client {
    public static void main(String[] args) {
        /*RealSubject realSubject = new RealSubject();
        /*ProxyHandler handler = new ProxyHandler(realSubject);
        Subject subject = (Subject) Proxy.newProxyInstance(
                RealSubject.class.getClassLoader(), RealSubject.class.getInterfaces(), handler);
        subject.doSth();*/

        Subject subject = (Subject) ProxyHandler.getProxy(RealSubject.class);
        subject.doSth();
    }
}
