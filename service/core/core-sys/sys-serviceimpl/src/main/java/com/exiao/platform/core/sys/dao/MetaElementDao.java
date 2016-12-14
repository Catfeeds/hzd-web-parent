package com.exiao.platform.core.sys.dao;

import java.util.List;
import java.util.Map;

import com.exiao.platform.common.util.rpc.result.PaginatedResult;
import com.exiao.platform.core.sys.data.MetaElement;
import com.exiao.platform.core.sys.data.Role;
import com.exiao.platform.framework.core.storage.StorageProvider;

public interface MetaElementDao extends StorageProvider<MetaElement>{

	
	public MetaElement getMetaElementByID(String id);
	
	/**
	 * 根据元数据名或编码查询
	 * @param dataName
	 * @param dataCode
	 * @return
	 */
	public List<MetaElement> getMetaElementByParam(String dataName,String dataCode);
    /**
     * 分页查找描述
     * 
     * @param pageSize
     * @param pageNo
     * @return
     */
	public PaginatedResult<MetaElement> flipPage(Map<String,Object> param, int pageSize,int pageNo);

	public boolean delete(Long id);	
	
}
