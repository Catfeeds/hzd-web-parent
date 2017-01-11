package com.hzcf.platform.webService;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hzcf.platform.api.config.ConstantsDictionary;
import com.hzcf.platform.api.util.AESUtil;
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
//		/**初始化参数*/
//		Map<String,Object> applyDataMap = new HashMap<String,Object>();//总的数据集合HuiZhongApplicationVo
//		List<Map<String,Object>> borrowRelationList = new ArrayList<Map<String,Object>>();//借款人关系List
//		Map<String,Object> borrowRelationMap = new HashMap<String,Object>();//借款人Map
//		List<Map<String,Object>> imageList = new ArrayList<Map<String,Object>>();//图片List
//		Map<String,Object> imageMap = new HashMap<String,Object>();//图片Map
//		String systemId = "APP";//系统标识
//		String single="";//签名信息
//		String mobile="15883819899";//申请人手机号
////		String key=ConstantsDictionary.dispatchLoadKey;//调度的“查询借款进度”接口的密钥
//		String key="01b503cf15f16f5e9c95938d09ef1219";//调度的“查询借款进度”接口的密钥
//		String result="";//返回结果
//		/**封装参数*/
//		applyDataMap.put("systemId",systemId);
//		applyDataMap.put("employeeId","201612240908");
//		applyDataMap.put("operatorId","1223434235");
//		applyDataMap.put("name","盖伦");
//		applyDataMap.put("education","20");
//		applyDataMap.put("domicileProvince","110000");
//		applyDataMap.put("domicileCity","110100");
//		applyDataMap.put("domicilePostCode","100020");
//		applyDataMap.put("idType","01");
//		applyDataMap.put("idNum","410581198901129097");
//		sdf.applyPattern("yyyy-MM-dd");
//		applyDataMap.put("idValidityDate",sdf.parse("2036-01-28"));
//		applyDataMap.put("birthday","19890112");
//		applyDataMap.put("gender","1");
//		applyDataMap.put("marriageStatus","10");
//		applyDataMap.put("childrenStatus","2");
//		applyDataMap.put("houseStatus","1");
//		applyDataMap.put("residentProvince","110000");
//		applyDataMap.put("residentCity","110100");
//		applyDataMap.put("residentAddress","1000101");
//		applyDataMap.put("residentTelAreaCode","010");
//		applyDataMap.put("residentTelCode","88888888");
//		applyDataMap.put("residentPostCode","100020");
//		applyDataMap.put("mobile1","15883819899");
//		applyDataMap.put("email","1333333@qq.com");
//		applyDataMap.put("annualIncome",new BigDecimal(1000000));
//		applyDataMap.put("creditCardLimit",new BigDecimal(1000000));
//		applyDataMap.put("liveTogether","1");
//		applyDataMap.put("loanPurposeOne","1");
//		applyDataMap.put("loanPurposeTwo","2");
//		applyDataMap.put("minApplyAmount",new BigDecimal(10000));
//		applyDataMap.put("maxApplyAmount",new BigDecimal(1000000));
//		applyDataMap.put("maxMonthlyPayment",new BigDecimal(2000));
//		applyDataMap.put("orgName","汇众");
//		applyDataMap.put("orgType","1");
//		applyDataMap.put("orgProvince","110000");
//		applyDataMap.put("orgCity","110100");
//		applyDataMap.put("orgAddress","1101001");
//		applyDataMap.put("orgPostCode","100020");
//		applyDataMap.put("orgTelAreaCode","010");
//		applyDataMap.put("orgTelCode","00000000");
//		applyDataMap.put("bankId","0");
//		applyDataMap.put("bankProvinceId","110000");
//		applyDataMap.put("bankCityId","110100");
//		applyDataMap.put("bankName","建外支行");
//		applyDataMap.put("bankAccountName","盖伦先生");
//		applyDataMap.put("bankAccountNo","62262201232095402890");
//		applyDataMap.put("isExpress","0");
//		applyDataMap.put("productId","1");
//		applyDataMap.put("period","12");
//		applyDataMap.put("receiverLoginName","嘉文");
//		applyDataMap.put("isInside","1");
//		applyDataMap.put("orgTeamId","12");
//		applyDataMap.put("borrowType","3");
//
//		//图片文件
//		imageMap.put("imageType","1");
//		imageMap.put("artWork","567");
//		imageMap.put("mid","23232");
//		imageMap.put("small","33333");
//		imageMap.put("displayName","33335454");
//		imageList.add(imageMap);
//		applyDataMap.put("imageList", imageList);
//
//		//借款人关系
//		borrowRelationMap.put("name","赵信");
//		borrowRelationMap.put("relationType","2");
//		borrowRelationMap.put("telCode","15687878787");
//		borrowRelationMap.put("type","0");
//		borrowRelationList.add(borrowRelationMap);
//		applyDataMap.put("borrowRelationList",borrowRelationList);
//
//		try {
//			//MD5加密
//			single=Md5Util.getMD5String(StringUtils.join(new String[]{systemId,mobile}, ","),key);
//			logger.info("接口：进件。请求参数signature："+single);
//			applyDataMap.put("signature", single);
//			applyDataMap.put("systemSourceId", systemId);
//			//Map对象转换成Json字符串
//			String str=JsonUtil.json2String(applyDataMap);
//			logger.info("接口：进件。请求参数转换成Json字符串："+str);
//			//AES加密
//			str = AESUtil.enCrypt(str,key);
//			logger.info("接口：进件。加密后的参数："+str);
//			str = "addHuiZhongApplyInfoParms="+str;
//			//发送Http请求，POST方式
////			result=HttpRequestUtil.sendPost(ConstantsDictionary.dispatchInsertLoadUrl,str);
//			result=HttpRequestUtil.sendPost("http://10.10.16.131:8080/Dispatch/app/huizhong2/addHuiZhongApplyInfo.do",str);
//			logger.info("接口：进件。返回的结果："+result);
//		} catch (Exception e) {
//			logger.error("接口：进件。发生异常，异常信息："+e.getMessage());
//			e.printStackTrace();
//		}
//		return result;



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
			JSONObject applyDataMap = new JSONObject();//总的数据集合HuiZhongApplicationVo
			JSONObject userInfoVOJsonObject=JSONObject.fromObject(userInfoVO);
			JSONObject userApplyInfoJsonObject=JSONObject.fromObject(userApplyInfo);
			JSONObject userVOJsonObject=JSONObject.fromObject(userVO);
			JSONArray userRelationVOListJsonArrray=JSONArray.fromObject(userRelationVOList);
			JSONArray userImageVOListJsonArrray=JSONArray.fromObject(userImageVOList);
			//封装参数
			applyDataMap.putAll(userInfoVOJsonObject);
			applyDataMap.putAll(userApplyInfoJsonObject);
			applyDataMap.putAll(userVOJsonObject);
			applyDataMap.put("borrowRelationList", userRelationVOListJsonArrray);
			applyDataMap.put("imageList", userImageVOListJsonArrray);
			//对一些参数进行补充完善，
			applyDataMap.put("employeeId",userInfoVO.getStaffNo());//员工编号
			applyDataMap.remove("staffNo");
			applyDataMap.put("operatorId",operatorId);//操作人ID
			applyDataMap.put("idType","01");//线上只有身份证号，传递"01"
			applyDataMap.put("idNum",userVO.getIdCard());//身份证号
			applyDataMap.remove("idCard");
			sdf.applyPattern("yyyy-MM-dd");
			applyDataMap.put("idValidityDate",sdf.parse(userInfoVO.getIdcardValidity()));//证件有效期
			applyDataMap.remove("idcardValidity");
			applyDataMap.put("mobile1",mobile);//电话
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