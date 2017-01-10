package com.hzcf.platform.mgr.sys.service.impl;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.PaginatedResult;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.common.util.uuid.UUIDGenerator;
import com.hzcf.platform.core.user.model.MsgBoxVO;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.MsgBoxservice;
import com.hzcf.platform.core.user.service.UserImageService;
import com.hzcf.platform.core.user.service.UserService;
import com.hzcf.platform.framework.fastdfs.FastDFSClient;
import com.hzcf.platform.mgr.sys.common.pageModel.DataGrid;
import com.hzcf.platform.mgr.sys.common.pageModel.PageHelper;
import com.hzcf.platform.mgr.sys.common.pageModel.SmsUserInfo;
import com.hzcf.platform.mgr.sys.common.util.DateUtils;
import com.hzcf.platform.mgr.sys.service.IUserService;
import com.hzcf.platform.mgr.sys.util.ConstantsDictionary;
import com.hzcf.platform.mgr.sys.util.ConstantsParam;

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
	FastDFSClient fastdfsClient;
	
	@Override
	public DataGrid getUserPage(PageHelper pageHelper, UserVO userVO){
		pageHelper.setStart((pageHelper.getPage()-1)*pageHelper.getRows());
		pageHelper.setEnd(pageHelper.getRows());
		Map<String, Object> parmMap = new HashMap();
		parmMap.put("user", userVO);
		parmMap.put("page", pageHelper);
		
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
		parmMap.put("type", ConstantsParam.IMG_TYPE_RZ);
		Result<List<UserImageVO>> userImage = userImageService.selectUserImageByUserIdAndType(parmMap);
		List<UserImageVO> userList = userImage.getItems();
		if(userImage.getStatus()==200 && userList.size()>0){
			if(userList.size()==1){
				se.setArtWorkA(this.geturl(userList.get(0).getArtWork()));
			}
			if(userList.size()==2){
				se.setArtWorkA(this.geturl(userList.get(0).getArtWork()));
				se.setArtWorkB(this.geturl(userList.get(1).getArtWork()));
			}
			if(userList.size()>=3){
				se.setArtWorkA(this.geturl(userList.get(0).getArtWork()));
				se.setArtWorkB(this.geturl(userList.get(1).getArtWork()));
				se.setArtWorkC(this.geturl(userList.get(2).getArtWork()));
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
		if(user.getItems().getSubmitTime()!=""&&user.getItems().getSubmitTime()!=null){
			se.setCreateTime(DateUtils.getDate(user.getItems().getSubmitTime()));
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
	public Result<Boolean> updateStatus(String mobile, String checkStatus,String nopassCause) {
		UserVO user = new UserVO();
		user.setCheckStatus(checkStatus);
		user.setMobile(mobile);
		if(nopassCause!=null&&nopassCause!=""){
			user.setNopassCause(nopassCause);
		}
		Result<UserVO> use1 = userSerivce.getByMobile(mobile);
		
		MsgBoxVO msgBoxVO = new MsgBoxVO();
		msgBoxVO.setId(UUIDGenerator.getUUID());
		msgBoxVO.setUserId(use1.getItems().getId());
		msgBoxVO.setStatus(ConstantsParam.MSG_STATUS_YES);
		msgBoxVO.setMsgType(ConstantsParam.MSG_TYPE);
		msgBoxVO.setIsRead(ConstantsParam.MSG_IS_READ_YES);
		msgBoxVO.setCreateTime(new Date());
		msgBoxVO.setMsgTitle("实名认证用户审核情况");
		String date ="";
		if(use1.getItems().getSubmitTime()!=""&&use1.getItems().getSubmitTime()!=null){
			date = DateUtils.getDate(use1.getItems().getSubmitTime());
		}
		
		if(checkStatus.equals(ConstantsParam.USER_CKECKSTATUS_N)){
			msgBoxVO.setMsgContent("尊敬的用户，您在"+date+"提交的实名认证申请未通过，请重新申请。");
			msgBoxservice.insertSelective(msgBoxVO);
		}
		if(checkStatus.equals(ConstantsParam.USER_CKECKSTATUS_Y)){
			
			msgBoxVO.setMsgContent("尊敬的用户，您在"+date+"提交的实名认证申请已通过。");
			msgBoxservice.insertSelective(msgBoxVO);
		}
		user.setId(use1.getItems().getId());
		user.setIdCard(use1.getItems().getIdCard());
		user.setApplyStatus(ConstantsParam.USE_APPLY_STASUE_N);
		return userSerivce.updateByPrimaryKeySelective(user);
	}

	@Override
	public Result<Boolean> updatePassWord(String mobile, String passWord) {
		UserVO user = new UserVO();
		user.setMobile(mobile);
		user.setPassword(passWord);
		Result<UserVO> useVO = userSerivce.getByMobile(mobile);
		user.setId(useVO.getItems().getId());
		return userSerivce.updateByPrimaryKeySelective(user);
		
	}

	@Override
	public Result<Boolean> status(String mobile, String status) {
		UserVO user = new UserVO();
		user.setMobile(mobile);
		user.setStatus(status);
		Result<UserVO> useVO = userSerivce.getByMobile(mobile);
		user.setId(useVO.getItems().getId());
		return userSerivce.updateByPrimaryKeySelective(user);
	}

	@Override
	public Result<Boolean> smsImgUpload(HttpServletRequest request,String path,String mobile) {
		UserVO user = new UserVO();
		user.setMobile(mobile);
		Result<UserVO> userVO = userSerivce.getByMobile(mobile);
		user.setId(userVO.getItems().getId());
		Map<String, String> parmMap = new HashMap<String, String>();
		UserImageVO userImage = new UserImageVO();
		userImage.setUserId(userVO.getItems().getId());
		userImage.setType(ConstantsParam.IMG_TYPE_RZ);
		parmMap.put("userId",userVO.getItems().getId());
		parmMap.put("type", ConstantsParam.IMG_TYPE_RZ);
		StringBuffer imgUrl = new StringBuffer();
		if(path!=null&&path!=""){
			String[] img = path.split("/");
			for (int i = 0; i < img.length; i++) {
				if(i>2){
					if(i==img.length-1){
						imgUrl.append(img[i]);
					}else{
						imgUrl.append(img[i]).append("/");
					}
				}
			}
		}
		
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
							file_url = fastdfsClient.upload(file.getBytes(), getSuffix(myFileName), null);
						}
						if(StringUtils.isBlank(file_url)){
							logger.e("本次上传失败");
							continue;
							//return new Result(StatusCodes.PIC_UPLOAD_FAILURE,false);
						}
						if(userVO.getStatus()== 200 && userList.size()>0){
							parmMap.put("artWork", imgUrl.toString());
							userImage.setArtWork(file_url);
							userImage.setUpdateTime(new Date());
							booleanResult = userImageService.updateImageByUserIdAndTypeAndUrl(parmMap);
						}
						if(userVO.getStatus()==200 && userList.isEmpty()){
							userImage.setImageId(UUIDGenerator.getUUID());//图片id
							userImage.setArtWork(file_url);//服务器存储的图片的地址
							userImage.setCreateTime(new Date());//创建时间
							booleanResult = userImageService.insertSelective(userImage);
						}
						if (StatusCodes.OK != (booleanResult.getStatus())) {
							logger.e("图片上传失败!");
							return new Result(StatusCodes.PIC_UPLOAD_FAILURE,false);
						}
						long endTime = System.currentTimeMillis();
						String url =ConstantsDictionary.imgUpload+"/"+file_url;
						logger.i("上传图片运行时间：" + String.valueOf(endTime - startTime) + "ms" +url);
						return new Result(StatusCodes.OK,true);
					} catch (Exception e) {
						logger.i("-----------系统异常,请检查数据源-------");
						e.printStackTrace();
					}
				}
			}
		}
		return new Result(StatusCodes.PIC_UPLOAD_FAILURE,false);
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
	
	
}
