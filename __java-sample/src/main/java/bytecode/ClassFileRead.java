package bytecode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric Cui
 * @date 2017/12/21 11:55
 * Desc    Setting | Editor | File and Code Templates
 */
public class ClassFileRead {

    public static void main(String[] args) {

        InputStream fileInputStream = new ClassFileRead().inputStream();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream))) {
            String line;
            StringBuilder builder = new StringBuilder();
            while ((line = bufferedReader.readLine()) != null) {
                builder.append(toHex(line));
                if (builder.length() % 16 == 0) {
                    builder.append("\n");
                }
            }
            System.out.println(builder.toString().toUpperCase());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fileInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static String toHex(String arg) throws UnsupportedEncodingException {
        // return String.format("%040x", new BigInteger(1, arg.getBytes("UTF-8"/*YOUR_CHARSET?*/)));
        return String.format("%x", new BigInteger(1, arg.getBytes("UTF-8"/*YOUR_CHARSET?*/)));
    }

    private InputStream inputStream() {
        return this.getClass().getResourceAsStream("Hello.class");
    }
}
