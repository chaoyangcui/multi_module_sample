package __java8.function;

import java.util.*;

/**
 *
 * @author Eric Cui
 * 
 * Created by Intellij IDEA.
 * Date  : 2018/1/7 16:44
 * Desc  : 描述信息
 */
public class FieldConfAccompany {
    private static Map<String, FieldConf> fieldConfMap = new HashMap<>();
    private static List<String> allFields = new ArrayList<>();

    static {
        for (FieldConf fc: FieldConf.values()) {
            fieldConfMap.put(fc.name(), fc);
            allFields.add(fc.getName());
        }
    }

    public static FieldConf getInstance(String name) {
        return fieldConfMap.get(name);
    }

    public static List<String> getAllFields() {
        return Collections.unmodifiableList(allFields);
    }
}
