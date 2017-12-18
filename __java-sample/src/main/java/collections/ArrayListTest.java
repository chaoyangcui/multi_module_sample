package collections;

import entity.ExampleEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric Cui
 * @date 2017/12/6 16:42
 * Desc    Setting | Editor | File and Code Templates
 */
public class ArrayListTest {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List<ExampleEntity> list = new ArrayList<>(100000);
        for (int i = 0; i < 100000; i++) {
            list.add(ExampleEntity.builder().id(i).desc("").build());
        }
        System.out.println(list.size());
        System.out.println(System.currentTimeMillis() - start);
    }

}
