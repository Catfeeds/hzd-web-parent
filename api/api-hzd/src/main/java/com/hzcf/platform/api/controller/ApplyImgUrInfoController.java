package com.hzcf.platform.api.controller;

import com.hzcf.platform.api.annotation.RequestAttribute;
import com.hzcf.platform.api.annotation.RequestBodyForm;
import com.hzcf.platform.api.annotation.biz.CheckString;
import com.hzcf.platform.api.aop.RequestValidation;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.api.service.IApplyImgUrInfoUrlService;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by leijiaming on 2017/1/9 0009.
 */
@RestController
@RequestValidation
public class ApplyImgUrInfoController {
    private static final Log logger = Log.getLogger(ApplyImgUrInfoController.class);

    @Autowired
    IApplyImgUrInfoUrlService applyImgUrInfoUrlService;
    @RequestMapping(value={"rest/api/100/delete/imgurl","api/100/delete/imgurl"},method= RequestMethod.POST)
    @ResponseBody
    public BackResult applyDictionaryQu(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user,
                                        @RequestBodyForm UserImageVO userImageVO){
            logger.i("进入图片删除接口");
            return applyImgUrInfoUrlService.deleteImgUrl(user,userImageVO);
    }

    @RequestMapping(value={"api/100/queryImgByApplyId/{applyId}","api/100/queryImgByApplyId"},method= RequestMethod.GET)
    @ResponseBody
    public BackResult queryImgByApplyId(@PathVariable @CheckString(min = 20 , max = 20, message = "申请单号输入不合法") String applyId){
        logger.i("进入图片查询接口--applyId："+applyId);
        return applyImgUrInfoUrlService.queryImgByApplyId(applyId);
    }

    @RequestMapping(value={"rest/api/100/saveImgByApplyId/{applyId}","api/100/saveImgByApplyId"},method= RequestMethod.POST)
    public BackResult saveImgByApplyId(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user,
                                       @PathVariable @CheckString(min = 20 , max = 20, message = "申请单号输入不合法") String applyId,
                                       @RequestBody List<UserImageVO> UserImage){
        return applyImgUrInfoUrlService.saveImgByApplyId(user,applyId, UserImage);

    }

    @RequestMapping(value={"rest/api/100/uploadImg","api/100/uploadImg"},method= RequestMethod.POST)
    public BackResult uploadImg(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user,
                                HttpServletRequest request){

        return applyImgUrInfoUrlService.uploadImg(request);
    }
}
