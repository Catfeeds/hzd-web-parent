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

    @RequestMapping(value = "api/100/furtherInformation",method = RequestMethod.GET)
    public BackResult msgBoxBack(@RequestParam @NotNull(message = "补充资料信息不能为空") String msgBoxBack,
                                 @RequestParam @NotNull(message = "申请编号信息不能为空")String borrowerApplyId){
        return imsgBoxBackService.msgBoxBack(msgBoxBack,borrowerApplyId);
    }
}
