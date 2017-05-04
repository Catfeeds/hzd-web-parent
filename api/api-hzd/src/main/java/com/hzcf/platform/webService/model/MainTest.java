package com.hzcf.platform.webService.model;

import com.hzcf.platform.api.util.HttpUtil;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

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
        String url = "api-hzd/api/100/furtherInformation?";

    }
}
