package com.hzcf.platform.mgr.sys.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.sys.model.SysUsersVO;
import com.hzcf.platform.core.sys.service.SysUsersService;
import com.hzcf.platform.mgr.sys.service.ISysUsersService;
/**
 * 
 * @author 李强
 *
 */
@Service
public class SysUsersServiceImpl implements ISysUsersService {
	
	private static final Log logger = Log.getLogger(SysUsersServiceImpl.class);
	
	@Autowired
	public SysUsersService sysUsersSerivce;

	@Override
	public SysUsersVO getSysUsersInfo(String username) {
		Result<SysUsersVO> sysUsersVO = sysUsersSerivce.getBySysUsersName(username);
		return sysUsersVO.getItems();
	}

}
