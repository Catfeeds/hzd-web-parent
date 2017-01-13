package com.hzcf.platform.api.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
  * 	作用：借款人查询借款的进度，信息
  * @author 作者:裴高祥 E-mail:pgx19890112@163.com Tel:13241706779
  * @date 创建时间：2016年12月29日 下午5:35:47 
  * @version 1.0 
  * @since  JDK1.7
  */
@RestController
public class LoadController {
	private static final Log logger = Log.getLogger(LoadController.class);
	@Autowired
    private ILoadService loadService;//借款信息的service
	/**进件接口
	 * 
	 */
	@RequestMapping(value="insertload",method = RequestMethod.POST)
    public void insertload(@RequestBody String params){
        logger.i("进件接口");
        loadService.insertLoad(params);
    }
	/**进件接口
	 * 
	 */
	@RequestMapping(value="operateload",method = RequestMethod.POST)
    public void operateload(@RequestBody String params) throws Exception {
        logger.i("进件接口");
        loadService.operateLoad(params);
    }
	/**借款人查询借款进度接口
	 * 
	 */
	@RequestMapping(value="rest/api/100/user/selectloadprogress",method = RequestMethod.POST)
    public BackResult selectloadprogress(@RequestAttribute(BaseConfig.USER_TYPE) UserVO user){
        logger.i("借款人查询借款进度接口");
        logger.i("入参"+ JsonUtil.json2String(user));
        return loadService.selectLoadProgress(user);
    }
}