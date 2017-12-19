package base.gc;

import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by cuiguiyang on 2017/3/7 21:29.
 * Desc
 * VM option：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
 */
public class GCTest {

    private static final int _1MB = 1024 * 1024;

    public static void main(String[] args) {

        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>();

        // 测试GC
        //testAllocation();

        // 测试大对象
        //testPretenureSizeThreshold();

        // 测试对象进入老年代阀值 -XX:MaxTuneringThreshold=1 -default value is 15
        testMaxTenuringThreshold();
    }

    /**
     * VM option：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:+UseSerialGC
     * -XX:MaxTenuringThreshold=1
     */
    public static void testMaxTenuringThreshold() {

        byte[] allocation1, allocation2, allocation3;
        allocation1 = new byte[_1MB / 4];
        // 何时进入老年代根据-XX:+MaxTenuringThreshold=1设置

        allocation2 = new byte[_1MB * 4];

        allocation3 = new byte[_1MB * 4];
        allocation3 = null;
        allocation3 = new byte[_1MB * 4];

    }

    /**
     * VM option：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:+UseParNewGC
     * -XX:PretenureSizeThreshold=_1M*3=3145728 -大对象设置，大于此大小的对象直接分配在老年代
     * 只对Serial和ParNew两款垃圾收集器有效
     */
    public static void testPretenureSizeThreshold() {
        byte[] allocation;
        allocation = new byte[4 * _1MB];
    }

    /**
     * VM option：-verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8
     */
    public static void testAllocation() {
        byte[] allocation1, allocation2, allocation3, allocation4;

        allocation1 = new byte[2 * _1MB];

        allocation2 = new byte[2 * _1MB];

        allocation3 = new byte[2 * _1MB];

        allocation4 = new byte[4 * _1MB]; // 触发GC
    }
}
