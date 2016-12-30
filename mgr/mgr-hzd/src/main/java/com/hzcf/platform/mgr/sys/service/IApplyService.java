package com.hzcf.platform.mgr.sys.service;

import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;

/**
 * @description:后台进件管理Service
 * @author zhangmx
 * 
 */
public interface IApplyService {
	
	public DataGrid getApplyPage(PageHelper pageHelper, UserApplyInfoVO apply);
	
}
