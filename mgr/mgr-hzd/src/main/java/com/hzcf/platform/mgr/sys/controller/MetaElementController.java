package com.hzcf.platform.mgr.sys.controller;

import java.util.ArrayList;
import java.util.List;

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

import com.exiao.platform.common.util.json.parser.JsonParserFactory;
import com.exiao.platform.common.util.response.ResponseBuilder;
import com.exiao.platform.common.util.rpc.result.PaginatedResult;
import com.exiao.platform.common.util.rpc.result.Result;
import com.exiao.platform.common.util.status.StatusCodes;
import com.exiao.platform.core.sys.model.BaseUser;
import com.exiao.platform.core.sys.model.Element;
import com.exiao.platform.core.sys.model.MetaElementVO;
import com.exiao.platform.core.sys.model.RoleVO;
import com.exiao.platform.core.sys.service.MetaElementService;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 元数据控制器
 * 
 * @author xiaojun
 *
 */
@RestController
public class MetaElementController {

	private Logger logger = LoggerFactory.getLogger(this.getClass());


	private ObjectMapper objectMapper;
	
	private ObjectMapper getObjectMapper() {
		objectMapper = new ObjectMapper();
		objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);
		objectMapper.setSerializationInclusion(Include.NON_NULL);
		return objectMapper;
	}
	@Autowired
	private MetaElementService metaElementService;

	@RequiresPermissions("sys:meta:view")
	@RequestMapping(value = "/metadata/list/page", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public ResponseEntity<Object> getPageList(@RequestBody SearchParam param) {

		System.out.println("============," + param.getPageSize() + ", " + param.getPageNo());

		PaginatedResult<MetaElementVO> result = metaElementService.queryListByPaginate(param.getDataName(),param.getDataCode(),
				param.getPageSize(), param.getPageNo());
		logger.info("================end= {}{}{}", param.getPageSize(), param.getPageNo(), result);
		return ResponseBuilder.instance().body(result).build();

	}

	@RequiresPermissions("sys:meta:view")
	@RequestMapping(value = "/metadata/getListByName", method = RequestMethod.GET)
	public ResponseEntity<Object> getMetaElementByName(HttpServletRequest request, @PathVariable String elementName) {

		Result<List<MetaElementVO>> result = metaElementService.getMetaElementVOByDataName(elementName);

		return ResponseBuilder.instance().body(result).build();
	}

	/**
	 * 根据元数据编码取得 元数据列表
	 * @param request
	 * @param elementCode
	 * @return
	 */
	
	@RequestMapping(value = "/metadata/{elementCode}", method = RequestMethod.GET)
	public ResponseEntity<Object> getMetaElementByCode(HttpServletRequest request, @PathVariable String elementCode) {

		Result<List<MetaElementVO>> result = metaElementService.getMetaElementVOByDataCode(elementCode);
		System.out.println(result);
		List<MetaElementVO> list=result.getItems();
		List<Element> elements=new ArrayList<Element>();
		for(MetaElementVO v :list){
			Element e=new Element();
			e.setPosition(v.getposition());
			e.setContentCn(v.getContentCn());
			e.setContentEn(v.getContentEn());
			elements.add(e);
		}
		objectMapper=getObjectMapper();
		ObjectNode root =objectMapper.createObjectNode();
		root.put("status", StatusCodes.OK);
		root.put("msg", "成功");
		root.put("data", JsonParserFactory.getParser().toJson(elements));
		return ResponseBuilder.instance().body(root).build();
	}

	@RequiresPermissions("sys:meta:view")
	@RequestMapping(value = "/meta/{id}", method=RequestMethod.GET)
	public ResponseEntity<Object> getMetaElementByID(HttpServletRequest request,@PathVariable String id) {
		
		Result<MetaElementVO> result= metaElementService.getMetaElementByID(id);
		
		return ResponseBuilder.instance().body(result).build();

	}
	@RequiresPermissions("sys:meta:edit")
	@RequestMapping(value = "/meta", method=RequestMethod.POST ,produces={"application/json;charset=UTF-8"})
	public ResponseEntity<Object> add(HttpServletRequest request,@RequestBody MetaElementVO metaElementVO) {
		
		
		Result<Boolean> result = (Result) metaElementService.create(metaElementVO);
		
		return ResponseBuilder.instance().body(result).build();
		
	}
	
	@RequiresPermissions("sys:meta:edit")
	@RequestMapping(value = "/meta", method=RequestMethod.PUT)
	public ResponseEntity<Object> modify(HttpServletRequest request,@RequestBody MetaElementVO metaElementVO) {
		
		
		Result<Boolean> result = (Result) metaElementService.update(metaElementVO);
		
		return ResponseBuilder.instance().body(result).build();
		
	}
	
	@RequiresPermissions("sys:meta:del")
	@RequestMapping(value = "/meta/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Object> delete(@PathVariable String id) {
		
		System.out.println("delete id is:"+id);
		
		Result<Boolean> result = metaElementService.delete(Long.parseLong(id));
		
		return ResponseBuilder.instance().body(result).build();
	}
	
	
	public static class SearchParam {
		String dataName;
		String dataCode;
		int pageSize;
		int pageNo;

		

		public String getDataName() {
			return dataName;
		}

		public void setDataName(String dataName) {
			this.dataName = dataName;
		}

		public String getDataCode() {
			return dataCode;
		}

		public void setDataCode(String dataCode) {
			this.dataCode = dataCode;
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
