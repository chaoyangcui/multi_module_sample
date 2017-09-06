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
public abstract class AbstractUnsafe {
    // AbstractUnsafe mechanics
    static sun.misc.Unsafe UNSAFE;
    static {
        try {
            Class<?> clazz = Class.forName("sun.misc.Unsafe");
            Constructor constructor = clazz.getDeclaredConstructor();
            constructor.setAccessible(true);
            UNSAFE = (sun.misc.Unsafe) constructor.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
