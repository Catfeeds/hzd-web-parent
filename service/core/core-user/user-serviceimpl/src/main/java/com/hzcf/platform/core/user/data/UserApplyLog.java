package com.hzcf.platform.core.user.data;

import com.hzcf.platform.common.model.AbstractEntity;

/**
 * Created by lll on 2017-04-10.
 */
public class UserApplyLog extends AbstractEntity {
    //id
    private String logId;
    //申请单号
    private String applyId;
    private String idCard;
    //身份证号
    private String applyType;
    //信息
    private String returnContent;
    //创建时间
    private String returnTime;

    public String getLogId() {
        return logId;
    }

    public void setLogId(String logId) {
        this.logId = logId;
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getApplyType() {
        return applyType;
    }

    public void setApplyType(String applyType) {
        this.applyType = applyType;
    }

    public String getReturnContent() {
        return returnContent;
    }

    public void setReturnContent(String returnContent) {
        this.returnContent = returnContent;
    }

    public String getReturnTime() {
        return returnTime;
    }

    public void setReturnTime(String returnTime) {
        this.returnTime = returnTime;
    }
}
