package encrypt;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * add dependency to pom.xml
 *   <dependency>
 *       <groupId>commons-codec</groupId>
 *       <artifactId>commons-codec</artifactId>
 *       <version>1.9</version>
 *   </dependency>
 *
 * @author Eric
 * Date    2017/11/6 14:17
 * Desc    Setting | Editor | File and Code Templates
 */
public class RSAUtil {

    // 非对称密钥算法
    private static final String KEY_ALGORITHM = "RSA";

    // 编码
    private static final String UTF8 = "UTF-8";

    /**
     * 密钥长度，DH算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 512;

    // 私钥
    public static final String PrivateKey = "MIIBVgIBADANBgkqhkiG9w0BAQEFAASCAUAwggE8AgEAAkEAl7z_WrXLBI_Ex_fn4vLOWfwmF3f4hxQG3zlufnkACliGFOYD0NolOgVMuYRTdfBjnPiIibcYwWoj70VBS25nvwIDAQABAkAg85IgiMoFu5LoOhXJWyEkuXUsM4ltDBRR9Y7hODWiCTG4QyN64JoB3gvc2Fm30KgIPe5pg_5NyLJsJ5dQzm5BAiEAyB8aJpF6UxfN6V-uEPhbIqoQTh1l9HUZTHF_S_ntTYsCIQDCG2k5hhmnNOwrLXCMKji6Ypxqo9HK51Cxs7MwT4e9HQIhALeTAFQc409R6MuJv41FpaQ4yNg_U4VZ3akn_BPf4fVdAiEAlgVRgmAem5jPn19zSSGozoVAlzsd_lBhkbZtm70cEBECIQCtDCYdzknKeuBt7YbmoWyWmeNIlSXaiDwOeDSl2hgytw";
    // 公钥
    public static final String PublicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAJe8_1q1ywSPxMf35-Lyzln8Jhd3-IcUBt85bn55AApYhhTmA9DaJToFTLmEU3XwY5z4iIm3GMFqI-9FQUtuZ78CAwEAAQ";

    /**
     * Base64 解码
     * @param data 需要解码数据
     * @return 返回解码后的数据
     */
    private static byte[] base64Decode(final String data) {
        return Base64Util.decode(data);
    }

    /**
     * Base64编码
     * @param data 需要编码的数据
     * @return 编码后的数据
     */
    private static String base64Encode(byte[] data) {
        return Base64Util.encode(data);
    }

    private static final KeyPair KEY_PAIR;
    static {
        // 实例化密钥生成器
        KeyPairGenerator keyPairGenerator = null;
        try {
            keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // 初始化密钥生成器
        assert keyPairGenerator != null;
        keyPairGenerator.initialize(KEY_SIZE);
        // 生成密钥对
        KEY_PAIR = keyPairGenerator.generateKeyPair();
    }

    /**
     * 初始化密钥对
     *
     * @return 密钥对
     */
    public static KeyPair initKeyPair() throws Exception {
        // 实例化密钥生成器
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        // 初始化密钥生成器
        keyPairGenerator.initialize(KEY_SIZE);
        // 生成密钥对
        return keyPairGenerator.generateKeyPair();
    }

    /**
     * 获取私钥
     *
     * @param keyPair 密钥对
     * @return 私钥
     */
    public static byte[] getPrivateKey(KeyPair keyPair) {
        return keyPair.getPrivate().getEncoded();
    }
    public static byte[] getPrivateKey() {
        return KEY_PAIR.getPrivate().getEncoded();
    }
    public static String getPrivateKeyString(KeyPair keyPair) throws Exception {
        return new String(keyPair.getPrivate().getEncoded(), UTF8);
    }
    public static String getPrivateKeyString() {
        return base64Encode(KEY_PAIR.getPrivate().getEncoded());
    }

    /**
     * 获取公钥
     *
     * @param keyPair 密钥对
     * @return 公钥
     */
    public static byte[] getPublicKey(KeyPair keyPair) {
        return keyPair.getPublic().getEncoded();
    }
    public static byte[] getPublicKey() {
        return KEY_PAIR.getPublic().getEncoded();
    }
    public static String getPublicKeyString(KeyPair keyPair) throws Exception {
        keyPair = (keyPair != null) ? keyPair : KEY_PAIR;
        return new String(keyPair.getPublic().getEncoded(), UTF8);
    }
    public static String getPublicKeyString() {
        return base64Encode(KEY_PAIR.getPublic().getEncoded());
    }

    /**
     * 私钥加密
     *
     * @param data 待加密的数据
     * @param key  私钥
     * @return 加密后的数据
     */
    public static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws Exception {
        // 取得私钥
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
        // 实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 生成私钥
        java.security.PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        // 数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }
    public static byte[] encryptByPrivateKey(byte[] data) throws Exception {
        return encryptByPrivateKey(data, getPrivateKey());
    }

    public static String encryptByPrivateKey(final String data, byte[] key) throws Exception {
        if (data == null || key == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        byte[] encryptData = encryptByPrivateKey(data.getBytes(UTF8), key);
        return base64Encode(encryptData);
    }
    public static String encryptByPrivateKey(final String data, String key) throws Exception {
        return encryptByPrivateKey(data, base64Decode(key));
    }
    public static String encryptByPrivateKey(final String data) throws Exception {
        byte[] key = getPrivateKey();
        return encryptByPrivateKey(data, key);
    }

    /**
     * 公钥加密
     *
     * @param data 待加密的数据
     * @param key  公钥
     * @return 加密后的数据
     */
    public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {
        // 取得公钥
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
        // 实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 生成公钥
        java.security.PublicKey publicKey = keyFactory.generatePublic(keySpec);
        // 数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }
    public static byte[] encryptByPublicKey(byte[] data) throws Exception {
        return encryptByPublicKey(data, getPublicKey());
    }

    public static String encryptByPublicKey(final String data, byte[] key) throws Exception {
        byte[] encryptData = encryptByPublicKey(data.getBytes(UTF8), key);
        return base64Encode(encryptData);
    }
    public static String encryptByPublicKey(final String data, String key) throws Exception {
        return encryptByPublicKey(data, base64Decode(key));
    }
    public static String encryptByPublicKey(final String data) throws Exception {
        return encryptByPublicKey(data, getPublicKey());
    }

    /**
     * 私钥解密
     *
     * @param data 待解密的数据
     * @param key  私钥
     * @return 解密后的数据
     */
    public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
        // 取得私钥
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
        // 实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 生成私钥
        java.security.PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        // 解密数据
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }
    public static byte[] decryptByPrivateKey(byte[] data) throws Exception {
        return decryptByPrivateKey(data, getPrivateKey());
    }

    public static String decryptByPrivateKey(final String data, byte[] key) throws Exception {
        byte[] encryptData = base64Decode(data);
        byte[] decryptData = decryptByPrivateKey(encryptData, key);
        return new String(decryptData, UTF8);
    }
    public static String decryptByPrivateKey(final String data, String key) throws Exception {
        return decryptByPrivateKey(data, base64Decode(key));
    }
    public static String decryptByPrivateKey(final String data) throws Exception {
        return decryptByPrivateKey(data, getPrivateKey());
    }

    /**
     * 公钥解密
     *
     * @param data 待解密的数据
     * @param key  公钥
     * @return 解密后的数据
     */
    public static byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception {
        // 获得公钥
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
        // 实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 生成公钥
        java.security.PublicKey publicKey = keyFactory.generatePublic(keySpec);
        // 解密数据
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }
    public static byte[] decryptByPublicKey(byte[] data) throws Exception {
        return decryptByPublicKey(data, getPublicKey());
    }

    public static String decryptByPublicKey(final String data, byte[] key) throws Exception {
        byte[] encryptData = base64Decode(data);
        byte[] decryptData = decryptByPublicKey(encryptData, key);
        return new String(decryptData, UTF8);
    }
    public static String decryptByPublicKey(final String data, String key) throws Exception {
        return decryptByPublicKey(data, base64Decode(key));
    }
    public static String decryptByPublicKey(final String data) throws Exception {
        return decryptByPublicKey(data, getPublicKey());
    }


    public static void main(String[] args) throws Exception {
        KeyPair keyPair = RSAUtil.initKeyPair();
        byte[] bPrivateKey = RSAUtil.getPrivateKey(keyPair);
        byte[] bPublicKey = RSAUtil.getPublicKey(keyPair);

        String sPrivateKey = RSAUtil.getPrivateKeyString();
        String sPublicKey = RSAUtil.getPublicKeyString();

        final String data = "深入理解Java虚拟机";
        System.out.println("原始数据:  " + data);

        byte[] encryptDataByte, decryptDataByte;
        String encryptData, decryptData;

        // 私钥加密
        encryptData = RSAUtil.encryptByPrivateKey(data, bPrivateKey);
        System.out.println("私钥加密之后的数据:  " + encryptData);
        // 公钥解密
        decryptData = RSAUtil.decryptByPublicKey(encryptData, bPublicKey);
        System.out.println("公钥解密之后的数据:  " + decryptData);

        System.out.println("==================================");

        //  公钥加密
        encryptData = RSAUtil.encryptByPublicKey(data, bPublicKey);
        System.out.println("公钥加密后的数据:  " + encryptData);
        decryptData = RSAUtil.decryptByPrivateKey(encryptData, bPrivateKey);
        System.out.println("私钥解密后的数据:  " + decryptData);

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.get();
    }

}
