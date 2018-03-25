package __java8;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * @since  2018/2/11 16:04
 *
 */
public class Java8Example {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);
        Supplier<Map<Integer, Integer>> mapSupplier =
                () -> list.stream().collect(Collectors.toMap(x -> x, y -> y * y));

        Map<Integer, Integer> mapValueAdd =
                list.stream()
                        .collect(
                                Collectors.toMap(x -> x, y -> y, (v1, v2) -> v1 + v2, mapSupplier));
        System.out.println(mapValueAdd);
    }
}
