package com.hzcf.platform.mgr.sys.controller;

import com.hzcf.platform.core.user.model.UserApplyLogVO;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import com.hzcf.platform.mgr.sys.service.impl.UserApplyLogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.crypto.Data;

/**
 * Created by lll on 2017-04-10.
 */
@Controller
public class SystemLogController {


    @Autowired
    UserApplyLogServiceImpl UserApplyLogService;
    /**
     * 用户列表页面
     * @return
     */
    @RequestMapping(value = "/systemLog",method = RequestMethod.GET)
    public String userList() {
        return "system/log";
    }

    @RequestMapping(value = "/systemLoglist")
    @ResponseBody
    public DataGrid systemLoglist(PageHelper pageHelper, UserApplyLogVO userApplyLogVO){
        return UserApplyLogService.queryUserApplyLog(pageHelper,userApplyLogVO);
    }
}
