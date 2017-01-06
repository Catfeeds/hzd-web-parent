package com.hzcf.platform.api.service.impl;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.api.config.ConstantsDictionary;
import com.hzcf.platform.api.form.onlineLoanapplyInfoPreviewForm;
import com.hzcf.platform.api.model.CheckApplyLoanStatus;
import com.hzcf.platform.api.service.IOnlineApplyLoanService;
import com.hzcf.platform.common.exception.CheckException;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.common.util.uuid.UUIDGenerator;
import com.hzcf.platform.api.common.DataVerifcation;
import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.core.user.model.*;
import com.hzcf.platform.core.user.service.UserApplyInfoSerivce;
import com.hzcf.platform.core.user.service.UserImageService;
import com.hzcf.platform.core.user.service.UserInfoService;
import com.hzcf.platform.core.user.service.UserRelationService;
import com.hzcf.platform.core.user.service.UserService;
import com.hzcf.platform.framework.fastdfs.FastDFSClient;

import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by leijiaming on 2016/12/28 0028.
 */
@Service
public class OnlineApplyLoanServiceSerivceImpl implements IOnlineApplyLoanService {
	private static final Log logger = Log.getLogger(OnlineApplyLoanServiceSerivceImpl.class);
	@Autowired
	public UserService userSerivce;
	@Autowired
	public UserApplyInfoSerivce userApplyInfoSerivce;
	@Autowired
	public UserInfoService userInfoService;
	@Autowired
	public UserRelationService UserRelationService;
	public UserImageService userImageService;
	@Autowired
	FastDFSClient fastdfsClient;

	@Override
	public BackResult isApplyLoanQuery(UserVO user) {
		logger.i("----------------进入校验是否可以进件");
		Map<String, Object> map = new HashMap<String, Object>();
		CheckApplyLoanStatus checkApplyLoanStatus = new CheckApplyLoanStatus();
		try {
			DataVerifcation.datavVerification(user.getMobile());
			Result<UserVO> byMobile = userSerivce.getByMobile(user.getMobile());
			UserVO items = byMobile.getItems();
			if (BaseConfig.card_status_1.equals(items.getCheckStatus())) {
				logger.i("------------用户未通过实名认证");
				checkApplyLoanStatus.setIdentityStatus(BaseConfig.card_status_1);
				checkApplyLoanStatus.setApplyLoanStatus(BaseConfig.apply_loan_0);
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), checkApplyLoanStatus);
			}

			// TODO 进件申请,查询进件信息未实现
			String ApplyLoanInfoStatus = "0";

			if (BaseConfig.apply_loan_1.equals(ApplyLoanInfoStatus)) {
				logger.i("------------------不能重复提交进件信息");
				checkApplyLoanStatus.setIdentityStatus(BaseConfig.card_status_0);
				checkApplyLoanStatus.setApplyLoanStatus(BaseConfig.apply_loan_1);
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), checkApplyLoanStatus);

			}

			checkApplyLoanStatus.setIdentityStatus(BaseConfig.card_status_0);
			checkApplyLoanStatus.setApplyLoanStatus(BaseConfig.apply_loan_0);
			checkApplyLoanStatus.setId(items.getId());
			checkApplyLoanStatus.setIdcard(items.getIdCard());
			checkApplyLoanStatus.setMobile(items.getMobile());
			checkApplyLoanStatus.setName(items.getName());
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),
					checkApplyLoanStatus);

		} catch (CheckException e) {
			logger.i("------------------缺少必传参数---" + e.getMessage());
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_9000.getCode(), e.getMessage(), null);

		} catch (Exception e) {
			logger.i("-----------系统异常,请检查数据源-------");
			e.printStackTrace();
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg(),
					null);
		}

	}

	@Override
	public BackResult onlineLoanapplyOne(UserVO user, UserApplyInfoVO userApplyInfoVO) {
		try {

			// ------------
			DataVerifcation.checkUserApplyInfoVO(userApplyInfoVO, user);
			String applyId = UUIDGenerator.getUUID();
			userApplyInfoVO.setApplyId(applyId);
			userApplyInfoVO.setUserId(user.getId());
			userApplyInfoVO.setStatus(BaseConfig.apply_loan_1);
			userApplyInfoVO.setApplySubmitTime(new Date());
			Result<String> stringResult = userApplyInfoSerivce.create(userApplyInfoVO);
			if (StatusCodes.OK == (stringResult.getStatus())) {
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), applyId);
			}

		} catch (CheckException e) {
			logger.i("缺少必传参数:---" + e.getMessage());
			e.printStackTrace();
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_9000.getCode(), e.getMessage(), null);

		} catch (Exception e) {
			logger.i("-----------系统异常,请检查数据源-------");
			e.printStackTrace();
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg(),
					null);
		}

		return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(), HzdStatusCodeEnum.MEF_CODE_0001.getMsg());

	}

	@Override
	public BackResult onlineLoanapplyInfoTwo(UserVO user, UserInfoVO userInfoVO, String applyId) {

		try {


			
			DataVerifcation.checkUserInfoVOTwo(userInfoVO, user);

			Result<UserApplyInfoVO> userApplyInfoVOResult = userApplyInfoSerivce.selectByApplyId(applyId);
			UserApplyInfoVO items = userApplyInfoVOResult.getItems();
			if (items == null) {
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_2400.getCode(),
						HzdStatusCodeEnum.MEF_CODE_2400.getMsg());
			}

			userInfoVO.setUserInfoId(UUIDGenerator.getUUID());
			userInfoVO.setUserId(user.getId());
			userInfoVO.setApplyId(applyId);
			userInfoVO.setCreateTime(new Date());

			userInfoVO.setIdType("01");//身份证类型,默认01
			userInfoVO.setDomicilePostCode("1111");//户籍邮政编码 TODO 户籍邮政编码
			userInfoVO.setResidentPostCode("1111");//家庭邮政编码 TODO 家庭邮政编码
			userInfoVO.setResidentTelAreaCode("区号"); //区号: TODO
			userInfoVO.setBorrowType("01");//借贷类型  TODO ?
			userInfoVO.setOrgTeamId("所属团队"); //所属团队 // TODO 所属团队
			userInfoVO.setIsInside("01"); //内网外网 // TODO 内网外挂
			userInfoVO.setReceiverLoginName("线上进件");//受理人
			userInfoVO.setProductId("精英贷1.89");//贷款类型 TODO
			userInfoVO.setIsExpress("0"); //是否加急,默认为0，就是默认为否


			Result<String> stringResult = userInfoService.create(userInfoVO);
			if (StatusCodes.OK == (stringResult.getStatus())) {
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), applyId);
			}
		} catch (CheckException e) {
			logger.i("缺少必传参数:---" + e.getMessage());
			e.printStackTrace();
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_9000.getCode(), e.getMessage(), null);

		} catch (Exception e) {
			logger.i("-----------系统异常,请检查数据源-------");
			e.printStackTrace();
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg(),
					null);
		}
		return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(), HzdStatusCodeEnum.MEF_CODE_0001.getMsg());

	}

	@Override
	public BackResult onlineLoanapplyInfoThree(UserVO user, UserInfoVO userInfoVO, String applyId) {

		try {


			DataVerifcation.checkUserInfoVOThree(userInfoVO, user);

			Result<UserInfoVO> userInfoVOResult = userInfoService.selectByApplyId(applyId);
			UserInfoVO items = userInfoVOResult.getItems();
			if (items == null) {
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_2400.getCode(),
						HzdStatusCodeEnum.MEF_CODE_2400.getMsg());
			}
			userInfoVO.setApplyId(applyId);
			userInfoVO.setOrgPostCode("单位邮政编码");//TODO 单位邮政编码
			userInfoVO.setOrgTelAreaCode("单位区号"); //TODO 单位区号
			userInfoVO.setCreateTime(new Date());
			Result<Boolean> booleanResult = userInfoService.updateUserInfo(userInfoVO);
			if (StatusCodes.OK == (booleanResult.getStatus())) {
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), applyId);
			}
		} catch (CheckException e) {
			logger.i("缺少必传参数:---" + e.getMessage());
			e.printStackTrace();
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_9000.getCode(), e.getMessage(), null);

		} catch (Exception e) {
			logger.i("-----------系统异常,请检查数据源-------");
			e.printStackTrace();
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg(),
					null);
		}

		return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(), HzdStatusCodeEnum.MEF_CODE_0001.getMsg());

	}

	@Override
	public BackResult onlineLoanapplyInfoPerfect(UserVO user, List<UserRelationVO> userRelationVO, String applyId) {

		try {


			DataVerifcation.checkUserRelationVO(user, userRelationVO);

			Result<UserApplyInfoVO> userApplyInfoVOResult = userApplyInfoSerivce.selectByApplyId(applyId);
			UserApplyInfoVO items = userApplyInfoVOResult.getItems();
			if (items == null) {
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_2400.getCode(),
						HzdStatusCodeEnum.MEF_CODE_2400.getMsg());
			}
			for (UserRelationVO userRelation : userRelationVO) {
				userRelation.setRelationId(UUIDGenerator.getUUID());
				userRelation.setApplyId(applyId);
				userRelation.setCreateTime(new Date());
				userRelation.setUserId(user.getId());
				Result<String> stringResult = UserRelationService.create(userRelation);
				if (StatusCodes.OK != (stringResult.getStatus())) {
					return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
							HzdStatusCodeEnum.MEF_CODE_0001.getMsg());
				}
			}
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg());

		} catch (CheckException e) {
			logger.i("缺少必传参数:---" + e.getMessage());
			e.printStackTrace();
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_9000.getCode(), e.getMessage(), null);

		} catch (Exception e) {
			logger.i("-----------系统异常,请检查数据源-------");
			e.printStackTrace();
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg(),
					null);
		}

		// return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
		// HzdStatusCodeEnum.MEF_CODE_0001.getMsg());

	}

	@Override
	public BackResult onlineLoanapplyImgUpload(HttpServletRequest request, UserVO user, UserImageVO userImageVO,String applyId) {

		//TODO 图片入参校验

		Result<UserApplyInfoVO> userApplyInfoVOResult = userApplyInfoSerivce.selectByApplyId(applyId);
		UserApplyInfoVO items = userApplyInfoVOResult.getItems();
		if (items == null) {
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_2400.getCode(),
					HzdStatusCodeEnum.MEF_CODE_2400.getMsg());
		}
		long startTime = System.currentTimeMillis();
		// 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		// 检查form中是否有enctype="multipart/form-data"
		String file_url = "";
		if (multipartResolver.isMultipart(request))
		{
			// 将request变成多部分request
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			// 获取multiRequest 中所有的文件名
			Iterator iter = multiRequest.getFileNames();
			while (iter.hasNext())
			{
				// 一次遍历所有文件
				MultipartFile file = multiRequest.getFile(iter.next().toString());
				if (file != null)
				{
					String myFileName = file.getOriginalFilename();
					try {
						if(StringUtils.isNotBlank(myFileName)){
							file_url = fastdfsClient.upload(file.getBytes(), getSuffix(myFileName), null);
						//	userImageService
						}

						if(StringUtils.isBlank(file_url)){
							return new BackResult(HzdStatusCodeEnum.MEF_CODE_4100.getCode(), HzdStatusCodeEnum.MEF_CODE_4100.getMsg());
						}
						userImageVO.setImageId(UUIDGenerator.getUUID());
						userImageVO.setApplyId(applyId);
						userImageVO.setArtWork(file_url);
						userImageVO.setCreateTime(new Date());
						Result<Boolean> booleanResult = userImageService.insertSelective(userImageVO);
						if (StatusCodes.OK != (booleanResult.getStatus())) {
							return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
									HzdStatusCodeEnum.MEF_CODE_0001.getMsg());
						}
						long endTime = System.currentTimeMillis();
						String url =ConstantsDictionary.imgUpload+"/"+file_url;
						Map   map = new HashedMap();
						map.put("url",url);
						map.put("type",userImageVO.getType());

						logger.i("上传图片运行时间：" + String.valueOf(endTime - startTime) + "ms" +url);
						return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),map);

					} catch (Exception e) {
						logger.i("-----------系统异常,请检查数据源-------");
						e.printStackTrace();
						return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg(),
								null);
					}
				}

			}


		}
		return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(), HzdStatusCodeEnum.MEF_CODE_0001.getMsg());

		
		/*
		 * File folder = new File("F:\\img"); String file_url = null; if
		 * (folder.isDirectory()) { File[] files = folder.listFiles(); for (File
		 * file : files) { if (file.exists() && file.isFile()) { ; try {
		 * file_url = fastdfsClient.upload(FileCommon.File2byte(file), "123",
		 * null); } catch (Exception e) { e.printStackTrace(); }
		 * System.out.println(file.getName() + " : " + file_url); } } }
		 */
		
	}


	@Override
	public BackResult onlineLoanapplyInfoPreview(UserVO user, String applyId) {

		Result<UserApplyInfoVO> userApplyInfoVOResult = userApplyInfoSerivce.selectByApplyId(applyId);
		UserApplyInfoVO userApplyInfoVO = userApplyInfoVOResult.getItems();

		Result<UserInfoVO> userInfoVOResult = userInfoService.selectByApplyId(applyId);
		UserInfoVO userInfoVO = userInfoVOResult.getItems();

		Result<List<UserRelationVO>> listResult = UserRelationService.selectByApplyId(applyId);
		List<UserRelationVO> userRelationVOList = listResult.getItems();
		if (userApplyInfoVO == null || userInfoVO == null || userRelationVOList.size() == 0) {
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_2400.getCode(), HzdStatusCodeEnum.MEF_CODE_2400.getMsg());
		}

		return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),
				new onlineLoanapplyInfoPreviewForm(userApplyInfoVO, userInfoVO, userRelationVOList));
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
}
