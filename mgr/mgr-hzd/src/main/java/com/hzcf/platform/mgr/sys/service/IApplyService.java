package com.hzcf.platform.mgr.sys.service;

import java.util.List;

import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.JsonResult;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @description:后台进件管理Service
 * @author zhangmx
 * 
 */
public interface IApplyService {
	
	public DataGrid getApplyPage(PageHelper pageHelper, UserApplyInfoVO apply);
	
	public List<UserApplyInfoVO> getUserApplyForSearch(UserApplyInfoVO apply);

	public JsonResult anewSubmitApply( String applyId,String mobile);
}
