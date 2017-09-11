import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/8/30 16:18
 * Desc    Global configuration
 */
public class GlobalConf {

    static {
        System.out.println("static block.");
    }

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("key", "value");

        // System.out.println(Integer.numberOfLeadingZeros(0));
        // System.out.println(Integer.numberOfLeadingZeros(1));
        // System.out.println(Integer.numberOfLeadingZeros(2));
        // System.out.println(Integer.numberOfLeadingZeros(3));

        // System.out.println(Integer.MAX_VALUE);
        // System.out.println(Integer.parseInt("7fffffff", 16));
        // System.out.println(Runtime.getRuntime().availableProcessors());

        // System.out.println(tableSizeFor(1));
        // System.out.println(tableSizeFor(2));
        // System.out.println(tableSizeFor(10));

        // String videoUrl = "http://toutiao.com/group/6440954669488407041/?iid=1843468903&app=news_article";
        // System.out.println(videoUrl.split("\\?")[0]);
        // System.out.println(videoUrl);
    }


    // private static final int MAXIMUM_CAPACITY = 1 << 30;
    // private static final int tableSizeFor(int c) {
    //     int n = c - 1;
    //     n |= n >>> 1;
    //     n |= n >>> 2;
    //     n |= n >>> 4;
    //     n |= n >>> 8;
    //     n |= n >>> 16;
    //     return (n < 0) ? 1 : (n >= MAXIMUM_CAPACITY) ? MAXIMUM_CAPACITY : n + 1;
    // }

}
