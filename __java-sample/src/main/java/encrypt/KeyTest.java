package encrypt;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.util.io.pem.PemObject;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * Created by IntelliJ IDEA.
 *
 * @author Eric
 * @date 2018/1/29 16:18
 * Description
 */
public class KeyTest {
    private static final String ALGORITHM = "RSA";
    private static final int KEYSIZE = 512;

    public static void main(String args[]) {
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM);
            keyPairGenerator.initialize(KEYSIZE);
            KeyPair keyPair = keyPairGenerator.genKeyPair();

            PublicKey publicKey = keyPair.getPublic();
            PrivateKey privateKey = keyPair.getPrivate();

            System.out.println(publicKey);
            System.out.println(Base64Util.encode(publicKey.getEncoded()));

            System.out.println("Public java.security: Algorithm: " + publicKey.getAlgorithm() + "Format: " + publicKey.getFormat());
            System.out.println("Private java.security: Algorithm: " + privateKey.getAlgorithm() + "Format: " + privateKey.getFormat() + "\n");

            PemObject pemObject = new PemObject("desc", publicKey.getEncoded());
            System.out.println(pemObject);
            System.out.println(Base64Util.encode(pemObject.getContent()));

            byte[] pKbytes = Base64.encodeBase64(publicKey.getEncoded());
            String pK = new String(pKbytes);
            String pubKey = "-----BEGIN PUBLIC KEY-----\n" + pK + "-----END PUBLIC KEY-----\n";
            System.out.println(pubKey);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}
