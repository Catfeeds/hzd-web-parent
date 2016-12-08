package com.hzcf.platform.api.user.controller;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.api.user.property.RockProperty;
import com.hzcf.platform.common.cache.ICache;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.webService.SmsObtainService;

/**
 * 
 * @description:短信Controller
 * @author zhang
 * @version 1.0
 */
@RestController
public class SmsController {
	private static final Log logger = Log.getLogger(SmsController.class);
	
    @Autowired
    private ICache cache;
    
    @Resource(name = "rockProperty")
    private RockProperty rockProperty;
	
	@RequestMapping(value="/testcache",method=RequestMethod.GET)
	public String testcache(){
		cache.save("useraaa", "cache_name1111", 10);
		System.out.println("setCache.......");
		
		String usera  = (String)cache.load("useraaa");
		System.out.println("getCache:::"+usera.toString());
		
		return null;
	}
	
	@RequestMapping(value="/getcache",method=RequestMethod.GET)
	public String getcache(){
		String usera  = (String)cache.load("useraaa");
		System.out.println("getCache:::"+usera);
		
		return null;
	}
	
	@RequestMapping(value="/testproperty",method=RequestMethod.GET)
	public String testproperty(){
		System.out.println("rockProperty:::::::"+rockProperty.getKey());
		System.out.println("getUsername:::::::"+rockProperty.getContentText());
		
		SmsObtainService.smsObtain("666666", "13810738215");
		return null;
	}
	
	@RequestMapping(value="/testbean", method=RequestMethod.POST)
	public String testbean(UserVO user){
		logger.i("userInfo::::::::::: "+user.toString());
		return null;
	}
	
	
}
