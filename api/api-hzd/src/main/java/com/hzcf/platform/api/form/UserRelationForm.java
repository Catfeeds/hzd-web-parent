package com.hzcf.platform.api.form;

import com.hzcf.platform.core.user.model.UserRelationVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leijiaming on 2017/1/5 0005.
 */
public class UserRelationForm implements Serializable{
    private static final long serialVersionUID = 6303178436925326641L;
    private  List<UserRelationVO> userRelationVO;

    public List<UserRelationVO> getUserRelationVO() {
        return userRelationVO;
    }

    public void setUserRelationVO(List<UserRelationVO> userRelationVO) {
        this.userRelationVO = userRelationVO;
    }
}
