package com.hzcf.platform.mgr.sys.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exiao.platform.common.util.response.ResponseBuilder;
import com.exiao.platform.common.util.rpc.result.PaginatedResult;
import com.exiao.platform.common.util.rpc.result.Result;
import com.exiao.platform.core.sys.model.SysLogVO;

import com.exiao.platform.core.sys.service.SysLogService;

@RestController
public class SysLogController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysLogService sysLogService;

	
	@RequiresPermissions("sys:log:view")
	@RequestMapping(value = "/sysLog/view/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getSysLogByID(@PathVariable String id) {

		Result<SysLogVO> result = sysLogService.getSysLogByID(id);

		return ResponseBuilder.instance().body(result).build();

	}
	
	@RequiresPermissions("sys:log:add")
	@RequestMapping(value = "/sysLog/add", method = RequestMethod.POST)
	public ResponseEntity<Object> addSysLog(HttpServletRequest request,@RequestBody SysLogVO sysLogVo) {

		Result<Long> result = sysLogService.create(sysLogVo);

		return ResponseBuilder.instance().body(result).build();

	}
	
	@RequiresPermissions("sys:log:view")
	@RequestMapping(value = "/sysLog/list/page", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public ResponseEntity<Object> getPageList(@RequestBody SearchParam param) {

		logger.info("============," + param.getPageSize() + ", " + param.getPageNo()+","+param.getOperator());
		Map<String ,Object> querymap=new HashMap<String ,Object>();
		querymap.put("logicalDel","0");
		if(StringUtils.isNotEmpty(param.getOperator())){
			querymap.put("operator", param.getOperator());
		}
		PaginatedResult<SysLogVO> result = sysLogService.flipPage(querymap, param.getPageSize(), param.getPageNo());
		logger.info("================end= {}{}{}", param.getPageSize(), param.getPageNo(), result);
		return ResponseBuilder.instance().body(result).build();

	}

	public static class SearchParam {
		int pageSize;
		int pageNo;
		String operator;//操作员
		
	

		public String getOperator() {
			return operator;
		}

		public void setOperator(String operator) {
			this.operator = operator;
		}

		
		public int getPageSize() {
			return pageSize;
		}

		public void setPageSize(int pageSize) {
			this.pageSize = pageSize;
		}

		public int getPageNo() {
			return pageNo;
		}

		public void setPageNo(int pageNo) {
			this.pageNo = pageNo;
		}
	}

}
