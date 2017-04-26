package com.hzcf.platform.mgr.sys.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.hzcf.platform.mgr.sys.common.pageModel.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.DictUtilService;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import com.hzcf.platform.mgr.sys.common.util.DateUtils;
import com.hzcf.platform.mgr.sys.common.util.ExportExcel;
import com.hzcf.platform.mgr.sys.service.IApplyService;
import com.hzcf.platform.mgr.sys.service.IUserService;
import com.hzcf.platform.mgr.sys.util.ConstantsParam;
/**
 * @description:后台进件管理Controller
 * @author zhangmx
 * 
 */
@Controller
public class ApplyController {

	private static final Log logger = Log.getLogger(ApplyController.class);
    
	@Autowired
	IApplyService applyService;
	
	@Autowired
    DictUtilService dictUtilService;
	
	@RequestMapping(value = "/apply/list",method = RequestMethod.GET)
	public String memberList() {
	    return "apply/list";
	}
	
	@RequestMapping(value="/apply/page",method=RequestMethod.POST)
    @ResponseBody
    public DataGrid userPage(PageHelper page, UserApplyInfoVO apply){

	    return applyService.getApplyPage(page, apply);
    }
	
	
	/**
	 * 导出Excel
	 * @return
	 */
	@RequestMapping(value = "/apply/excel",method = RequestMethod.POST)
    public void exportApplyUser(HttpServletResponse response, UserApplyInfoVO apply){
		String title = "jinjian"+DateUtils.getDate();
        String[] rowsName = new String[]{"序号","手机号","姓名","身份证号","借款用途","借款用途详情","申请最低额度","申请最高额度","月还款最高额度","提交时间","进件状态"};
        List<UserApplyInfoVO> result = applyService.getUserApplyForSearch(apply);
        List<Object[]>  dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        UserApplyInfoVO vo = null;
        for (int i = 0; i < result.size(); i++) {
        	vo = result.get(i);
            objs = new Object[rowsName.length];
            
            String loanPurposeOne = vo.getLoanPurposeOne();
            String loanPurposeOneValue = dictUtilService.convertLoanPurposeOne(loanPurposeOne);
            String loanPurposeTwo = vo.getLoanPurposeTwo();
            String loanPurposeTwoValue = dictUtilService.convertLoanPurposeTwo(loanPurposeOne,loanPurposeTwo);
            
            objs[0] = i+1;
            //objs[0] = vo.getApplyId();
            objs[1] = vo.getMobile();
            objs[2] = vo.getName();
            objs[3] = vo.getIdCard();
            objs[4] = loanPurposeOneValue;   //vo.getLoanPurposeOne();
            objs[5] = loanPurposeTwoValue;  //vo.getLoanPurposeTwo();
            objs[6] = vo.getMinApplyAmount();
            objs[7] = vo.getMaxApplyAmount();
            
            objs[8] = vo.getMaxMonthlyPayment();
            objs[9] = DateUtils.formatDate(vo.getApplySubmitTime(), "yyyy-MM-dd HH:mm:ss");
            String status = vo.getStatus();
            if(status==null||status==""){
            	objs[10] = "";
            }
            if((ConstantsParam.USER_APPLYINFO_STATU_WJ).equals(status) || (ConstantsParam.USER_APPLYINFO_STATU_DSH).equals(status)){
            	objs[10] = "未进件";
            }
            if((ConstantsParam.USER_APPLYINFO_STATU_JJ).equals(status)){
            	objs[10] = "已进件";
            }
//            if((ConstantsParam.USER_APPLYINFO_STATU_DSH).equals(status)){
//            	objs[10] = "待审核";
//            }
            dataList.add(objs);
        }
        ExportExcel ex = new ExportExcel(title, rowsName, dataList);
        ex.export(response);
   }

    /**
     * 重新提交
     * @param applyId
     * @param mobile
     * @return
     */
    @RequestMapping(value = "/apply/anewSubmitApply",method = RequestMethod.POST)
    @ResponseBody
    public JsonResult anewSubmitApply(String applyId, String mobile){

       return applyService.anewSubmitApply(applyId,mobile);
   }
}
