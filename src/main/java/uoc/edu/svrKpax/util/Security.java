package uoc.edu.svrKpax.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

public class Security {

	private final static String USER = "USER.";
	private final static String GAME = "GAME.";

	static public String encrypt(String containt){
		return getCrypt(containt);
	}

	static public Boolean isIdGame(String containt) {
		return containt.startsWith("GAME.");
	}

	static public String getIdSession() {
		return getId("");
	}

	static public String getIdUser() {
		return getId(USER);
	}

	static public String getIdGame() {
		return getId(GAME);
	}
	

	static private String getCrypt(String randomNum) {
		byte[] result = null;
		try {
			// get its digest
			MessageDigest sha;
			sha = MessageDigest.getInstance("SHA-1");
			result = sha.digest(randomNum.getBytes());

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return hexEncode(result);
	}

	static private String getId(String type) {
		SecureRandom prng;
		String resultCrypt = null;
		try {
			// generate a random number
			prng = SecureRandom.getInstance("SHA1PRNG");

			String randomNum = new Integer(prng.nextInt()).toString();

			resultCrypt = getCrypt(randomNum);

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return type + resultCrypt;
	}

	static private String hexEncode(byte[] aInput) {
		StringBuilder result = new StringBuilder();
		char[] digits = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'a', 'b', 'c', 'd', 'e', 'f' };
		for (int idx = 0; idx < aInput.length; ++idx) {
			byte b = aInput[idx];
			result.append(digits[(b & 0xf0) >> 4]);
			result.append(digits[b & 0x0f]);
		}
		return result.toString();
	}
}