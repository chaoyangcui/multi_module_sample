import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
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
        InputStream is = fileStream.getFileInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
        /*String line;
        try {
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        Stream.of(bufferedReader.lines()).toArray().toString();
    }

    private InputStream getFileInputStream() {
        return getClass().getResourceAsStream("/project.properties");
        // return getClass().getResourceAsStream("FileStream.java");
    }

}
