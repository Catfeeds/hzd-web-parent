package com.hzcf.platform.api.service;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserVO;
import org.apache.http.protocol.HttpService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by leijiaming on 2017/1/9 0009.
 */
public interface IApplyImgUrInfoUrlService {
    /**
     * 删除图片
     *
     * @param user
     * @return
     */
    public BackResult deleteImgUrl(UserVO userVO,UserImageVO userImageVO);

    public BackResult queryImgByApplyId(String applyId);

    public BackResult saveImgByApplyId(UserVO userVO,String applyId, String checkSource,List<UserImageVO> UserImage);

    public BackResult uploadImg(HttpServletRequest request);
}
