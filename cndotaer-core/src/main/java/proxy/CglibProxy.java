package proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import proxy.impl.Rabbit;

import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Created by cuiguiyang on 2017/3/18 17:09.
 * Desc
 */
public class CglibProxy implements MethodInterceptor {

    private static final Enhancer enhancer = new Enhancer();

    private Object getProxy(Class clazz) {
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        return enhancer.create();
    }

    public static void main(String[] args) {

        System.out.println(UUID.randomUUID().toString());

        CglibProxy proxy = new CglibProxy();
        Rabbit rabbit = (Rabbit) proxy.getProxy(Rabbit.class);
        rabbit.move("hhhhhhhhhhaaaaa");

    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("befoooooooooooore........");
        Object result = methodProxy.invokeSuper(o, objects);
        System.out.println("afffffffffffffter........");
        return result;
    }
}
