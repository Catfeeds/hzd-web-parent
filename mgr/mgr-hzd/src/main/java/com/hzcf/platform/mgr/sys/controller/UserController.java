package com.hzcf.platform.mgr.sys.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.framework.fastdfs.FastDFSClient;
import com.hzcf.platform.framework.fastdfs.common.FileCommon;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import com.hzcf.platform.mgr.sys.common.pageModel.SmsUserInfo;
import com.hzcf.platform.mgr.sys.service.IUserService;
/**
 * @description:后台用户管理
 * @author zhangmx
 * 
 */
@Controller
public class UserController {

	private static final Log logger = Log.getLogger(UserController.class);
    
	@Autowired
	IUserService sysUserService;
	
	@Autowired
	FastDFSClient fastdfsClient;
	
	@RequestMapping(value = "/users/fast",method = RequestMethod.GET)
	public String fast() throws Exception {
		// String res = fastdfsClient.helloFjx();
		//File folder = new File("C:\\Users\\Administrator\\Desktop\\testpic");
		File folder = new File("D:\\img");
		if (folder.isDirectory()) {
			File[] files = folder.listFiles();
			for (File file : files) {
				if (file.exists() && file.isFile()) {
					String file_url = fastdfsClient.upload(FileCommon.File2byte(file), getSuffix(file.getName()), null);
					System.out.println(file.getName() + " : " + file_url);
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
    public DataGrid checkUserPage(PageHelper page, UserVO user){
		return sysUserService.getUserPage(page, user);
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
	
	@RequestMapping(value="/users/check/edit",method=RequestMethod.GET)
	public String edit(String mobile,Model m) {
		
		//System.out.println(mobile);
		SmsUserInfo smsUserInfo = sysUserService.getSmsUserDetail(mobile);
		m.addAttribute("smsUserInfo",smsUserInfo);
		return "users/edit";
	}
	
	@RequestMapping(value="/users/check/update",method=RequestMethod.POST)
    public String update(String mobile,String name,String idCard ,HttpServletResponse response) throws IOException{
		Result<Boolean> bool = sysUserService.update(mobile, name, idCard);
		Boolean status = bool.getItems().booleanValue();
		response.getWriter().print(status);
		return "redirect:/users/check/list";
    }	
	
	@RequestMapping(value="/users/check/updateStatus",method=RequestMethod.POST)
	public String updateStatus(String mobile,String checkStatus,String nopassCause,HttpServletResponse response) throws IOException{
		Result<Boolean> bool = sysUserService.updateStatus(mobile, checkStatus,nopassCause);
		Boolean status = bool.getItems().booleanValue();
		response.getWriter().print(status);
		return "users/checklist";
	}
	
	@RequestMapping(value="/users/check/updatePassword",method=RequestMethod.POST)
	public void updatePassword(String mobile,String passWord,HttpServletResponse response) throws IOException{
		Result<Boolean> bool=sysUserService.updatePassWord(mobile, passWord);
		Boolean status = bool.getItems().booleanValue();
		response.getWriter().print(status);
	}
	/**
	 * 修改状态
	 * @param mobile
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(value="/users/check/status",method=RequestMethod.POST)
	public void status(String mobile,String status,HttpServletResponse response) throws IOException{
		Result<Boolean> bool = sysUserService.status(mobile, status);
		Boolean statu = bool.getItems().booleanValue();
		response.getWriter().print(statu);
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
}
