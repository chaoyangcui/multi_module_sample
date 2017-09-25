package __java9;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/9/22 15:17
 * Desc    Java9 Example
 */
public class Java9Example {

    private static final Logger logger = Logger.getGlobal();
    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Java9Example.class);
    static {
    }

    public static void main(String[] args) {
        System.out.println(ProcessHandle.current().info().toString());
        List<Integer> immutableList = List.of(1, 2, 3, 4);
        immutableList.stream().filter((a) -> a % 2 == 0).forEach(System.out::println);

        Interface0 interface0 = new Impl0();
        interface0.t();
        interface0.t1();
        System.out.println("---------------------------------------------\n\n");

        Java9Example example = new Java9Example();
        example.fileReadAndPrint();

        example.compareAndSetTarget(null, "Hello Java9 CAS.");
        example.printTarget();


        Executor executor = CompletableFuture.delayedExecutor(10L, TimeUnit.SECONDS);
        executor.execute(() -> System.out.println("Hello Java9"));

        logger.info("info");
        LOG.info("INFO");
    }

    public void compareAndSetTarget(Object expact, Object update) {
        TARGET.compareAndSet(this, expact, update);
    }

    public void printTarget() {
        System.out.println(TARGET.get(this));
    }

    @SuppressWarnings("unchecked")
    public void fileReadAndPrint() {
        InputStream is0 = getClass().getResourceAsStream("/ini0.ini");
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
        Optional<InputStream> inputStreamOptional = Optional.ofNullable(is);
        Optional<InputStream> optional = inputStreamOptional.map((InputStream value) -> getClass().getResourceAsStream("/project.properties"));
        is = optional.orElseGet(() -> getClass().getResourceAsStream("ini0.ini"));
        inputStreamOptional.ifPresentOrElse(inputStream -> {
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    System.out.println(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }, () -> System.out.println("failed to read file."));
    }


    private Object target;

    private static final VarHandle TARGET;

    static {
        try {
            MethodHandles.Lookup l = MethodHandles.lookup();
            TARGET = l.findVarHandle(Java9Example.class, "target", Object.class);
        } catch (ReflectiveOperationException e) {
            throw new Error(e);
        }
    }
}

class OptionalExample {
    private static ObjectMapper mapper = new ObjectMapper();

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        System.out.println(division(4, 2));
        System.out.println(division(4, 0));


        division(4, 2).ifPresent(System.out::println);

        Optional<Java9Example> optional = Optional.of (new Java9Example());
        optional.ifPresent(Java9Example::fileReadAndPrint);



        Map<String, String> map = new HashMap<>(){{
            String val2 = "{\"key2\":\"val2\"}";
            put("key1", val2);
        }};
        String val2 = Optional.of(map)
                .map((map0) -> {
                    System.out.println(map.get("key1"));
                    return map.get("key1");
                })
                .map((val1) -> {
                    Map<String, String> map1 = null;
                    try {
                        map1 = mapper.readValue(val1, Map.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return Optional.ofNullable(map1).map((e) -> e.get("key2")).orElse(null);
                }).orElse("key2 is not found.");
        System.out.println(val2);

    }


    public static Optional<Integer> division(Integer i1, Integer i2) {
        if (i2 == 0) return Optional.empty();
        else {
            Integer i3 = i1 / i2;
            return Optional.of(i3);
        }
    }
}