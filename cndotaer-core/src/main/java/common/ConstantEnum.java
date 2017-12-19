package common;

import java.nio.charset.Charset;

/**
 * Created by cuiguiyang on 2017/3/12 18:08.
 * Desc
 */
public class ConstantEnum {
    public static final int OFFSET = 0;

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 1234;
    public static final int PORT_NIO = 8080;

    public static final String UTF8 = "UTF-8";
    public static final Charset CHARSET_UTF8 = Charset.forName(UTF8);


    // true & false
    public static final boolean TRUE = true;
    public static final boolean FALSE = false;

}
