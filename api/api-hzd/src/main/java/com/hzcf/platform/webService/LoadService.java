package com.hzcf.platform.webService;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hzcf.platform.api.config.ConstantsDictionary;
import com.hzcf.platform.api.util.AESUtil;
import com.hzcf.platform.api.util.DateExtendUtils;
import com.hzcf.platform.api.util.HttpRequestUtil;
import com.hzcf.platform.api.util.HttpTool;
import com.hzcf.platform.api.util.Md5Util;
import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.rpc.result.Result;
import com.hzcf.platform.core.user.model.UserApplyInfoVO;
import com.hzcf.platform.core.user.model.UserImageVO;
import com.hzcf.platform.core.user.model.UserInfoVO;
import com.hzcf.platform.core.user.model.UserRelationVO;
import com.hzcf.platform.core.user.model.UserVO;
import com.hzcf.platform.core.user.service.UserApplyInfoSerivce;
import com.hzcf.platform.core.user.service.UserImageService;
import com.hzcf.platform.core.user.service.UserInfoService;
import com.hzcf.platform.core.user.service.UserRelationService;
import com.hzcf.platform.core.user.service.UserService;
import com.hzcf.platform.core.user.webService.model.BorrowRelationVo;
import com.hzcf.platform.core.user.webService.model.HuiZhongApplicationVo;
import com.hzcf.platform.core.user.webService.model.ImageVo;

import net.sf.json.JSONArray;
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
	public SimpleDateFormat sdf = new SimpleDateFormat();//日期操作类,"yyyy-MM-dd HH:mm:ss"

	/**
	 * @Title: insertLoad
	 * @Description:线下和调度对接，进件，就是保存借款信息
	 * @time: 2017年1月7日 下午6:58:46
	 * @return:String
	 * @throws Exception
	 */
	public String insertLoad(String applyId) throws Exception{
		/**初始化参数*/
		String result="";//返回结果
		//发送到调度的参数信息
		String signature="";//签名信息
		String systemSourceId=ConstantsDictionary.dispatchLoadSystemSourceId;//系统标识,就是“APP”
		String systemId=ConstantsDictionary.dispatchLoadSystemId;//进件标识,就是“APP”
		String employeeId="";//员工编号
		String operatorId=ConstantsDictionary.dispatchLoadSystemId;//操作人ID,就是“APP”
		String mobile="";//申请人手机号
		String key=ConstantsDictionary.dispatchLoadKey;//调度的“查询借款进度”接口的密钥
		//用于查询的参数信息
		String userId="";//用户id
		try {
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
			Result<List<UserImageVO>> userImageVOListResult=userImageService.getUserId(userId);
			List<UserImageVO> userImageVOList=userImageVOListResult.getItems();
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
			for(int i=0;i<userImageVOList.size();i++){
				UserImageVO userImageVO=userImageVOList.get(i);
				ImageVo imageVo=new ImageVo();
				imageVo.setImageType(userImageVO.getImageType());
				String artWork=userImageVO.getArtWork();
				imageVo.setArtWork(artWork);
				imageVo.setDisplayName(artWork.substring(artWork.lastIndexOf("/")+1));
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
			//AES加密
			str = AESUtil.enCrypt(str,key);
			logger.info("接口：进件。加密后的参数："+str);
			str = "addHuiZhongApplyInfoParms="+str;
			/**发送Http请求，POST方式*/
			result=HttpRequestUtil.sendPost(ConstantsDictionary.dispatchLoadInsertLoadUrl,str);
			logger.info("接口：进件。返回的结果："+result);
		} catch (Exception e) {
			logger.error("接口：进件。发生异常，异常信息："+e.getMessage());
			e.printStackTrace();
		}
		return result;
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
		String systemSourceId=ConstantsDictionary.dispatchLoadSystemSourceId;//系统标识,就是“APP”
		String signature="";//签名信息
		String key = ConstantsDictionary.dispatchLoadKey;//调度的“查询借款进度”接口的密钥
		//发送数据的Map
		Map<String,Object> weiXinQueryProgressParms = new HashMap<String,Object>();
		weiXinQueryProgressParms.put("idcard",idCard);//身份证号
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
	//测试方法
	public static void main(String[] args) throws Exception {
	}
}