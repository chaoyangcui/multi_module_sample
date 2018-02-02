package encrypt;


/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * @date 2018/1/29 16:22
 * Description
 */
public class DecryptTest {

    private static final String PrivateKey = RSAUtil.PrivateKey;

    public static void main(String[] args) throws Exception {

        System.out.println(RSAUtil.encryptByPublicKey("hello", RSAUtil.PublicKey));

        String encrypt = "LXSMjbHowHWk_m4lPHaQmMtmWaAvHudDCfiZrThiYsDaHKsY6Kz6ZVQKHUqotcb9cd9ZdDqblDDFjzzGb0UEQw";
        System.out.println(RSAUtil.decryptByPrivateKey(encrypt, PrivateKey));

        System.out.println(new String(org.apache.commons.codec.binary.Base64.decodeBase64("NTAzMDE3MTE2")));

    }

}
