package com.hzcf.platform.mgr.sys.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hzcf.platform.common.util.rpc.result.Result;
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
	
	public Result<Boolean> update(String mobile,String name,String idCard);
	
	public Result<Map> updateStatus(String mobile,String checkStatus,String nopassCause);
	/**
	 * @Title: updatePassWord 
	 * @Description:根据借款人的手机号修改借款人的密码 
	 * @time: 2017年1月18日 上午10:09:54  
	 * @return:Result<Boolean>
	 */
	public Result<Boolean> updatePassWord(String mobile,String passWord);
	
	public Result<Boolean> status(String mobile,String status);
	
	public Result<String> smsImgUpload(HttpServletRequest request,String imgId,String mobile);
	//public Result<Boolean> update(SmsUserInfo smsUserInfo);
	
	public List<UserVO> getCheckUserForSearch(UserVO user);
}
