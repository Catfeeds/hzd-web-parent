package com.hzcf.platform.core.sys.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.hzcf.platform.core.sys.dao.PermissionDao;
import com.hzcf.platform.core.sys.data.Permission;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.sys.model.PermissionVO;
import com.hzcf.platform.core.sys.model.RoleVO;
import com.hzcf.platform.core.sys.service.PermissionService;
import com.hzcf.platform.framework.core.service.impl.CommonBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.StorageProvider;
@Service
public class PermissionServiceImpl extends CommonBaseServiceImpl<PermissionVO, Permission>implements PermissionService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private PermissionDao permissionDao;
	
	@Override
	public Result<Long> create(PermissionVO m) {
		// TODO Auto-generated method stub
		
		Permission parent =permissionDao.getPermissionByID(m.getParentId().toString());
		m.setParentIds(parent.getParentIds()+","+m.getParentId());
		m.setLogicalDel(0);
		
		return super.create(m);
	}

	public Result<Boolean> deleteById(Long id){
		// TODO Auto-generated method stub	
		Permission vo = permissionDao.getPermissionByID(id.toString());
        vo.setLogicalDel(1);//删除标识
        permissionDao.update(vo);
		return new Result<Boolean>(StatusCodes.OK, true);
		
	}

	@Override
	public Result<Boolean> update(PermissionVO m) {
		// TODO Auto-generated method stub
		Permission parent =permissionDao.getPermissionByID(m.getParentId().toString());
		m.setParentIds(parent.getParentIds()+","+m.getParentId());
		
		return super.update(m);
	}

	@Override
	protected PermissionVO getModel() {
		// TODO Auto-generated method stub
		return new PermissionVO();
	}

	@Override
	protected Permission getEntity() {
		// TODO Auto-generated method stub
		return new Permission();
	}

	@Override
	protected StorageProvider<Permission> getGenericDAO() {
		// TODO Auto-generated method stub
		return permissionDao;
	}

	@Override
	public Result<List<PermissionVO>> getCollecion() {
		// TODO Auto-generated method stub
		List<PermissionVO> resultList = new ArrayList<PermissionVO>();
		int status = StatusCodes.OK;
		try {
			List<Permission> list = permissionDao.getCollecion();
			if (list != null && list.size() > 0) {
				for (Permission s : list) {
					resultList.add(toVO(s));
				}
			} else {
				status = StatusCodes.NOT_FOUND;
			}
		} catch (Exception e) {
			logger.error("an error occur in Permission getCollection service, {} ", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<List<PermissionVO>>(status, resultList);
	}


	@Override
	public PaginatedResult<PermissionVO> queryListByPaginate(Map<String, Object> param, int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		try {
			PaginatedResult<PermissionVO> result = new PaginatedResult<PermissionVO>();

			PaginatedResult<Permission> resultDO = permissionDao.flipPage(param, pageSize, pageNo);
			if (null != resultDO && resultDO.getItems().size() > 0) {
				for (Permission Permission : resultDO.getItems()) {
					PermissionVO PermissionVO = toVO(Permission);

					result.addItem(PermissionVO);
				}
			}
			result.setPaginate(resultDO.getPaginate());
			result.setStatus(StatusCodes.OK);
			return result;
		} catch (Exception e) {
			logger.error("an error occur in findList service : {}", e);
			return new PaginatedResult<PermissionVO>(StatusCodes.INTERNAL_SERVER_ERROR, pageSize, pageNo, 0);
		}
	}

	@Override
	public Result<List<PermissionVO>> getCollecionByRoleId(Integer role_id) {
		// TODO Auto-generated method stub
		List<PermissionVO> resultList = new ArrayList<PermissionVO>();
		int status = StatusCodes.OK;
		try {
			List<Permission> list = permissionDao.getCollecion(role_id);
			if (list != null && list.size() > 0) {
				for (Permission s : list) {
					resultList.add(toVO(s));
				}
			} else {
				status = StatusCodes.NOT_FOUND;
			}
		} catch (Exception e) {
			logger.error("an error occur in Permission getCollection service, {} ", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<List<PermissionVO>>(status, resultList);
	}

	@Override
	public Result<List<PermissionVO>> getPermissionCollecionByUserId(Integer user_id) {
		// TODO Auto-generated method stub
		List<PermissionVO> resultList = new ArrayList<PermissionVO>();
		int status = StatusCodes.OK;
		try {
			List<Permission> list = permissionDao.getPermissionCollecion(user_id);
			if (list != null && list.size() > 0) {
				for (Permission s : list) {
					resultList.add(toVO(s));
				}
			} else {
				status = StatusCodes.NOT_FOUND;
			}
		} catch (Exception e) {
			logger.error("an error occur in Permission getCollection service, {} ", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<List<PermissionVO>>(status, resultList);
	}
/*
	@Override
	public Set<String> getPermissionListByUserId(Integer user_id) {
		// TODO Auto-generated method stub
		
		Set<String> resourceIds = new HashSet<String>();
		List<Permission> list = permissionDao.getPermissionCollecion(user_id);
		if (list != null && list.size() > 0) {
			for (Permission s : list) {
				if (StringUtils.isNotBlank(s.getPermission())) {
					resourceIds.add(s.getPermission());
				}
			}
		} else {
			 return Collections.EMPTY_SET;
		}
		
		return resourceIds;
	}

	@Override
	public Set<String> getPermissionListByUserCode(String userCode) {
		// TODO Auto-generated method stub
		Set<String> resourceIds = new HashSet<String>();
		List<Permission> list = permissionDao.getPermissionListByUserCode(userCode);
		if (list != null && list.size() > 0) {
			for (Permission s : list) {
				if (StringUtils.isNotBlank(s.getPermission())) {
					resourceIds.add(s.getPermission());
				}
			}
		} else {
			 return Collections.EMPTY_SET;
		}
		
		return resourceIds;
	}
*/
	@Override
	public List<String> getMenuListByUserId(Integer user_id) {
		// TODO Auto-generated method stub
		return permissionDao.getMenuByUserId(user_id);
	}
  
	@Override
	public List<String> getPermissionListByUserId(Integer user_id) {
		// TODO Auto-generated method stub
		return permissionDao.getPermissionByUserId(user_id);
	}

	@Override
	public Result<PermissionVO> getPermissionById(Long id) {
		// TODO Auto-generated method stub
		PermissionVO permissionVO = new PermissionVO();
		int status = StatusCodes.OK;
		try {
			Permission permission = permissionDao.getPermissionByID(id.toString());
			if (null == permission) {
				status = StatusCodes.NOT_FOUND;
			}
			permissionVO = toVO(permission);
		} catch (Exception e) {
			logger.error("an error occur in getRoleByID service : {}", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<PermissionVO>(status, permissionVO);
  
	}

	@Override
	public PaginatedResult<PermissionVO> flipPage(Map<String, Object> param, int pageSize, int pageNo) {
		// TODO Auto-generated method stub
				try {
					PaginatedResult<PermissionVO> result = new PaginatedResult<PermissionVO>();
					
					PaginatedResult<Permission> resultDO = this.permissionDao.flipPage(param,pageSize, pageNo);
					if (null != resultDO && resultDO.getItems().size() > 0) {
						for (Permission permission : resultDO.getItems()) {
							PermissionVO vo=toVO(permission);
							
							result.addItem(vo);
						}
					}
					result.setPaginate(resultDO.getPaginate());
					result.setStatus(StatusCodes.OK);
					return result;
				} catch (Exception e) {
					logger.error("an error occur in Permission findPage service : {}", e);
					return new PaginatedResult<PermissionVO>(StatusCodes.INTERNAL_SERVER_ERROR, pageSize, pageNo, 0);
				}
	}

	@Override
	public PaginatedResult<PermissionVO> queryForRoleNotExistflipPage(Map<String, Object> param, int pageSize,
			int pageNo) {
		// TODO Auto-generated method stub
		try {
			PaginatedResult<PermissionVO> result = new PaginatedResult<PermissionVO>();
			
			PaginatedResult<Permission> resultDO = this.permissionDao.queryForRoleNotExistflipPage(param,pageSize, pageNo);
			if (null != resultDO && resultDO.getItems().size() > 0) {
				for (Permission permission : resultDO.getItems()) {
					PermissionVO vo=toVO(permission);
					
					result.addItem(vo);
				}
			}
			result.setPaginate(resultDO.getPaginate());
			result.setStatus(StatusCodes.OK);
			return result;
		} catch (Exception e) {
			logger.error("an error occur in Permission findPage service : {}", e);
			return new PaginatedResult<PermissionVO>(StatusCodes.INTERNAL_SERVER_ERROR, pageSize, pageNo, 0);
		}
	}

}
