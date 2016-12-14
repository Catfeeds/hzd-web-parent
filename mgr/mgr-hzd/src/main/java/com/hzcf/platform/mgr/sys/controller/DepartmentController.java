package com.hzcf.platform.mgr.sys.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hzcf.platform.common.util.response.ResponseBuilder;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.sys.model.DepartmentVO;
import com.hzcf.platform.core.sys.service.DepartmentService;

@RestController
public class DepartmentController {
	
	private Logger logger = LoggerFactory.getLogger(this.getClass());
  
	@Autowired
	private DepartmentService departmentService;
	
	@RequestMapping(value = "/department/getList", method = RequestMethod.GET)
	public ResponseEntity<Object> getDepartmentList(HttpServletRequest request) {

		Result<List<DepartmentVO>> result = departmentService.getCollecion();
	
		return ResponseBuilder.instance().body(result).build();
	}
	
}
