package com.hzcf.platform.core.user.model;
import com.hzcf.platform.common.model.BaseVO;

import java.util.Date;

public class UserImageVO extends BaseVO {
    private String imageId;

    private String userId;

    private String applyId;

    private String userInfoId;

    private String artWork;

    private String small;

    private String imageType;

    private String type;



    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId == null ? null : imageId.trim();
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

    public String getUserInfoId() {
        return userInfoId;
    }

    public void setUserInfoId(String userInfoId) {
        this.userInfoId = userInfoId == null ? null : userInfoId.trim();
    }

    public String getArtWork() {
        return artWork;
    }

    public void setArtWork(String artWork) {
        this.artWork = artWork == null ? null : artWork.trim();
    }

    public String getSmall() {
        return small;
    }

    public void setSmall(String small) {
        this.small = small == null ? null : small.trim();
    }

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType == null ? null : imageType.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

}