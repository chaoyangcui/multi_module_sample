package encrypt;

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * Date    2017/11/6 14:17
 * Desc    Setting | Editor | File and Code Templates
 */
public class RSAUtil_1 {

    // 非对称密钥算法
    public static final String KEY_ALGORITHM = "RSA";

    // 编码
    private static final String UTF_8 = "UTF-8";

    /**
     * 密钥长度，DH算法的默认密钥长度是1024
     * 密钥长度必须是64的倍数，在512到65536位之间
     */
    private static final int KEY_SIZE = 512;
    // 公钥
    private static final String PUBLIC_KEY = "RSAPublicKey";
    // 私钥
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    private static final Base64.Encoder ENCODER = Base64.getEncoder();
    private static final Base64.Decoder DECODER = Base64.getDecoder();

    public static byte[] base64Decode(final String data) {
        return DECODER.decode(data);
    }

    public static String base64Encode(byte[] data) {
        return ENCODER.encodeToString(data);
    }

    /**
     * 初始化密钥对
     *
     * @return 密钥对
     */
    public static KeyPair getKeyPair() throws Exception {
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

    public static String getPrivateKeyString(KeyPair keyPair) throws Exception {
        return new String(keyPair.getPrivate().getEncoded(), UTF_8);
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

    public static String getPublicKeyString(KeyPair keyPair) throws Exception {
        return new String(keyPair.getPublic().getEncoded(), UTF_8);
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
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        // 数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    public static String encryptByPrivateKey(final String data, byte[] key) throws Exception {
        if (data == null || key == null) {
            throw new IllegalArgumentException("参数不能为空");
        }
        byte[] encryptData = encryptByPrivateKey(data.getBytes(UTF_8), key);
        return base64Encode(encryptData);
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
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        // 数据解密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    public static String encryptByPublicKey(final String data, byte[] key) throws Exception {
        byte[] encryptData = encryptByPublicKey(data.getBytes(UTF_8), key);
        return base64Encode(encryptData);
    }

    /**
     * 私钥解密
     *
     * @param data 待解密的数据
     * @param key  私钥
     * @return 解密后的数据
     */
    public static byte[] descryptByPrivateKey(byte[] data, byte[] key) throws Exception {
        // 取得私钥
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
        // 实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 生成私钥
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        // 解密数据
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateKey);
        return cipher.doFinal(data);
    }

    public static String descryptByPrivateKey(final String data, byte[] key) throws Exception {
        byte[] encryptData = base64Decode(data);
        byte[] descryptData = descryptByPrivateKey(encryptData, key);
        return new String(descryptData, UTF_8);
    }

    /**
     * 公钥解密
     *
     * @param data 待解密的数据
     * @param key  公钥
     * @return 解密后的数据
     */
    public static byte[] descryptByPublicKey(byte[] data, byte[] key) throws Exception {
        // 获得公钥
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
        // 实例化密钥工厂
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        // 生成公钥
        PublicKey publicKey = keyFactory.generatePublic(keySpec);
        // 解密数据
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicKey);
        return cipher.doFinal(data);
    }

    public static String descryptByPublicKey(final String data, byte[] key) throws Exception {
        byte[] encryptData = base64Decode(data);
        byte[] descryptData = descryptByPublicKey(encryptData, key);
        return new String(descryptData, UTF_8);
    }


    public static void main(String[] args) throws Exception {
        KeyPair keyPair = RSAUtil_1.getKeyPair();
        byte[] privateKey = RSAUtil_1.getPrivateKey(keyPair);
        byte[] publicKey = RSAUtil_1.getPublicKey(keyPair);

        final String data = "深入理解Java虚拟机";
        System.out.println("原始数据:  " + data);

        byte[] encryptDataByte, descryptDataByte;
        String encryptData, descryptData;

        // 私钥加密
        encryptData = RSAUtil_1.encryptByPrivateKey(data, privateKey);
        System.out.println("私钥加密之后的数据:  " + encryptData);
        // 公钥解密
        descryptData = RSAUtil_1.descryptByPublicKey(encryptData, publicKey);
        System.out.println("公钥解密之后的数据:  " + descryptData);

        System.out.println("==================================");

        //  公钥加密
        encryptData = RSAUtil_1.encryptByPublicKey(data, publicKey);
        System.out.println("公钥加密后的数据:  " + encryptData);
        descryptData = RSAUtil_1.descryptByPrivateKey(encryptData, privateKey);
        System.out.println("私钥解密后的数据:  " + descryptData);

        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.get();
    }

}
