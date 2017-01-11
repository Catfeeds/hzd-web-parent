package com.hzcf.platform.api.service.impl;

import com.hzcf.platform.api.common.BackResult;
import com.hzcf.platform.api.common.DictBase;
import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.api.config.ConstantsDictionary;
import com.hzcf.platform.api.form.onlineLoanapplyInfoPreviewForm;
import com.hzcf.platform.api.model.CheckApplyLoanStatus;
import com.hzcf.platform.api.service.IOnlineApplyLoanService;
import com.hzcf.platform.api.util.CustomerUtils;
import com.hzcf.platform.api.util.DateUtil;
import com.hzcf.platform.api.util.serialnumber;
import com.hzcf.platform.common.exception.CheckException;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.common.util.uuid.UUIDGenerator;
import com.hzcf.platform.api.common.DataVerifcation;
import com.hzcf.platform.api.baseEnum.HzdStatusCodeEnum;
import com.hzcf.platform.core.user.model.*;
import com.hzcf.platform.core.user.service.*;
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
	public UserRelationService userRelationService;
	@Autowired
	public UserImageService userImageService;
	@Autowired
	FastDFSClient fastdfsClient;
	@Autowired
	DictUtilService dictUtilService;


	@Override
	public BackResult isApplyLoanQuery(UserVO user) {
		logger.i("----------------进入校验是否可以进件");
		Map<String, Object> map = new HashMap<String, Object>();
		CheckApplyLoanStatus checkApplyLoanStatus = new CheckApplyLoanStatus();
		try {
			DataVerifcation.datavVerification(user.getMobile());
			Result<UserVO> byMobile = userSerivce.getByMobile(user.getMobile());
			if (StatusCodes.OK != (byMobile.getStatus()) && byMobile!=null) {
				logger.i("数据查询失败 。。。。。。。。。。。。。 ");
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0001.getMsg());
			}
			UserVO items = byMobile.getItems();
			if (BaseConfig.card_status_1.equals(items.getCheckStatus())) {
				logger.i("------------用户未通过实名认证");
				checkApplyLoanStatus.setIdentityStatus(BaseConfig.card_status_1);
				checkApplyLoanStatus.setApplyLoanStatus(BaseConfig.apply_loan_0);
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), checkApplyLoanStatus);
			}



			if (BaseConfig.apply_loan_1.equals(items.getApplyStatus())) {
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
			DataVerifcation.checkUserApplyInfoVO(userApplyInfoVO, user);
			if(StringUtils.isNotBlank(userApplyInfoVO.getApplyId())){
				logger.i("-用户进件申请第一步 >>更新信息");
				Result<UserApplyInfoVO> userApplyInfoVOResult = userApplyInfoSerivce.selectByApplyId(userApplyInfoVO.getApplyId());
				if (userApplyInfoVOResult.getItems()!=null ){
					userApplyInfoVO.setApplySubmitTime(new Date());
					Result<Boolean> booleanResult = userApplyInfoSerivce.updateApplyId(userApplyInfoVO);
					if (StatusCodes.OK == (booleanResult.getStatus())) {
						logger.i("-用户进件申请第一步 >>更新信息成功");
						Map map = new HashMap();
						map.put("applyId", userApplyInfoVO.getApplyId());
						return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),
								HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), map);
					}
				}
				logger.i("-用户进件申请第一步 >>更新失败 未查询到申请信息>>applyId:"+userApplyInfoVO.getApplyId());
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0001.getMsg(), null);
				}
			// ------------
			logger.i("-用户进件申请第一步 >保存个人信息");
			String applyId = serialnumber.Getnum();
			userApplyInfoVO.setApplyId(applyId);
			userApplyInfoVO.setUserId(user.getId());
			userApplyInfoVO.setStatus(BaseConfig.apply_loan_1);
			userApplyInfoVO.setApplySubmitTime(new Date());
			Result<String> stringResult = userApplyInfoSerivce.create(userApplyInfoVO);

			if (StatusCodes.OK == (stringResult.getStatus())) {
				logger.i("return  -----用户进件申请第一步 保存个人信息成功 。。。。。。。 ");
				Map map = new HashMap();
				map.put("applyId", applyId);
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), map);
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
		logger.i("return  -----用户进件申请第一步      失败    。。。。。。。 ");
		return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(), HzdStatusCodeEnum.MEF_CODE_0001.getMsg());

	}

	@Override
	public BackResult onlineLoanapplyInfoTwo(UserVO user, UserInfoVO userInfoVO, String applyId) {

		try {

			DataVerifcation.checkUserInfoVOTwo(userInfoVO, user);

			Result<UserApplyInfoVO> userApplyInfoVOResult = userApplyInfoSerivce.selectByApplyId(applyId);
			if (StatusCodes.OK != (userApplyInfoVOResult.getStatus())) {
				logger.i("数据查询失败 。。500。。。。。。。。。。。 ");
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0001.getMsg());
			}
			UserApplyInfoVO items = userApplyInfoVOResult.getItems();
			if (items == null) {
				logger.i("用户进件申请第二步 无效的借款编号");
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_2400.getCode(),
						HzdStatusCodeEnum.MEF_CODE_2400.getMsg());
			}

			Result<UserInfoVO> userInfoVOResult = userInfoService.selectByApplyId(applyId);

			if (StatusCodes.OK != (userInfoVOResult.getStatus())) {
				logger.i("数据查询失败 。userInfoVOResult   >>>>500。。。。。。。。。。。 ");
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0001.getMsg());
			}

			if (userInfoVOResult.getItems() != null) {
				logger.i("用户进件申请第二步 更新借款信息");
				userInfoVO.setCreateTime(new Date());
				userInfoVO.setGender(CustomerUtils.calculateGender(user.getIdCard()));

				//jiating
				UserDictJson userDictJson = dictUtilService.convertCityBean(userInfoVO.getResidentProvince(), userInfoVO.getResidentCity());
				//huji
				UserDictJson userDictJson1 = dictUtilService.convertCityBean(userInfoVO.getDomicileProvince(), userInfoVO.getDomicileCity());

				userInfoVO.setDomicilePostCode(userDictJson1.getPostcode());//户籍邮政编码 TODO 户籍邮政编码
				userInfoVO.setResidentPostCode(userDictJson.getPostcode());//家庭邮政编码 TODO 家庭邮政编码
				userInfoVO.setResidentTelAreaCode(userDictJson.getAreacode()); //区号: TODO
				userInfoVO.setIsInside(userDictJson.getIsInside()); //内网外网 // TODO 内网外挂
				Date BirthdayDate = CustomerUtils.calculateBirthDate(user.getIdCard());
				userInfoVO.setBirthday(BirthdayDate);  //生日: TODO

				Result<Boolean> booleanResult = userInfoService.updateUserInfo(userInfoVO);
				if (StatusCodes.OK == (booleanResult.getStatus())) {
					logger.i("用户进件申请第二步 更新借款信息成功");
					Map map = new HashMap();
					map.put("applyId", applyId);

					logger.i("return  -----用户进件申请第二步,  成功。。。。。。 ");
					return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),
							HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), map);
				}
				logger.i("return  -----用户进件申请第二步,  失败。。。。。。 applyId :"+applyId);
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0001.getMsg(), null);
			}
				//jiating
				UserDictJson userDictJson = dictUtilService.convertCityBean(userInfoVO.getResidentProvince(), userInfoVO.getResidentCity());
				//huji
				UserDictJson userDictJson1 = dictUtilService.convertCityBean(userInfoVO.getDomicileProvince(), userInfoVO.getDomicileCity());

				userInfoVO.setUserInfoId(UUIDGenerator.getUUID());
				userInfoVO.setUserId(user.getId());
				userInfoVO.setApplyId(applyId);
				userInfoVO.setCreateTime(new Date());
				userInfoVO.setGender(CustomerUtils.calculateGender(user.getIdCard()));
				userInfoVO.setIdType(DictBase.IDCARDTYPE_01); //身份证类型,默认01
				userInfoVO.setDomicilePostCode(userDictJson1.getPostcode());//户籍邮政编码 TODO 户籍邮政编码
				userInfoVO.setResidentPostCode(userDictJson.getPostcode());//家庭邮政编码 TODO 家庭邮政编码
				userInfoVO.setResidentTelAreaCode(userDictJson.getAreacode()); //区号: TODO
				// 	userInfoVO.setBorrowType("01");//借贷类型  不需要传递 ?
				//userInfoVO.setOrgTeamId("所属团队"); //所属团队 // TODO 所属团队
				//家庭地址
				userInfoVO.setIsInside(userDictJson.getIsInside()); //内网外网 // TODO 内网外挂
				userInfoVO.setReceiverLoginName(DictBase.SETRECEIVERLOGINNAME);//受理人
				userInfoVO.setProductId(DictBase.SETPRODUCTID_01);//贷款类型 TODO
				userInfoVO.setIsExpress(DictBase.SETISEXPRESS); //是否加急,默认为0，就是默认为否
			Date BirthdayDate = CustomerUtils.calculateBirthDate(user.getIdCard());
			userInfoVO.setBirthday(BirthdayDate);  //生日: TODO


				Result<String> stringResult = userInfoService.create(userInfoVO);
				if (StatusCodes.OK == (stringResult.getStatus())) {
					Map map = new HashMap();
					map.put("applyId", applyId);

					logger.i("return  -----用户进件申请第二步,  成功。。。。。。 ");
					return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),
							HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), map);
				}


			} catch(CheckException e){
				logger.i("缺少必传参数:---" + e.getMessage());
				e.printStackTrace();
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_9000.getCode(), e.getMessage(), null);

			} catch(Exception e){
				logger.i("-----------系统异常,请检查数据源-------");
				e.printStackTrace();
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_9999.getCode(), HzdStatusCodeEnum.MEF_CODE_9999.getMsg(),
						null);
			}
			logger.i("return  -----用户进件申请第二步,  失败。。。。。。 ");
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(), HzdStatusCodeEnum.MEF_CODE_0001.getMsg());

		}
	@Override
	public BackResult onlineLoanapplyInfoThree(UserVO user, UserInfoVO userInfoVO, String applyId) {

		try {

			DataVerifcation.checkUserInfoVOThree(userInfoVO, user);

			Result<UserInfoVO> userInfoVOResult = userInfoService.selectByApplyId(applyId);
			if (StatusCodes.OK != (userInfoVOResult.getStatus())) {
				logger.i("数据查询失败 。userInfoVOResult   >>>>500。。。。。。。。。。。 ");
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0001.getMsg());
			}
			UserInfoVO items = userInfoVOResult.getItems();
			if (items == null) {
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_2400.getCode(),
						HzdStatusCodeEnum.MEF_CODE_2400.getMsg());
			}
			userInfoVO.setApplyId(applyId);

			UserDictJson userDictJson = dictUtilService.convertCityBean(userInfoVO.getOrgProvince(), userInfoVO.getOrgCity());

			userInfoVO.setOrgPostCode(userDictJson.getAreacode());//TODO 单位邮政编码
			userInfoVO.setOrgTelAreaCode(userDictJson.getAreacode()); //TODO 单位区号

			userInfoVO.setCreateTime(new Date());
			Result<Boolean> booleanResult = userInfoService.updateUserInfo(userInfoVO);
			if (StatusCodes.OK == (booleanResult.getStatus())) {
				
				Map map = new HashMap();
				map.put("applyId", applyId);
				
				logger.i("进入  -----用户进件申请第三步,  成功。。。。。。。。。");
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), map);
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
		logger.i("进入  -----用户进件申请第三步,  失败。。。。。。。。。");
		return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(), HzdStatusCodeEnum.MEF_CODE_0001.getMsg());

	}

	@Override
	public BackResult onlineLoanapplyInfoPerfect(UserVO user, List<UserRelationVO> userRelationVO, String applyId) {

		try {


			DataVerifcation.checkUserRelationVO(user, userRelationVO);

			Result<UserApplyInfoVO> userApplyInfoVOResult = userApplyInfoSerivce.selectByApplyId(applyId);
			if (StatusCodes.OK != (userApplyInfoVOResult.getStatus())) {
				logger.i("数据查询失败 。userApplyInfoVOResult   >>>>500。。。。。。。。。。。 ");
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0001.getMsg());
			}
			UserApplyInfoVO items = userApplyInfoVOResult.getItems();
			if (items == null) {
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_2400.getCode(),
						HzdStatusCodeEnum.MEF_CODE_2400.getMsg());
			}
			//清空关系数据
			Result<Boolean> delResult = userRelationService.deleteByApplyId(applyId);
			if (StatusCodes.OK != (delResult.getStatus())) {
				logger.i("进入  -----用户进件申请第四步,  清空关系数据 失败 。。。。。。。。。。。。。 ");

				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0001.getMsg());
			}
			//插入关系数据
			for (UserRelationVO userRelation : userRelationVO) {
				userRelation.setRelationId(UUIDGenerator.getUUID());
				userRelation.setApplyId(applyId);
				userRelation.setCreateTime(new Date());
				userRelation.setUserId(user.getId());
				Result<String> stringResult = userRelationService.create(userRelation);
				if (StatusCodes.OK != (stringResult.getStatus())) {
					logger.i("进入  -----用户进件申请第四步,  失败 。。。。。。。。。。。。。 ");
					return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
							HzdStatusCodeEnum.MEF_CODE_0001.getMsg());
				}
			}
			
			Map map = new HashMap();
			map.put("applyId", applyId);
			logger.i("进入  -----用户进件申请第四步,  成功 。。。。。。。。。。。。。 ");
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), map);

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

		if (StatusCodes.OK != (userApplyInfoVOResult.getStatus())) {
			logger.i("数据查询失败 。userApplyInfoVOResult   >>>>500。。。。。。。。。。。 ");
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
					HzdStatusCodeEnum.MEF_CODE_0001.getMsg());
		}
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
							//synchronized (this) {
								file_url = fastdfsClient.upload(file.getBytes(), getSuffix(myFileName), null);
							//}
						//	userImageService
						}

						if(StringUtils.isBlank(file_url)){
							return new BackResult(HzdStatusCodeEnum.MEF_CODE_4100.getCode(), HzdStatusCodeEnum.MEF_CODE_4100.getMsg());
						}
						userImageVO.setImageId(UUIDGenerator.getUUID());
						userImageVO.setUserId(user.getId());
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
		logger.i("---------------->>>>>>>个人信息预览接口");
		Result<UserApplyInfoVO> userApplyInfoVOResult = userApplyInfoSerivce.selectByApplyId(applyId);

		UserApplyInfoVO userApplyInfoVO = userApplyInfoVOResult.getItems();

		Result<UserInfoVO> userInfoVOResult = userInfoService.selectByApplyId(applyId);
		UserInfoVO userInfoVO = userInfoVOResult.getItems();

		Result<List<UserRelationVO>> listResult = userRelationService.selectByApplyId(applyId);
		List<UserRelationVO> userRelationVOList = listResult.getItems();

		if (userApplyInfoVO == null || userInfoVO == null || userRelationVOList.size() == 0) {
			logger.i("--------------查询个人信息失败");
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_2400.getCode(), HzdStatusCodeEnum.MEF_CODE_2400.getMsg());
		}

		for(UserRelationVO u : userRelationVOList){
			u.setRelationType(dictUtilService.convertDict(DictBase.RELATION_TO_APPLYER,u.getRelationType()));
		}
		logger.i("--------------查询个人信息成功");
		return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(), HzdStatusCodeEnum.MEF_CODE_0000.getMsg(),
				new onlineLoanapplyInfoPreviewForm(userApplyInfoVO, userInfoVO, userRelationVOList));
	}

	@Override
	public BackResult onlineLoanapplyInfoSubmit(UserVO user, String applyId) {

		Result<UserApplyInfoVO> userApplyInfoVOResult = userApplyInfoSerivce.selectByApplyId(applyId);
		UserApplyInfoVO userApplyInfoVOResultitems = userApplyInfoVOResult.getItems();
		if (userApplyInfoVOResultitems == null) {
			logger.i("用户进件申请第七步 无效的借款编号");
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_2400.getCode(),
					HzdStatusCodeEnum.MEF_CODE_2400.getMsg());
		}

		CheckApplyLoanStatus checkApplyLoanStatus = new CheckApplyLoanStatus();
			Result<UserVO> byMobile = userSerivce.getByMobile(user.getMobile());
			UserVO items = byMobile.getItems();
			if(items==null){
				logger.i("用户进件申请第七步,未查询到用户信息");
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_1045.getCode(),HzdStatusCodeEnum.MEF_CODE_1045.getMsg(),null);
			}

		if (BaseConfig.apply_loan_1.equals(items.getApplyStatus())) {
			logger.i("------------------不能重复提交进件信息");
			checkApplyLoanStatus.setIdentityStatus(BaseConfig.card_status_0);
			checkApplyLoanStatus.setApplyLoanStatus(BaseConfig.apply_loan_1);
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_2333.getCode(),
					HzdStatusCodeEnum.MEF_CODE_2333.getMsg(), checkApplyLoanStatus);

		}

			if (BaseConfig.card_status_0.equals(items.getCheckStatus())) {
				logger.i("------------用户已经通过实名认证,直接提交进件信息");
				// 如果用于已经实名认证,直接进件    TODO 提交进件
				UserVO updateUserVO=new UserVO();
				updateUserVO.setId(items.getId());//用户id
				updateUserVO.setApplyStatus(BaseConfig.apply_loan_0);
				Result<Boolean> booleanResult = userSerivce.updateByPrimaryKeySelective(updateUserVO);
				if (StatusCodes.OK == booleanResult.getStatus()) {
					logger.i("------------用户已经通过实名认证,直接提交进件信息成功");
					return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
							HzdStatusCodeEnum.MEF_CODE_0001.getMsg());
				}
				checkApplyLoanStatus.setIdentityStatus(BaseConfig.card_status_0);
				checkApplyLoanStatus.setApplyLoanStatus(BaseConfig.apply_loan_0);
				return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),
						HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), checkApplyLoanStatus);
			}
		  logger.i("------------用户实名认证待审核,进件信息提交到后台");

		UserVO updateUserVO=new UserVO();
		updateUserVO.setId(items.getId());//用户id
		updateUserVO.setApplyStatus(BaseConfig.apply_loan_2);
		Result<Boolean> booleanResult = userSerivce.updateByPrimaryKeySelective(updateUserVO);
		if (StatusCodes.OK == booleanResult.getStatus()) {
			logger.i("------------用户实名认证在审核中,直接提交进件到后台");
			return new BackResult(HzdStatusCodeEnum.MEF_CODE_0001.getCode(),
					HzdStatusCodeEnum.MEF_CODE_0001.getMsg());
		}
		   checkApplyLoanStatus.setIdentityStatus(items.getCheckStatus());
		   checkApplyLoanStatus.setApplyLoanStatus(items.getApplyStatus());

			 return new BackResult(HzdStatusCodeEnum.MEF_CODE_0000.getCode(),
				HzdStatusCodeEnum.MEF_CODE_0000.getMsg(), checkApplyLoanStatus);
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
