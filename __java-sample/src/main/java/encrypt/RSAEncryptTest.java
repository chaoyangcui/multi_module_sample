package encrypt;

/**
 * @author Administrator
 */
public class RSAEncryptTest {
    public static void main(String[] args) throws Exception {
        String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDYGSDkvE00yWEgoknBIe1zOGomiFi3aabUUdn+fLCH4hNcM+Z8hjgB9R2nusGq+WkWupXnt3BO4QItmyWj+iJ7sbjcPl2UzbGL6B43I58TuPomaxq8G4FpknzCciO1ErI+ttgcW8lQWaSp6rLBgzSe28gR7cf9lWlaYBI6z9pM3wIDAQAB";
        String couponThemeId = "1047046000001419";
        try {
            System.out.println(BaseRSAUtils.encryptByPublicKey(couponThemeId.getBytes(), publicKey));
        } catch (Exception e) {
            e.printStackTrace();
        }

        String cid = "P10001";
        String t = "1512613239493";
        String p = RSAUtil.encryptByPrivateKey(String.format("%s#%s", cid, t), RSAUtil.PrivateKey);
        System.out.println("加密:" + p);
        String descrypt = RSAUtil.decryptByPublicKey(p, RSAUtil.PublicKey);
        System.out.println("解密:" + descrypt);

    }
}
