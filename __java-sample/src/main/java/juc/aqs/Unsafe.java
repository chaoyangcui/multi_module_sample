package juc.aqs;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/5 16:52
 * Desc    Setting | Editor | File and Code Templates
 */
public class Unsafe {

    public static void main(String[] args) {
        Unsafe unsafe = new Unsafe();
        new Unsafe().unpark(unsafe);
    }

    public native void unpark(Object var1);
}
