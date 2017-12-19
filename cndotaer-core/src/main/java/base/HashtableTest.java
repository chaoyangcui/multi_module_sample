package base;

/**
 * Created by cuiguiyang on 2017/3/22 21:42.
 * Desc
 */
public class HashtableTest {
    private static final long _1s = 1000;
    public static int count = 2;

    public static void main(String[] args) {
        HashtableTest test = new HashtableTest();
        System.out.println(test.count());

        new Thread(() -> test.count()).start();

        new Thread(() -> {
            try {
                Thread.sleep(_1s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(test.countLargeThenThree());
        }).start();

    }

    public int inc(){
        synchronized(this){
            return ++count;
        }
    }

    public synchronized int count() {
        try {
            Thread.sleep(_1s * 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return count;
    }

    public synchronized boolean countLargeThenThree() {
        return count > 3;
    }

}
