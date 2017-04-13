package com.hzcf.platform.api.service.impl;

import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.service.ImsgBoxBackService;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.MsgBoxservice;
import com.hzcf.platform.core.user.service.UserApplyInfoSerivce;
import com.hzcf.platform.core.user.service.UserService;
import com.hzcf.platform.webService.model.MsgBoxBack;
import freemarker.log.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by lll on 2017-04-13.
 */
@Service
public class MsgBoxBackServiceImpl implements ImsgBoxBackService {
    private static final Log logger = Log.getLogger(OnlineApplyLoanServiceSerivceImpl.class);
    @Autowired
    UserService userSerivce;
    @Autowired
    UserApplyInfoSerivce userApplyInfoSerivce;
    @Autowired
    MsgBoxservice msgBoxservice;


    @Override
    public BackResult msgBoxBack(MsgBoxBack msgBoxBack, String borrowerApplyId) {

        Result<UserApplyInfoVO> userApplyInfoVOResult =
                userApplyInfoSerivce.selectByBorrowerApplyId(borrowerApplyId);
        if (StatusCodes.OK != userApplyInfoVOResult.getStatus()) {
            logger.i("补充资料回调数据异常 ---borrowerApplyId："+borrowerApplyId);

            return new BackResult(HzdStatusCodeEnum.HZD_CODE_9999.getCode(),
                    HzdStatusCodeEnum.HZD_CODE_9999.getMsg(), null);
        }

        if(userApplyInfoVOResult.getItems()==null){
            logger.i("补充资料回调数据失败，未查到借款信息 ---borrowerApplyId："+borrowerApplyId);

            return new BackResult(HzdStatusCodeEnum.HZD_CODE_2400.getCode(),
                    HzdStatusCodeEnum.HZD_CODE_2400.getMsg(), null);
        }
        Result<UserVO> userVOResult = userSerivce.selectByPrimaryKey(userApplyInfoVOResult.getItems().getUserId());
        UserApplyInfoVO userApplyInfoVO = userApplyInfoVOResult.getItems();
        UserVO userVO =userVOResult.getItems();
        return null;
    }
}
