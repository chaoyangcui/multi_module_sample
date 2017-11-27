
import java.lang.reflect.Proxy;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/10/20 16:26
 * Desc    Setting | Editor | File and Code Templates
 */
public class RPCProxy {


    public static void main(String[] args) {
        RPCProxy rpcProxy = new RPCProxy();
        Subject subject = rpcProxy.create(Subject.class);
        String result = subject.doSth();
        System.out.println(result);
    }

    @SuppressWarnings("unchecked")
    public <T> T create(Class<T> interfaceClass) {
        return (T) Proxy.newProxyInstance(
                interfaceClass.getClassLoader(),
                new Class<?>[]{interfaceClass},
                (proxy, method, args) -> {
                    String service = method.getDeclaringClass().getName() + "." + method.getName() + "()";
                    String result = "call remote service\n";
                    return result + String.format("result from RemoteService[%s]", service);
                });
    }

}
