/**
 * 
 */
package com.hzcf.platform.core.sys.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.IBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.sys.dao.AreaDao;
import com.hzcf.platform.core.sys.data.Area;
import com.hzcf.platform.core.sys.model.AreaVO;
import com.hzcf.platform.core.sys.service.AreaService;
import com.google.common.base.Strings;

/**
 * @author allen.shen
 * @Date 2015年10月16日
 * 
 * description: 
 */
@Service
public class AreaServiceImpl extends AbstractBaseServiceImpl<AreaVO, Area> implements AreaService {
	
	private Logger logger = LoggerFactory.getLogger(AreaServiceImpl.class);
	
	@Autowired
	private AreaDao areaDao;

	@Override
	public Result<List<AreaVO>> getAreaInfoByParams(String params) {

		int status = StatusCodes.OK;
		Result<List<AreaVO>> result = new Result<>(status, null);
		if (Strings.isNullOrEmpty(params)) {
			result.setStatus(StatusCodes.INVALID_PARAMETER);
			return result;
		}
		
		try {
			List<Area> areaInfoList = null;
			
			if (params.equals("province")) {
				areaInfoList = areaDao.getAreaInfoByProvince();
			}
			else if (params.equals("city")) {
				areaInfoList = areaDao.getAreaInfoByCity();
			}
			else {}
			if (null == areaInfoList) {
				logger.debug("areaInfoList data not found.");
				return new Result<List<AreaVO>>(StatusCodes.NOT_FOUND, null);
			}
			List<AreaVO> areaVOList = new ArrayList<AreaVO>();
			for (Area areaInfo : areaInfoList) {
				areaVOList.add(toVO(areaInfo));
			}
			logger.debug("areaInfoVOList={} areaInfoList={}", areaVOList, areaInfoList);
			result.setItems(areaVOList);
			
		} catch (Exception e) {
			status = StatusCodes.INTERNAL_SERVER_ERROR;
			logger.error("an error occured in getAreaInfoByCity service as, {}", e);
		}
		result.setStatus(status);
		return result;
	}

	@Override
	public Result<List<AreaVO>> getAreaInfoByParentCode(String parentCode) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected AreaVO getModel() {
		return new AreaVO();
	}

	@Override
	protected Area getEntity() {
		return new Area();
	}

	@Override
	protected IBaseDao<Area> getGenericDAO() {
		return areaDao;
	}

	

}
