package com.hzcf.platform.webService;

import com.hzcf.platform.api.annotation.RequestBodyForm;
import com.hzcf.platform.api.aop.RequestValidation;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.service.ImsgBoxBackService;
import com.hzcf.platform.core.user.model.MsgBoxVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lll on 2017-04-13.
 */
@RestController
@RequestValidation
public class MsgBoxBackController {

    @Autowired
    ImsgBoxBackService imsgBoxBackService;

    /**
     * 线下回调 补充资料 信审审核/综合业务平台审核
     * @param checkSource  8=信审 6=综合业务平台
     * @param msgBoxVO
     * @param borrowerApplyId
     * @return
     */
    @RequestMapping(value = "api/100/furtherInformation/{borrowerApplyId}/{checkSource}",method = RequestMethod.POST)
    public BackResult msgBoxBack(@RequestBodyForm  MsgBoxVO msgBoxVO,
                                 @PathVariable   String borrowerApplyId, @PathVariable String checkSource){
        return imsgBoxBackService.msgBoxBack(msgBoxVO.getMsgContent(), checkSource,borrowerApplyId);
    }

}
