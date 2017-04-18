package com.hzcf.platform.api.form;

import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserRelationVO;

import java.io.Serializable;
import java.util.List;

/**
 * Created by leijiaming on 2017/1/5 0005.
 */
public class UserImageForm implements Serializable{

    private  List<UserImageVO> userImageVO;

    public List<UserImageVO> getUserImageVO() {
        return userImageVO;
    }

    public void setUserImageVO(List<UserImageVO> userImageVO) {
        this.userImageVO = userImageVO;
    }

}
