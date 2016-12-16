package com.hzcf.platform.core.sys.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hzcf.platform.framework.core.service.impl.AbstractBaseServiceImpl;
import com.hzcf.platform.framework.core.storage.IBaseDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.sys.dao.MetaElementDao;
import com.hzcf.platform.core.sys.data.MetaElement;
import com.hzcf.platform.core.sys.model.MetaElementVO;
import com.hzcf.platform.core.sys.service.MetaElementService;


@Service
public class MetaElementServiceImpl extends AbstractBaseServiceImpl<MetaElementVO, MetaElement>
		implements MetaElementService {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private MetaElementDao metaElementDao;

	@Override
	protected MetaElementVO getModel() {
		// TODO Auto-generated method stub
		return new MetaElementVO();
	}

	@Override
	protected MetaElement getEntity() {
		// TODO Auto-generated method stub
		return new MetaElement();
	}

	@Override
	protected IBaseDao<MetaElement> getGenericDAO() {
		// TODO Auto-generated method stub
		return metaElementDao;
	}

	@Override
	public PaginatedResult<MetaElementVO> queryListByPaginate(String data_name,String dataCode, int pageSize, int pageNo) {
		// TODO Auto-generated method stub
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("logicalDel", "0");
		param.put("dataName", data_name);
		param.put("dataCode", dataCode);
		return this.querylistByPaginate(param, pageSize, pageNo);
	}

	private PaginatedResult<MetaElementVO> querylistByPaginate(Map<String, Object> param, int pageSize, int pageNo) {
		try {
			PaginatedResult<MetaElementVO> result = new PaginatedResult<MetaElementVO>();

			PaginatedResult<MetaElement> resultDO = metaElementDao.flipPage(param, pageSize, pageNo);
			if (null != resultDO && resultDO.getItems().size() > 0) {
				for (MetaElement metaElement : resultDO.getItems()) {
					MetaElementVO metaElementVO = toVO(metaElement);

					result.addItem(metaElementVO);
				}
			}
			result.setPaginate(resultDO.getPaginate());
			result.setStatus(StatusCodes.OK);
			return result;
		} catch (Exception e) {
			logger.error("an error occur in findList service : {}", e);
			return new PaginatedResult<MetaElementVO>(StatusCodes.INTERNAL_SERVER_ERROR, pageSize, pageNo, 0);
		}
	}

	@Override
	public Result<List<MetaElementVO>> getMetaElementVOByDataName(String dataName) {
		// TODO Auto-generated method stub
		return getMetaElementVOByListByParam(dataName, null);
	}

	public Result<List<MetaElementVO>> getMetaElementVOByListByParam(String dataName, String dataCode) {
		// TODO Auto-generated method stub
		List<MetaElementVO> resultList = new ArrayList<MetaElementVO>();
		int status = StatusCodes.OK;
		try {
			List<MetaElement> list = metaElementDao.getMetaElementByParam(dataName, dataCode);
			if (list != null && list.size() > 0) {
				for (MetaElement s : list) {
					s.setLogicalDel(null);
					resultList.add(toVO(s));
				}
			} else {
				status = StatusCodes.NOT_FOUND;
			}
		} catch (Exception e) {
			logger.error("an error occur in MetaElement getCollection service, {} ", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<List<MetaElementVO>>(status, resultList);
	}

	@Override
	public Result<List<MetaElementVO>> getMetaElementVOByDataCode(String dataCode) {
		// TODO Auto-generated method stub
		return getMetaElementVOByListByParam(null, dataCode);
	}

	@Override
	public Result<MetaElementVO> getMetaElementByID(String id) {
		// TODO Auto-generated method stub
		MetaElementVO metaVO = new MetaElementVO();
		int status = StatusCodes.OK;
		try {
			MetaElement meta = metaElementDao.getMetaElementByID(id);
			if (null == meta) {
				status = StatusCodes.NOT_FOUND;
			}
			metaVO = toVO(meta);
		} catch (Exception e) {
			logger.error("an error occur in getMetaElementByID service : {}", e);
			status = StatusCodes.INTERNAL_SERVER_ERROR;
		}
		return new Result<MetaElementVO>(status, metaVO);
	}

	@Override
	public Result<Boolean> delete(Long id) {
		// TODO Auto-generated method stub
		try {
			this.metaElementDao.delete(id);
			return new Result<Boolean>(StatusCodes.OK, true);
		} catch (Exception e) {
			logger.error("an error occur in delete service : {}", e);
			return new Result<Boolean>(StatusCodes.INTERNAL_SERVER_ERROR, false);
		}
	}
    
}
