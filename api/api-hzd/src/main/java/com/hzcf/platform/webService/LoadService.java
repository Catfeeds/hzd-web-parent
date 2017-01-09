package com.hzcf.platform.webService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.hzcf.platform.api.config.ConstantsDictionary;
import com.hzcf.platform.api.util.AESUtil;
import com.hzcf.platform.api.util.HttpTool;
import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.utils.Md5Util;
import com.hzcf.platform.core.user.model.UserVO;

/**
  * @Description:对借款信息的操作，如：进件，借款人查询借款进度
  * 	该类专门负责线上和调度的对接	
  * @author 作者:裴高祥
  * @date 创建时间：2017年1月7日 下午6:37:17 
  * @version 1.0 
  * @since  JDK1.7
  */
public class LoadService {
	private static Logger logger = Logger.getLogger(LoadService.class);
	/**
	 * @Title: insertLoad 
	 * @Description:线下和调度对接，进件，就是保存借款信息
	 * @time: 2017年1月7日 下午6:58:46  
	 * @return:String
	 */
	public static String insertLoad(String applyId){
		/**初始化参数*/
		Map<String,Object> applyDataMap = new HashMap<String,Object>();//总的数据集合HuiZhongApplicationVo
		List<Map<String,Object>> borrowRelationList = new ArrayList<Map<String,Object>>();//借款人关系List
		Map<String,Object> borrowRelationMap = new HashMap<String,Object>();//借款人Map
		List<Map<String,Object>> imageList = new ArrayList<Map<String,Object>>();//图片List
		Map<String,Object> imageMap = new HashMap<String,Object>();//图片Map
		String systemId = "APP";//系统标示
		String single="";//签名信息
		String mobile="13211980914";//申请人手机号
		String key=ConstantsDictionary.dispatchLoadKey;//调度的“查询借款进度”接口的密钥
		String result="";//返回结果
		/**封装参数*/
		applyDataMap.put("systemId",systemId);
		applyDataMap.put("employeeId","201612240908");
		applyDataMap.put("operatorId","1223434235");
		applyDataMap.put("name","盖伦");
		applyDataMap.put("education","20");
		applyDataMap.put("domicileProvince","110000");
		applyDataMap.put("domicileCity","110100");
		applyDataMap.put("domicilePostCode","100020");
		applyDataMap.put("idType","01");
		applyDataMap.put("idNum","110226198501272116");
		applyDataMap.put("idValidityDate","20201212");
		applyDataMap.put("birthday","19821212");
		applyDataMap.put("gender","1");
		applyDataMap.put("marriageStatus","10");
		applyDataMap.put("childrenStatus","2");
		applyDataMap.put("houseStatus","1");
		applyDataMap.put("residentProvince","110000");
		applyDataMap.put("residentCity","110100");
		applyDataMap.put("residentAddress","1000101");
		applyDataMap.put("residentTelAreaCode","010");
		applyDataMap.put("residentTelCode","88888888");
		applyDataMap.put("residentPostCode","100020");
		applyDataMap.put("mobile1","15883819899");
		applyDataMap.put("email","1333333@qq.com");
		applyDataMap.put("annualIncome",new BigDecimal(1000000));
		applyDataMap.put("creditCardLimit",new BigDecimal(1000000));
		applyDataMap.put("liveTogether","1");
		applyDataMap.put("loanPurposeOne","1");
		applyDataMap.put("loanPurposeTwo","2");
		applyDataMap.put("minApplyAmount",new BigDecimal(10000));
		applyDataMap.put("maxApplyAmount",new BigDecimal(1000000));
		applyDataMap.put("maxMonthlyPayment",new BigDecimal(2000));
		applyDataMap.put("orgName","汇众");
		applyDataMap.put("orgType","1");
		applyDataMap.put("orgProvince","110000");
		applyDataMap.put("orgCity","110100");
		applyDataMap.put("orgAddress","1101001");
		applyDataMap.put("orgPostCode","100020");
		applyDataMap.put("orgTelAreaCode","010");
		applyDataMap.put("orgTelCode","00000000");
		applyDataMap.put("bankId","0");
		applyDataMap.put("bankProvinceId","110000");
		applyDataMap.put("bankCityId","110100");
		applyDataMap.put("bankName","建外支行");
		applyDataMap.put("bankAccountName","盖伦先生");
		applyDataMap.put("bankAccountNo","62262201232095402890");
		applyDataMap.put("isExpress","0");
		applyDataMap.put("productId","1");
		applyDataMap.put("period","12");
		applyDataMap.put("receiverLoginName","嘉文");
		applyDataMap.put("isInside","1");
		applyDataMap.put("orgTeamId","12");
		applyDataMap.put("borrowType","3");
		
		//图片文件
		imageMap.put("imageType","1");
		imageMap.put("artWork","567");
		imageMap.put("mid","23232");
		imageMap.put("small","33333");
		imageMap.put("displayName","33335454");
		imageList.add(imageMap);
		applyDataMap.put("imageList", imageList);
		
		//借款人关系
		borrowRelationMap.put("name","赵信");
		borrowRelationMap.put("relationType","2");
		borrowRelationMap.put("telCode","15687878787");
		borrowRelationMap.put("type","0");
		borrowRelationList.add(borrowRelationMap);
		applyDataMap.put("borrowRelationList",borrowRelationList);
		
		try {
			//MD5加密
			single=Md5Util.getMD5String(StringUtils.join(new String[]{systemId,mobile}, ","),key);
			applyDataMap.put("signature", single);
			applyDataMap.put("systemSourceId", systemId);
			//Map对象转换成Json字符串
			String str=JsonUtil.json2String(applyDataMap);
			logger.info("接口：进件。请求参数："+str);
			//AES加密
			str = AESUtil.enCrypt(str,key);
			logger.info("接口：进件。加密后的参数："+str);
			str = "addHuiZhongApplyInfoParms="+str;
			//发送Http请求，POST方式
			result=HttpTool.sendPostJson(ConstantsDictionary.dispatchInsertLoadUrl,str);
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
	public static String selectLoadProgress(UserVO user){
		/**初始化参数*/
		String result="";//设置返回结果
		String idcard =user.getIdCard();//身份证号
		String systemId = "APP";//系统标识
		String single="";//签名信息
		String key = ConstantsDictionary.dispatchLoadKey;//调度的“查询借款进度”接口的密钥
		//发送数据的Map
		Map<String,Object> weiXinQueryProgressParms = new HashMap<String,Object>();
		weiXinQueryProgressParms.put("idcard",idcard);//身份证号
		weiXinQueryProgressParms.put("systemSourceId", systemId);//系统标识
		try {
			//MD5加密
			single=Md5Util.getMD5String(StringUtils.join(new String[]{systemId,idcard}, ","),key);
			weiXinQueryProgressParms.put("signature", single);
			//将Map对象转换成JSON类型字符串
			String str=JsonUtil.json2String(weiXinQueryProgressParms);
			logger.info("接口：借款人查询借款进度。请求参数："+str);
			//AES加密
			str = AESUtil.enCrypt(str,key);
			logger.info("接口：借款人查询借款进度。加密后的参数："+str);
			str = "weiXinQueryProgressParms="+str;
			//发送Http请求，POST方式
			result = HttpTool.sendPostJson(ConstantsDictionary.dispatchSelectLoadProgressUrl, str);
			logger.info("接口：借款人查询借款进度。返回结果："+result);
		} catch (Exception e) {
			logger.error("接口：借款人查询借款进度。发生异常，异常信息："+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	public static void main(String[] args) {
		System.out.println(insertLoad("123"));
	}
}