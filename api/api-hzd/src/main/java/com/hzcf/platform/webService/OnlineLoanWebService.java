package com.hzcf.platform.webService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.stereotype.Component;

import com.hzcf.platform.api.model.OnlineLoanInfo;
import com.hzcf.platform.api.util.HttpRequestUtil;
import com.hzcf.platform.common.util.http.HttpRequest;
import com.hzcf.platform.common.util.json.parser.JsonUtil;
import com.hzcf.platform.common.util.log.Log;
import com.hzcf.platform.common.util.utils.AESUtil;
import com.hzcf.platform.common.util.utils.Md5Util;
import com.hzcf.platform.api.config.ConstantsDictionary;

/**
 * 
 * @description:进件申请和查询
 * @author 雷佳明
 * @version 1.0
 * 
 *          <pre>
 * Modification History: 
 * Date              Author      Version     Description 
 * ------------------------------------------------------------------ 
 * 2016年12月7日                       雷佳明                           1.0       1.0 Version
 *          </pre>
 */
@Component
public class OnlineLoanWebService {
	private static final Log logger = Log.getLogger(OnlineLoanWebService.class);

	/**
	 * @throws Exception
	 * 
	 * @Description: 申请 @User: 雷佳明 @FileName: WipeRecordMgr.java @param
	 * 参数 @return 返回类型 @date 2016年12月7日 @throws
	 */
	public String OnlineLoanApply(OnlineLoanInfo onlineLoanInfo) throws Exception {

		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			// 借款参数拼接
			String single = Md5Util.getMD5String(
					StringUtils.join(new String[] { ConstantsDictionary.APP, onlineLoanInfo.getOpenId() }, ","),
					ConstantsDictionary.KEY);

			jsonMap.put("phoneNumber", onlineLoanInfo.getMobile());
			jsonMap.put("name", onlineLoanInfo.getName());
			jsonMap.put("idcard", onlineLoanInfo.getIdCard());
			jsonMap.put("area", onlineLoanInfo.getArea());
			jsonMap.put("openid", onlineLoanInfo.getOpenId());
			jsonMap.put("systemSourceId", ConstantsDictionary.APP);
			jsonMap.put("signature", single);

			String ReqJson = JsonUtil.json2String(jsonMap);
			logger.i("外访协助请求的JSON数据：" + ReqJson);
			String billParms;

			billParms = AESUtil.enCrypt(ReqJson, ConstantsDictionary.KEY);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("weiXinSubmitApplicationParms", billParms));//POST
			String paramsData = URLEncodedUtils.format(params, "UTF-8"); //GET
			URLEncodedUtils.format(params, "UTF-8");
			//String sendRsp = HttpRequest.sendGet(ConstantsDictionary.offlineInsertLoad, paramsData);
			String result=HttpRequestUtil.sendPost(ConstantsDictionary.offlineInsertLoad,paramsData);

			return result;
		} catch (Exception e) {
			throw new Exception("外访协助接口服务异常");
		}

	}

	/**
	 * @return
	 * 
	 * @Description: 此方法作废
	 * 参数 @return 返回类型 @date 2016年12月7日 @throws
	 */
	public String OnlineLoanQuery(String mobile) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			// 借款参数拼接
			String single = Md5Util.getMD5String(
					StringUtils.join(new String[] { ConstantsDictionary.APP, mobile }, ","), ConstantsDictionary.KEY);

			// TODO 目前是openid
			jsonMap.put("openid", mobile);
			jsonMap.put("systemSourceId", ConstantsDictionary.APP);
			jsonMap.put("signature", single);
			String ReqJson = JsonUtil.json2String(jsonMap);
			logger.i("请求的JSON数据：" + ReqJson);
			String billParms;

			billParms = AESUtil.enCrypt(ReqJson, ConstantsDictionary.KEY);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("weiXinQueryProgressParms", billParms));
			String paramsData = URLEncodedUtils.format(params, "UTF-8");
			String sendRsp = HttpRequest.sendGet(ConstantsDictionary.APP, paramsData);
			logger.i("返回参数：" + sendRsp);
			// WxjinjianQueryRsp wxrsp
			// =JsonUtil.string2Object(json.toString(),WxjinjianQueryRsp.class);
			return sendRsp;
		} catch (Exception e) {
			throw new Exception("借款查询接口服务异常");
		}
	}
}
