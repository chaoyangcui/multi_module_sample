package proxy;

import proxy.impl.Rabbit;
import proxy.inter.Animal;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

/**
 * Created by cuiguiyang on 2017/3/18 16:10.
 * Desc
 */
public class ProxyTest {

    public static void main(String[] args) {
        Animal rabbit = new Rabbit();

        InvocationHandler rabbitHandler = new MoveHandler(rabbit);
        Animal animal1 = (Animal) Proxy.newProxyInstance(
                rabbit.getClass().getClassLoader(),
                rabbit.getClass().getInterfaces(),
                rabbitHandler);
        //animal1.move("happy");

        InvocationHandler eatHandler = new EatHandler(animal1);
        Animal animal3 = (Animal) Proxy.newProxyInstance(
                rabbit.getClass().getClassLoader(),
                rabbit.getClass().getInterfaces(),
                eatHandler);
        animal3.move("happy");


        /*MoveHandler rabbitHandler2 = new MoveHandler(rabbit);
        Animal animal2 = rabbitHandler2.getProxy();
        animal2.move("sad..");*/
    }

}
