package com.hzcf.platform.api.util;

import com.hzcf.platform.api.config.ConstantsDictionary;

/**
 * Created by leijiaming on 2017/1/6 0006.
 */
public class ImageUrlUtil {

    public static String geturl(String url){
        return ConstantsDictionary.imgUpload+"/"+url;
    }
}
