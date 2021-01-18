package proxy.jdk;


import proxy.dto.User;
import proxy.dto.UserApi;
import proxy.dto.UserApiImpl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p>Created by Intellij IDEA.
 *
 * @author Eric Cui
 * @since 2019-03-24 21:41
 */
public class JDKDynamicProxy {

    public static void main(String[] args) throws Throwable {
        InvocationHandler handler = (proxy, method, args1) -> {
            System.out.println("execute before....");
            Object result = method.invoke(proxy, args1);
            System.out.println("execute after.....");
            return result;
        };
        Object result =
                handler.invoke(
                        new UserApiImpl(),
                        UserApiImpl.class.getMethod("save", User.class),
                        new Object[] {User.builder().name("Eric").address("河南郑州").build()});
        System.out.println("result: " + result);

        InvocationHandler proxoyHandler = new ProxoyHandler(new UserApiImpl());
        UserApi userApi = (UserApi) Proxy.newProxyInstance(UserApiImpl.class.getClassLoader(), new Class[]{UserApi.class}, proxoyHandler);
        User user = userApi.save(User.builder().name("Eric").address("河南郑州").build());
        System.out.println(user);
    }

    static class ProxoyHandler implements InvocationHandler {
        private Object target;

        public ProxoyHandler(Object target) {
            this.target = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("before ....");
            System.out.println(proxy.getClass());
            System.out.println(method.getName());
            Object result = method.invoke(target, args);
            System.out.println("after  ....");

            return result;
        }
    }
}
