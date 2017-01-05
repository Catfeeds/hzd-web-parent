package com.hzcf.platform.api.model;

import java.io.Serializable;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
public class CheckApplyLoanStatus implements Serializable{
    /**
     * 身份状态
     * 实名认证有效状态 1无效 0有效
     */
    private String identityStatus;
    /**
     * 进件状态
     * 进件申请状态 0-未进件 1-已进件
     */
    private String applyLoanStatus;//进件状态

    private String id;
    private String Idcard;
    private String name;
    private String mobile;


    public String getIdentityStatus() {
        return identityStatus;
    }

    public void setIdentityStatus(String identityStatus) {
        this.identityStatus = identityStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdcard() {
        return Idcard;
    }

    public void setIdcard(String idcard) {
        Idcard = idcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getApplyLoanStatus() {
        return applyLoanStatus;
    }

    public void setApplyLoanStatus(String applyLoanStatus) {
        this.applyLoanStatus = applyLoanStatus;
    }
}

