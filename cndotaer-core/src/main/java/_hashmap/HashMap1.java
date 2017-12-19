package _hashmap;

import org.apache.commons.lang3.math.NumberUtils;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by cuiguiyang on 2017/7/3 21:42.
 * Desc:
 */
public class HashMap1 {
    public static void main(String[] args) {
        HashMap<String, String> map1 = new HashMap<>();
        ConcurrentHashMap<String, String> map2 = new ConcurrentHashMap<>();
        map1.put("", "");
        map2.put("", "");

        String str = "hello";
        int n = 1;
        if (map2.size() == map1.size()) {
            n = 88;
        }
        System.out.println(NumberUtils.isNumber(str) && 88 == n);
    }
}
