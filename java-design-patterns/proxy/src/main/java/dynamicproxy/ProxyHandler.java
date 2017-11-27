package dynamicproxy;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/10/19 10:28
 * Desc    Setting | Editor | File and Code Templates
 */
public class ProxyHandler implements InvocationHandler {
    private Object target;

    public ProxyHandler(Object target) {
        this.target = target;
    }

    public static Object getProxy(Object obj) {
        if (obj instanceof Class) {
            Class<?> clazz = ((Class) obj);
            try {
                Constructor constructor = clazz.getConstructor();
                obj = constructor.newInstance();
            } catch (NoSuchMethodException
                    | IllegalAccessException
                    | InvocationTargetException
                    | InstantiationException e) {
                e.printStackTrace();
            }
        }
        return java.lang.reflect.Proxy.newProxyInstance(obj.getClass().getClassLoader(),
                obj.getClass().getInterfaces(), new ProxyHandler(obj));
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("doSth before.");
        Object result =  method.invoke(target, args);
        System.out.println("doSth after.");
        return result;
    }
}
