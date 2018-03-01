package jvm;

import bytecode.Singleton;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * @since 2018/3/1 15:04
 * Description
 */
public class ClassLoadFlow {
    private static ClassLoadFlow intsance = new ClassLoadFlow();
    public static int counter1;
    public static int counter2 = 0;
    private ClassLoadFlow() {
        counter1++;
        counter2++;
    }
    public static ClassLoadFlow getInstance() {
        return intsance;
    }

    public static void main(String[] args) {
        ClassLoadFlow instance = ClassLoadFlow.getInstance();
        System.out.println("counter1 = " + Singleton.counter1 + ", counter2 = " + Singleton.counter2);
    }
}
