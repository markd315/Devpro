import javax.crypto.*;

public class SKE {
    public static void main(String[] args) {
	try {
	    System.out.println("Symmetric key encryption:");
	    KeyGenerator keygenerator = KeyGenerator.getInstance("AES");
	    SecretKey secretKey = keygenerator.generateKey();
	    Cipher cipher = Cipher.getInstance("AES");
	    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	    // Done initializing objects
	    // Text to encrypt.
	    byte[] text = "This text will become encrypted 0_o".getBytes("UTF8");
	    byte[] textEncrypted = cipher.doFinal(text);
	    String s = new String(textEncrypted);
	    //Test output
	    System.out.println("Encrypted as: " + s);
	    // Set the cipher object to decrypt.
	    cipher.init(Cipher.DECRYPT_MODE, secretKey);
	    byte[] textDecrypted = cipher.doFinal(textEncrypted);
	    s = new String(textDecrypted);
	    System.out.println("Revealed as: " + s);
	} catch (Exception e) {
	    System.out.println("Exception");
	}
    }
}