package com.hzcf.platform.api.form;

import com.hzcf.platform.core.user.model.UserRelationVO;

import java.util.List;

/**
 * Created by leijiaming on 2017/1/5 0005.
 */
public class UserRelationForm {
   private  List<UserRelationVO> UserRelationVO;

    public List<UserRelationVO> getUserRelationVOList() {
        return UserRelationVO;
    }

    public void setUserRelationVOList(List<UserRelationVO> userRelationVOList) {
        UserRelationVO = userRelationVOList;
    }
}
