package com.hzcf.platform.api.service;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.core.user.model.UserVO;

/**
 * 
 * @description:修改密码
 * @author 雷佳明
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月8日                       雷佳明                           1.0       1.0 Version 
 * </pre>
 */
public interface IUserpwdForService {
	
	/**
	 * 
		 * @Description: 修改密码
		 * @User: 雷佳明
		 * @FileName: WipeRecordMgr.java
		 * @param 参数  
		 * @return 返回类型 
		 * @date 2016年12月8日
		 * @throws
	 */
	public BackResult updatepwdForlogin(UserVO user,String smsnum);
	/**
	 * 
		 * @Description: 找回密码
		 * @User: 雷佳明
		 * @FileName: WipeRecordMgr.java
		 * @param 参数  
		 * @return 返回类型 
		 * @date 2016年12月8日
		 * @throws
	 */
	public BackResult findpwdForlogin(UserVO user,String smsnum);
}
