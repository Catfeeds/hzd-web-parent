/**
 * 
 */
package com.hzcf.platform.mgr.sys.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exiao.platform.common.util.response.ResponseBuilder;
import com.exiao.platform.common.util.rpc.result.Result;
import com.exiao.platform.core.sys.model.AreaVO;
import com.exiao.platform.core.sys.service.AreaService;

/**
 * @author allen.shen
 * @Date 2015年9月9日
 * 
 * description: 供应链金融对外暴露的API接口。
 */
@RestController
public class AreaController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private AreaService areaService;
	
	@RequestMapping(value = "/area/{params}", method = RequestMethod.GET)
	public ResponseEntity<Object> getAreaInfo(@PathVariable String params) {
		
		logger.debug("into AreaController getAreaInfo method, {} ", params);
		Result<List<AreaVO>> result = areaService.getAreaInfoByParams(params);
		
		return ResponseBuilder.instance().body(result).build();
	}
	
	@RequestMapping(value = "/area/range/{parentCode}", method = RequestMethod.GET)
	public ResponseEntity<Object> getAreaListByCode(@PathVariable String parentCode) {
		
		Result<List<AreaVO>> result = areaService.getAreaInfoByParentCode(parentCode);
		
		return ResponseBuilder.instance().body(result).build();
	}
	
	

}
