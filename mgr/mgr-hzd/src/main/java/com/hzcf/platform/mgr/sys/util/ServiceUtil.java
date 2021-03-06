package com.hzcf.platform.mgr.sys.util;

import com.google.gson.Gson;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.mgr.sys.common.util.StringUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.web.util.HtmlUtils;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * <pre>
 * File: ServiceUtil.java Description:
 * Company: 国美小额贷款有限公司
 * Author: guicl
 * Datetime: 2016-06-14 13:35
 * </pre>
 */
public class ServiceUtil {

    private static Hashtable<String, String> areaCode = createAreaCode();


    /**
     * validate mobile no format
     *
     * @param mobile
     * @return
     */
    public static boolean validateMobile(String mobile) {
        boolean flag = false;
        try {
            String regEx = "\\d{11}";
            if (StringUtils.isEmpty(mobile)) {
                return mobile.matches(regEx);
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static boolean validateChannel(String channel) {
        boolean flag = false;
        try {
            String regEx = "\\d{4}";
            if (StringUtils.isEmpty(channel)) {
                return channel.matches(regEx);
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static boolean validateBankCardNo(String cardNo) {
        boolean flag = false;
        try {
            String regEx = "\\d{10,20}";
            if (StringUtils.isEmpty(cardNo)) {
                return cardNo.matches(regEx);
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static boolean validateVersion(String version) {
        boolean flag = false;
        try {
            String regEx = "[^\\d\\.]/g";
            if (StringUtils.isEmpty(version)) {
                return version.matches(regEx);
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    public static boolean vaslidateEmail(String email) {
        boolean flag = false;
        try {
            String regEx = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
            if (StringUtils.isEmpty(email)) {
                return email.matches(regEx);
            }
        } catch (Exception e) {
            flag = false;
        }
        return flag;
    }

    /**
     * 身份证格式校验
     *
     * @param IDStr
     * @return
     */
    public static boolean validateIdNo(String IDStr) {
        String errorInfo = "";// 记录错误信息
        String[] ValCodeArr = {"1", "0", "x", "9", "8", "7", "6", "5", "4", "3", "2"};
        String[] Wi = {"7", "9", "10", "5", "8", "4", "2", "1", "6", "3", "7", "9", "10", "5", "8", "4", "2"};
        String Ai = "";
        // ================ 号码的长度 15位或18位 ================
        if (IDStr.length() != 15 && IDStr.length() != 18) {
            errorInfo = "身份证号码长度应该为15位或18位。";

            return false;
        }
        // =======================(end)========================

        // ================ 数字 除最后以为都为数字 ================
        if (IDStr.length() == 18) {
            Ai = IDStr.substring(0, 17);

        } else if (IDStr.length() == 15) {
            Ai = IDStr.substring(0, 6) + "19" + IDStr.substring(6, 15);
        }
        if (isNumeric(Ai) == false) {
            errorInfo = "身份证15位号码都应为数字 ; 18位号码除最后一位外，都应为数字。";
            return false;
        }
        // =======================(end)========================

        // ================ 出生年月是否有效 ================
        String strYear = Ai.substring(6, 10);// 年份
        String strMonth = Ai.substring(10, 12);// 月份
        String strDay = Ai.substring(12, 14);// 月份
        if (isDate(strYear + "-" + strMonth + "-" + strDay) == false) {
            errorInfo = "身份证生日无效。";
            return false;
        }
        GregorianCalendar gc = new GregorianCalendar();
        SimpleDateFormat s = new SimpleDateFormat("yyyy-MM-dd");
        try {
            if ((gc.get(Calendar.YEAR) - Integer.parseInt(strYear)) > 150
                    || (gc.getTime().getTime() - s.parse(strYear + "-" + strMonth + "-" + strDay).getTime()) < 0) {
                errorInfo = "身份证生日不在有效范围。";
                return false;
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (Integer.parseInt(strMonth) > 12 || Integer.parseInt(strMonth) == 0) {
            errorInfo = "身份证月份无效";
            return false;
        }
        if (Integer.parseInt(strDay) > 31 || Integer.parseInt(strDay) == 0) {
            errorInfo = "身份证日期无效";
            return false;
        }
        // =====================(end)=====================

        // ================ 地区码时候有效 ================
        Hashtable<String, String> h = getAreaCode();
        if (h.get(Ai.substring(0, 2)) == null) {
            errorInfo = "身份证地区编码错误。";

            return false;
        }
        // ==============================================

        // ================ 判断最后一位的值 ================
        int TotalmulAiWi = 0;
        for (int i = 0; i < 17; i++) {
            TotalmulAiWi = TotalmulAiWi + Integer.parseInt(String.valueOf(Ai.charAt(i))) * Integer.parseInt(Wi[i]);
        }
        int modValue = TotalmulAiWi % 11;
        String strVerifyCode = ValCodeArr[modValue];
        Ai = Ai + strVerifyCode;

        if (IDStr.length() == 18) {
            if (Ai.equals(IDStr) == false) {
                errorInfo = "身份证无效，不是合法的身份证号码";
                return false;
            }
        } else {
            return true;
        }
        // =====================(end)=====================
        return true;
    }

    /**
     * 功能：设置地区编码
     *
     * @return Hashtable 对象
     */
    private static Hashtable<String, String> getAreaCode() {
        return areaCode;
    }

    private static Hashtable<String, String> createAreaCode() {
        Hashtable<String, String> hashtable = new Hashtable<String, String>();
        hashtable.put("11", "北京");
        hashtable.put("12", "天津");
        hashtable.put("13", "河北");
        hashtable.put("14", "山西");
        hashtable.put("15", "内蒙古");
        hashtable.put("21", "辽宁");
        hashtable.put("22", "吉林");
        hashtable.put("23", "黑龙江");
        hashtable.put("31", "上海");
        hashtable.put("32", "江苏");
        hashtable.put("33", "浙江");
        hashtable.put("34", "安徽");
        hashtable.put("35", "福建");
        hashtable.put("36", "江西");
        hashtable.put("37", "山东");
        hashtable.put("41", "河南");
        hashtable.put("42", "湖北");
        hashtable.put("43", "湖南");
        hashtable.put("44", "广东");
        hashtable.put("45", "广西");
        hashtable.put("46", "海南");
        hashtable.put("50", "重庆");
        hashtable.put("51", "四川");
        hashtable.put("52", "贵州");
        hashtable.put("53", "云南");
        hashtable.put("54", "西藏");
        hashtable.put("61", "陕西");
        hashtable.put("62", "甘肃");
        hashtable.put("63", "青海");
        hashtable.put("64", "宁夏");
        hashtable.put("65", "新疆");
        hashtable.put("71", "台湾");
        hashtable.put("81", "香港");
        hashtable.put("82", "澳门");
        hashtable.put("91", "国外");
        return hashtable;
    }

    /**
     * 功能：判断字符串是否为数字
     *
     * @param str
     * @return
     */
    private static boolean isNumeric(String str) {
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if (isNum.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 功能：判断字符串是否为日期格式
     *
     * @param strDate
     * @return
     */
    public static boolean isDate(String strDate) {
        Pattern pattern = Pattern.compile(
                "^((\\d{2}(([02468][048])|([13579][26]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])))))|(\\d{2}(([02468][1235679])|([13579][01345789]))[\\-\\/\\s]?((((0?[13578])|(1[02]))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(3[01])))|(((0?[469])|(11))[\\-\\/\\s]?((0?[1-9])|([1-2][0-9])|(30)))|(0?2[\\-\\/\\s]?((0?[1-9])|(1[0-9])|(2[0-8]))))))(\\s(((0?[0-9])|([1-2][0-3]))\\:([0-5]?[0-9])((\\s)|(\\:([0-5]?[0-9])))))?$");
        Matcher m = pattern.matcher(strDate);
        if (m.matches()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断时间是否符合格式
     *
     * @param date
     * @return
     */
    public static boolean validateData(String date, String format) {
        boolean result = true;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        try {
            sdf.setLenient(false);
            sdf.parse(date);

        } catch (ParseException e) {
            result = false;
        }
        return result;
    }

    public static boolean isNameString(String num) {
        Pattern p = Pattern
                .compile("^[\\u4e00-\\u9fa5]{2,6}+$");
        Matcher m = p.matcher(num);
        return m.matches();
    }


    public static void main(String[] aaa) {
        String specialStr = "<div id=/'testDiv/'>test1;test2</div>";
		String str1 = HtmlUtils.htmlEscape(specialStr);// ①转换为HTML转义字符表示
		System.out.println(str1);

    }

    public static boolean test5(String str) {
        return str.matches("^.*[a-zA-Z]+.*$")
                && str.matches("^.*[0-9]+.*$")
                ;
    }

    /**
     * 用于密码是否合法
     */
    public static boolean isSecretNum(String num) {

        Pattern p = Pattern.compile("^[(?!^\\d+$)(?!^[a-zA-Z]+$)(?!^[_#@]+$)a-zA-Z0-9!-¥）（；：？！，。《》～“”!@#$%^&*()_:;\"',<>?.!@#$%^&*()_:;\"',<.>]{8,16}$");
        Matcher m = p.matcher(num);
        return m.matches();
    }

	/*public static Object htmlTransferredMeaning(Object o){
		UserVO u = new UserVO();
		u.setCheckStatus("1");
		u.setIdCard("2");
		System.out.println(ConvertObjToMap(u));
	}*/


}
