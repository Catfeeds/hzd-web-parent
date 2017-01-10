package com.hzcf.platform.mgr.sys.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

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
	
	public Result<Boolean> updateStatus(String mobile,String checkStatus,String nopassCause);
	
	public Result<Boolean> updatePassWord(String mobile,String passWord);
	
	public Result<Boolean> status(String mobile,String status);
	
	public Result<String> smsImgUpload(HttpServletRequest request,String imgId,String mobile);
	//public Result<Boolean> update(SmsUserInfo smsUserInfo);
}
