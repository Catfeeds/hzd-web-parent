package com.hzcf.platform.mgr.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
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
import com.exiao.platform.core.sys.model.BaseUser;
import com.exiao.platform.core.sys.model.ScheduleJobVO;
import com.exiao.platform.core.sys.service.ScheduleJobService;

@RestController
public class ScheduleJobController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private ScheduleJobService scheduleJobService;

	@RequestMapping(value = "/scheduleJob/{id}", method = RequestMethod.GET)
	public ResponseEntity<Object> getScheduleJobByID(HttpServletRequest request, @PathVariable String id) {
		
		Result<ScheduleJobVO> result = scheduleJobService.getScheduleJobById(id);
		return ResponseBuilder.instance().body(result).build();

	}

	@RequestMapping(value = "/scheduleJob/list", method = RequestMethod.GET)
	public ResponseEntity<Object> getAll(HttpServletRequest request) {

		Result<List<ScheduleJobVO>> resultList = scheduleJobService.getCollecion();
		return ResponseBuilder.instance().body(resultList).build();

	}
	

	@RequestMapping(value = "/scheduleJob", method = RequestMethod.PUT)
	public ResponseEntity<Object> modify(HttpServletRequest request, @RequestBody ScheduleJobVO scheduleJobVO) {

		Result<Boolean> result = (Result) scheduleJobService.update(scheduleJobVO);

		return ResponseBuilder.instance().body(result).build();

	}

	@RequestMapping(value = "/scheduleJob/page", method = RequestMethod.POST)
	public ResponseEntity<Object> getPageList(HttpServletRequest request, @RequestBody SearchParam param) {

		logger.info("============," + param.getPageSize() + ", " + param.getPageNo());
		Map<String, Object> querymap = new HashMap<String, Object>();

		PaginatedResult<ScheduleJobVO> result = scheduleJobService.flipPage(querymap, param.getPageSize(),
				param.getPageNo());
		logger.info("================end= {}{}{}", param.getPageSize(), param.getPageNo(), result);

		return ResponseBuilder.instance().body(result).build();
	}

	@RequestMapping(value = "/scheduleJob", method = RequestMethod.POST)
	public ResponseEntity<Object> add(HttpServletRequest request, @RequestBody ScheduleJobVO scheduleJobVO) {

		Subject subject = SecurityUtils.getSubject();
		BaseUser user = (BaseUser) subject.getPrincipal();
		scheduleJobVO.setCreateBy(user.getId().intValue());
		scheduleJobVO.setJobStatus(0);
		Result<Long> result = (Result) scheduleJobService.create(scheduleJobVO);

		return ResponseBuilder.instance().body(result).build();

	}
	

	@RequestMapping(value = "/scheduleJob/forbid/{id}", method = RequestMethod.POST)
	public ResponseEntity<Object> forbid(HttpServletRequest request,@PathVariable String id) {

		Result<Boolean> result = scheduleJobService.updateSheduleJobById(id);

		return ResponseBuilder.instance().body(result).build();
	}

	public static class SearchParam {
		
		/** 任务名称 */
		private String jobName;
		/** 任务分组 */
		private String jobGroup;
		/** 任务状态 0禁用 1启用 2删除 */
		private Integer jobStatus;
		
		int pageSize;
		
		int pageNo;

		public String getJobName() {
			return jobName;
		}

		public void setJobName(String jobName) {
			this.jobName = jobName;
		}

		public String getJobGroup() {
			return jobGroup;
		}

		public void setJobGroup(String jobGroup) {
			this.jobGroup = jobGroup;
		}

		public Integer getJobStatus() {
			return jobStatus;
		}

		public void setJobStatus(Integer jobStatus) {
			this.jobStatus = jobStatus;
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
