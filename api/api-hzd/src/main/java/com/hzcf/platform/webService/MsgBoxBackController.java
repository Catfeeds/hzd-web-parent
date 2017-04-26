package com.hzcf.platform.webService;

import com.hzcf.platform.api.annotation.RequestBodyForm;
import com.hzcf.platform.api.annotation.biz.Check;
import com.hzcf.platform.api.annotation.biz.CheckString;
import com.hzcf.platform.api.aop.RequestValidation;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.service.ImsgBoxBackService;
import com.hzcf.platform.core.user.model.MsgBoxVO;
import com.hzcf.platform.webService.model.MsgBoxBack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Created by lll on 2017-04-13.
 */
@RestController
@RequestValidation
public class MsgBoxBackController {
    @Autowired
    ImsgBoxBackService imsgBoxBackService;

    @RequestMapping(value = "api/100/furtherInformation/{borrowerApplyId}",method = RequestMethod.POST)
    public BackResult msgBoxBack(@RequestBodyForm  MsgBoxVO msgBoxVO,
                                 @PathVariable   String borrowerApplyId){
        return imsgBoxBackService.msgBoxBack(msgBoxVO.getMsgContent(),borrowerApplyId);
    }

}
