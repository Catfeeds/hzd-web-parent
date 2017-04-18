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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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

    @RequestMapping(value = "api/100/furtherInformation/{borrowerApplyId}/{msgBoxBack}",method = RequestMethod.POST)
    public BackResult msgBoxBack(@PathVariable   String msgBoxBack,
                                 @PathVariable @CheckString(min = 12,max = 12,message = "借款编号长度不合法") String borrowerApplyId){
        return imsgBoxBackService.msgBoxBack(msgBoxBack,borrowerApplyId);
    }
}
