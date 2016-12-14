package com.hzcf.platform.core.sys.util;


import java.util.Random;

import org.apache.shiro.crypto.hash.Md5Hash;

public class Md5HashUtil {
	
	 public static String entryptPasswordByMd5(String plainPassword,String salt){
		 //String salt="eteokues";
		 Md5Hash  md5hash=new Md5Hash(plainPassword,salt,1);
		 
		 return md5hash.toHex();
		 
	 }

	public static String getSalt() {
		// String salt="eteokues";

		char[] codeSeq = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R', 'S',
				'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
		Random random = new Random();
		StringBuilder s = new StringBuilder();
		for (int i = 0; i < 8; i++) {
			String r = String.valueOf(codeSeq[random.nextInt(codeSeq.length)]);// random.nextInt(10));

			s.append(r);
		}
		System.out.println("加密salt：" + s.toString());
		return s.toString();

	}
	 public static boolean validatePasswordByMd5(String plainPassword,String password,String salt){
		 //String salt="eteokues";
		 Md5Hash  md5hash=new Md5Hash(plainPassword,salt,1);
		 return password.equals(md5hash.toHex());
		
	 }
	 public static void main(String[] args){
			String password="111111";//cb571f7bd7a6f73ab004a70322b963d5
			
			System.out.println("加密后的密码 ："+entryptPasswordByMd5("111111","test"));
	}
	 
	 
}
