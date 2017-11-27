package cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/10/19 11:14
 * Desc    Setting | Editor | File and Code Templates
 */
public class ProxyHandler implements MethodInterceptor {

    @Override
    public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
        System.out.println("before....");
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("after.....");
        return result;
    }

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealSubject.class);
        /*enhancer.setCallback((InvocationHandler) (proxy, method, args1) -> {
            System.out.println("do sth.");
            return null;
        });*/
        enhancer.setCallback(new ProxyHandler());
        RealSubject realSubject = (RealSubject) enhancer.create();
        realSubject.doSth();
    }
}
