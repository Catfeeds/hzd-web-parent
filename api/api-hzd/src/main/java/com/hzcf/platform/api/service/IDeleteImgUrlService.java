package com.hzcf.platform.api.service;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserVO;

/**
 * Created by leijiaming on 2017/1/9 0009.
 */
public interface IDeleteImgUrlService {
    /**
     * 删除图片
     *
     * @param user
     * @return
     */
    public BackResult deleteImgUrl(UserVO userVO,UserImageVO userImageVO);


}
