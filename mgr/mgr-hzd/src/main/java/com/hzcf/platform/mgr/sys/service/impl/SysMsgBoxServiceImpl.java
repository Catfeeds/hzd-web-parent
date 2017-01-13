package com.hzcf.platform.mgr.sys.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.core.user.model.MsgBoxVO;
import com.hzcf.platform.core.user.service.MsgBoxservice;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import com.hzcf.platform.mgr.sys.service.ISysMsgBoxService;



public class SysMsgBoxServiceImpl implements ISysMsgBoxService {

	@Autowired
	public MsgBoxservice msgBoxService;
	
	@Override
	public DataGrid getMsgBoxPage(PageHelper pageHelper, MsgBoxVO msg){
		
		pageHelper.setStart((pageHelper.getPage()-1)*pageHelper.getRows());
		pageHelper.setEnd(pageHelper.getRows());
		Map<String, Object> parmMap = new HashMap();
		parmMap.put("msg", msg);
		parmMap.put("page", pageHelper);
		
		/*PaginatedResult<MsgBoxVO> result = msgBoxService.getMsgBoxPage(parmMap);
		
		DataGrid dataGrid = new DataGrid();
		dataGrid.setTotal(new Long(result.getPaginate().getTotalItemsCount()));
		List list = result.getItems();
		dataGrid.setRows(list);*/
		return null;
	}
}
