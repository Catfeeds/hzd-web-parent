package com.hzcf.platform.mgr.sys.service.impl;

import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.user.model.UserApplyLogVO;
import com.hzcf.platform.core.user.service.UserApplyLogService;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by lll on 2017-04-10.
 */
@Service
public class UserApplyLogServiceImpl {

    private Logger logger = LoggerFactory.getLogger(UserApplyLogServiceImpl.class);

    @Autowired
    private UserApplyLogService queryUserApplyLog;

    public DataGrid queryUserApplyLog(PageHelper pageHelper, UserApplyLogVO userApplyLogVO) {
        pageHelper.setStart((pageHelper.getPage()-1)*pageHelper.getRows());
        pageHelper.setEnd(pageHelper.getRows());
        Map<String, Object> parmMap = new HashMap();
        parmMap.put("userApplyLog", userApplyLogVO);
        parmMap.put("page", pageHelper);

        DataGrid dataGrid = new DataGrid();
        Result<List<UserApplyLogVO>> listResult = queryUserApplyLog.queryUserApplyLog(parmMap);
        List<UserApplyLogVO> items = listResult.getItems();
        if(StatusCodes.OK ==listResult.getStatus()){
            dataGrid.setRows(items);

        }
        Result<Long> userApplyLogTotal = queryUserApplyLog.getUserApplyLogTotal(parmMap);
        dataGrid.setTotal(userApplyLogTotal.getItems());

        return dataGrid;
    }




}
