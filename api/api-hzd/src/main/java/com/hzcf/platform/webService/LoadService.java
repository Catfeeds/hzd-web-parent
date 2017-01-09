package com.hzcf.platform.webService;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import com.hzcf.platform.api.config.ConstantsDictionary;
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
	public static String insertLoad(UserVO user,Map map){
		/**初始化参数*/
		String result="";//设置返回结果
		String idCard=user.getIdCard();//身份证号
		String systemSourceId="";//系统标识
		String signature="";//签名信息
		String key=ConstantsDictionary.dispatchLoadKey;//调度的“查询借款进度”接口的密钥
		//发送数据的Map
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("idCard",idCard);//身份证号
		params.put("systemSourceId",systemSourceId);//系统标识
		try {
			signature = Md5Util.getMD5String(StringUtils.join(new String[]{idCard,systemSourceId}, ","),key);
			params.put("signature",signature);//签名信息
			//Map转换成JSON类型的字符串
			String paramsJson=JsonUtil.json2String(params);
			logger.info("接口：进件，就是保存借款信息。请求参数："+paramsJson);
			result = HttpTool.doPostJson(ConstantsDictionary.dispatchInsertLoadUrl,paramsJson);
			logger.info("接口：进件，就是保存借款信息。返回结果："+result);
		} catch (Exception e) {
			logger.error("接口：进件，就是保存借款信息。发生异常，异常信息："+e.getMessage());
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
		String idCard=user.getIdCard();//身份证号
		String systemSourceId="";//系统标识
		String signature="";//签名信息
		String key=ConstantsDictionary.dispatchLoadKey;//调度的“查询借款进度”接口的密钥
		//发送数据的Map
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("idCard",idCard);//身份证号
		params.put("systemSourceId",systemSourceId);//系统标识
		try {
			signature = Md5Util.getMD5String(StringUtils.join(new String[]{idCard,systemSourceId}, ","),key);
			params.put("signature",signature);//签名信息
			//Map转换成JSON类型的字符串
			String paramsJson=JsonUtil.json2String(params);
			logger.info("接口：借款人查询借款进度。请求参数："+paramsJson);
			result = HttpTool.doPostJson(ConstantsDictionary.dispatchSelectLoadProgressUrl,paramsJson);
			logger.info("接口：借款人查询借款进度。返回结果："+result);
		} catch (Exception e) {
			logger.error("接口：借款人查询借款进度。发生异常，异常信息："+e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
}