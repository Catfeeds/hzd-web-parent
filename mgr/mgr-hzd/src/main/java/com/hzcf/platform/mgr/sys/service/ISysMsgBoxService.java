package com.hzcf.platform.mgr.sys.service;

import com.hzcf.platform.core.user.model.MsgBoxVO;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;

/**
 * @author 李强
 *
 */
public interface ISysMsgBoxService {

	public DataGrid getMsgBoxPage(PageHelper pageHelper, MsgBoxVO msgBox);
	
}
