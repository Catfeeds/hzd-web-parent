package com.hzcf.platform.api.controller;

import com.hzcf.platform.api.annotation.RequestAttribute;
import com.hzcf.platform.api.annotation.RequestBodyForm;
import com.hzcf.platform.api.annotation.biz.Check;
import com.hzcf.platform.api.aop.RequestValidation;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.api.form.UserImageForm;
import com.hzcf.platform.api.service.IApplyImgUrInfoUrlService;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

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
    public BackResult queryImgByApplyId(@PathVariable  String applyId){
        logger.i("进入图片查询接口--applyId："+applyId);
        return applyImgUrInfoUrlService.queryImgByApplyId(applyId);
    }

    /**
     * APP 补充资料接口
     * @param user
     * @param applyId
     * @param checkSource  审核源：8=信审  6=综合业务平台
     * @param userImageVO
     * @return
     */
    @RequestMapping(value={"rest/api/100/saveImgByApplyId/{applyId}/{checkSource}","api/100/saveImgByApplyId/{applyId}/{checkSource}"},method= RequestMethod.POST)
    public BackResult saveImgByApplyId(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user,
                                       @PathVariable String applyId, @PathVariable String checkSource,
                                       @RequestBody @Check UserImageForm userImageVO){
        logger.i("进入补充资料接口--applyId："+applyId);
        return applyImgUrInfoUrlService.saveImgByApplyId(user,applyId,checkSource, userImageVO.getUserImageVO());

    }

    @RequestMapping(value={"rest/api/100/uploadImg","api/100/uploadImg"},method= RequestMethod.POST)
    public BackResult uploadImg(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user,
                                HttpServletRequest request){

        return applyImgUrInfoUrlService.uploadImg(request);
    }
}
