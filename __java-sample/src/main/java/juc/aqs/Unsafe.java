package juc.aqs;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/5 16:52
 * Desc    Setting | Editor | File and Code Templates
 */
public class Unsafe {

    private int num;

    public static void main(String[] args) {
        Unsafe unsafe = new Unsafe();

        System.out.println(UNSAFE.getInt(unsafe, NUM_OFFSET));

        UNSAFE.compareAndSwapInt(unsafe, NUM_OFFSET, 0, 10);
        System.out.println(UNSAFE.getInt(unsafe, NUM_OFFSET));
    }


    // Unsafe mechanics
    private static sun.misc.Unsafe UNSAFE;
    private static long NUM_OFFSET;
    static {
        try {
            Class<?> clazz = Class.forName("sun.misc.Unsafe");
            Constructor constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            UNSAFE = (sun.misc.Unsafe) constructor.newInstance();

            Class<?> tk = Unsafe.class;
            Field numField = tk.getDeclaredField("num");
            NUM_OFFSET = UNSAFE.objectFieldOffset(numField);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
