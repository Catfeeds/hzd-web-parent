package com.hzcf.platform.webService.model;

import com.hzcf.platform.api.util.DateUtil;
import com.hzcf.platform.api.util.JpushClientUtil;
import com.hzcf.platform.common.util.json.parser.JsonUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lll on 2017-04-24.
 */
public class MainTest {
    public static void main(String[] args){
    /*    List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("msgBoxBack", "dsfdsfds"));
        params.add(new BasicNameValuePair("borrowerApplyId", "11111111"));
        //   UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(params, Charset.forName("UTF-8"));//POST
        String format = URLEncodedUtils.format(params, "UTF-8");//GET·
        //String sendRsp = HttpRequest.sendGet(ConstantsDictionary.offlineInsertLoad, paramsData);
        String url = "http://10.10.16.45:8080/api-hzd/api/100/furtherInformation?";
        String execute = HttpUtil.execute(url + format);
        System.out.println(execute);*/
        //String url = "api-hzd/api/100/furtherInformation?";
        //推送别名用户
        Map<String, Object> jsonmap = new HashMap<>();
        jsonmap.put("tagCode", 201);   //
        jsonmap.put("applyId", "111111222222222");
        System.out.println("结果：：：" + JpushClientUtil.sendToAliasId("bbb4d955fe544a2593576ab6d601390c",
                "汇中贷消息","汇中贷消息", "尊敬的用户，您在"+ DateUtil.getDate()
                        +"提交的线上进件图片资料有部分不正确,需要重新上传补充.",
                JsonUtil.json2String(jsonmap)));
    }
}
