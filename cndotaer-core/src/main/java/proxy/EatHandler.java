package proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * Created by cuiguiyang on 2017/3/18 16:38.
 * Desc
 */
public class EatHandler implements InvocationHandler {

    private Object target;

    public EatHandler(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("start eat......");

        Object result = method.invoke(target, args);

        System.out.println("stop eat......");

        return result;
    }
}
