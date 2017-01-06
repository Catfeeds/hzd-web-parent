package com.hzcf.platform.api.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.api.annotation.RequestAttribute;
import com.hzcf.platform.api.annotation.RequestBodyForm;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.api.service.IOnlineApplyLoanService;
import com.hzcf.platform.api.service.IRealNameService;
import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserVO;

/**
  * @Description:用户的实名认证操作
  * @author 作者:裴高祥 E-mail:pgx19890112@163.com Tel:13241706779
  * @date 创建时间：2016年12月29日 下午3:44:18 
  * @version 1.0 
  * @since  JDK1.7
  */
@RestController
public class RealNameController {
	private static final Log logger = Log.getLogger(RealNameController.class);
    @Autowired
    private IRealNameService realNameService;//实名认证的service
	/**查询实名认证状态，信息
	 * 
	 * */
    @RequestMapping(value="rest/selectrealname",method = RequestMethod.POST)
    public BackResult selectrealname(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user){
        logger.i("查询借款人实名认证信息");
        logger.i("入参"+ JsonUtil.json2String(user));
        return realNameService.selectRealName(user);
    }
	/**保存实名认证信息
	 * 
	 * */
    @RequestMapping(value="rest/api/100/user/saverealname",method = RequestMethod.POST)
    public BackResult saverealname(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user){
        logger.i("保存借款人实名认证信息");
        logger.i("入参"+ JsonUtil.json2String(user));
        return realNameService.saveRealName(user);
    }
	/**上传实名认证图片
	 * 
	 * */
    @RequestMapping(value="rest/api/100/user/saverealnamepic",method = RequestMethod.POST)
    public BackResult saverealnamepic(HttpServletRequest request,
            @RequestAttribute(BaseConfig.USER_TYPE)  UserVO user,
            @RequestBodyForm UserImageVO userImageVO)  {
        logger.i("保存借款人实名认证信息的图片");
        logger.i("入参"+ JsonUtil.json2String(user));
        return realNameService.saveRealNamePic(request,user, userImageVO);
    }

    /**查询实名认证图片
     *
     * */
    @RequestMapping(value={"rest/api/100/user/findImageInfo","test1/test1"},method = RequestMethod.POST)
    public BackResult findImageInfo(
                                      @RequestAttribute(BaseConfig.USER_TYPE)  UserVO user)  {
        logger.i("保存借款人实名认证信息的图片");
        logger.i("入参"+ JsonUtil.json2String(user));
        return realNameService.findImageInfo(user);
    }
}