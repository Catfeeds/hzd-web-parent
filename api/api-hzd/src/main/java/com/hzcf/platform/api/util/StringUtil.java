package com.hzcf.platform.api.util;

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



    //主方法测试
    public static void main(String[] args) {
        String m= "rest/api/100/delete/imgurl";
        System.out.print(getSuffix(m));

    }
}
