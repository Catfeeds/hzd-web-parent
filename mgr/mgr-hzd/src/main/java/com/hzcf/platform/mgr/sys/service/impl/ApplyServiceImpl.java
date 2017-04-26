package com.hzcf.platform.mgr.sys.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hzcf.platform.core.user.service.UserService;
import com.hzcf.platform.mgr.sys.common.pageModel.JsonResult;
import com.hzcf.platform.mgr.sys.webService.LoadService;
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
import org.springframework.web.bind.annotation.RequestParam;

@Service
public class ApplyServiceImpl implements IApplyService {
	private static final Log logger = Log.getLogger(ApplyServiceImpl.class);
	
	@Autowired
	public UserApplyInfoSerivce userApplyInfoSerivce;
	@Autowired
	public UserService userService;
	@Autowired
    DictUtilService dictUtilService;
	@Autowired
	LoadService loadService;
	
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
			userApplyInfoVO.setCheckStatus(userService.selectByPrimaryKey(userApplyInfoVO.getUserId()).getItems().getCheckStatus());
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

	@Override
	public JsonResult anewSubmitApply( String applyId ,String mobile) {

		Map map = loadService.operateLoadMap(applyId, userService.getByMobile(mobile).getItems());

		boolean success =(boolean)map.get("result");
		String resultMsg= (String)map.get("resultMsg");
		return new JsonResult(success,resultMsg,null);
	}

}
