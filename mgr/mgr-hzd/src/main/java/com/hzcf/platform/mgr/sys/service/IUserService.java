package com.hzcf.platform.mgr.sys.service;

import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import com.hzcf.platform.mgr.sys.common.pageModel.SmsUserInfo;

/**
 * 
 * @author zhangmx
 * 
 */
public interface IUserService {
	
	public DataGrid getUserPage(PageHelper pageHelper, UserVO user);
	
	public SmsUserInfo getSmsUserDetail(String mobile);
	
	public void save(String mobile,String name,String idCard,String card1,String card2,String card3);
}
