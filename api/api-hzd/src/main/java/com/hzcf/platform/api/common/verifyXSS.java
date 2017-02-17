package com.hzcf.platform.api.common;

import com.hzcf.platform.api.filter.MyRequestWrapper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * Created by leijiaming on 2017/2/16 0016.
 */
public class verifyXSS {

    private  static  final  String[]  XSS ={"<",">","script"};
    public static boolean verify(HttpServletRequest request) {
        MyRequestWrapper myRequestWrapper = null;
        try {
            myRequestWrapper = new MyRequestWrapper((HttpServletRequest) request);
            String body = myRequestWrapper.getBody();
            if(StringUtils.isNotBlank(body)){

                for (int i = 0;i<XSS.length;i++){
                    int i1 = body.indexOf(XSS[i]);
                    if(i1!=-1){
                        return  false;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
