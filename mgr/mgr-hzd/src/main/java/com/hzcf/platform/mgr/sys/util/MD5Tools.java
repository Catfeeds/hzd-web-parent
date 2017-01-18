package com.hzcf.platform.mgr.sys.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
  * @Description:用于对借款人的密码加密，对系统后台用户密码加密
  * 加密的 key  =  01b503cf15f16f5e9c95938d09ef1219
  * @author 作者:裴高祥
  * @date 创建时间：2017年1月18日 上午9:48:28 
  * @version 1.0 
  * @since  JDK1.7
  */
public class MD5Tools {
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
//    public static String getMD5(String val) throws NoSuchAlgorithmException{
//        MessageDigest md5 = MessageDigest.getInstance("MD5");
//        md5.update(val.getBytes());
//        byte[] m = md5.digest();//加密
//        return getString(m);
//    }


    /**
     * MD5加密
     * @param message 要进行MD5加密的字符串
     * @return 加密结果为32位字符串
     */
    public static String getMD5(String message) {
        MessageDigest messageDigest = null;
        StringBuffer md5StrBuff = new StringBuffer();
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(message.getBytes("UTF-8"));

            byte[] byteArray = messageDigest.digest();
            for (int i = 0; i < byteArray.length; i++)
            {
                if (Integer.toHexString(0xFF & byteArray[i]).length() == 1)
                    md5StrBuff.append("0").append(Integer.toHexString(0xFF & byteArray[i]));
                else
                    md5StrBuff.append(Integer.toHexString(0xFF & byteArray[i]));
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
        return md5StrBuff.toString().toUpperCase();//字母大写
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
//    public static String getMD5String(String str, String key) {
//        String result = null;
//        try {
//            result = getMD5(str+key);
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }
//        return result;
//    }

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
//    public static String p2pMd5(String systemId,String date,String size,String key){
//        //给的东西少一个String 工具
//        return Md5Util.getMD5String(StringUtils.join(new String[]{systemId,date,size}, ","),key);
//    }
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
	public static void main(String[] args) {
		String str1=MD5Tools.getMD5("admin");
		String str2=MD5Tools.getMD5("test123456");
		System.out.println(str1);//admin123456    21232F297A57A5A743894A0E4A801FC3
		System.out.println(str2);//test123456     47EC2DD791E31E2EF2076CAF64ED9B3D
		
	}
}