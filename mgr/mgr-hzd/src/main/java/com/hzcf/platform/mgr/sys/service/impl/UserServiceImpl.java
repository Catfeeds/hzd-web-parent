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
		Long userId = new Long(user.getItems().getId());
		Result<UserImageVO> userImage =  userImageService.getByPK(userId);
		se.setMobile(mobile);
		se.setName(user.getItems().getName());
		se.setIdCard(user.getItems().getIdCard());
		se.setCheckStatus(user.getItems().getCheckStatus());
		se.setCreateTime(dateUtils.formatDate(user.getItems().getCreateTime()));
		se.setArtWork(userImage.getItems().getArtWork());
		se.setSmall(userImage.getItems().getSmall());
		se.setImageType(userImage.getItems().getImageType());
		se.setType(userImage.getItems().getType());
		
		return se;
	}


	@Override
	public void save(String mobile,String name, String idCard, String card1, String card2, String card3) {
		UserVO user = new UserVO();
		UserImageVO userImage = new UserImageVO();
		user.setName(name);
		user.setIdCard(idCard);
		
		Result<UserVO> userVO = userSerivce.getByMobile(mobile);
		userImage.setUserId(userVO.getItems().getId());
		userImage.setArtWork(card1);
		userImage.setArtWork(card2);
		userImage.setArtWork(card3);
		userSerivce.insertSelective(user);
		userImageService.update(userImage);
	}



}
