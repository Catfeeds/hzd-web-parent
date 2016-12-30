package com.hzcf.platform.mgr.sys.service.impl;


import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.service.UserApplyInfoSerivce;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import com.hzcf.platform.mgr.sys.service.IApplyService;

@Service
public class ApplyServiceImpl implements IApplyService {
	private static final Log logger = Log.getLogger(ApplyServiceImpl.class);
	
	@Autowired
	public UserApplyInfoSerivce userApplyInfoSerivce;
	
	
	@Override
	public DataGrid getApplyPage(PageHelper pageHelper, UserApplyInfoVO apply){
		pageHelper.setStart((pageHelper.getPage()-1)*pageHelper.getRows());
		pageHelper.setEnd(pageHelper.getRows());
		Map<String, Object> parmMap = new HashMap();
		parmMap.put("apply", apply);
		parmMap.put("page", pageHelper);
		
		DataGrid dataGrid = new DataGrid();
		dataGrid.setTotal(userApplyInfoSerivce.getUserApplyInfoTotal(parmMap));
		PaginatedResult result = userApplyInfoSerivce.getUserApplyInfoList(parmMap);
		dataGrid.setRows(result.getItems());
		return dataGrid;
	}


}
