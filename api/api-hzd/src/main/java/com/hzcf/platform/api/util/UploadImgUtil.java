package com.hzcf.platform.api.util;

import com.hzcf.platform.api.config.ConstantsDictionary;

import com.hzcf.platform.common.util.log.Log;
import com.imageserver.ImageServer;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Iterator;

/**
 * Created by lll on 2017-04-13.
 */
@Component
public class UploadImgUtil {
    private static final Log logger = Log.getLogger(UploadImgUtil.class);

    @Autowired
    private  ImageServer imageServer;

    public  String upLoadImg(HttpServletRequest request){
        // 将当前上下文初始化给 CommonsMutipartResolver （多部分解析器）
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
                request.getSession().getServletContext());
        // 检查form中是否有enctype="multipart/form-data"
        String file_url = "";
        if (multipartResolver.isMultipart(request)) {
            // 将request变成多部分request
            MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
            // 获取multiRequest 中所有的文件名
            Iterator iter = multiRequest.getFileNames();
            while (iter.hasNext()) {
                // 一次遍历所有文件
                MultipartFile file = multiRequest.getFile(iter.next().toString());
                if (file != null) {
                    String myFileName = file.getOriginalFilename();
                    try {
                        if (StringUtils.isNotBlank(myFileName)) {
                            //synchronized (this) {
                            byte[] bytes = file.getBytes();
                            file_url = imageServer.uploadFile(file.getBytes(), getSuffix(myFileName));
                            //}
                            //	userImageService
                        }

                         file_url = ConstantsDictionary.imgUpload + "/" + file_url;


                    } catch (Exception e) {
                        e.printStackTrace();
                        return "";
                    }
                }
                logger.i("上传失败，没有读取到文件信息------");

            }


        }
        return file_url;
    }

    private static String getSuffix(String url) {
        if (url != null) {
            int index = url.lastIndexOf(".");
            if (index > 0) {
                return url.substring(index + 1);
            }
        }
        return url;
    }
}
