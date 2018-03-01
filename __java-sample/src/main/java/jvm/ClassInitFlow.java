package jvm;

import bytecode.Singleton;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * @since 2018/3/1 15:07
 * Description
 */
public class ClassInitFlow {

    private static final String CONST_VAR = getConst();
    private static String staticVar = getStatic();

    public static int counter1;
    public static int counter2 = 0;

    public static String getConst() {
        System.out.println("final field.");
        return "";
    }
    public static String getStatic() {
        System.out.println("static field.");
        return "";
    }

    static {
        System.out.println("static block.");
    }

    private static String staticVar2 = getStatic();

    private ClassInitFlow() {
        System.out.println("constructor.");
        counter1++;
        counter2++;
    }
    public static ClassInitFlow getInstance() {
        return new ClassInitFlow();
    }

    public static void main(String[] args) {
        ClassInitFlow instance = ClassInitFlow.getInstance();
        System.out.println("counter1 = " + Singleton.counter1 + ", counter2 = " + Singleton.counter2);
    }

}
