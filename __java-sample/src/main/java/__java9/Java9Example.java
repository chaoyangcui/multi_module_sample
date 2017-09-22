package __java9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/22 15:17
 * Desc    Setting | Editor | File and Code Templates
 */
public class Java9Example {

    Module module;

    public static void main(String[] args) {
        System.out.println(ProcessHandle.current().info().toString());
        List<Integer> immutableList = List.of(1, 2, 3, 4);
        immutableList.stream().filter((a) -> a % 2 == 0).forEach(System.out::println);

        Interface0 interface0 = new Impl0();
        interface0.t();
        interface0.t1();
        System.out.println("---------------------------------------------");

        Java9Example example = new Java9Example();
        example.fileReadAndPrint();


        Executor executor = CompletableFuture.delayedExecutor(10L, TimeUnit.SECONDS);
        executor.execute(() -> System.out.println("Hello Java9"));
    }

    public void fileReadAndPrint() {
        InputStream is0 = getClass().getResourceAsStream("ini0.ini");
        try (is0) {
            BufferedReader reader0 = new BufferedReader(new InputStreamReader(is0));
            try (reader0) {
                String line;
                while ((line = reader0.readLine()) != null) {
                    System.out.println(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        InputStream is = getClass().getResourceAsStream("ini0.ini");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

class OptionalExample {
    public static void main(String[] args) {
        System.out.println(division(4, 2));
        System.out.println(division(4, 0));


        division(4, 2).ifPresent(System.out::println);
    }


    public static Optional<Integer> division(Integer i1, Integer i2) {
        if (i2 == 0) return Optional.empty();
        else {
            Integer i3 = i1 / i2;
            return Optional.of(i3);
        }
    }
}