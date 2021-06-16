package com.item.collect.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.item.collect.common.UrlConst;
import com.item.collect.entity.Beans;
import com.item.model.entity.NamenodeJvmmetrics;

import java.io.IOException;

public class JsonUtil {

    public static <T> T parseObject(String text, Class<T> clazz) {
        return JSONObject.parseObject(text, clazz);
    }

    public static <T> T parseHadoopResponse(String hadoopResponse,Class<T> clazz){
        Beans beans = parseObject(hadoopResponse,Beans.class);
        assert beans != null;
        JSONObject object = (JSONObject) beans.getBeans().get(0);
        return JSON.toJavaObject(object,clazz);
    }


    public static void main(String[] args) throws IOException {

        String res = HttpUtil.get(UrlConst.getNamenodeJvmmetrics());
        NamenodeJvmmetrics n = parseHadoopResponse(res,NamenodeJvmmetrics.class);
        System.out.println(n);

    }

}
