package proxy.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import proxy.dto.User;
import proxy.dto.UserApi;

import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * <p>Created by Intellij IDEA.
 *
 * @author Eric Cui
 * @since 2019-03-24 21:59
 */
public class CglibDynamicProxy {
    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserApi.class);
        enhancer.setCallback(new ProxyInterceptor());

        System.out.println("Class<UserApi> is interface: " + UserApi.class.isInterface());

        UserApi userApi = (UserApi) enhancer.create();
        User userParam = User.builder().name("HelloC").address("shanghai").build();
        User user = userApi.save(userParam);
        System.out.println(user);
    }

    static class ProxyInterceptor implements MethodInterceptor {
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) {
            System.out.println(obj instanceof UserApi);
            System.out.println(method);
            System.out.println(method.getName());
            Parameter[] parameters = method.getParameters();
            System.out.println(parameters);
            Class[] classes = method.getParameterTypes();
            for (Class clazz : classes) {
                System.out.println(clazz.getName() + " : " + (clazz.getClass() == UserApi.class.getClass()));
            }
            System.out.println(args);
            System.out.println(proxy);
            return null;
        }
    }
}


