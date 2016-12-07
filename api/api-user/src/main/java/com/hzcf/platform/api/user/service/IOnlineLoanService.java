package com.hzcf.platform.api.user.service;

import com.hzcf.platform.api.model.OnlineLoanInfo;
import com.hzcf.platform.api.user.common.BackResult;
import com.hzcf.platform.core.user.model.UserVO;

/**
 * 
 * @description:
 * @author 雷佳明
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月7日                       雷佳明                           1.0       1.0 Version 
 * </pre>
 */
public interface IOnlineLoanService {
	
	public BackResult OnlineLoanApply(UserVO user,OnlineLoanInfo onlineLoanInfo);
	
	public BackResult OnlineLoanQuery(UserVO user);
}
