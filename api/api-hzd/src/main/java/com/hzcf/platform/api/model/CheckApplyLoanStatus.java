package com.hzcf.platform.api.model;

import java.io.Serializable;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */
public class CheckApplyLoanStatus implements Serializable{
    /**
     * 身份状态
     * 实名认证有效状态 1无效 0有效 2待审核
     */
    private String identityStatus;
    /**
     * 进件状态
     * 进件申请状态 0-未进件 1-已进件
     */
    private String onlineApplyLoanStatus;//线上状态
    private String offlineApplyLoanStatus;//线下状态

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

    public String getOnlineApplyLoanStatus() {
        return onlineApplyLoanStatus;
    }

    public void setOnlineApplyLoanStatus(String onlineApplyLoanStatus) {
        this.onlineApplyLoanStatus = onlineApplyLoanStatus;
    }

    public String getOfflineApplyLoanStatus() {
        return offlineApplyLoanStatus;
    }

    public void setOfflineApplyLoanStatus(String offlineApplyLoanStatus) {
        this.offlineApplyLoanStatus = offlineApplyLoanStatus;
    }
}

