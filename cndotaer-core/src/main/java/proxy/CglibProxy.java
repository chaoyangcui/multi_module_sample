package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.apache.poi.ss.formula.functions.T;
import proxy.impl.Rabbit;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by cuiguiyang on 2017/3/18 17:09.
 * Desc
 */
public class CglibProxy implements MethodInterceptor {

    private static final Enhancer enhancer = new Enhancer();
    private static final CglibProxy proxy = new CglibProxy();

    @SuppressWarnings("unchecked")
    private <T> T getProxy(Class<T> clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        return (T) enhancer.create();
    }

    public static <T> T getProxyObject(Class<T> clazz) {
        return proxy.getProxy(clazz);
    }

    // public static void main(String[] args) {
    //
    //     System.out.println(UUID.randomUUID().toString());
    //
    //     Rabbit rabbit = CglibProxy.getProxyObject(Rabbit.class);
    //     rabbit.move("rabbit ran fast.");
    //
    // }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        // System.out.println("befoooooooooooore........");
        invokeBefore();
        Object result = methodProxy.invokeSuper(o, objects);
        invodeAfter();
        // System.out.println("afffffffffffffter........");
        return result;
    }

    public void invokeBefore() { }
    public void invodeAfter() { }

}
