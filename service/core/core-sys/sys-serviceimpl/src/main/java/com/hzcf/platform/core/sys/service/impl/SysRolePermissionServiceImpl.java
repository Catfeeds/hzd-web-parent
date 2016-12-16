package com.hzcf.platform.core.sys.service.impl;

import java.util.ArrayList;

import java.util.List;
import java.util.Map;

import com.hzcf.platform.core.sys.dao.SysRolePermissionDao;
import com.hzcf.platform.core.sys.data.SysRolePermission;
import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.IBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;

import com.hzcf.platform.core.sys.model.PermissionVO;
import com.hzcf.platform.core.sys.model.RoleVO;
import com.hzcf.platform.core.sys.model.SysRolePermissionVO;
import com.hzcf.platform.core.sys.service.PermissionService;
import com.hzcf.platform.core.sys.service.RoleService;
import com.hzcf.platform.core.sys.service.SysRolePermissionService;


@Service
public class SysRolePermissionServiceImpl extends AbstractBaseServiceImpl<SysRolePermissionVO, SysRolePermission>
		implements SysRolePermissionService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SysRolePermissionDao sysRolePermissionDao;

	@Autowired
	private RoleService roleService;
	
	@Autowired
	private PermissionService permissionService;
	
	@Override
	protected SysRolePermissionVO getModel() {
		// TODO Auto-generated method stub
		return new SysRolePermissionVO();
	}

	@Override
	protected SysRolePermission getEntity() {
		// TODO Auto-generated method stub
		return new SysRolePermission();
	}

	@Override
	protected IBaseDao<SysRolePermission> getGenericDAO() {
		// TODO Auto-generated method stub
		return sysRolePermissionDao;
	}

	@Override
	public Result<List<SysRolePermissionVO>> getSysRolePermissionByRoleId(Long roleId) {
		// TODO Auto-generated method stub
		List<SysRolePermissionVO> resultList = new ArrayList<SysRolePermissionVO>();
		int status = StatusCodes.OK;
		try {
			List<SysRolePermission> list = sysRolePermissionDao.getSysRolePermissionByRoleId(roleId);
			if (list != null && list.size() > 0) {
				for (SysRolePermission rp : list) {
					resultList.add(toVO(rp));
				}
			} else {
				status = StatusCodes.NOT_FOUND;
			}
		} catch (Exception e) {
			logger.error("an error occur in SysRolePermission getSysRolePermissionByRoleId service, {} ", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<List<SysRolePermissionVO>>(status, resultList);
	}

	@Override
	public PaginatedResult<SysRolePermissionVO> flipPage(Map<String, Object> param, int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		try {
			PaginatedResult<SysRolePermissionVO> result = new PaginatedResult<SysRolePermissionVO>();
			
			PaginatedResult<SysRolePermission> resultDO = this.sysRolePermissionDao.flipPage(param,pageSize, pageNo);
			if (null != resultDO && resultDO.getItems().size() > 0) {
				for (SysRolePermission rp : resultDO.getItems()) {
					SysRolePermissionVO vo=toVO(rp);
					if(vo.getRoleId()!=null){
						Result<RoleVO> role=this.roleService.getRoleByID(vo.getRoleId().toString());
						vo.setRoleName(role.getItems().getRoleName());
					}
					
					if (vo.getPermissionId() != null) {
						Result<PermissionVO> permission = this.permissionService
								.getPermissionById(vo.getPermissionId());
						vo.setPermissionName(permission.getItems().getName());
						vo.setMenu(permission.getItems().getHref_url());
						vo.setPermission(permission.getItems().getPermission());

					}
					result.addItem(vo);
				}
			}
			result.setPaginate(resultDO.getPaginate());
			result.setStatus(StatusCodes.OK);
			return result;
		} catch (Exception e) {
			logger.error("an error occur in SysRolePermission findPage service : {}", e);
			return new PaginatedResult<SysRolePermissionVO>(StatusCodes.INTERNAL_SERVER_ERROR, pageSize, pageNo, 0);
		}
	}

	@Override
	public Result<Boolean> add(String ids,String roleId) {
		// TODO Auto-generated method stub
		String[] id=ids.split(",");	
		for(int i=0;i<id.length;i++){
			SysRolePermission entity=new SysRolePermission();
			entity.setPermissionId(new Long(id[i]));
			entity.setRoleId(new Long(roleId));
			this.sysRolePermissionDao.create(entity);
		}
		return new Result<Boolean>(StatusCodes.OK, true);
	}

	@Override
	public Result<Boolean> delete(Long id) {
		// TODO Auto-generated method stub
		try {
			this.sysRolePermissionDao.delete(id);
			return new Result<Boolean>(StatusCodes.OK, true);
		} catch (Exception e) {
			logger.error("an error occur in delete service : {}", e);
			return new Result<Boolean>(StatusCodes.INTERNAL_SERVER_ERROR, false);
		}
	}


}
