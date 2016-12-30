package com.hzcf.platform.api.form;

import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserInfoVO;
import com.hzcf.platform.core.user.model.UserRelationVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leijiaming on 2016/12/30 0030.
 */
public class onlineLoanapplyInfoPreviewForm implements Serializable{

    private UserApplyInfoVO userApplyInfoVO;
    private UserInfoVO userInfoVO;
    private List<UserRelationVO>  UserRelationVOList;

    public onlineLoanapplyInfoPreviewForm(UserApplyInfoVO userApplyInfoVO, UserInfoVO userInfoVO, List<UserRelationVO> userRelationVOList) {
        this.userApplyInfoVO = userApplyInfoVO;
        this.userInfoVO = userInfoVO;
        UserRelationVOList = userRelationVOList;
    }

    public UserApplyInfoVO getUserApplyInfoVO() {
        return userApplyInfoVO;
    }

    public void setUserApplyInfoVO(UserApplyInfoVO userApplyInfoVO) {
        this.userApplyInfoVO = userApplyInfoVO;
    }

    public UserInfoVO getUserInfoVO() {
        return userInfoVO;
    }

    public void setUserInfoVO(UserInfoVO userInfoVO) {
        this.userInfoVO = userInfoVO;
    }

    public List<UserRelationVO> getUserRelationVOList() {
        return UserRelationVOList;
    }

    public void setUserRelationVOList(List<UserRelationVO> userRelationVOList) {
        UserRelationVOList = userRelationVOList;
    }
}
