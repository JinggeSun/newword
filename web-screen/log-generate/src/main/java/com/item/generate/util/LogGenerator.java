package com.item.generate.util;

import com.sun.tools.javac.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zcm
 */
public class LogGenerator {

    /**
     * 生成日志
     * @param url
     * @param code
     */
    public static void generator(String url,String code) throws Exception{
        if (url == null || url.length() == 0){
            throw new RuntimeException("url is error");
        }
        if (code == null || code.length() != 6){
            throw new RuntimeException("code is error");
        }

        while (true){
            System.out.println("--------------------------");
            String log = RandomUtil.getLog();
            //将log上传
            Map<String,String> param = new HashMap<>(1);
            param.put("data",log);
            System.out.println(log);
            try {
                HttpUtil.post(url,param);
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println("--------------------------");
            //休息3秒
            Thread.sleep(3000);
        }

    }
}
