package com.hzcf.platform.api.baseEnum;

import org.apache.commons.lang.StringUtils;

/**
 * @author leijiaming
 * @datetime 2017/1/5 18:56
 */
public enum GenderCodeEnum {
    M("M", "男"), F("F", "女"), O("O", "其他");
    private String code;
    private String msg;

    private GenderCodeEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static GenderCodeEnum getByCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            for (GenderCodeEnum e : values()) {
                if (e.code.equals(code)) {
                    return e;
                }
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
