package com.hzcf.platform.mgr.sys.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserService;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import com.hzcf.platform.mgr.sys.service.ISysUserService;

@Service
public class SysUserServiceImpl implements ISysUserService {
	private static final Log logger = Log.getLogger(SysUserServiceImpl.class);
	
	@Autowired
	public UserService userSerivce;
	
	
	@Override
	public DataGrid getUserPage(PageHelper pageHelper, UserVO user){
		
		pageHelper.setStart((pageHelper.getPage()-1)*pageHelper.getRows());
		pageHelper.setEnd(pageHelper.getRows());
		Map<String, Object> parmMap = new HashMap();
		parmMap.put("user", user);
		parmMap.put("page", pageHelper);
		
		PaginatedResult<UserVO> result = userSerivce.getUserPage(parmMap);
		
		DataGrid dataGrid = new DataGrid();
		dataGrid.setTotal(new Long(result.getPaginate().getTotalItemsCount()));
		List list = result.getItems();
		dataGrid.setRows(list);
		return dataGrid;
	}


}
