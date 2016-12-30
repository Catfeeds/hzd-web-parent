package com.hzcf.platform.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.api.annotation.RequestAttribute;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.api.service.ILoadService;
import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.user.model.UserVO;

/**
  * @Description:借款信息的Controller
  * @author 作者:裴高祥 E-mail:pgx19890112@163.com Tel:13241706779
  * @date 创建时间：2016年12月29日 下午5:35:47 
  * @version 1.0 
  * @since  JDK1.7
  */
@RestController
public class LoadController {
	private static final Log logger = Log.getLogger(LoadController.class);
	@Autowired
    private ILoadService loadService;//实名认证的service
	/**
	 * 
	 */
	@RequestMapping(value="rest/selectload")
    public BackResult selectload(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user){
        logger.i("查询借款人借款信息");
        logger.i("入参"+ JsonUtil.json2String(user));
        return loadService.selectload(user);
    }
}