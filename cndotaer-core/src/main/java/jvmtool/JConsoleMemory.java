package jvmtool;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cuiguiyang on 2017/3/25 20:54.
 * Desc
 */
public class JConsoleMemory {
    /**
     * 内存占位符对象，一个OOMObject大约占64k
     */
    static class OOMObject {
        public byte[] placeholder = new byte[64 * 1024];
    }

    public static void fillHeap(int num) throws InterruptedException {
        List<OOMObject> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(500);
            list.add(new OOMObject());
        }

        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        int num = 1000;
        fillHeap(num);
    }

}
