package com.item.collect;

import com.alibaba.fastjson.JSON;
import com.item.collect.common.UrlConst;
import com.item.collect.util.DateUtil;
import com.item.collect.util.HttpUtil;
import com.item.collect.util.JsonUtil;
import com.item.collect.util.KafkaUtil;
import com.item.model.entity.NameNodeFSNamesystem;
import com.item.model.entity.NameNodeMsg;
import com.item.model.entity.NamenodeJvmmetrics;


/**
 * @author zcm
 */
public class JvmMetricsApp {

    public static void main(String[] args) {

        NamenodeJvmmetrics namenodeJvmmetrics = null;
        NameNodeFSNamesystem nameNodeFSNamesystem = null;
        /**
         * 1 获取 hadoop信息
         */
        try {
            String jvmUrl = UrlConst.getNamenodeJvmmetrics();
            String jvmResponse = HttpUtil.get(jvmUrl);
            //将返回值解析为实体
            namenodeJvmmetrics = JsonUtil.parseHadoopResponse(jvmResponse,NamenodeJvmmetrics.class);
            namenodeJvmmetrics.setCreateTime(DateUtil.getNowTime());
        }catch (Exception e){
            e.printStackTrace();
        }

        /**
         * 2 获取 hadoop信息
         */
        try {
            String fsUrl = UrlConst.getNamenodeFsnamesystem();
            String jvmResponse = HttpUtil.get(fsUrl);
            //将返回值解析为实体
            nameNodeFSNamesystem = JsonUtil.parseHadoopResponse(jvmResponse,NameNodeFSNamesystem.class);
            nameNodeFSNamesystem.setCreateTime(DateUtil.getNowTime());

        }catch (Exception e){
            e.printStackTrace();
        }

        NameNodeMsg nameNodeMsg = new NameNodeMsg();
        nameNodeMsg.setNameNodeFSNamesystem(nameNodeFSNamesystem);
        nameNodeMsg.setNamenodeJvmmetrics(namenodeJvmmetrics);
        //序列化
        String jsonObject = JSON.toJSONString(nameNodeMsg);
        System.out.println(jsonObject);
        //发送kafka
        KafkaUtil.initKafka(jsonObject);

    }
}
