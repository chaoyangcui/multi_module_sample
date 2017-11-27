package encrypt;

/**
 * @author Administrator
 */
public class Test {
	public static void main(String[] args) {
		String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDYGSDkvE00yWEgoknBIe1zOGomiFi3aabUUdn+fLCH4hNcM+Z8hjgB9R2nusGq+WkWupXnt3BO4QItmyWj+iJ7sbjcPl2UzbGL6B43I58TuPomaxq8G4FpknzCciO1ErI+ttgcW8lQWaSp6rLBgzSe28gR7cf9lWlaYBI6z9pM3wIDAQAB";
		String couponThemeId="1047046000001419";
		try {
			System.out.println(BaseRSAUtils.encryptByPublicKey(couponThemeId.getBytes(), publicKey));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
