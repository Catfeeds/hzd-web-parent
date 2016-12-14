package com.exiao.platform.core.sys.service;

import java.util.List;

import com.exiao.platform.common.util.rpc.result.PaginatedResult;
import com.exiao.platform.common.util.rpc.result.Result;

import com.exiao.platform.core.sys.model.MetaElementVO;
import com.exiao.platform.core.sys.model.RoleVO;
import com.exiao.platform.framework.core.service.CommonBaseService;


public interface MetaElementService extends CommonBaseService<MetaElementVO>{

	/**
	 * 查询所有元数据
	 * @param data_name
	 * @param pageSize
	 * @param pageNo
	 * @return
	 */

	public PaginatedResult<MetaElementVO> queryListByPaginate(String data_name,String dataCode,int pageSize,int pageNo);

	public Result<List<MetaElementVO>>  getMetaElementVOByDataName(String dataName);
	
	public Result<List<MetaElementVO>>  getMetaElementVOByDataCode(String dataCode);
	
	public Result<MetaElementVO>  getMetaElementByID(String id);
	
	/**  
	 * 添加
	 */
	public Result<Long> create(MetaElementVO metaVo);
	  
	public Result<Boolean> delete(Long id);
}
