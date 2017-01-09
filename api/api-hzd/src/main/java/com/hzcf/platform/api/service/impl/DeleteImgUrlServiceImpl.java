package com.hzcf.platform.api.service.impl;


import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.controller.DeleteImgUrlController;
import com.hzcf.platform.api.service.IDeleteImgUrlService;
import com.hzcf.platform.api.util.StringUtil;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserImageService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by leijiaming on 2017/1/9 0009.
 */
@Service
public class DeleteImgUrlServiceImpl implements IDeleteImgUrlService {
    private static final Log logger = Log.getLogger(DeleteImgUrlServiceImpl.class);

    @Autowired
    UserImageService userImageService;

    @Override
    public BackResult deleteImgUrl(UserVO userVO ,UserImageVO userImageVO) {

        if(StringUtils.isBlank( userImageVO.getArtWork())){
            logger.i("artWork为空");
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_9000.getCode(),
                    "artWork为空",null);
        }
        userImageVO.setUserId(userVO.getId());
        userImageVO.setArtWork(StringUtil.getSufFirst(userImageVO.getArtWork()));
        Result<Boolean> booleanResult = userImageService.deleteByPrimaryKey(userImageVO);
        if (StatusCodes.OK == (booleanResult.getStatus())) {
            logger.i("删除图片成功");
            return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),
                    HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),null);
        }
        logger.i("删除图片失败");
        return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
                HzdStatusCodeEnum.MEF_CODE_0001.getMsg(),null);
    }
}
