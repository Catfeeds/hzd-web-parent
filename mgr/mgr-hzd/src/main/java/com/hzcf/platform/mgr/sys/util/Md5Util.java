package com.hzcf.platform.mgr.sys.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.lang3.StringUtils;
/**
 *  md5加密方法
  * @ClassName: Md5Util
  * @Description:线上向线下发送请求时，使用该类进行加密
  * @author Comsys-chengqiang
  * @date 2014-12-30 上午10:47:23
  *
 */
public class Md5Util {
	/**
	 * 
	* @Title: getMD5 
	* @Description: java
	* @param @param val
	* @param @return
	* @param @throws NoSuchAlgorithmException    设定文件 
	* @return String    返回类型 
	* @author chengqiang
	* @date 2015-9-6 下午2:27:38 
	* @throws
	 */
	public static String getMD5(String val) throws NoSuchAlgorithmException{  
        MessageDigest md5 = MessageDigest.getInstance("MD5");  
        md5.update(val.getBytes());  
        byte[] m = md5.digest();//加密  
        return getString(m);  
	}  
	
    private static String getString(byte[] b){  
        StringBuffer sb = new StringBuffer();  
         for(int i = 0; i < b.length; i ++){  
          sb.append(b[i]);  
         }  
         return sb.toString(); 
    }
    
    /**
     * key加密
     * @param key */
    public static String getMD5String(String str, String key) {
    	String result = null;
		try {
			result = getMD5(str+key);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return result;
    }
    
    /**
     * 
    * @Title: p2pMd5 
    * @Description: 与p2p进行的md5加密
    * @param @param systemId
    * @param @param date
    * @param @param size
    * @param @return    设定文件 
    * @return String    返回类型 
    * @author chengqiang
    * @date 2015-5-7 上午10:19:21 
    * @throws
     */
    public static String p2pMd5(String systemId,String date,String size,String key){
    	return Md5Util.getMD5String(StringUtils.join(new String[]{systemId,date,size}, ","),key); 
    }
    /**
     * 
    * @Title: md5 
    * @Description: 与php之间的md5
    * @param @param inputStr
    * @param @return
    * @param @throws NoSuchAlgorithmException    设定文件 
    * @return String    返回类型 
    * @author chengqiang
    * @date 2015-9-6 下午2:27:56 
    * @throws
     */
    public static String md52php(String key,String val) throws NoSuchAlgorithmException {
	    String md5Str = key+val;
	    if(md5Str != null) {
	        MessageDigest md = MessageDigest.getInstance("MD5");
	        md.update(md5Str.getBytes());
	        BigInteger hash = new BigInteger(1, md.digest());
	        md5Str = hash.toString(16);
	        if((md5Str.length() % 2) != 0) {
	            md5Str = "0" + md5Str;
	        }
	    }
	    return md5Str;
	}
}
