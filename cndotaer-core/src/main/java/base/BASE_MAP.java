package base;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by cuiguiyang on 2017/2/9 21:06.
 * Desc
 */
public class BASE_MAP {

    public static void main(String[] args) {
        /*int num = 2;
        System.out.println(num << 1);
        System.out.println(num << 2);
        System.out.println(num << 3);
        System.out.println(num << 4);
        System.out.println(num << 5);
        System.out.println(Integer.toBinaryString(num));
        System.out.println(Integer.toBinaryString(17));*/

        String AaAa = "AaAa";
        String BBBB = "BBBB";
        String BBBB1 = "BBBB1";
        String BBBB2 = "BBBB2";
        String BBBB3 = "BBBB3";

        Map<String, Object> map = new HashMap<>();
        map.put(AaAa, "AaAa");
        map.put(BBBB, "BBBB");
        map.put(BBBB1, "BBBB1");
        map.put(BBBB2, "BBBB2");
        map.put(BBBB3, "BBBB3");
        System.out.println(map.hashCode());

        /*System.out.println(map.get(AaAa));
        System.out.println(map.get(BBBB));*/

        // 31 * 65 + 65
        // 130 + (30 * 65 = 1950)

        System.out.println((int) 'A');
        System.out.println("AA".hashCode());

    }

}
