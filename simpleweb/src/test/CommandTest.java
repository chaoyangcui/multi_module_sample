import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by cuiguiyang on 2017/6/12 20:53.
 * Desc:
 */
public class CommandTest {
    public static void main1(String[] args) {
        String[] commands = {
                "/bin/zsh",
                "-c",
                "ls /Users/cuiguiyang"
        };

        InputStream is = null;
        Process process = null;
        try {
            int lines = 1000;
            for (int i = 1; i <= lines; i++) {
                commands[2] = String.format("print %d >> /Users/cuiguiyang/log_test.log", i);
                process = Runtime.getRuntime().exec(commands);
            }
            process = Runtime.getRuntime().exec(commands);

            is = process.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        int lines = 900;
        int size = 200;
        int times = lines / size;

        String[] commands = {
                "/bin/zsh",
                "-c",
                ""
        };
        String command = "tail -n %d /Users/cuiguiyang/log_test.log | head -n %d";
        Process process = null;
        InputStream is = null;
        try {
            for (int i = times + 1; i > 0; i--) {
                commands[2] = String.format(command, i * size, size);
                if (i == times + 1) {
                    commands[2] = String.format(command, lines, lines - times * size);
                }
                if (i == 1) {
                    commands[2] = String.format(command, i * size, lines - i * size);
                }
                System.out.println("Command:" + commands[2]);
                process = Runtime.getRuntime().exec(commands);
                is = process.getInputStream();
                BufferedReader bReader = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = bReader.readLine()) != null) {
                    System.out.print(line + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (process != null) {
                process.destroy();
            }
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
