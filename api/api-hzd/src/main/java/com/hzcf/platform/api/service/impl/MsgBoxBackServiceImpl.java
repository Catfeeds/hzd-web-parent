package com.hzcf.platform.api.service.impl;

import com.hzcf.platform.api.annotation.LogAnnotation;
import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.config.ConstantsDictionary;
import com.hzcf.platform.api.service.ImsgBoxBackService;
import com.hzcf.platform.api.util.DateUtil;
import com.hzcf.platform.api.util.JpushClientUtil;
import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.common.util.uuid.UUIDGenerator;
import com.hzcf.platform.core.user.model.MsgBoxVO;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.service.MsgBoxservice;
import com.hzcf.platform.core.user.service.UserApplyInfoSerivce;
import com.hzcf.platform.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

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
    @LogAnnotation
    public BackResult msgBoxBack(String msgBoxBack, String checkSource, String borrowerApplyId) {
        logger.i("补充资料回调接口---审核源(8=信审补充资料，6=综合补充资料): "+ checkSource +"    borrowerApplyId：" + borrowerApplyId +"    msgBoxBack："+msgBoxBack);
        Result<UserApplyInfoVO> userApplyInfoVOResult =
                userApplyInfoSerivce.selectByBorrowerApplyId(borrowerApplyId);
        if (StatusCodes.OK != userApplyInfoVOResult.getStatus()) {
            logger.i("补充资料回调数据异常 ---borrowerApplyId：" + borrowerApplyId);

            return new BackResult(HzdStatusCodeEnum.HZD_CODE_9999.getCode(),
                    HzdStatusCodeEnum.HZD_CODE_9999.getMsg(), null);
        }

        if (userApplyInfoVOResult.getItems() == null) {
            logger.i("补充资料回调数据失败，未查到借款信息 ---borrowerApplyId：" + borrowerApplyId);

            return new BackResult(HzdStatusCodeEnum.HZD_CODE_2400.getCode(),
                    HzdStatusCodeEnum.HZD_CODE_2400.getMsg(), null);
        }
        UserApplyInfoVO userApplyInfoVO = userApplyInfoVOResult.getItems();
        userApplyInfoVO.setAdditionalSubmitTime(new Date());
        userApplyInfoVO.setAdditionalStatus("0");//待补充状态0 (后台用)
        userApplyInfoVO.setAdditionalContent(msgBoxBack);

        Result<Boolean> booleanResult = userApplyInfoSerivce.updateApplyId(userApplyInfoVO);
        if (StatusCodes.OK != booleanResult.getStatus()) {
            logger.i("补充资料回调数据 更新 userApplyInfoSerivce信息失败 ---borrowerApplyId：" + borrowerApplyId);
            return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
                    HzdStatusCodeEnum.HZD_CODE_0001.getMsg(), null);
        }
        logger.i("补充资料回调接口-更新补件状态成功--borrowerApplyId：" + borrowerApplyId);

        //写入站内信
        MsgBoxVO msgBoxVO = new MsgBoxVO();
        msgBoxVO.setMsgId(UUIDGenerator.getUUID());
        msgBoxVO.setUserId(userApplyInfoVO.getUserId());
        //msgBoxVO.setStatus(ConstantsParam.MSG_STATUS_YES);
        msgBoxVO.setMsgType(checkSource);    //8=信审补充资料，6=综合业务平台补充资料
        msgBoxVO.setIsRead("0");
        msgBoxVO.setCreateTime(new Date());
        msgBoxVO.setMsgTitle("补充资料");
        msgBoxVO.setStatus("0");
        msgBoxVO.setMsgContent(msgBoxBack);

        Result<Boolean> booleanResult1 = msgBoxservice.insertSelective(msgBoxVO);

        if(StatusCodes.OK!=booleanResult1.getStatus()){
            logger.i("补充资料回调数据 保存 msgBoxVO信息失败 ---borrowerApplyId：" + borrowerApplyId);
            return new BackResult(HzdStatusCodeEnum.HZD_CODE_0001.getCode(),
                    HzdStatusCodeEnum.HZD_CODE_0001.getMsg(), null);
        }
        logger.i("补充资料回调接口-写入站内信成功--borrowerApplyId：" + borrowerApplyId);

        //推送通知
        Map<String, Object> jsonmap = new HashMap<>();
        jsonmap.put("tagCode", ConstantsDictionary.JPUSH_MSGBOX_TAGCODE);   //201
        jsonmap.put("applyId", userApplyInfoVO.getApplyId());
        int res = JpushClientUtil.sendToAliasId(userApplyInfoVO.getUserId().replaceAll("-", ""),
                "尊敬的用户，您在"+ DateUtil.formatDate3(new Date())
                        +"提交的线上进件图片资料有部分不正确,需要重新上传补充.","汇中贷消息标题", "汇中贷消息内容",
                JsonUtil.json2String(jsonmap));
        if(res != 1){
            logger.i("补充资料回调接口 推送消息 失败----"+userApplyInfoVO.getUserId());
            return new BackResult(HzdStatusCodeEnum.HZD_CODE_7100.getCode(),
                    HzdStatusCodeEnum.HZD_CODE_7100.getMsg(), null);
        }
        logger.i("补充资料回调接口 推送消息 成功----"+userApplyInfoVO.getUserId());

        return new BackResult(HzdStatusCodeEnum.HZD_CODE_0000.getCode(),
                HzdStatusCodeEnum.HZD_CODE_0000.getMsg(), null);
    }

}
