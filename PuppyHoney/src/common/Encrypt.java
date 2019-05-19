package common;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class Encrypt {
 
	public static String getSha512(String key)
	{
		String encPwd=null;
		MessageDigest md=null;
		try
		{
			md=MessageDigest.getInstance("SHA-512");
			
		}
		catch(NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		byte[] bytes=key.getBytes(Charset.forName("UTF-8"));
		md.update(bytes);
		encPwd=Base64.getEncoder().encodeToString(md.digest());
		return encPwd;
	}
	
	public static String getSha256(String input) {

		StringBuffer result = new StringBuffer();

		try {

			MessageDigest digest = MessageDigest.getInstance("SHA-256");

			byte[] salt = "hi hackers".getBytes();

			digest.reset();

			digest.update(salt);

			byte[] chars = digest.digest(input.getBytes("UTF-8"));

			for(int i = 0; i < chars.length; i++) {

				String hex = Integer.toHexString(0xff & chars[i]);

				if(hex.length() == 1) result.append('0');

				result.append(hex);

			}

		} catch (Exception e) {

			e.printStackTrace();

		}

		return result.toString();

	}




}
