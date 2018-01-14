package proxy;

import proxy.impl.Rabbit;

/**
 * @author Eric Cui
 * <p>
 * Created by Intellij IDEA.
 * Date  : 2018/1/14 12:18
 * Desc  : 描述信息
 */
public class CglibProxyTest extends CglibProxy {

    @Override
    public void invokeBefore() {
        System.out.println("before");
    }

    @Override
    public void invodeAfter() {
        System.out.println("after");
    }

    public static void main(String[] args) {
        Rabbit rabbit = CglibProxy.getProxyObject(Rabbit.class);
        rabbit.move("rabbit ran fast.");
    }
}
