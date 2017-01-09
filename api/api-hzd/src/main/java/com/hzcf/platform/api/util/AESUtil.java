package com.hzcf.platform.api.util;

import java.security.SecureRandom;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 * 
 * @ClassName: AESUtil：加密工具类， 
 *        线上和调度对接时，需要使用该工具类进行加密
 * @Description: aes工具类
 * @author 裴高祥
 * @date 2015-5-22 下午4:50:15 
 *
 */
public class AESUtil {		
	
	/**
	 * 加密函数
	 * @param content   加密的内容
	 * @param strKey    密钥
	 * @return  		返回二进制字符数组
	 * @throws Exception
	 */
	public static String enCrypt(String content,String strKey) throws Exception{
		KeyGenerator keygen;		
		SecretKey desKey;
		Cipher c;		
		byte[] cByte;
		String str = content;
		//解决LINUX下KEY不一致
		keygen = KeyGenerator.getInstance("AES");
        SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );  
        secureRandom.setSeed(strKey.getBytes());  
        keygen.init(128,secureRandom);  
		desKey = keygen.generateKey();		
		c = Cipher.getInstance("AES");
		
		c.init(Cipher.ENCRYPT_MODE, desKey);
		
		cByte = c.doFinal(str.getBytes("UTF-8"));		
		//加密过的二进制数组转化成16进制的字符串
		return parseByte2HexStr(cByte);
	}
	
	/** 解密函数
	 * @param src   加密过的二进制字符数组
	 * @param strKey  密钥
	 * @return
	 * @throws Exception
	 */
	public static String deCrypt (String src,String strKey) throws Exception{
		KeyGenerator keygen;		
		SecretKey desKey;
		Cipher c;		
		byte[] cByte;	
		
		keygen = KeyGenerator.getInstance("AES");
		//解决LINUX下KEY不一致
		SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG" );  
	    secureRandom.setSeed(strKey.getBytes());  
	    keygen.init(128,secureRandom);  
		
		desKey = keygen.generateKey();
		c = Cipher.getInstance("AES");
		
		c.init(Cipher.DECRYPT_MODE, desKey);
		//将16进制转换为二进制
		byte[] encrytByte = AESUtil.parseHexStr2Byte(src);		
		cByte = c.doFinal(encrytByte);	
		
		return new String(cByte,"UTF-8");
	}
	
	
	/**2进制转化成16进制
	 * @param buf
	 * @return
	 */
	public static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
				}
			sb.append(hex.toUpperCase());
			}
		return sb.toString();
	}
	
	
	/**将16进制转换为二进制
	 * @param hexStr
	 * @return
	 */ 	
	public static byte[] parseHexStr2Byte(String hexStr) { 
	        if (hexStr.length() < 1) 
	                return null; 
	        byte[] result = new byte[hexStr.length()/2]; 
	        for (int i = 0;i< hexStr.length()/2; i++) { 
	                int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16); 
	                int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16); 
	                result[i] = (byte) (high * 16 + low); 
	        } 
	        return result; 
	} 

}