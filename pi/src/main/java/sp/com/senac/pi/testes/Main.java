package sp.com.senac.pi.testes;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Main {

	public static void main(String args[]) {
		 String s="master";
		 MessageDigest m = null;
		try {
			m = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 m.update(s.getBytes(),0,s.length());
		 System.out.println("MD5: "+new BigInteger(1,m.digest()).toString(8));
		 System.out.println("MD5: "+ m.digest());
		 
		 MessageDigest digest = null;
		try {
			digest = MessageDigest.getInstance("SHA-512");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	
	    digest.reset();
	    digest.update(s.getBytes(StandardCharsets.UTF_8));
	    s = String.format("%040x", new BigInteger(1,digest.digest() ));
	    System.out.println("SHA 512: "+ s);
	    System.out.println("SHA 512: "+ digest.digest());
	    System.out.println("SHA 512: "+new BigInteger(1,digest.digest()).toString(16));
	}
}
//fb1008a80bf14bd80d41ea2b4fcf3604
//7r204ag2vh9fc0qgfa5d7sudg4
//3730400425002770513660065017242551763633004
//333719479265054617344184325964481639940
//3323010000202220002333011023312000311001322202231033303303120010