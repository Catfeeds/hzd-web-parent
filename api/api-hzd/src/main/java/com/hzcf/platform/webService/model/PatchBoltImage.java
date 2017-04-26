package com.hzcf.platform.webService.model;

import java.io.Serializable;

/**
 * Created by lll on 2017-04-19.
 */
public class PatchBoltImage implements Serializable{

    private String imageType;	//图片类型
    private String artWork;	//图片地址
    private String displayName;//	图片名称

    public String getImageType() {
        return imageType;
    }

    public void setImageType(String imageType) {
        this.imageType = imageType;
    }

    public String getArtWork() {
        return artWork;
    }

    public void setArtWork(String artWork) {
        this.artWork = artWork;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }
}
