package com.hzcf.platform.mgr.sys.service.impl;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserImageService;
import com.hzcf.platform.core.user.service.UserService;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import com.hzcf.platform.mgr.sys.common.pageModel.SmsUserInfo;
import com.hzcf.platform.mgr.sys.common.util.DateUtils;
import com.hzcf.platform.mgr.sys.service.IUserService;

@Service
public class UserServiceImpl implements IUserService {
	private static final Log logger = Log.getLogger(UserServiceImpl.class);
	
	@Autowired
	public UserService userSerivce;
	
	@Autowired
	private UserImageService userImageService;
	
	@Override
	public DataGrid getUserPage(PageHelper pageHelper, UserVO userVO){
		pageHelper.setStart((pageHelper.getPage()-1)*pageHelper.getRows());
		pageHelper.setEnd(pageHelper.getRows());
		Map<String, Object> parmMap = new HashMap();
		parmMap.put("user", userVO);
		parmMap.put("page", pageHelper);
		
		DataGrid dataGrid = new DataGrid();
		dataGrid.setTotal(userSerivce.getUserTotal(parmMap));
		PaginatedResult result = userSerivce.getUserList(parmMap);
		dataGrid.setRows(result.getItems());
		return dataGrid;
	}

	
	@Override
	public SmsUserInfo getSmsUserDetail(String mobile) {
		SmsUserInfo se = new SmsUserInfo();
		DateUtils dateUtils = new DateUtils();
		Result<UserVO> user = userSerivce.getByMobile(mobile);
		//Result<UserImageVO> userImage =  userImageService.getById(user.getItems().getId());
		//System.out.println((user.getItems()).getId());
		Result<UserImageVO> userImage = userImageService.getById((user.getItems()).getId());
		if(userImage.getStatus()==200 && userImage.getItems()!=null){
			se.setArtWork(userImage.getItems().getArtWork());
			se.setSmall(userImage.getItems().getSmall());
			se.setImageType(userImage.getItems().getImageType());
			se.setType(userImage.getItems().getType());
		}
		
		se.setMobile(mobile);
		se.setName(user.getItems().getName());
		se.setIdCard(user.getItems().getIdCard());
		String statu = user.getItems().getCheckStatus();
		se.setCheckStatus(statu);
		if("0".equals(statu)){
			se.setStatusInfo("通过");
			se.setButt("返回");
		}
		if("1".equals(statu)){
			se.setStatusInfo("不通过");
			se.setButt("返回");
		}
		if("2".equals(statu)){
			se.setStatusInfo("待审核");
			se.setButt("提交");
		}
		if(user.getItems().getSubmitTime()!=""&&user.getItems().getSubmitTime()!=null){
			se.setCreateTime(dateUtils.getDate(user.getItems().getSubmitTime()));
		}
		//System.out.println(user.getItems().getCreateTime());
		
		return se;
	}


	@Override
	public void update(String mobile,String name, String idCard) {
		UserVO user = new UserVO();
		user.setMobile(mobile);
		user.setName(name);
		user.setIdCard(idCard);
		
		Result<UserVO> userVO = userSerivce.getByMobile(mobile);
		user.setId(userVO.getItems().getId());
		userSerivce.updateByPrimaryKeySelective(user);
	}


	@Override
	public Result<Boolean> updateStatus(String mobile, String checkStatus,String nopassCause) {
		UserVO user = new UserVO();
		user.setCheckStatus(checkStatus);
		user.setMobile(mobile);
		if(nopassCause!=null){
			user.setNopassCause(nopassCause);
		}
		Result<UserVO> use1 = userSerivce.getByMobile(mobile);
		user.setId(use1.getItems().getId());
		return userSerivce.updateByPrimaryKeySelective(user);
	}


	/*@Override
	public Result<Boolean> update(SmsUserInfo smsUserInfo) {
		UserVO user = new UserVO();
		UserImageVO userImage = new UserImageVO();
		user.setMobile(smsUserInfo.getMobile());
		user.setName(smsUserInfo.getName());
		user.setIdCard(smsUserInfo.getIdCard());
		userImage.setSmall(smsUserInfo.getSmall());
		userImage.setType(userImage.getType());
		Result<UserVO> use1 = userSerivce.getByMobile(smsUserInfo.getMobile());
		user.setId(use1.getItems().getId());
		return userSerivce.updateByPrimaryKeySelective(user);
	}*/



}
