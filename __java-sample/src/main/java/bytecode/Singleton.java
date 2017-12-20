package bytecode;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric Cui
 * @date 2017/12/20 11:02
 * Desc    Setting | Editor | File and Code Templates
 */
public class Singleton {
    private static Singleton instance = new Singleton();
    public static int counter1;
    public static int counter2 = 0;
    private Singleton() {
        counter1++;
        counter2++;
    }
    public static Singleton getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println("counter1 = " + Singleton.counter1 + ", counter2 = " + Singleton.counter2);
    }
}
