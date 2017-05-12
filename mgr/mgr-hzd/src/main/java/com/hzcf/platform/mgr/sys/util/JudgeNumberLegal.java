package com.hzcf.platform.mgr.sys.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 原来版本的正则表达式
 * Created by Administrator on 2016/12/12.
 */

public class JudgeNumberLegal {

    /** 用于判断手机号段是否合法 */
    public static boolean isMobileNum(String num) {
        Pattern p = Pattern.compile("^(1[3,4,5,7,8][0-9])\\d{8}$");
        Matcher m = p.matcher(num);
        return m.matches();
    }

    /** 用于判断手机号段是否合法 */
    public static boolean isFixPhoneNum(String num) {
        Pattern p = Pattern
                .compile("/^(^0\\d{2}-?\\d{8}$)|(^0\\d{3}-?\\d{7}$)|(^\\(0\\d{2}\\)-?\\d{8}$)|(^\\(0\\d{3}\\)-?\\d{7}$)$/");
        Matcher m = p.matcher(num);
        return m.matches();
    }

    /** 用于判断身份证15位是否合格*/
    public static boolean isCardNum15(String num){
        Pattern p = Pattern
                .compile("^[1-9]\\\\d{5}[1-9]\\\\d{3}((0\\\\d)|(1[0-2]))(([0|1|2]\\\\d)|3[0-1])\\\\d{4}$");
        Matcher m = p.matcher(num);
        return m.matches();
    }

    /** 用于判断身份证15位是否合格*/
    public static boolean isCardNum18(String num){
        Pattern p = Pattern
                .compile("^[1-9]\\d{5}[1-9]\\d{3}((0\\d)|(1[0-2]))(([0|1|2]\\d)|3[0-1])\\d{3}([0-9]|X|x)$");
        Matcher m = p.matcher(num);
        return m.matches();
    }



    /** 用于判断用户名是否合格*/
    public static boolean isNameString(String num){
        Pattern p = Pattern
                .compile("^[\\u4e00-\\u9fa5]{2,6}+$");
        Matcher m = p.matcher(num);
        return m.matches();
    }

    /** 用于判断验证码是否合格*/
    public static boolean isCodeNum(String num){
        Pattern p = Pattern
                .compile("^[0-9]{6}$");
        Matcher m = p.matcher(num);
        return m.matches();
    }

    /** 用于密码是否合法 */
    public static boolean isSecretNum(String num) {
        //-¥）（；：？！，。《》～“”!@#$%^&*()_:;\"',<>?.a-zA-Z0-9!@#$%^&*()_:;\"',<.>
        //a-zA-Z0-9!@#$%^&*()_:;"',<.>`~！@#￥%……&*（）——：；“‘，《。》·~
        Pattern p = Pattern.compile("^[a-zA-Z0-9!-¥）（；：？！，。《》～“”!@#$%^&*()_:;\"',<>?.!@#$%^&*()_:;\"',<.>]{8,16}$");
        Matcher m = p.matcher(num);
        return m.matches();
    }

    /** 用于邮箱是否合法 */
    public static boolean isEmail(String email) {
        Pattern p = Pattern
                .compile("^([a-zA-Z0-9_\\-\\.]+)@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.)|(([a-zA-Z0-9\\-]+\\.)+))([a-zA-Z]{2,4}|[0-9]{1,3})(\\]?)$");
        Matcher m = p.matcher(email);
        return m.matches();
    }

    /** 密码是否合法 */
	/*public static boolean isLegal(String num) {
		String regexone = "^[a-zA-Z0-9!@#$%^&*()_:;\"'>.`~]{6,16}$";
		String regexthree = "^[a-zA-Z]{6,16}$";
		String regexfour = "^\\d{6,16}$";
		// if
		// (Pattern.compile(regexone).matcher(num).matches()&Pattern.compile(regexthree).matcher(num).matches()&Pattern.compile(regexfour).matcher(num).matches())
		// {
		// return false ;
		// }else {
		// return true;
		// }
		if (Pattern.compile(regexone).matcher(num).matches()) {
			if (!Pattern.compile(regexthree).matcher(num).matches()
					& !Pattern.compile(regexfour).matcher(num).matches()) {
				return true;
			}
			return false;
		} else {
			return false;
		}
	}*/

    // 企业名称是否合法
    public static boolean isCompanyLegal(String num) {
        String regex = "@^[\u4e00-\u9fa5()]{1,50}$";
        if (Pattern.compile(regex).matcher(num).matches()) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean linkedNameIsLegal(String num) {
        String regex = "@^[\u4e00-\u9fa5a-zA-Z]{2,20}$";
        if (Pattern.compile(regex).matcher(num).matches()) {
            return true;
        }
        return false;

    }

    /**
     * 判断配件名称合法性
     */
    public static boolean isCarPartNameLegal(String carpartname) {
        // String regex = "@^[\u4e00-\u9fa5a-zA-Z0-9_]$";
        String regex = "^[\u4e00-\u9fa5a-zA-Z0-9()（），,.、/\\~_&*# -]{1,20}$";
        if (Pattern.compile(regex).matcher(carpartname).matches()) {
            return true;
        }
        return false;

    }

    /**
     * 判断配件备注合法性 50字
     */
    public static boolean isCarPartRemarkLegal(String carpartname) {
        // String regex = "@^[\u4e00-\u9fa5a-zA-Z0-9_]$";
        String regex = "^[\u4e00-\u9fa5a-zA-Z0-9()（），,.、/\\~_&*# -]{1,50}$";
        if (Pattern.compile(regex).matcher(carpartname).matches()) {
            return true;
        }
        return false;

    }

    /**
     * 判断配件备注合法性 100字
     */
    public static boolean isRemarkLegal(String carpartname) {
        // String regex = "@^[\u4e00-\u9fa5a-zA-Z0-9_]$";
        String regex = "^[\u4e00-\u9fa5a-zA-Z0-9()（），。,.、/\\~_&*# -]{1,100}$";
        if (Pattern.compile(regex).matcher(carpartname).matches()) {
            return true;
        }
        return false;
    }



    /** 用于判断座机是否合法 包括分机 */
    public static boolean isFixPhoneNumAndExt(String num) {
        Pattern p = Pattern
                .compile("/^(^0\\d{2}-?\\d{8,13}$)|(^0\\d{3}-?\\d{7,12}$)|(^\\(0\\d{2}\\)-?\\d{8,13}$)|(^\\(0\\d{3}\\)-?\\d{7,12}$)$/");
        Matcher m = p.matcher(num);
        return m.matches();
    }

    /** 判断400开头号段是否合法 */
    public static boolean is400Or800PhoneNum(String num) {
        Pattern p = Pattern.compile("^400\\d{7}|^800\\d{7}");
        Matcher m = p.matcher(num);
        return m.matches();
    }
}
