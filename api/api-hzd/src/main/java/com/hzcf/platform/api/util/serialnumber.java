package com.hzcf.platform.api.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by leijiaming on 2017/1/7 0007.
 */
public class serialnumber {


        /**
         * 获取现在时间
         * @return返回字符串格式yyyyMMddHHmmss
         */
        public static String getStringDate() {
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
            String dateString = formatter.format(currentTime);
            System.out.println("TIME:::"+dateString);
            return dateString;
        }
        /**
         * 由年月日时分秒+3位随机数
         * 生成流水号
         * @return
         */
        public static String Getnum(){
            String t = getStringDate();
            int x=(int)(Math.random()*900)+100;
            String serial = "APP"+t + x;
            return serial;
        }

        //主方法测试
        public static void main(String[] args) {
            String m= Getnum();
            System.out.println(m);
        }

}
