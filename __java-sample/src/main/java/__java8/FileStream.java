package __java8;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/8/18 18:13
 * Desc    Setting | Editor | File and Code Templates
 */
public class FileStream {

    public static void main(String[] args) {
        FileStream fileStream = new FileStream();
        InputStream is = null;
        BufferedReader bufferedReader = null;
        try {
            is = fileStream.getFileInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(is));

            Supplier<Stream<String>> supplier = bufferedReader::lines;
            // Supplier<Stream<String>> supplier = () -> Stream.of("server.port=80", "server.contentPath=sssarm");

            List<Map<String, String>> list =
                    supplier.get()
                            .filter(line -> line.contains("="))
                            .map(line ->
                                    (Map<String, String>) new HashMap<String, String>() {{
                                        put(line.split("=")[0], line.split("=")[1]);
                                    }}
                            ).collect(Collectors.toList());
            System.out.println(Arrays.toString(list.toArray()));

            Map<String, String> map =
                    supplier.get()
                            .filter(line -> line.contains("="))
                            .collect(Collectors.toMap(line -> line.split("=")[0], line -> line.split("=")[1]));
            System.out.println(map.toString());

        } catch (Exception ignore) {
        } finally {
            close(is, bufferedReader);
        }
    }

    private static void close(Closeable... closeables) {
        if (closeables == null || closeables.length == 0) {
            return;
        }
        for (Closeable closeable : closeables) {
            close(closeable);
        }
    }
    private static void close(Closeable closeable) {
        if (closeable == null) {
            return;
        }
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private InputStream getFileInputStream() {
        return getClass().getResourceAsStream("/project.properties");
    }

}
