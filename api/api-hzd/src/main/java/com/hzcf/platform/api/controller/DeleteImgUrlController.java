package com.hzcf.platform.api.controller;

import com.hzcf.platform.api.annotation.RequestAttribute;
import com.hzcf.platform.api.annotation.RequestBodyForm;
import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.api.service.IDeleteImgUrlService;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by leijiaming on 2017/1/9 0009.
 */
@RestController
public class DeleteImgUrlController {
    private static final Log logger = Log.getLogger(DeleteImgUrlController.class);

    @Autowired
    IDeleteImgUrlService deleteImgUrlService;
    @RequestMapping(value={"rest/api/100/delete/imgurl","api/100/delete/imgurl"},method= RequestMethod.POST)
    public BackResult applyDictionaryQu(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user,
                                        @RequestBodyForm UserImageVO userImageVO){
            logger.i("进入图片删除接口");
            return deleteImgUrlService.deleteImgUrl(user,userImageVO);
    }
}
