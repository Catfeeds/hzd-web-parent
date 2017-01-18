package com.hzcf.platform.mgr.sys.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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
	public boolean CheckLogin(String username,String password) {
		boolean login = false;
		Result<SysUsersVO> sysUsersVO = sysUsersSerivce.getBySysUsersName(username);
		SysUsersVO sysUsers = sysUsersVO.getItems();
		if (sysUsers != null){
			String passwordDB = sysUsersVO.getItems().getPassword();
			if (passwordDB.equals(password)){
				login = true;
			}
		} else {
			login = false;
		}
		return login;
	}
	/**后台用户修改登录密码
	 * 
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRED) 
	public Map<String,Object> updatePassword(String username, String password, String passwordNew) {
		/**初始化参数，设置返回结果*/
		Map<String,Object> result=new HashMap<String,Object>();
		Result<SysUsersVO> sysUsersVO = sysUsersSerivce.getBySysUsersName(username);
		SysUsersVO sysUsers=sysUsersVO.getItems();
		if(sysUsers==null){
			result.put("code","-1");
			result.put("message","未找到用户信息");
		}else{
			if(password.equals(sysUsers.getPassword())){
				SysUsersVO params=new SysUsersVO();
				params.setUserName(username);
				params.setPassword(passwordNew);
				Result<Integer> updateresult=sysUsersSerivce.updateByUserNameSelective(params);
				if(updateresult.getItems()==1){
					result.put("code","1");
					result.put("message","修改用户密码成功");		
				}else{
					result.put("code","-3");
					result.put("message","修改用户密码失败");
				}
			}else{
				result.put("code","-2");
				result.put("message","用户原始密码输入错误");
			}
		}
		return result;
	}
	
	
	
	
	
	
	
	
	
	
	
}