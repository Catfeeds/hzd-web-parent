package com.hzcf.platform.mgr.sys.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.service.DictUtilService;
import com.hzcf.platform.core.user.service.UserApplyInfoSerivce;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import com.hzcf.platform.mgr.sys.service.IApplyService;

@Service
public class ApplyServiceImpl implements IApplyService {
	private static final Log logger = Log.getLogger(ApplyServiceImpl.class);
	
	@Autowired
	public UserApplyInfoSerivce userApplyInfoSerivce;
	
	@Autowired
    DictUtilService dictUtilService;
	
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
		
		//借款用途-转码
		List<UserApplyInfoVO> applyList = result.getItems();
		for(UserApplyInfoVO userApplyInfoVO : applyList){
			String loanPurposeOne = userApplyInfoVO.getLoanPurposeOne();
			String loanPurposeOneValue = dictUtilService.convertLoanPurposeOne(loanPurposeOne);
			userApplyInfoVO.setLoanPurposeOne(loanPurposeOneValue);
			String loanPurposeTwo = userApplyInfoVO.getLoanPurposeTwo();
			String loanPurposeTwoValue = dictUtilService.convertLoanPurposeTwo(loanPurposeOne,loanPurposeTwo);
			userApplyInfoVO.setLoanPurposeTwo(loanPurposeTwoValue);
//			userApplyInfoVO.setLoanPurposeOne(dictUtilService.convertLoanPurposeOne(userApplyInfoVO.getLoanPurposeOne()));
//			userApplyInfoVO.setLoanPurposeTwo(dictUtilService.convertLoanPurposeTwo(userApplyInfoVO.getLoanPurposeOne(), userApplyInfoVO.getLoanPurposeTwo()));
		}
		result.setItems(applyList);
		dataGrid.setRows(result.getItems());
		return dataGrid;
	}
	
	@Override
	public List<UserApplyInfoVO> getUserApplyForSearch(UserApplyInfoVO apply){
		Map<String, Object> parmMap = new HashMap();
		parmMap.put("apply", apply);
		PaginatedResult result =  userApplyInfoSerivce.getUserApplyForSearch(parmMap);
		return result.getItems();
	}

}
