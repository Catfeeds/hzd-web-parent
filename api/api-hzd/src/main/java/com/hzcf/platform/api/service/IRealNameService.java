package com.hzcf.platform.api.service;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.core.user.model.UserVO;
import javax.servlet.http.HttpServletRequest;
import com.hzcf.platform.core.user.model.UserImageVO;

/**
  * @Description:实名认证的service
  * @author 作者:cpp E-mail:lettger@163.com Tel:18612036574
  * @date 创建时间：2016年12月29日 下午3:46:43 
  * @version 1.0 
  * @since  JDK1.8
  */
public interface IRealNameService {
	//查询实名认证状态，信息
	public BackResult selectRealName(UserVO user);
	//保存实名认证信息
	public BackResult saveRealName(UserVO user);
	//上传实名认证的图片
	public BackResult saveRealNamePic(HttpServletRequest request, UserVO user, UserImageVO userImageVO,String applyId);
}