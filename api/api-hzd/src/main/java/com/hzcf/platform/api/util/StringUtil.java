package com.hzcf.platform.api.util;

import com.hzcf.platform.api.config.ConstantsDictionary;
import com.hzcf.platform.common.util.utils.ServiceUtil;
import org.apache.commons.collections.bag.SynchronizedSortedBag;

import java.util.Date;

/**
 * Created by leijiaming on 2017/1/9 0009.
 */
public class StringUtil {


    public static String getSuffix(String url) {
        if (url != null) {
            int index = url.lastIndexOf(".");
            if (index > 0) {
                return url.substring(index + 1);
            }
        }
        return url;
    }

    public static String getSufFirst(String url){
        if (url != null) {
            int index = url.indexOf("/");
            if (index > 0) {
                return url.substring(index + 1);
            }
        }
        return url;
    }

    public static String replaceAll(String url){

        return url.replaceAll(ConstantsDictionary.imgUpload,"");
    }


    //主方法测试
    public static void main(String[] args) {
      /*  String m= "rest/api/100/delete/imgurl";
        System.out.print(getSuffix(m));*/
       /* Date BirthdayDate = CustomerUtils.calculateBirthDate("350301198002075153");
        System.out.print(BirthdayDate);
       */ boolean b = ServiceUtil.validateIdNo("330726196507040016");
        System.out.println(b);
       /* Date date = DateExtendUtils.parseDate("2012-01-18");
        System.out.print(date);*/
    }
}
