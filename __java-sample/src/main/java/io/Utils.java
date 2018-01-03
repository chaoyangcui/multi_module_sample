package io;

/**
 * Created by Intellij IDEA.
 * Date  : 2018/1/3 20:28
 *
 * @author Eric Cui
 * Desc  : 描述信息
 */
public class Utils {

    private static final String ADB_PATH = "D:\\__sssarm\\platform-tools\\adb";

    public static boolean isWin() {
        String osName = System.getProperty("os.name", "");
        return osName.contains("Win");
    }

    public static String getAdbPath() {
        if (isWin()) {
            return ADB_PATH;
        } else {
            return "adb";
        }
    }

}
