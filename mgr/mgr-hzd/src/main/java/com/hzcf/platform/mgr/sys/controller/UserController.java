package com.hzcf.platform.mgr.sys.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hzcf.platform.core.user.service.UserService;
import com.hzcf.platform.mgr.sys.common.pageModel.JsonResult;
import com.hzcf.platform.mgr.sys.util.ServiceUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import com.hzcf.platform.mgr.sys.common.pageModel.SmsUserInfo;
import com.hzcf.platform.mgr.sys.common.util.DateUtils;
import com.hzcf.platform.mgr.sys.common.util.ExportExcel;
import com.hzcf.platform.mgr.sys.service.IUserService;
import com.hzcf.platform.mgr.sys.util.ConstantsParam;

import net.sf.json.JSONObject;
/**
 * @description:后台用户管理
 * @author zhangmx
 * 
 */
@Controller
public class UserController {

	private static final Log logger = Log.getLogger(UserController.class);
    
	@Autowired
	IUserService sysUserService;//借款人service
	@Autowired
    UserService userSerivce;
	@Autowired
	//private ImageServer imageServer;
	
	@RequestMapping(value = "/users/fast",method = RequestMethod.GET)
	public String fast() throws Exception {
		// String res = fastdfsClient.helloFjx();
		//File folder = new File("C:\\Users\\Administrator\\Desktop\\testpic");
		File folder = new File("D:\\img");
		if (folder.isDirectory()) {
			File[] files = folder.listFiles();
			for (File file : files) {
				if (file.exists() && file.isFile()) {
					//String file_url = imageServer.uploadFile(FileCommon.File2byte(file), getSuffix(file.getName()));
					//System.out.println(file.getName() + " : " + file_url);
				}
			}
		}
		return "redirect:/users/check/list";
	}
	
	private static String getSuffix(String url) {
		if (url != null) {
			int index = url.lastIndexOf(".");
			if (index > 0) {
				return url.substring(index + 1);
			}
		}
		return url;
	}
	
	/**
	 * 用户列表页面
	 * @return
	 */
	@RequestMapping(value = "/users/list",method = RequestMethod.GET)
	public String userList() {
	    return "users/list";
	}
	
	/**
	 * 用户分页
	 * @param page
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/users/page",method=RequestMethod.POST)
    @ResponseBody
    public DataGrid userPage(PageHelper page, UserVO user){
		return sysUserService.getUserPage(page, user);
    }
	
	/**
	 * 实名认证列表页面
	 * @return
	 */
	@RequestMapping(value = "/users/check/list",method = RequestMethod.GET)
	public String checkUserList() {
	    return "users/checklist";
	}
	
	/**
	 * 实名认证列表分页
	 * @param page
	 * @param user
	 * @return
	 */
	@RequestMapping(value="/users/check/page",method=RequestMethod.POST)
    @ResponseBody
    public DataGrid checkUserPage(PageHelper page, UserVO user)
	{
		return sysUserService.getCheckUserPage(page, user);
    }	
	/**
	 * 实名认证详情页面
	 * @return
	 */
	@RequestMapping(value="/users/check/detail",method=RequestMethod.GET)
	public String detail(String mobile,Model m) {
		
		//System.out.println(mobile);
		SmsUserInfo smsUserInfo = sysUserService.getSmsUserDetail(mobile);
		m.addAttribute("smsUserInfo",smsUserInfo);
		return "users/detail";
	}
	/**
	 * 实名认证修改页面
	 * @param mobile
	 * @param m
	 * @return
	 */
	@RequestMapping(value="/users/check/edit",method=RequestMethod.GET)
	public String edit(String mobile,Model m) {
		
		//System.out.println(mobile);
		SmsUserInfo smsUserInfo = sysUserService.getSmsUserDetail(mobile);
		m.addAttribute("smsUserInfo",smsUserInfo);
		return "users/edit";
	}
	/**
	 * 根据手机号更新用户姓名和身份证号信息
	 * @param mobile
	 * @param name
	 * @param idCard
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@ResponseBody
	@RequestMapping(value="/users/check/update",method=RequestMethod.POST)
    public JsonResult update(String mobile, String name, String idCard , HttpServletResponse response) throws IOException{

		if(!ServiceUtil.validateIdNo(idCard.toLowerCase())){
			return new JsonResult(false,"用户身份证号输入不合法",null);

		}

		if(!ServiceUtil.isNameString(name)){
			return new JsonResult(false,"用户姓名输入不合法",null);

		}

		Result<UserVO> byMobile = userSerivce.getByMobile(mobile);
		UserVO uu = byMobile.getItems();
		if(uu.getCheckStatus().equals("2")){
			return new JsonResult(false,"待审核状态，无法修改实名认证信息",null);
		}
		PageHelper page = new PageHelper();
		UserVO user = new UserVO();
		user.setIdCard(idCard);
		page.setPage(0);
		user.setCheckStatus("0");
		DataGrid checkUserPage = sysUserService.getCheckUserPage(page, user);

		if(checkUserPage.getTotal()>0 || checkUserPage.getRows().size()>0){
			return new JsonResult(false,"身份证号重复修改失败",null);
		}
		Result<Boolean> bool = sysUserService.update(mobile, name, idCard);
		Boolean status = bool.getItems().booleanValue();
		return new JsonResult(status,"修改成功",null);
    }	
	/**
	 * 修改实名认证用户的审核状态
	 * @param mobile
	 * @param checkStatus
	 * @param nopassCause
	 * @param response
	 * @return
	 * @throws IOException
	 */
	@RequestMapping(value="/users/check/updateStatus",method=RequestMethod.POST)
	@ResponseBody
	public void updateStatus(String mobile,String checkStatus,String nopassCause,HttpServletResponse response) throws IOException{
		Result<Map> map = sysUserService.updateStatus(mobile, checkStatus,nopassCause);
		//boolean result = (boolean)map.getItems().get("result");
		//String resultMsg = String.valueOf(map.getItems().get("resultMsg"));
		//Boolean status = bool.getItems().booleanValue();
//		response.getWriter().print(map);
		JSONObject mapJSON=JSONObject.fromObject(map.getItems());
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // prevents caching at the
												// proxy server
		response.setCharacterEncoding("UTF-8");
		response.setContentType("textml;charset=UTF-8");
		response.getWriter().write(mapJSON.toString());
		response.flushBuffer();
//		return "users/checklist";
	}
	/**
	 * 修改借款人的登录密码
	 * @param mobile
	 * @param passWord
	 * @param response
	 * @throws IOException
	 */
	@RequestMapping(value="/users/check/updatePassword",method=RequestMethod.POST)
	public void updatePassword(String mobile,String passWord,HttpServletResponse response) throws IOException{
		/**初始化参数*/
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("code","-1");
		/**验证参数是否合法*/
		//验证手机号
		if(StringUtils.isBlank(mobile)){
			result.put("message","修改借款人密码失败，借款人手机号为空");
		}else{
			//验证密码是否合法
			if(StringUtils.isBlank(passWord) || passWord.length()<8 || passWord.length()>16){
				result.put("message","修改借款人密码失败，借款人密码不符合要求");
			}else{
				/**执行数据修改操作*/
				Result<Boolean> updateResult=sysUserService.updatePassWord(mobile, passWord);
				if(updateResult!=null && updateResult.getItems()!=null && updateResult.getItems()==true){
					result.put("code","1");
					result.put("message","修改借款人密码成功");	
				}else{
					result.put("message","修改借款人密码失败");
				}
			}
		}
		JSONObject json=JSONObject.fromObject(result);
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // prevents caching at the
												// proxy server
		response.setCharacterEncoding("UTF-8");
		response.setContentType("textml;charset=UTF-8");
		response.getWriter().write(json.toString());
		response.flushBuffer();
	}
	/**
	 * 修改借款人的“禁用/启用”状态
	 * @param mobile
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/users/check/status",method=RequestMethod.POST)
	public void status(String mobile,String status,HttpServletResponse response) throws IOException{
		/**初始化参数*/
		Map<String,Object> result=new HashMap<String,Object>();
		result.put("code","-1");
		/**验证参数的合法性*/
		if(StringUtils.isBlank(mobile)){
			result.put("message","修改借款人状态失败，借款人手机号为空");
		}else{
			/**执行数据修改操作*/
			Result<Boolean> updateResult = sysUserService.status(mobile, status);
			if(updateResult!=null && updateResult.getItems()!=null && updateResult.getItems()==true){
				result.put("code","1");
				result.put("message","修改借款人状态成功");	
			}else{
				result.put("message","修改借款人状态失败");
			}
		}
		JSONObject json=JSONObject.fromObject(result);
		response.setHeader("Cache-Control", "no-cache"); // HTTP 1.1
		response.setHeader("Pragma", "no-cache"); // HTTP 1.0
		response.setDateHeader("Expires", 0); // prevents caching at the
												// proxy server
		response.setCharacterEncoding("UTF-8");
		response.setContentType("textml;charset=UTF-8");
		response.getWriter().write(json.toString());
		response.flushBuffer();
	}
	
	/*
    @RequestMapping(value="/users/check/updateUserAndImage",method = RequestMethod.POST)
    public String smsImgUpload(HttpServletRequest request,String mobile,String name,String idCard)  {
        logger.i("更新用户信息和实名认证信息的图片");
        sysUserService.smsImgUpload(request, mobile,name,idCard);
        return "redirect:/users/check/list";
    }*/
	
    /**上传实名认证更新用户信息
     * @throws IOException 
     * 
     * */
	 @RequestMapping(value="/users/check/updateImage",method = RequestMethod.POST)
	 @ResponseBody
	 public String smsImgUpload(HttpServletRequest request,String imgId,String mobile){
        logger.i("更新用户信息和实名认证信息的图片");
        Result<String> bool = sysUserService.smsImgUpload(request, imgId,mobile);
        
        return bool.getItems();
	 }
	 
	 /**
	 * 导出Excel
	 * @return
	 */
	@RequestMapping(value = "/checkuser/excel",method = RequestMethod.POST)
    public void exportCheckUser(HttpServletResponse response, UserVO user){
		String title = "smrz"+DateUtils.getDate();
        String[] rowsName = new String[]{"序号","手机号","姓名","身份证号","提交时间","审核状态","不通过原因"};
        List<UserVO> result = sysUserService.getCheckUserForSearch(user);
        List<Object[]>  dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        UserVO vo = null;
        for (int i = 0; i < result.size(); i++) {
        	vo = result.get(i);
            objs = new Object[rowsName.length];
            objs[0] = i+1;
            objs[1] = vo.getMobile();
            objs[2] = vo.getName();
            objs[3] = vo.getIdCard();
            //objs[4] = DateUtils.formatDate(vo.getSubmitTime(), "yyyy-MM-dd HH:mm:ss");
            objs[4] = vo.getSubmitTime();
            String checkStatus = vo.getCheckStatus();
            if(checkStatus.equals(ConstantsParam.USER_CKECKSTATUS_Y)){
            	 objs[5] = "通过";
            }
            if(checkStatus.equals(ConstantsParam.USER_CKECKSTATUS_N)){
            	objs[5] = "不通过";
            }
            if(checkStatus.equals(ConstantsParam.USER_CKECKSTATUS)){
            	objs[5] = "待审核";
            }
            //objs[5] = vo.getApplyStatus();
            objs[6] = vo.getNopassCause();
            
            dataList.add(objs);
        }
        ExportExcel ex = new ExportExcel(title, rowsName, dataList);
        ex.export(response);
   }
	
	/**
	 * 导出用户列表Excel
	 * @return
	 */
	@RequestMapping(value = "/user/excel",method = RequestMethod.POST)
    public void exportUser(HttpServletResponse response, UserVO user){
		String title = "yonghu"+DateUtils.getDate();
        String[] rowsName = new String[]{"序号","手机号","姓名","身份证号","用户状态","注册时间","进件状态"};
        List<UserVO> result = sysUserService.getCheckUserForSearch(user);
        List<Object[]>  dataList = new ArrayList<Object[]>();
        Object[] objs = null;
        UserVO vo = null;
        for (int i = 0; i < result.size(); i++) {
        	vo = result.get(i);
            objs = new Object[rowsName.length];
            objs[0] = i+1;
            objs[1] = vo.getMobile();
            objs[2] = vo.getName();
            objs[3] = vo.getIdCard();
            String userStatus = vo.getStatus();
            if (userStatus.equals(ConstantsParam.USER_STATUS_Y)) {
            	objs[4] = "正常";
            }
            if (userStatus.equals(ConstantsParam.USER_STATUS_N)) {
            	objs[4] = "禁用";
            }
            objs[5] = DateUtils.formatDate(vo.getCreateTime(), "yyyy-MM-dd HH:mm:ss");
            String applyStatus = vo.getApplyStatus();
            if(applyStatus.equals(ConstantsParam.USER_CKECKSTATUS_Y)||applyStatus.equals(ConstantsParam.USER_CKECKSTATUS)){
            	objs[6] = "未进件";
            }
            if(applyStatus.equals(ConstantsParam.USER_CKECKSTATUS_N)){
            	objs[6] = "已进件";
            }
            dataList.add(objs);
        }
        ExportExcel ex = new ExportExcel(title, rowsName, dataList);
        ex.export(response);
   }
}
