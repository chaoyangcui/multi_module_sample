package jvm;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * @since 2018/3/1 15:04
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
        System.out.println(
                "counter1 = " + ClassLoadFlow.counter1 + ", counter2 = " + ClassLoadFlow.counter2);
    }
}
