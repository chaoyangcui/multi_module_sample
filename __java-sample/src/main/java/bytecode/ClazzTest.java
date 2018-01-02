package bytecode;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric Cui
 * @date 2017/12/7 16:43
 * Desc    Setting | Editor | File and Code Templates
 */
public class ClazzTest extends Thread implements Runnable {

    public int instanceField = 1;
    public static int classField = 2;
    public static final int constField = 3;

    {
        System.out.println("static block");
    }

    public static void main(String[] args) {
        System.out.println(Integer.toHexString(2018));
        ClazzTest clazz = new ClazzTest();
        System.out.println("instantField: " + clazz.instanceField);
        System.out.println("classField  : " + ClazzTest.classField);
        clazz.start();
    }

    @Override
    public void run() {
        System.out.println("Thread start run. " + constField);
    }

}
