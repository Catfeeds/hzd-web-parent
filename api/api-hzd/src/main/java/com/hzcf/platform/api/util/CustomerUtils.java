package com.hzcf.platform.api.util;

import com.hzcf.platform.api.baseEnum.GenderCodeEnum;
import com.hzcf.platform.common.util.utils.ServiceUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.Date;

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
}
