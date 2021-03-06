package com.hzcf.platform.mgr.sys.service.impl;


import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.common.util.uuid.UUIDGenerator;
import com.hzcf.platform.core.user.model.MsgBoxVO;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.MsgBoxservice;
import com.hzcf.platform.core.user.service.UserApplyInfoSerivce;
import com.hzcf.platform.core.user.service.UserImageService;
import com.hzcf.platform.core.user.service.UserService;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import com.hzcf.platform.mgr.sys.common.pageModel.SmsUserInfo;
import com.hzcf.platform.mgr.sys.common.util.DateUtils;
import com.hzcf.platform.mgr.sys.service.IUserService;
import com.hzcf.platform.mgr.sys.util.*;
import com.hzcf.platform.mgr.sys.webService.LoadService;
import com.imageserver.ImageServer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class UserServiceImpl implements IUserService {
	private static final Log logger = Log.getLogger(UserServiceImpl.class);
	
	@Autowired
	public UserService userSerivce;
	
	@Autowired
	private UserImageService userImageService;
	
	@Autowired
	private MsgBoxservice msgBoxservice;
	
	@Autowired
	private UserApplyInfoSerivce userApplyInfoSerivce;

	@Autowired
	private ImageServer imageServer;
	
	@Autowired
	private LoadService loadService;
	
	/**
	 * 用户列表
	 */
	@Override
	public DataGrid getUserPage(PageHelper pageHelper, UserVO userVO){
		pageHelper.setStart((pageHelper.getPage()-1)*pageHelper.getRows());
		pageHelper.setEnd(pageHelper.getRows());
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("user", userVO);
		parmMap.put("page", pageHelper);
		
		DataGrid dataGrid = new DataGrid();
		dataGrid.setTotal(userSerivce.getUserTotal(parmMap));
		PaginatedResult result = userSerivce.getUserList(parmMap);
		dataGrid.setRows(result.getItems());
		return dataGrid;
	}
	
	/**
	 * 实名认证列表
	 */
	@Override
	public DataGrid getCheckUserPage(PageHelper pageHelper, UserVO userVO){
		pageHelper.setStart((pageHelper.getPage()-1)*pageHelper.getRows());
		pageHelper.setEnd(pageHelper.getRows());
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("user", userVO);
		parmMap.put("page", pageHelper);
		parmMap.put("ischeckuser", "true");
		
		DataGrid dataGrid = new DataGrid();
		dataGrid.setTotal(userSerivce.getUserTotal(parmMap));
		PaginatedResult result = userSerivce.getUserList(parmMap);
		dataGrid.setRows(result.getItems());
		return dataGrid;
	}

	
	@Override
	public SmsUserInfo getSmsUserDetail(String mobile) {
		SmsUserInfo se = new SmsUserInfo();
		Map<String, String> parmMap = new HashMap<String, String>();
		Result<UserVO> user = userSerivce.getByMobile(mobile);
		parmMap.put("userId",user.getItems().getId());
		parmMap.put("imageType", "B1");
		Result<List<UserImageVO>> userImage = userImageService.selectUserImageByUserIdAndType(parmMap);
		List<UserImageVO> userList = userImage.getItems();
		if(userImage.getStatus()==200 && userList.size()>0){
			if(userList.size()==1){
				se.setArtWorkA(this.geturl(userList.get(0).getArtWork()));
				se.setImgIdA(userList.get(0).getImageId());
			}
			if(userList.size()==2){
				se.setArtWorkA(this.geturl(userList.get(0).getArtWork()));
				se.setArtWorkB(this.geturl(userList.get(1).getArtWork()));
				se.setImgIdA(userList.get(0).getImageId());
				se.setImgIdB(userList.get(1).getImageId());
			}
			if(userList.size()>=3){
				se.setArtWorkA(this.geturl(userList.get(0).getArtWork()));
				se.setArtWorkB(this.geturl(userList.get(1).getArtWork()));
				se.setArtWorkC(this.geturl(userList.get(2).getArtWork()));
				se.setImgIdA(userList.get(0).getImageId());
				se.setImgIdB(userList.get(1).getImageId());
				se.setImgIdC(userList.get(2).getImageId());
			}
		}
		
		se.setMobile(mobile);
		se.setName(user.getItems().getName());
		se.setIdCard(user.getItems().getIdCard());
		String statu = user.getItems().getCheckStatus();
		se.setCheckStatus(statu);
		if(ConstantsParam.USER_CKECKSTATUS_Y.equals(statu)){
			se.setStatusInfo("通过");
			se.setButt("返回");
		}
		if(ConstantsParam.USER_CKECKSTATUS_N.equals(statu)){
			se.setStatusInfo("不通过");
			se.setButt("返回");
		}
		if(ConstantsParam.USER_CKECKSTATUS.equals(statu)){
			se.setStatusInfo("待审核");
			se.setButt("提交");
		}
		if(user.getItems().getSubmitTime()!=""&&user.getItems().getCreateTime()!=null){
			se.setCreateTime(DateUtils.getDateTimeString(user.getItems().getCreateTime()));
		}
		return se;
	}

	@Override
	public Result<Boolean> update(String mobile,String name, String idCard) {
		UserVO user = new UserVO();
		user.setMobile(mobile);
		user.setName(name);
		user.setIdCard(idCard);
		
		Result<UserVO> userVO = userSerivce.getByMobile(mobile);
		user.setId(userVO.getItems().getId());
		return userSerivce.updateByPrimaryKeySelective(user);
	}

	@Override
	public Result<Map> updateStatus(String mobile, String checkStatus,String nopassCause) {
		String applyId = "";
		UserVO user = new UserVO();
		user.setCheckStatus(checkStatus);
		user.setMobile(mobile);


		if(nopassCause!=null&&nopassCause!=""){
			user.setNopassCause(nopassCause);
		}
		Result<UserVO> result = userSerivce.getByMobile(mobile);
		//调用线下是否成功
		boolean jinjian=false;
		boolean flag = false;
		Map tempMap = new HashMap();
		//提交线下系统
		if(checkStatus.equals(ConstantsParam.USER_CKECKSTATUS_Y)&&result.getStatus()==200){
			//TODO  查询applyId
			//Result<UserApplyInfoVO> resultApply = userApplyInfoSerivce.selectByUserId(result.getItems().getId());
			Map parmMap = new HashMap();
			parmMap.put("userId", result.getItems().getId());
			parmMap.put("status", ConstantsParam.USER_APPLYINFO_STATU_DSH);	//USER_APPLYINFO_STATU_WJ
			Result<UserApplyInfoVO> resultApply = userApplyInfoSerivce.selectByUserIdAndStatus(parmMap);
			if(resultApply.getStatus()==200 && resultApply.getItems()!=null){
				applyId = resultApply.getItems().getApplyId();
				if(!applyId.equals("")){
					// TODO 调裴高翔接口把applyId传过去
					try {
						jinjian=true;
						tempMap = loadService.operateLoadMap(applyId,result.getItems());
						flag = (boolean)tempMap.get("result");
					} catch (Exception e) {
						logger.e("进件接口调用失败!");
						e.printStackTrace();
					}
				}
			}else{
				logger.e("UserApplyInfoVO数据查询失败");
			}
		}
		//写入站内信
		MsgBoxVO msgBoxVO = new MsgBoxVO();
		msgBoxVO.setMsgId(UUIDGenerator.getUUID());
		msgBoxVO.setUserId(result.getItems().getId());
		//msgBoxVO.setStatus(ConstantsParam.MSG_STATUS_YES);
		msgBoxVO.setMsgType(ConstantsParam.MSG_TYPE);
		msgBoxVO.setIsRead(ConstantsParam.MSG_IS_READ_YES);
		msgBoxVO.setCreateTime(new Date());
		msgBoxVO.setMsgTitle("实名认证用户审核情况");
		String date ="";
		if(result.getItems().getSubmitTime()!=""&&result.getItems().getSubmitTime()!=null){
			date = DateUtils.getDateString(result.getItems().getSubmitTime());
		}

		Map<String, Object> jsonmap = new HashMap<>();
		jsonmap.put("tagCode", ConstantsDictionary.JPUSH_MSGBOX_TAGCODE);   //201
		jsonmap.put("applyId", applyId);

		if(checkStatus.equals(ConstantsParam.USER_CKECKSTATUS_N)){		//||flag==false
			msgBoxVO.setStatus(ConstantsParam.MSG_STATUS_BTG);
			msgBoxVO.setMsgContent("尊敬的用户，您在"+date+"提交的实名认证申请未通过，请重新申请。");
			Result<Boolean> resultmsg = msgBoxservice.insertSelective(msgBoxVO);
			if(StatusCodes.OK ==resultmsg.getStatus()){
				JpushClientUtil.sendToAliasId(msgBoxVO.getUserId().replaceAll("-", ""),
						"尊敬的用户，您在"+DateUtil.formatDate3(new Date())
								+"提交的实名认证申请未通过，请重新申请。","汇中贷消息标题", "汇中贷消息内容",
						jsonmap);
			}
		}
		if(checkStatus.equals(ConstantsParam.USER_CKECKSTATUS_Y)){		//&&flag==true
			msgBoxVO.setStatus(ConstantsParam.MSG_STATUS_TG);
			msgBoxVO.setMsgContent("尊敬的用户，您在"+date+"提交的实名认证申请已通过。");
			Result<Boolean> resultmsg2 = msgBoxservice.insertSelective(msgBoxVO);
			if(StatusCodes.OK ==resultmsg2.getStatus()){
				JpushClientUtil.sendToAliasId(msgBoxVO.getUserId().replaceAll("-", ""),
						"尊敬的用户，您在"+DateUtil.formatDate3(new Date())
								+"提交的实名认证申请已通过。","汇中贷消息标题", "汇中贷消息内容",
						jsonmap);

				//修改之前未通过的站内信状态
				Result<Boolean> passresult = msgBoxservice.updateCheckPassByUserId(msgBoxVO);
				if(StatusCodes.OK ==passresult.getStatus()){
					logger.e("MsgBox 修改之前未通过的站内信状态成功。");
				}
			}
		}
		user.setId(result.getItems().getId());
		user.setIdCard(result.getItems().getIdCard());
		user.setApplyStatus(ConstantsParam.USE_APPLY_STASUE_N);
		Result<Boolean> bool = userSerivce.updateByPrimaryKeySelective(user);
//		tempMap = new HashMap();
//		if(flag||checkStatus.equals(ConstantsParam.USER_CKECKSTATUS_N)){
//			tempMap.put("result", bool.getItems());
//			tempMap.put("resultMsg","");
//			return new Result<Map>(ConstantsParam.APPLY_STATUS_SUCCESS, tempMap);
//		}else{
//			//调用线下失败
//			//String resultMsg = String.valueOf(tempMap.get("resultMsg"));
//			return new Result<Map>(ConstantsParam.APPLY_STATUS_FAIL, tempMap);
//		}
		//"实名认证"审核不通过
		if(checkStatus.equals(ConstantsParam.USER_CKECKSTATUS_N)){
			tempMap.put("result", bool.getItems());
			tempMap.put("resultMsg","");
			return new Result<Map>(ConstantsParam.APPLY_STATUS_SUCCESS, tempMap);
		//"实名认证"审核通过,未进件
		}else if(checkStatus.equals(ConstantsParam.USER_CKECKSTATUS_Y) && jinjian==false){
			//调用线下失败
			//String resultMsg = String.valueOf(tempMap.get("resultMsg"));
			tempMap.put("result", bool.getItems());
			tempMap.put("resultMsg","");
			return new Result<Map>(ConstantsParam.APPLY_STATUS_FAIL, tempMap);
		//"实名认证"审核通过,有进件,但是进件成功
		}else if(checkStatus.equals(ConstantsParam.USER_CKECKSTATUS_Y) && jinjian==true && flag==true){
			tempMap.put("result", flag);
			tempMap.put("resultMsg","");
			return new Result<Map>(ConstantsParam.APPLY_STATUS_FAIL, tempMap);
		//"实名认证"审核通过,有进件,但是进件失败
		}else if(checkStatus.equals(ConstantsParam.USER_CKECKSTATUS_Y) && jinjian==true && flag==false){
			tempMap.put("result", flag);
			return new Result<Map>(ConstantsParam.APPLY_STATUS_FAIL, tempMap);
		}
		return null;
	}
	/**
	 * @Title: updatePassWord 
	 * @Description:根据借款人的手机号修改借款人的密码 
	 * @time: 2017年1月18日 上午10:09:54  
	 * @return:Result<Boolean>
	 */
	@Override
	public Result<Boolean> updatePassWord(String mobile, String passWord) {
		UserVO user = new UserVO();
		Result<UserVO> useVO = userSerivce.getByMobile(mobile);
		UserVO userVO=useVO.getItems();
		if(userVO!=null){
			user.setId(userVO.getId());
			user.setPassword(MD5Tools.getMD5(passWord));
		}else{
			return new Result<Boolean>(StatusCodes.OK,false);//根据手机号未查询到对应的借款人信息
		}
		return userSerivce.updateByPrimaryKeySelective(user);
	}
	/**
	 * @Title: status 
	 * @Description:修改借款人的“启用/禁用”状态 
	 * @time: 2017年1月18日 上午11:06:06  
	 * @return:Result<Boolean>
	 */
	@Override
	public Result<Boolean> status(String mobile, String status) {
		UserVO user = new UserVO();
		Result<UserVO> useVO = userSerivce.getByMobile(mobile);
		if(useVO!=null && useVO.getItems()!=null){
			user.setId(useVO.getItems().getId());
			user.setStatus(status);
			return userSerivce.updateByPrimaryKeySelective(user);
		}
		return new Result<Boolean>(StatusCodes.OK,false);//根据手机号未查询到对应的借款人信息
	}

	@Override
	public Result<String> smsImgUpload(HttpServletRequest request,String imgId,String mobile) {
		UserVO user = new UserVO();
		user.setMobile(mobile);
		Result<UserVO> userVO = userSerivce.getByMobile(mobile);
		user.setId(userVO.getItems().getId());
		Map<String, String> parmMap = new HashMap<String, String>();
		UserImageVO userImage = new UserImageVO();
		userImage.setImageId(imgId);
		userImage.setUserId(userVO.getItems().getId());
		userImage.setImageType("B1");
		parmMap.put("userId",userVO.getItems().getId());
		parmMap.put("imageType", "B1");
		
		Result<List<UserImageVO>> uImage = userImageService.selectUserImageByUserIdAndType(parmMap);
		List<UserImageVO> userList = uImage.getItems();
		//TODO 图片入参校验
		long startTime = System.currentTimeMillis();
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		String file_url = "";
		
		if (multipartResolver.isMultipart(request)){
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()){
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next());
				if (file != null){
					String myFileName = file.getOriginalFilename();
					Result<Boolean> booleanResult = null;
					try {
						if(StringUtils.isNotBlank(myFileName)){
							file_url = imageServer.uploadFile(file.getBytes(), getSuffix(myFileName));
						}
						if(StringUtils.isBlank(file_url)){
							logger.e("本次上传失败");
							continue;
							//return new Result(StatusCodes.PIC_UPLOAD_FAILURE,false);
						}
						//该用户有图片信息
						if(userVO.getStatus()== 200 && userList.size()>0){
							userImage.setArtWork(file_url);
							userImage.setUpdateTime(new Date());
							booleanResult = userImageService.updateImage(userImage);
						}
						//该用户没有图片信息
						if(userVO.getStatus()==200 && userList.isEmpty()){
							userImage.setImageId(UUIDGenerator.getUUID());//图片id
							userImage.setArtWork(file_url);//服务器存储的图片的地址
							userImage.setCreateTime(new Date());//创建时间
							booleanResult = userImageService.insertSelective(userImage);
						}
						if (StatusCodes.OK != (booleanResult.getStatus())) {
							logger.e("图片上传失败!");
							return new Result<String>(StatusCodes.PIC_UPLOAD_FAILURE,"false");
						}
						long endTime = System.currentTimeMillis();
						String url =ConstantsDictionary.imgUpload+"/"+file_url;
						logger.i("上传图片运行时间：" + String.valueOf(endTime - startTime) + "ms" +url);
						return new Result<String>(StatusCodes.OK,url);
					} catch (Exception e) {
						logger.i("-----------系统异常,请检查数据源-------");
						e.printStackTrace();
					}
				}
			}
		}
		return new Result<String>(StatusCodes.PIC_UPLOAD_FAILURE,"false");
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
	
	public String geturl(String url){
		return ConstantsDictionary.imgUpload+"/"+url;
	}


	@Override
	public List<UserVO> getCheckUserForSearch(UserVO user) {
		Map<String, Object> parmMap = new HashMap<String, Object>();
		parmMap.put("user", user);
		PaginatedResult result =  userSerivce.getCheckUserForSearch(parmMap);
		return result.getItems();
	}
	
	
}
