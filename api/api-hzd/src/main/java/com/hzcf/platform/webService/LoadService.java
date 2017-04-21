package com.hzcf.platform.webService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hzcf.platform.common.util.uuid.UUIDGenerator;
import com.hzcf.platform.core.user.model.*;
import com.hzcf.platform.core.user.service.*;
import com.hzcf.platform.webService.model.PatchBoltImage;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.hzcf.platform.api.config.BaseConfig;
import com.hzcf.platform.api.config.ConstantsDictionary;
import com.hzcf.platform.api.util.AESUtil;
import com.hzcf.platform.api.util.DateExtendUtils;
import com.hzcf.platform.api.util.HttpRequestUtil;
import com.hzcf.platform.api.util.Md5Util;
import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.common.util.status.StatusCodes;
import com.hzcf.platform.core.user.webService.model.BorrowRelationVo;
import com.hzcf.platform.core.user.webService.model.HuiZhongApplicationVo;
import com.hzcf.platform.core.user.webService.model.ImageVo;

import net.sf.json.JSONObject;

/**
  * @Description:对借款信息的操作，如：进件，借款人查询借款进度
  * 	该类专门负责线上和调度的对接
  * @author 作者:裴高祥
  * @date 创建时间：2017年1月7日 下午6:37:17
  * @version 1.0
  * @since  JDK1.7
  */
@Component
public class LoadService {
	private static Logger logger = Logger.getLogger(LoadService.class);
	@Autowired
	public UserService userSerivce;//用户service
	@Autowired
	public UserApplyInfoSerivce userApplyInfoSerivce;//用户申请信息service
	@Autowired
	public UserInfoService userInfoService;//用户信息service
	@Autowired
	public UserRelationService userRelationService;//用户关系service
	@Autowired
	public UserImageService userImageService;//用户图片service
	//public SimpleDateFormat sdf = new SimpleDateFormat();//日期操作类,"yyyy-MM-dd HH:mm:ss"
	@Autowired
	public UserApplyLogService userApplyLogService;
	/**
	 * @Title: insertLoad 进件接口
	 * @Description:线下和调度对接，进件，就是保存借款信息
	 * 	注意：这个接口只发送进件信息，不会修改数据库中的“进件状态字段”
	 * @time: 2017年1月7日 下午6:58:46
	 * @return:String
	 * @throws Exception
	 */
	@Transactional(propagation=Propagation.REQUIRED)
	public String insertLoad(String applyId) {
		/**初始化参数*/
		String result="";//返回结果
		//发送到调度的参数信息
		String signature="";//签名信息
		String systemSourceId=ConstantsDictionary.APP;//系统标识,就是“APP”
		String systemId=ConstantsDictionary.APP;//进件标识,就是“APP”
		String operatorId=ConstantsDictionary.APP;//操作人ID,就是“APP”
		String mobile="";//申请人手机号
		String key=ConstantsDictionary.KEY;//调度的“查询借款进度”接口的密钥
		//用于查询的参数信息
		String userId="";//用户id
			/**查询数据库，获取参数*/
			//借款人详细信息
			Result<UserInfoVO> userInfoVOResult=userInfoService.selectByApplyId(applyId);
			UserInfoVO userInfoVO=userInfoVOResult.getItems();
			//借款人申请信息
			Result<UserApplyInfoVO> userApplyInfoResult=userApplyInfoSerivce.selectByApplyId(applyId);
			UserApplyInfoVO userApplyInfo=userApplyInfoResult.getItems();
			//用户id
			userId=userApplyInfo.getUserId();
			//借款人信息
			Result<UserVO> userVOResult=userSerivce.selectByPrimaryKey(userId);
			UserVO userVO=userVOResult.getItems();
			//用户电话
			mobile=userVO.getMobile();
			//借款人关系集合
			Result<List<UserRelationVO>> userRelationVOListResult=userRelationService.selectByApplyId(applyId);
			List<UserRelationVO> userRelationVOList=userRelationVOListResult.getItems();
			//借款人图片信息
			Result<List<UserImageVO>> userImageVOListResult1=userImageService.selectByApplyId(applyId);
			Result<List<UserImageVO>> userImageVOList=userImageService.getUserId(userId);
			List<UserImageVO> userImage1=userImageVOListResult1.getItems();
			List<UserImageVO> userImageVO2=userImageVOList.getItems();
			userImage1.addAll(userImageVO2);
			/**组装参数，封装成要发送的数据*/
			//借款人基本数据
			HuiZhongApplicationVo huiZhongApplicationVo=new HuiZhongApplicationVo();
			huiZhongApplicationVo.setName(userVO.getName());
			huiZhongApplicationVo.setEducation(userInfoVO.getEducation());
			huiZhongApplicationVo.setDomicileProvince(userInfoVO.getDomicileProvince());
			huiZhongApplicationVo.setDomicileCity(userInfoVO.getDomicileCity());
			huiZhongApplicationVo.setDomicilePostCode(userInfoVO.getDomicilePostCode());
			huiZhongApplicationVo.setIdType("01");//线上只有身份证号
			huiZhongApplicationVo.setIdNum(userVO.getIdCard());//设置身份证号
			//设置证件的有效期
			Date date1=DateExtendUtils.parseDate(userInfoVO.getIdcardValidity());
			huiZhongApplicationVo.setIdValidityDate(date1.getTime());//证件有效期
			//设置“出生日期”
			huiZhongApplicationVo.setBirthday((userInfoVO.getBirthday()).getTime());
			huiZhongApplicationVo.setGender(userInfoVO.getGender());
			huiZhongApplicationVo.setMarriageStatus(userInfoVO.getMarriageStatus());
			huiZhongApplicationVo.setChildrenStatus(userInfoVO.getChildrenStatus());
			huiZhongApplicationVo.setHouseStatus(userInfoVO.getHouseStatus());
			huiZhongApplicationVo.setResidentProvince(userInfoVO.getResidentProvince());
			huiZhongApplicationVo.setResidentCity(userInfoVO.getResidentCity());
			huiZhongApplicationVo.setResidentAddress(userInfoVO.getResidentAddress());
			huiZhongApplicationVo.setResidentTelAreaCode(userInfoVO.getResidentTelAreaCode());
			huiZhongApplicationVo.setResidentTelCode(userInfoVO.getResidentTelCode());
			huiZhongApplicationVo.setResidentPostCode(userInfoVO.getResidentPostCode());
			huiZhongApplicationVo.setMobile1(userVO.getMobile());
			huiZhongApplicationVo.setEmail(userInfoVO.getEmail());
			huiZhongApplicationVo.setAnnualIncome(userInfoVO.getAnnualIncome());
			huiZhongApplicationVo.setCreditCardLimit(userInfoVO.getCreditCardLimit());
			huiZhongApplicationVo.setLiveTogether(userInfoVO.getLiveTogether());
			huiZhongApplicationVo.setDomicileAddress(userInfoVO.getDomicileAddress());
			//
			String temp = "";
			if("A".equals(userApplyInfo.getLoanPurposeOne())){
				temp = "CONSUMPTION";
			}else if("B".equals(userApplyInfo.getLoanPurposeOne())){
				temp ="RUN";
			}else if("C".equals(userApplyInfo.getLoanPurposeOne())){
				temp ="EMERGENCY";
			}else if("D".equals(userApplyInfo.getLoanPurposeOne())){
				temp ="OTHER";
			}
			huiZhongApplicationVo.setLoanPurposeOne(temp);
			huiZhongApplicationVo.setLoanPurposeTwo(userApplyInfo.getLoanPurposeTwo());
			huiZhongApplicationVo.setMinApplyAmount(userApplyInfo.getMinApplyAmount());
			huiZhongApplicationVo.setMaxApplyAmount(userApplyInfo.getMaxApplyAmount());
			huiZhongApplicationVo.setMaxMonthlyPayment(userApplyInfo.getMaxMonthlyPayment());
			huiZhongApplicationVo.setOrgName(userInfoVO.getOrgName());
			huiZhongApplicationVo.setOrgType(userInfoVO.getOrgType());
			huiZhongApplicationVo.setOrgProvince(userInfoVO.getOrgProvince());
			huiZhongApplicationVo.setOrgCity(userInfoVO.getOrgCity());
			huiZhongApplicationVo.setOrgAddress(userInfoVO.getOrgAddress());
			huiZhongApplicationVo.setOrgPostCode(userInfoVO.getOrgPostCode());
			huiZhongApplicationVo.setOrgTelAreaCode(userInfoVO.getOrgTelAreaCode());
			huiZhongApplicationVo.setOrgTelCode(userInfoVO.getOrgTelCode());

			huiZhongApplicationVo.setIsExpress(userInfoVO.getIsExpress());
			huiZhongApplicationVo.setProductId(userInfoVO.getProductId());
			huiZhongApplicationVo.setPeriod(userApplyInfo.getPeriod());
			huiZhongApplicationVo.setReceiverLoginName(userInfoVO.getReceiverLoginName());
			huiZhongApplicationVo.setIsInside(userInfoVO.getIsInside());
			huiZhongApplicationVo.setOrgTeamId(userInfoVO.getOrgTeamId());
			huiZhongApplicationVo.setBorrowType(userInfoVO.getBorrowType());
			//借款人的关系集合
			List<BorrowRelationVo> borrowRelationList=new ArrayList<BorrowRelationVo>();
			for(int i=0;i<userRelationVOList.size();i++){
				UserRelationVO userRelationVO=userRelationVOList.get(i);
				BorrowRelationVo borrowRelationVo=new BorrowRelationVo();
				borrowRelationVo.setName(userRelationVO.getName());
				borrowRelationVo.setRelationType(userRelationVO.getRelationType());
				borrowRelationVo.setTelCode(userRelationVO.getMobile());
				borrowRelationVo.setType(userRelationVO.getType());
				borrowRelationList.add(borrowRelationVo);
			}
			huiZhongApplicationVo.setBorrowRelationList(borrowRelationList);
			//借款人的图片集合
			List<ImageVo> imageList=new ArrayList<ImageVo>();
			for(int i=0;i<userImage1.size();i++){
				UserImageVO userImageVO=userImage1.get(i);
				ImageVo imageVo=new ImageVo();
				String imageType=userImageVO.getImageType();//图片类型
				imageVo.setImageType(imageType);
				String artWork=userImageVO.getArtWork();//图片完整路径
				imageVo.setArtWork(artWork);
				imageVo.setDisplayName(imageType+"-"+artWork.substring(artWork.lastIndexOf(".")-5));//图片名称，示例：B1-G8020.JPG
				imageList.add(imageVo);
			}
			huiZhongApplicationVo.setImageList(imageList);			
			//对一些参数进行补充完善，
			JSONObject applyDataMap=JSONObject.fromObject(huiZhongApplicationVo);
			applyDataMap.put("employeeId",userInfoVO.getStaffNo());//员工编号
			applyDataMap.put("operatorId",operatorId);//操作人ID
			/**加密参数*/
			//MD5加密
			signature=Md5Util.getMD5String(StringUtils.join(new String[]{systemSourceId,mobile}, ","),key);
			logger.info("接口：进件。生成的签名信息："+signature);
			applyDataMap.put("signature", signature);//签名信息
			applyDataMap.put("systemSourceId", systemSourceId);//系统标识
			applyDataMap.put("systemId", systemId);//进件标识
			//Map对象转换成Json字符串
//			String str=JsonUtil.json2String(applyDataMap);//此处这个方法不能正确的将对象转成字符串，故不用
			String str=applyDataMap.toString();
			logger.info("接口：进件。请求参数："+str);
			
			try {
				//AES加密
				str = AESUtil.enCrypt(str,key);
				logger.info("接口：进件。加密后的参数："+str);
				str = "addHuiZhongApplyInfoParms="+str;
				/**发送Http请求，POST方式*/
				result=HttpRequestUtil.sendPost(ConstantsDictionary.dispatchLoadInsertLoadUrl,str);
				logger.info("接口：进件。返回的结果："+result);
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		return result;
	}
	/**
	 * @Title: operateLoad 进件接口
	 * @Description:线下和调度对接，进件，就是保存借款信息
	 * 	注意：这个接口不仅会发送进件信息，还会修改数据库中的“进件状态字段”
	 * @time: 2017年1月7日 下午6:58:46
	 * @return:String
	 * @throws Exception
	 */
	@Transactional
	public boolean operateLoad(String applyId,UserVO user) {
			String result=insertLoad(applyId);
			/**根据线下返回的结果，修改借款人的“借款状态”
			 * 线下返回结果示例：{"retInfo":"进件成功!","retCode":"0000"}
			 * */
			logger.info("接口：进件。开始修改数据库中‘进件状态’");
			try {
				if(StringUtils.isNotBlank(result)){
					JSONObject resultJSON=JSONObject.fromObject(result);
					String retCode=resultJSON.getString("retCode");
					String retInfo = resultJSON.getString("retInfo");


					if("0000".equals(retCode)){
						String borrowerApplyId = resultJSON.getString("borrowerApplyId");


						/**修改User中的“借款状态”*/
						//组装参数
						UserVO updateUserVO=new UserVO();
						Result<UserInfoVO> userInfoVOResult=userInfoService.selectByApplyId(applyId);
						UserInfoVO userInfoVO=userInfoVOResult.getItems();
						updateUserVO.setId(userInfoVO.getUserId());
						updateUserVO.setApplyStatus("1");
						//修改数据库user中的进件状态
						Result<Boolean> updateUserVOResult=userSerivce.updateByPrimaryKeySelective(updateUserVO);
						logger.info("修改User中的'进件状态'，结果："+updateUserVOResult.getItems());
						/**修改UserApplyInfo中的“借款状态”*/
						//组装参数
						UserApplyInfoVO updateUserApplyInfoVO=new UserApplyInfoVO();
						updateUserApplyInfoVO.setApplyId(applyId);
						updateUserApplyInfoVO.setStatus("1");
						updateUserApplyInfoVO.setBorrowerApplyId(borrowerApplyId);
						//修改数据库中user_apply_info中的进件状态
						Result<Boolean> updateUserApplyInfoVOResult=userApplyInfoSerivce.updateApplyId(updateUserApplyInfoVO);
						logger.info("修改UserApplyInfo中的'进件状态'，结果："+updateUserApplyInfoVOResult.getItems());
						if(updateUserVOResult.getItems()==false || updateUserApplyInfoVOResult.getItems()==false){
							return false;
						}
					}else{
						logger.info("调用进件接口出错，保存错误日志信息 applyId "+applyId);
						UserApplyLogVO userApplyLog = new UserApplyLogVO();
						userApplyLog.setId(UUIDGenerator.getUUID());
						userApplyLog.setApplyId(applyId);
						userApplyLog.setApplyType("1");
						userApplyLog.setIdCard(user.getIdCard());
						userApplyLog.setReturnContent(retInfo);
						userApplyLog.setReturnTime(new Date());
						userApplyLogService.insertUserApplyLog(userApplyLog);

						return false;
					}
				}else{
					return false;
				}
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		return true;
	}
	/**
	 * @Title: deleteLoad 
	 * @Description:根据userId删除借款人的申请信息 
	 * @time: 2017年1月18日 下午4:53:00  
	 * @return:String
	 */
	public boolean deleteLoad(String userId){
		/**根据userId，status查询借款人的申请信息*/
		//封装参数
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("userId",userId);
		params.put("status",BaseConfig.apply_loan_0);//status=0  表示未进件
		//查询数据
		Result<List<UserApplyInfoVO>> userApplyInfoResult=userApplyInfoSerivce.selectByUserIdAndStatusAll(params);
		if(userApplyInfoResult.getItems()==null){
			return false;//查询借款人的进件信息失败
		}
		List<UserApplyInfoVO> userApplyInfoList=userApplyInfoResult.getItems();
		if(userApplyInfoList.size()==0){
			return true;//借款人没有未进件的申请信息
		}
		/**执行批量删除进件操作*/
		//获取applyId集合
		Map<String,Object> paramsMap=new HashMap<String,Object>();
		List<String> applyIdList=new ArrayList<String>();
		for(int i=0;i<userApplyInfoList.size();i++){
			UserApplyInfoVO uaiv=userApplyInfoList.get(i);
			applyIdList.add(uaiv.getApplyId());
		}
		paramsMap.put("applyIdList", applyIdList);
		paramsMap.put("status",BaseConfig.apply_loan_0);//status=0  表示未进件
		Result<Boolean> deleteImage = null;//批量删除借款人的图片信息
		Result<Boolean> deleteRelation = null;//批量删除借款人的关系信息
		Result<Boolean> deleteApplyInfo = null;//批量删除借款人的申请进件信息

			//执行删除操作
			deleteImage = userImageService.deleteImageByApplyIdList(applyIdList);
			deleteRelation = userRelationService.deleteRelationByApplyIdList(applyIdList);
			deleteApplyInfo = userApplyInfoSerivce.deleteByApplyIdListAndStatus(paramsMap);
			userInfoService.deleteUserInfoByApplyId(userApplyInfoList.get(0).getApplyId());


		/**解析数据操作结果，设置返回数据*/
		if(deleteRelation.getStatus()!=StatusCodes.OK || deleteImage.getStatus()!=StatusCodes.OK || deleteApplyInfo.getStatus()!=StatusCodes.OK){
			return false;
		}
		return true;
	}
	/**
	 * @Title: selectLoadProgress 
	 * @Description:线下和调度对接，借款人查询借款进度
	 * @time: 2017年1月7日 下午6:58:46  
	 * @return:String
	 */
	public static String selectLoadProgress(String idCard){
		/**初始化参数*/
		String result="";//设置返回结果
		//发送到调度的参数信息
//		String idcard =idCard;//身份证号
		String systemSourceId=ConstantsDictionary.APP;//系统标识,就是“APP”
		String signature="";//签名信息
		String key = ConstantsDictionary.KEY;//调度的“查询借款进度”接口的密钥
		//发送数据的Map
		Map<String,Object> weiXinQueryProgressParms = new HashMap<String,Object>();
		weiXinQueryProgressParms.put("phoneNum",idCard);//身份证号
		weiXinQueryProgressParms.put("systemSourceId", systemSourceId);//系统标识
		try {
			//MD5加密
			signature=Md5Util.getMD5String(StringUtils.join(new String[]{systemSourceId,idCard}, ","),key);
			weiXinQueryProgressParms.put("signature", signature);
			logger.info("接口：借款人查询借款进度。signature："+signature);
			//将Map对象转换成JSON类型字符串
			String str=JsonUtil.json2String(weiXinQueryProgressParms);
			logger.info("接口：借款人查询借款进度。请求参数："+str);
			//AES加密
			str = AESUtil.enCrypt(str,key);
			logger.info("接口：借款人查询借款进度。加密后的参数："+str);
			str = "weiXinQueryProgressParms="+str;
			//发送Http请求，POST方式
			result = HttpRequestUtil.sendPost(ConstantsDictionary.dispatchLoadSelectLoadProgressUrl, str);
			logger.info("接口：借款人查询借款进度。返回结果："+result);
		} catch (Exception e) {
			logger.error("接口：借款人查询借款进度。发生异常，异常信息："+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	public static String applyPatchBolt(List<PatchBoltImage>  patchBoltImage,String borrowerId){
		/**初始化参数*/
		String result="";//设置返回结果
		//发送到调度的参数信息
		String systemSourceId=ConstantsDictionary.APP;//系统标识,就是“APP”
		String signature="";//签名信息
		String key = ConstantsDictionary.KEY;//调度的“查询借款进度”接口的密钥
		//发送数据的Map
		Map<String,Object> supplyHuiZhongData = new HashMap<String,Object>();
		supplyHuiZhongData.put("systemSourceId", systemSourceId);//系统标识
		supplyHuiZhongData.put("borrowerApplyId", borrowerId);//借款编号
		supplyHuiZhongData.put("imageList", patchBoltImage);//图片信息
		try {
			//MD5加密
			signature=Md5Util.getMD5String(StringUtils.join(new String[]{systemSourceId,borrowerId}, ","),key);
			supplyHuiZhongData.put("signature", signature);

			//将Map对象转换成JSON类型字符串
			String str=JsonUtil.json2String(supplyHuiZhongData);
			logger.info("接口：补件--》请求参数："+str);
			//AES加密
			str = AESUtil.enCrypt(str,key);

			str = "supplyHuiZhongDataParms="+str;
			//发送Http请求，POST方式
			result = HttpRequestUtil.sendPost(ConstantsDictionary.dispatchLoadSelectLoadProgressUrl, str);
			logger.info("接口：补件---》返回结果："+result);
		} catch (Exception e) {
			logger.error("接口：补件---》发生异常，异常信息："+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}


	public static void main(String[] aaa){
		List<PatchBoltImage>  patchBoltImageList = new ArrayList<>();
		String img = "group1/M00/00/09/CgoKQFj0jMmAbg1pAAJIGTKnX4o868.jpg";
		String img1 = "roup1/M00/00/09/CgoKQFj0gaCAClV7AAOaRJWaHHs412.jpg";
		String img2 = "group1/M00/00/09/CgoKQFj1tfmAMXBbAAJprCPinB8283.jpg";
			PatchBoltImage  patchBoltImage  = new PatchBoltImage();
			PatchBoltImage  patchBoltImage1  = new PatchBoltImage();
			PatchBoltImage  patchBoltImage2  = new PatchBoltImage();

		patchBoltImage.setArtWork(img);
		patchBoltImage.setDisplayName(img+"-"+img.substring(img.lastIndexOf(".")-5));//图片名称，示例：B1-G8020.JPG
		patchBoltImage.setImageType("C3");


		patchBoltImage1.setArtWork(img1);
		patchBoltImage1.setDisplayName(img1+"-"+img1.substring(img1.lastIndexOf(".")-5));//图片名称，示例：B1-G8020.JPG
		patchBoltImage1.setImageType("C8");


		patchBoltImage2.setArtWork(img2);
		patchBoltImage2.setDisplayName(img2+"-"+img2.substring(img2.lastIndexOf(".")-5));//图片名称，示例：B1-G8020.JPG
		patchBoltImage2.setImageType("E1");

		patchBoltImageList.add(patchBoltImage);
		patchBoltImageList.add(patchBoltImage1);
		patchBoltImageList.add(patchBoltImage2);
		String app20170418175508942 = applyPatchBolt(patchBoltImageList, "APP20170418175508942");
		System.out.println(app20170418175508942);
	}

}