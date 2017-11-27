import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/10/26 15:35
 * Desc    Setting | Editor | File and Code Templates
 */
public class SimpleExample {
    private static Logger LOG = LoggerFactory.getLogger(SimpleExample.class.getName());

    public static void main(String[] args) {
        // testLog();

        // System.out.println(testGetAndIncrement());

        // testHashMap();
        System.out.println(DateTime.now().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        testConcurrentHashMap();
    }

    private static void testConcurrentHashMap() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        String result = map.put("a", "a");
        System.out.println(map);
    }

    public static void testLog() {
        LOG.info("logback info()");
        LOG.debug("logback debug");
        LOG.error("logback error");
    }

    private static void testHashMap() {
        HashMap<String, String> map = new HashMap<>(1 << 4);

        String key = "a";
        System.out.println(Integer.toBinaryString(key.hashCode()));
        map.put("a", "a");
        map.put("aa", "aa");
        map.put("aaa", "aaa");
        map.put("aaaa", "aaaa");
        map.put("b", "b");
        map.put("bb", "bb");
        map.put("bbb", "bbb");
        map.put("bbbb", "bbb");
        map.put("c1", "c");
        map.put("c2", "cc");
        map.put("c3", "ccc");
        map.put("c4", "cccc");
        map.put("d1", "d");
        map.put("d2", "dd");
        map.put("d3", "ddd");
        map.put("d4", "dddd");
        map.put("d5", "ddddd");

        System.out.println(map.toString());
        System.out.println(map.get(key));
    }

    public void queue() {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(Integer.MAX_VALUE);

        LinkedBlockingQueue<String> linkedBlockingQueue = new LinkedBlockingQueue<>();
        try {
            linkedBlockingQueue.put("put");
            linkedBlockingQueue.take();
            linkedBlockingQueue.offer("offer");
            linkedBlockingQueue.poll();
            linkedBlockingQueue.peek();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        linkedBlockingQueue.add("");
    }

    private static boolean testGetAndIncrement() {
        final AtomicInteger count = new AtomicInteger();
        int c = -1;
        final ReentrantLock putLock = new ReentrantLock();
        putLock.lock();
        try {
            if (count.get() < Integer.MAX_VALUE) {
                c = count.getAndIncrement();
            }
        } finally {
            putLock.unlock();
        }
        return c >= 0;
    }

}
