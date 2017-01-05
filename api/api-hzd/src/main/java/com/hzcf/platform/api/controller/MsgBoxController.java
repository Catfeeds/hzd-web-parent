package com.hzcf.platform.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.api.annotation.RequestBodyForm;
import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.service.IMsgBoxService;
import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.user.model.MsgBoxVO;

/**
  * @Description:站内信 Controller
  * @author zhangmx
  */
@RestController
public class MsgBoxController {
	private static final Log logger = Log.getLogger(MsgBoxController.class);
	@Autowired
    private IMsgBoxService msgBoxService;
	
	/**
     * by zhangmx 
     * 未读个数
     */

	@RequestMapping(value = {"rest/api/100/user/znInfo/status","api/100/user/znInfo/status"},method = RequestMethod.POST)
	public BackResult selectUnReadNum(@RequestBodyForm MsgBoxVO msgBoxVO){
		logger.i("站内信未读个数");
        logger.i("入参"+ JsonUtil.json2String(msgBoxVO));
        return msgBoxService.selectUnReadNum(msgBoxVO);
	}
	/**
     * by zhangmx
     * 查询所有消息
     */
	@RequestMapping(value = {"rest/api/100/user/znInfo/all","api/100/user/znInfo/all"},method = RequestMethod.POST)
	public BackResult selectAllByUser(@RequestBodyForm MsgBoxVO msgBoxVO){
		logger.i("站内信查询所有消息");
        logger.i("入参"+ JsonUtil.json2String(msgBoxVO));
        return msgBoxService.selectAllByUser(msgBoxVO);
	}

}