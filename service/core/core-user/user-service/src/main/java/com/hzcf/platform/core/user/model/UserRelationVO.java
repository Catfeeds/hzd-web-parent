package com.hzcf.platform.core.user.model;

/**
 * Created by leijiaming on 2016/12/29 0029.
 */

import com.hzcf.platform.common.model.BaseVO;

import java.util.Date;

public class UserRelationVO  extends BaseVO {

    private String relationId;//用户关系ID

    private String userId;//用户ID

    private String applyId;//用户申请编号

    private String name;//证明人姓名

    private String relationType;//和本人关系

    private String mobile;//电话/手机号

    private String type;//0=工作证明 1=家庭证明人2=紧急联络人

    private String organName;//单位名称
    private String organAddr;//单位地址



    public String getRelationId() {
        return relationId;
    }

    public void setRelationId(String relationId) {
        this.relationId = relationId == null ? null : relationId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getApplyId() {
        return applyId;
    }

    public void setApplyId(String applyId) {
        this.applyId = applyId == null ? null : applyId.trim();
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType == null ? null : relationType.trim();
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getOrganName() {
        return organName;
    }

    public void setOrganName(String organName) {
        this.organName = organName;
    }

    public String getOrganAddr() {
        return organAddr;
    }

    public void setOrganAddr(String organAddr) {
        this.organAddr = organAddr;
    }


    @Override
    public String toString() {
        return "UserRelationVO{" +
                "relationId='" + relationId + '\'' +
                ", userId='" + userId + '\'' +
                ", applyId='" + applyId + '\'' +
                ", name='" + name + '\'' +
                ", relationType='" + relationType + '\'' +
                ", mobile='" + mobile + '\'' +
                ", type='" + type + '\'' +
                ", organName='" + organName + '\'' +
                ", organAddr='" + organAddr + '\'' +
                '}';
    }
}