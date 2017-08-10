import java.io.UnsupportedEncodingException;
import java.security.*;

import javax.crypto.*;

public class ASKE {
    public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException, InvalidKeyException, UnsupportedEncodingException {
	// The only things that differ are that messages are encrypted
	// and decrypted by the cipher with 2 distinct keys
	// The priv will undo the public's encryption and vice-versa
	System.out.println("Asymmetric key encryption:");
	KeyPairGenerator keygenerator = KeyPairGenerator.getInstance("RSA");
	KeyPair secretKey = keygenerator.generateKeyPair();
	PublicKey pub = secretKey.getPublic();
	PrivateKey priv = secretKey.getPrivate();
	Cipher cipher = Cipher.getInstance("RSA");
	cipher.init(Cipher.ENCRYPT_MODE, priv);
	// Done initializing objects
	// Text to encrypt.
	byte[] text = "This text will become encrypted 0_o".getBytes("UTF8");
	byte[] textEncrypted = cipher.doFinal(text);
	String s = textEncrypted.toString();
	// Test output
	System.out.println("Encrypted as: " + s);
	// Set the cipher object to decrypt.
	cipher.init(Cipher.DECRYPT_MODE, pub);
	byte[] textDecrypted = cipher.doFinal(textEncrypted);
	s = new String(textDecrypted);
	System.out.println("Revealed as: " + s);
    }
}