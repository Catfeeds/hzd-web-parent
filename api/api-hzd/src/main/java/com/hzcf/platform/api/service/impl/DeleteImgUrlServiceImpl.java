package com.hzcf.platform.api.service.impl;

import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.service.IDeleteImgUrlService;
import com.hzcf.platform.api.util.StringUtil;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserImageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by leijiaming on 2017/1/9 0009.
 */
public class DeleteImgUrlServiceImpl implements IDeleteImgUrlService {

    @Autowired
    UserImageService userImageService;

    @Override
    public BackResult deleteImgUrl(UserVO userVO ,UserImageVO userImageVO) {

        if(StringUtils.isBlank( userImageVO.getArtWork())){
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_9000.getCode(),
                    HzdStatusCodeEnum.MEF_CODE_9000.getMsg(),null);
        }
        userImageVO.setUserId(userVO.getId());
        userImageVO.setArtWork(StringUtil.getSufFirst(userImageVO.getArtWork()));
        Result<Boolean> booleanResult = userImageService.deleteByPrimaryKey(userImageVO);
        if (StatusCodes.OK == (booleanResult.getStatus())) {
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),
                    HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),null);
        }

        return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
                HzdStatusCodeEnum.MEF_CODE_0001.getMsg(),null);
    }
}