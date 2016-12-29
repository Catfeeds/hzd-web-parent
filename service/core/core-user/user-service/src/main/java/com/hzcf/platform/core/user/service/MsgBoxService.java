package com.hzcf.platform.core.user.service;

import java.util.Map;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.core.user.model.MsgBoxVO;
import com.hzcf.platform.framework.core.service.IBaseService;

/**
 * 
 * @description:msgBox功能操作
 * @author 李强
 * @version 1.0
 * 
 * <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月29日                             李强                             1.0         1.0 Version 
 * </pre>
 */
public interface MsgBoxService  extends IBaseService<MsgBoxVO> {
	
	public PaginatedResult<MsgBoxVO> getMsgBoxPage(Map<String, Object> parmMap);
}
