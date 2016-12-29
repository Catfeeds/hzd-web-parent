package com.hzcf.platform.api.service.impl;


import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.api.model.CheckApplyLoanStatus;
import com.hzcf.platform.api.service.IOnlineApplyLoanService;
import com.hzcf.platform.common.exception.CheckException;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.common.util.uuid.UUIDGenerator;
import com.hzcf.platform.core.DataVerifcation;
import com.hzcf.platform.core.HzdStatusCodeEnum;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserInfoVO;
import com.hzcf.platform.core.user.model.UserRelationVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserApplyInfoSerivce;
import com.hzcf.platform.core.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by leijiaming on 2016/12/28 0028.
 */
@Service
public class OnlineApplyLoanServiceSerivce implements IOnlineApplyLoanService {
    private static final Log logger = Log.getLogger(OnlineApplyLoanServiceSerivce.class);
    @Autowired
    public UserService userSerivce;
    @Autowired
    public UserApplyInfoSerivce userApplyInfoSerivce;

    @Override
    public BackResult isApplyLoanQuery(UserVO user) {
        logger.i("----------------进入校验是否可以进件");
        Map<String,Object> map = new HashMap<String,Object>();
        CheckApplyLoanStatus checkApplyLoanStatus = new CheckApplyLoanStatus();
        try {
            DataVerifcation.datavVerification(user.getMobile());
            Result<UserVO> byMobile = userSerivce.getByMobile(user.getMobile());
            UserVO items = byMobile.getItems();
            if (BaseConfig.card_status_1.equals(items.getCheckStatus())){
                logger.i("------------用户未通过实名认证");
                checkApplyLoanStatus.setCardStatus(BaseConfig.card_status_1);
                checkApplyLoanStatus.setApplyLoanStatus(BaseConfig.apply_loan_0);
                return new  BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),checkApplyLoanStatus);
            }

            //TODO 进件申请,查询进件信息未实现
            String  ApplyLoanInfoStatus = "0";


            if(BaseConfig.apply_loan_1.equals(ApplyLoanInfoStatus)){
                logger.i("------------------不能重复提交进件信息");
                checkApplyLoanStatus.setCardStatus(BaseConfig.card_status_0);
                checkApplyLoanStatus.setApplyLoanStatus(BaseConfig.apply_loan_1);
                return new  BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),checkApplyLoanStatus);

            }
            map.put("userId",items.getId());
            map.put("card",items.getIdCard());
            map.put("name",items.getName());
            return new  BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),map);

        }catch (CheckException e){
            logger.i("------------------缺少必传参数---"+e.getMessage());
            return new  BackResult(HzdStatusCodeEnum.MEF_CODE_9000.getCode(),e.getMessage(),null);

        }catch (Exception e){
            logger.i("-----------系统异常,请检查数据源-------");
            e.printStackTrace();
            return new  BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(),HzdStatusCodeEnum.MEF_CODE_9999.getMsg(),null);
        }



    }

    @Override
    public BackResult onlineLoanapplyOne(UserVO user, UserApplyInfoVO userApplyInfoVO) {
        try {
            //TODO 测试数据
            if(user==null){
               user= new UserVO();
                user.setId("89898989898aa");
                Result<UserApplyInfoVO> userApplyInfoVOResult = userApplyInfoSerivce.selectByUserId("89898989898aa");
                UserApplyInfoVO items = userApplyInfoVOResult.getItems();
                return new BackResult(0,"成功",items);
            }


            //------------
            DataVerifcation.checkUserApplyInfoVO(userApplyInfoVO,user);
            String applyId=UUIDGenerator.getUUID();
            userApplyInfoVO.setApplyId(applyId);
            userApplyInfoVO.setUserId(user.getId());
            userApplyInfoVO.setStatus(BaseConfig.apply_loan_1);
            userApplyInfoVO.setApplySubmitTime(new Date());
            Result<String> stringResult = userApplyInfoSerivce.create(userApplyInfoVO);
            if(StatusCodes.OK==(stringResult.getStatus())){
                return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg());
            }

        }catch (CheckException e ){
            e.printStackTrace();
            return new  BackResult(HzdStatusCodeEnum.MEF_CODE_9000.getCode(),e.getMessage(),null);

        }catch (Exception e){
            logger.i("-----------系统异常,请检查数据源-------");
            e.printStackTrace();
            return new  BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(),HzdStatusCodeEnum.MEF_CODE_9999.getMsg(),null);
        }

        return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(), HzdStatusCodeEnum.MEF_CODE_0001.getMsg());



    }

    @Override
    public BackResult onlineLoanapplyInfoTwo(UserVO user, UserInfoVO userInfoVO) {
        return null;
    }

    @Override
    public BackResult onlineLoanapplyInfoThree(UserVO user, UserInfoVO userInfoVO) {
        return null;
    }

    @Override
    public BackResult onlineLoanapplyInfoPerfect(UserVO user,  List<UserRelationVO> userRelationVO) {
        return null;
    }

}
