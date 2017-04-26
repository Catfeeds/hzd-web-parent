package com.hzcf.platform.webService.model;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

public class MsgBoxBack implements Serializable {

    @NotNull(message = "消息类型不能为空")
    private String msgType;
    @NotNull(message = "消息标题不能为空")
    private String msgTitle;
    @NotNull(message = "消息内容不能为空")
    private String msgContent;

    



    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType == null ? null : msgType.trim();
    }

    public String getMsgTitle() {
        return msgTitle;
    }

    public void setMsgTitle(String msgTitle) {
        this.msgTitle = msgTitle == null ? null : msgTitle.trim();
    }

    public String getMsgContent() {
        return msgContent;
    }

    public void setMsgContent(String msgContent) {
        this.msgContent = msgContent == null ? null : msgContent.trim();
    }



    

    
}