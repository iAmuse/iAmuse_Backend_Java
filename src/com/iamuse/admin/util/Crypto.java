package com.iamuse.admin.util;
import java.security.GeneralSecurityException;
import java.util.UUID;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Crypto {

	//private static String key = UUID.randomUUID().toString().replaceAll("-", "");
	private static String key = "bb64e53d18e74444b008b7bbf12ac115".replaceAll("-", "");

	public static String encrypt(String value) throws GeneralSecurityException {
		SecretKeySpec sks = new SecretKeySpec(hexStringToByteArray(key), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.ENCRYPT_MODE, sks, cipher.getParameters());
		byte[] encrypted = cipher.doFinal(value.getBytes());
		return byteArrayToHexString(encrypted);
	}


	public static String decrypt(String message) throws GeneralSecurityException {
		SecretKeySpec sks = new SecretKeySpec(hexStringToByteArray(key), "AES");
		Cipher cipher = Cipher.getInstance("AES");
		cipher.init(Cipher.DECRYPT_MODE, sks);
		byte[] decrypted = cipher.doFinal(hexStringToByteArray(message));
		return new String(decrypted);
	}

	private static String byteArrayToHexString(byte[] b){
		StringBuffer sb = new StringBuffer(b.length * 2);
		for (int i = 0; i < b.length; i++){
			int v = b[i] & 0xff;
			if (v < 16) {
				sb.append('0');
			}
			sb.append(Integer.toHexString(v));
		}
		return sb.toString().toUpperCase();
	}

	private static byte[] hexStringToByteArray(String s) {
		byte[] b = new byte[s.length() / 2];
		for (int i = 0; i < b.length; i++){
			int index = i * 2;
			int v = Integer.parseInt(s.substring(index, index + 2), 16);
			b[i] = (byte)v;
		}
		return b;
	}

}
