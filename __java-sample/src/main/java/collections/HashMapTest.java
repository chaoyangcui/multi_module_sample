package collections;

import java.util.HashMap;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * @date 2018/1/9 11:54
 * Description
 */
public class HashMapTest {
    public static void main(String[] args) {
        System.out.println(1 << 3);
        HashMap<String, String> map = new HashMap<>(1 << 3);
        map.put("a", "a");
        map.put("b", "b");
        map.put("c", "c");
        map.put("d", "d");
        map.put("e", "e");
    }
}
