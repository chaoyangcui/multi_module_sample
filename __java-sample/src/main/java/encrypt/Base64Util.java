package encrypt;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * @date 2018/1/22 16:09
 * Base64 编码/解码
 */
public final class Base64Util {

    /**
     * Base64 解码
     * @param data 解码数据
     * @return 解码后的数据
     */
    public static byte[] decode(final String data) {
        return org.apache.commons.codec.binary.Base64.decodeBase64(data);
    }

    /**
     * Base64 编码
     * @param data 编码数据
     * @return 编码后的数据
     */
    public static String encode(byte[] data) {
        return org.apache.commons.codec.binary.Base64.encodeBase64URLSafeString(data);
    }

}
