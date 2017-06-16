package com.hzcf.platform.api.util;

import com.hzcf.platform.api.baseEnum.GenderCodeEnum;
import com.hzcf.platform.common.util.utils.ServiceUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by leijiaming on 2017/1/5 0005.
 */
public class CustomerUtils {
    public static final int INVALID_IDENTITY_NO = -1;

    /**
     * 根据身份证号计算男女 M/F
     *
     * @param identityNo
     *            身份证号
     * @return {@link GenderCodeEnum#getCode()} M/F
     */
    public static String calculateGender(String identityNo) {
        // int length = isValidIdentityNo(identityNo);
        if (StringUtils.isBlank(identityNo)) {
            return null;
        } else if (identityNo.length() != 15 && identityNo.length() != 18) {
            return null;
        }
        int length = identityNo.length();
        if (length == INVALID_IDENTITY_NO) {
            return null;
        }
        //
        char gc;
        if (length == 18)
            gc = identityNo.charAt(16);
        else
            gc = identityNo.charAt(14);
        int nu = (gc - 0) % 2;
        return (nu == 0) ? GenderCodeEnum.F.getCode() : GenderCodeEnum.M.getCode();
    }

    /**
     * 根据身份证号判断用户出生日期
     *
     * @param identityNo
     *            身份证号
     * @return 返回出生日期
     */
    public static Date calculateBirthDate(String identityNo) {
        int length = isValidIdentityNo(identityNo);
        if (length == INVALID_IDENTITY_NO) {
            return null;
        }
        // 执行生日截取
        if (length == 18) {
            String dateStr = identityNo.substring(6, 14);
            return DateExtendUtils.parseDate(dateStr);
        } else {
            String dateStr = identityNo.substring(6, 12);
            // 15位身份证
            return DateExtendUtils.parseDate("19" + dateStr);
        }
    }

    /**
     * 校验是否为合法的身份证号
     *
     * @param identityNo
     *            传入的身份证号
     * @return 返回身份证号长度 15/18 位有效身份证号长度， -1身份证未通过验证
     */
    public static int isValidIdentityNo(String identityNo) {
        if (StringUtils.isBlank(identityNo)) {
            return INVALID_IDENTITY_NO;
        } else if (identityNo.length() != 15 && identityNo.length() != 18) {
            return INVALID_IDENTITY_NO;
        } else {
            boolean vResult = ServiceUtil.validateIdNo(identityNo);
            if (vResult) {
                return identityNo.length();
            } else {
                return INVALID_IDENTITY_NO;
            }
        }
    }

/*    public static boolean isNameString(String num) {
        Pattern p = Pattern.compile("^[\\u4e00-\\u9fa5]{2,6}+$");
        Matcher m = p.matcher(num);
        return m.matches();
    }*/

    /** 用于判断用户名是否合格*/
    public static boolean isNameString(String num){
        int count1 = verfy(num);//中文点的个数
        int count2 = verfy1(num);//英文点的个数
        int  countSum = count1+count2;
        if(countSum>1){
            return false;
        }
        String pattern = "^[\\u4e00-\\u9fa5·•]{2,10}";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(num);

        return m.matches();

    }
    //判断英文点的方法
    public static int verfy1(String content){
        String ss = "·";//中文点
        int contentLenght = content.length();
        String s1 = content.replaceAll(ss,"");
        int replaceLenght = s1.length();
        return contentLenght-replaceLenght;

    }
    //判断中文点的方法
    public static int verfy(String content){
        String ss = "•";//中文点
        int contentLenght = content.length();
        String s1 = content.replaceAll(ss,"");
        int replaceLenght = s1.length();
        return contentLenght-replaceLenght;

    }

    public static void main(String a[]){
        String realName = "世界你来看•世界你来";
        if(!CustomerUtils.isNameString(realName)){
            System.out.println("实名认证保存失败:用户传入姓名不合法,realName:"+realName);
        }else{
            System.out.println("名称正确");
        }
    }

}
