package bytecode;

import date.DateTimeTest;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric Cui
 * @date 2017/12/7 16:43
 * Desc    Setting | Editor | File and Code Templates
 */
public class ClazzTest {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException, InvocationTargetException, NoSuchMethodException {
        Class<DateTimeTest> clazz = DateTimeTest.class;
        Constructor<DateTimeTest> constructor = clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        Method method = clazz.getDeclaredMethods()[1];
        method.setAccessible(true);
        method.invoke(constructor.newInstance());

        System.out.println(Arrays.toString(clazz.getDeclaredMethods()));
    }
}
