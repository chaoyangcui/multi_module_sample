package proxy;

import proxy.inter.Animal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by cuiguiyang on 2017/3/18 16:08.
 * Desc
 */
public class MoveHandler implements InvocationHandler {

    private Animal animal;

    public MoveHandler(Animal animal) {
        this.animal = animal;
    }

    public <T extends Animal> T getProxy() {
        ClassLoader classLoader = animal.getClass().getClassLoader();
        Class<?>[] classes = animal.getClass().getInterfaces();

        return (T) Proxy.newProxyInstance(classLoader, classes, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        System.out.println("animal start move.");

        Object result = method.invoke(animal, args);

        System.out.println("animal stop move.");

        return result;
    }
}
