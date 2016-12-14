package com.exiao.platform.core.sys.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exiao.platform.common.util.rpc.result.PaginatedResult;
import com.exiao.platform.common.util.rpc.result.Result;
import com.exiao.platform.common.util.status.StatusCodes;
import com.exiao.platform.core.sys.dao.SysUserRoleDao;
import com.exiao.platform.core.sys.dao.UserDao;
import com.exiao.platform.core.sys.data.SysUserRole;
import com.exiao.platform.core.sys.data.User;
import com.exiao.platform.core.sys.model.ChangePwdVO;
import com.exiao.platform.core.sys.model.DepartmentVO;
import com.exiao.platform.core.sys.model.RoleVO;
import com.exiao.platform.core.sys.model.UserVO;
import com.exiao.platform.core.sys.service.DepartmentService;
import com.exiao.platform.core.sys.service.RoleService;
import com.exiao.platform.core.sys.service.UserService;
import com.exiao.platform.core.sys.util.Md5HashUtil;
import com.exiao.platform.framework.core.service.impl.CommonBaseServiceImpl;
import com.exiao.platform.framework.core.storage.StorageProvider;


@Service
public class UserServiceImpl extends CommonBaseServiceImpl<UserVO, User> implements UserService{

	
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private SysUserRoleDao sysUserRoleDao;
	
	
	
	@Autowired
	private RoleService roleService;
	
	@Autowired
	private DepartmentService departmentService;
	

	@Override
	protected UserVO getModel() {
		// TODO Auto-generated method stub
		return new UserVO();
	}

	@Override
	protected User getEntity() {
		// TODO Auto-generated method stub
		return new User();
	}

	@Override
	protected StorageProvider<User> getGenericDAO() {
		// TODO Auto-generated method stub
		return userDao;
	}

	@Override
	public Result<Long> create(UserVO m) {
		// TODO Auto-generated method stub
		m.setLocked(0);
		Result<Long> result=new Result<Long>();
        if(userDao.getUserByLoginName(m.getLoginName())!=null){
        	logger.error("an error occured in sys create service user LoginName is exist ");
			result.setItems(0L);
			result.setStatus(StatusCodes.SYS_EXIST_USER_CODE);
			result.setMsg("添加失败，用户编号:"+m.getLoginName()+"已存在，不能重复!");
			return result;
        }

		result= super.create(m);
    	logger.info("##########user save result :"+result.toString());

		if(m.getRoleID()!=null){
			SysUserRole entity = new SysUserRole();
			//entity.setUserId(m.getId());
			entity.setUserId(result.getItems());
			entity.setRoleId(m.getRoleID().longValue());
			sysUserRoleDao.create(entity);
			Result<RoleVO> rolevo = roleService.getRoleByID(m.getRoleID().toString());
			if (rolevo != null) {
				//岗位人员计数+1
				RoleVO role = rolevo.getItems();
				Integer personNum=role.getPersonNum();
				if(personNum!=null){
				    role.setPersonNum(personNum+1);
				}else{
					role.setPersonNum(1);
				}
				
				roleService.update(role);
			}
			
		}
		return result;
	}

	@Override
	public Result<Boolean> update(UserVO m) {
		// TODO Auto-generated method stub
		if(m.getRoleID()!=null){
			SysUserRole entity = sysUserRoleDao.getByUserId(m.getId().longValue());
			entity.setRoleId(m.getRoleID().longValue());
			sysUserRoleDao.update(entity);
			
			Result<RoleVO> oldrolevo = roleService.getRoleByID(entity.getRoleId().toString());
			Result<RoleVO> newrolevo = roleService.getRoleByID(m.getRoleID().toString());

			if (oldrolevo != null&&newrolevo!=null&&(!oldrolevo.getItems().getId().equals(newrolevo.getItems().getId()))) {
				//岗位人员计数-1
				RoleVO oldrole = oldrolevo.getItems();
				Integer old_personNum=oldrole.getPersonNum();
				oldrole.setPersonNum(old_personNum-1);
				roleService.update(oldrole);
				//岗位人员计数+1
				RoleVO newrole = oldrolevo.getItems();
				Integer new_personNum=newrole.getPersonNum();
				newrole.setPersonNum(new_personNum+1);
				roleService.update(newrole);
			}
			
		}
		return super.update(m);
	}

	@Override
	public Result<List<UserVO>> getCollecion() {
		// TODO Auto-generated method stub
		List<UserVO> resultList = new ArrayList<UserVO>();
		int status = StatusCodes.OK;
		try {
			List<User> list = userDao.getCollecion();
			if (list != null && list.size() > 0) {
				for (User u : list) {
					resultList.add(toVO(u));
				}
			} else {
				status = StatusCodes.NOT_FOUND;
			}
		} catch (Exception e) {
			logger.error("an error occur in user getCollection service, {} ", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<List<UserVO>>(status, resultList);
	}

	@Override
	public Result getUserByID(String id) {
		// TODO Auto-generated method stub
		UserVO userVO = new UserVO();
		int status = StatusCodes.OK;
		try {
			User user = userDao.getUserByID(id);
			if (null == user) {
				status = StatusCodes.NOT_FOUND;
			}
			
			userVO = toVO(user);
			if(user.getRoleID()!=null){
			Result<RoleVO> rolevo=roleService.getRoleByID(user.getRoleID().toString());
			logger.info("roleName is:"+rolevo.getItems().getRoleName());
			userVO.setRoleName(rolevo.getItems().getRoleName());
			}
			if(user.getDepartmentId()!=null){
			Result<DepartmentVO> deptvo=departmentService.getDepartmentByID(new Long(user.getDepartmentId()));
			logger.info("deptName is:"+deptvo.getItems().getDeptName());
			userVO.setDeptName(deptvo.getItems().getDeptName());
			}
			

		} catch (Exception e) {
			logger.error("an error occur in getUserByID service : {}", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<UserVO>(status, userVO);
	}

	@Override
	public PaginatedResult<UserVO> flipPage(Map<String, Object> param, int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		try {
			PaginatedResult<UserVO> result = new PaginatedResult<UserVO>();
			//param.put("logicalDel","0");
			PaginatedResult<User> resultDO = this.userDao.flipPage(param,pageSize, pageNo);
			if (null != resultDO && resultDO.getItems().size() > 0) {
				for (User user : resultDO.getItems()) {

					UserVO userVO = new UserVO();
					userVO = toVO(user);
					if (user.getDepartmentId() != null) {
						Result<DepartmentVO> deptvo = departmentService
								.getDepartmentByID(new Long(user.getDepartmentId()));
						userVO.setDeptName(deptvo.getItems().getDeptName());
					}
					if (user.getRoleID() != null) {
						Result<RoleVO> rolevo = roleService.getRoleByID(user.getRoleID().toString());
						userVO.setRoleName(rolevo.getItems().getRoleName());
					}
					result.addItem(userVO);
					//result.addItem(toVO(user));
				}
			}
			result.setPaginate(resultDO.getPaginate());
			result.setStatus(StatusCodes.OK);
			return result;
		} catch (Exception e) {
			logger.error("an error occur in User flipPage service : {}", e);
			return new PaginatedResult<UserVO>(StatusCodes.INTERNAL_SERVER_ERROR, pageSize, pageNo, 0);
		}
	}

	@Override
	public Result getUserByLoginName(String loginName) {
		// TODO Auto-generated method stub
		UserVO userVO = new UserVO();
		int status = StatusCodes.OK;
		try {
			User user = userDao.getUserByLoginName(loginName);
			if (null == user) {
				status = StatusCodes.NOT_FOUND;
			}
			userVO = toVO(user);
		} catch (Exception e) {
			logger.error("an error occur in getUserByLoginName service : {}", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<UserVO>(status, userVO);
	}

	@Override
	public  Result<Boolean> changePsw(ChangePwdVO pswVO) {
		// TODO Auto-generated method stub
		int status = StatusCodes.OK;
 
		User user = userDao.getUserByID(pswVO.getId().toString());
		if(user==null){
			status = StatusCodes.NOT_FOUND;
			return new Result<Boolean>(StatusCodes.OK, false);
		}
		String salt=user.getSalt();
		String oriPsw=Md5HashUtil.entryptPasswordByMd5(pswVO.getOriPsd(), salt);
        if(!oriPsw.equals(user.getPassword())){
        	 status = StatusCodes.SYS_INCONSISTENT_PASSWORD;
        	 return new Result<Boolean>(StatusCodes.OK, false);
        }
		String newPsw=Md5HashUtil.entryptPasswordByMd5(pswVO.getNewPsd(), salt);
		user.setPassword(newPsw);
		userDao.updateUserPsw(user);
		return new Result<Boolean>(StatusCodes.OK, true);
	}

	@Override
	public Result<Boolean> deleteById(Long id) {
		// TODO Auto-generated method stub
		User user = userDao.getUserByID(id.toString());
		Result<RoleVO> rolevo = roleService.getRoleByID(user.getRoleID().toString());
		if (rolevo != null) {
			//岗位人员计数-1
			RoleVO role = rolevo.getItems();
			Integer old_personNum=role.getPersonNum();
			role.setPersonNum(old_personNum-1);
			roleService.update(role);
		}
        user.setLogicalDel(1);//删除标识
        userDao.updateUserPsw(user);
		return new Result<Boolean>(StatusCodes.OK, true);
	}

	@Override
	public Result<Boolean> changeUserPsw(ChangePwdVO pswVO) {
		// TODO Auto-generated method stub
		int status = StatusCodes.OK;
		 
		User user = userDao.getUserByID(pswVO.getId().toString());
		if(user==null){
			status = StatusCodes.NOT_FOUND;
			return new Result<Boolean>(StatusCodes.OK, false);
		}
		String salt=user.getSalt();
		
		String newPsw=Md5HashUtil.entryptPasswordByMd5(pswVO.getNewPsd(), salt);
		user.setPassword(newPsw);
		userDao.updateUserPsw(user);
		return new Result<Boolean>(StatusCodes.OK, true);
	}

	@Override
	public Result<List<UserVO>> getUserListByIds(Long... ids) {
		// TODO Auto-generated method stub
		List<UserVO> resultList = new ArrayList<UserVO>();
		int status = StatusCodes.OK;
		try {
			List<User> list = userDao.getUserListByIds(ids);
			if (list != null && list.size() > 0) {
				for (User u : list) {
					resultList.add(toVO(u));
				}
			} else {
				status = StatusCodes.NOT_FOUND;
			}
		} catch (Exception e) {
			logger.error("an error occur in user getUserListByIds service, {} ", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<List<UserVO>>(status, resultList);
	}

	@Override
	public Result<Boolean> updateUserByID(String id, boolean flag) {
		// TODO Auto-generated method stub
		int status = StatusCodes.OK;
		 
		User user = userDao.getUserByID(id);
		if(user==null){
			status = StatusCodes.NOT_FOUND;
			return new Result<Boolean>(StatusCodes.OK, false);
		}
		if(flag==true){
			user.setLocked(new Integer(1));
		}else{
			user.setLocked(new Integer(0));
		}
		userDao.updateUserPsw(user);
		return new Result<Boolean>(StatusCodes.OK, true);
	}

}
