package com.hzcf.platform.core.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hzcf.platform.core.sys.dao.RoleDao;
import com.hzcf.platform.core.sys.dao.UserDao;
import com.hzcf.platform.core.sys.data.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.sys.data.User;
import com.hzcf.platform.core.sys.model.RoleVO;
import com.hzcf.platform.core.sys.service.RoleService;
import com.hzcf.platform.framework.core.service.impl.CommonBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.StorageProvider;
@Service
public class RoleServiceImpl extends CommonBaseServiceImpl<RoleVO, Role>implements RoleService {

	private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	@Autowired
	private RoleDao roleDao;

	@Autowired
	private UserDao userDao;
	
	
	@Override
	protected StorageProvider<Role> getGenericDAO() {
		// TODO Auto-generated method stub
		return roleDao;
	}

	@Override
	public Result<Long> create(RoleVO m) {
		// TODO Auto-generated method stub
		if(m==null){
			return null;
		}
		Result<Long> result=new Result<Long>();
        if(roleDao.getListByName(m.getRoleName()).size()>0){
        	logger.error("an error occured in sys create service user LoginName is exist ");
			result.setItems(0L);
			result.setStatus(StatusCodes.SYS_EXIST_ROLE_NAME);
			result.setMsg("添加失败，角色名称:"+m.getRoleName()+"已存在，不能重复!");
			return result;
        }
		
		return super.create(m);
	}

	@Override
	public Result<List<RoleVO>> getCollecion() {
		// TODO Auto-generated method stub
		List<RoleVO> resultList = new ArrayList<RoleVO>();
		int status = StatusCodes.OK;
		try {
			List<Role> list = roleDao.getCollecion();
			if (list != null && list.size() > 0) {
				for (Role s : list) {
					resultList.add(toVO(s));
				}
			} else {
				status = StatusCodes.NOT_FOUND;
			}
		} catch (Exception e) {
			logger.error("an error occur in role getCollection service, {} ", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<List<RoleVO>>(status, resultList);
	}

	@Override
	public Result getRoleByID(String id) {
		// TODO Auto-generated method stub
		RoleVO roleVO = new RoleVO();
		int status = StatusCodes.OK;
		try {
			Role role = roleDao.getRoleByID(id);
			if (null == role) {
				status = StatusCodes.NOT_FOUND;
			}
			roleVO = toVO(role);
		} catch (Exception e) {
			logger.error("an error occur in getRoleByID service : {}", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<RoleVO>(status, roleVO);
	}

	@Override
	protected RoleVO getModel() {
		// TODO Auto-generated method stub
		return new RoleVO();
	}

	@Override
	protected Role getEntity() {
		// TODO Auto-generated method stub
		return new Role();
	}

	

	@Override
	public Result<Boolean> deleteById(Integer id) {
		// TODO Auto-generated method stub
		try {
			/*this.roleDao.delete(id);*/
			Role role = roleDao.getRoleByID(id.toString());
			role.setLogicalDel(1);//删除标识
			roleDao.update(role);
			return new Result<Boolean>(StatusCodes.OK, true);
		} catch (Exception e) {
			logger.error("an error occur in delete service : {}", e);
			return new Result<Boolean>(StatusCodes.INTERNAL_SERVER_ERROR, false);
		}
	}

	@Override
	public PaginatedResult<RoleVO> flipPage(Map<String, Object> param, int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		try {
			PaginatedResult<RoleVO> result = new PaginatedResult<RoleVO>();
			param.put("logicalDel","0");
			PaginatedResult<Role> resultDO = this.roleDao.flipPage(param,pageSize, pageNo);
			if (null != resultDO && resultDO.getItems().size() > 0) {
				for (Role role : resultDO.getItems()) {
					RoleVO vo=toVO(role);
					if(role.getCreateBy()!=null){
					 User user=userDao.getUserByID(role.getCreateBy().toString());
					 vo.setCreateName(user.getLoginName());
					}
					result.addItem(vo);
				}
			}
			result.setPaginate(resultDO.getPaginate());
			result.setStatus(StatusCodes.OK);
			return result;
		} catch (Exception e) {
			logger.error("an error occur in Role findPage service : {}", e);
			return new PaginatedResult<RoleVO>(StatusCodes.INTERNAL_SERVER_ERROR, pageSize, pageNo, 0);
		}
	}

	@Override
	public Result<List<RoleVO>> getCollecionByUserId(Long userId) {
		// TODO Auto-generated method stub
		// TODO Auto-generated method stub
				List<RoleVO> resultList = new ArrayList<RoleVO>();
				int status = StatusCodes.OK;
				try {
					List<Role> list = roleDao.getCollecionByUserId(userId);
					if (list != null && list.size() > 0) {
						for (Role s : list) {
							resultList.add(toVO(s));
						}
					} else {
						status = StatusCodes.NOT_FOUND;
					}
				} catch (Exception e) {
					logger.error("an error occur in role getCollection service, {} ", e);
					status = StatusCodes.INTERNAL_SERVER_ERROR;
				}
				return new Result<List<RoleVO>>(status, resultList);
	}

}
