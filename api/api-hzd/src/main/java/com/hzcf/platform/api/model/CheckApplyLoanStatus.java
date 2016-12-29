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
    private String cardStatus;
    /**
     * 进件状态
     * 进件申请状态 0-未进件 1-已进件
     */
    private String applyLoanStatus;//进件状态


    public String getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(String cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getApplyLoanStatus() {
        return applyLoanStatus;
    }

    public void setApplyLoanStatus(String applyLoanStatus) {
        this.applyLoanStatus = applyLoanStatus;
    }
}

