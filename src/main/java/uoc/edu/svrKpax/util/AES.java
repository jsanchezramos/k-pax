package uoc.edu.svrKpax.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import com.sun.org.apache.xml.internal.security.utils.Base64;

/**
 * This program generates a AES key, retrieves its raw bytes, and then
 * reinstantiates a AES key from the key bytes. The reinstantiated key is used
 * to initialize a AES cipher for encryption and decryption.
 */

public class AES {

	public static String descrypt(String texto) throws Exception {
		if (texto.length() < 1) {
			System.err
					.println("ERROR: specify a base64 encoded encrypted string as parameter\n");
			System.exit(1);
		}
		// define keys
		String secret = new String("1234567890123456");
		String iv = new String("abcdefghijklmnop");
		// base64 decoding
		int i = 0;
		
		
		String enc64 = texto.trim().replaceAll(" ", "+");
		byte[] enc64bytes = enc64.getBytes();
		byte[] dec64bytes = Base64.decode(enc64bytes);
		// because the toString method does not work you have to cast each array
		// element separately

		// set decrypt params
		Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
		SecretKeySpec keySpec = new SecretKeySpec(secret.getBytes(), "AES");
		IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
		ByteArrayInputStream fis = new ByteArrayInputStream(dec64bytes);
		CipherInputStream cis = new CipherInputStream(fis, cipher);
		ByteArrayOutputStream fos = new ByteArrayOutputStream();
		// decrypting
		byte[] b = new byte[8];
		while ((i = cis.read(b)) != -1) {
			fos.write(b, 0, i);
		}
		String result = new String(fos.toString().trim().replaceAll(" ", ""));
		fos.flush();
		fos.close();
		cis.close();
		fis.close();

		return result;
	}
}
