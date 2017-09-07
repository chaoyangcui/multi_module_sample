package juc.aqs;

/**
 * Created by Intellij IDEA.
 * Author: Eric Cui
 * Date  : 2017/9/6 20:41
 * Desc  : 描述信息
 */
public class UnsafeExample extends AbstractUnsafe {
    private int num;

    public static void main(String[] args) {
        UnsafeExample unsafeExample = new UnsafeExample();

        System.out.println(UNSAFE.getInt(unsafeExample, NUM_OFFSET));

        if (UNSAFE.compareAndSwapInt(unsafeExample, NUM_OFFSET, 10, 20)) {
            System.out.println(UNSAFE.getInt(unsafeExample, NUM_OFFSET));
        } else {
            UNSAFE.compareAndSwapInt(unsafeExample, NUM_OFFSET, UNSAFE.getInt(unsafeExample, NUM_OFFSET), 10);
        }

        System.out.println(UNSAFE.getInt(unsafeExample, NUM_OFFSET));
    }

    private static long NUM_OFFSET;
    static {
        Class<?> clazz = UnsafeExample.class;
        try {
            NUM_OFFSET =
                    UNSAFE.objectFieldOffset(clazz.getDeclaredField("num"));
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
