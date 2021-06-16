package com.item.hive.service;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.api.MetaException;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zcm
 */

public class MyHiveService {

    private String hiveJdbcUrl;
    private String username;
    private String password;
    private String hiveThriftUrl;

    private static HiveMetaStoreClient hiveMetaStoreClient;

    public MyHiveService(String hiveJdbcUrl,String username,String password,String hiveThriftUrl){
        this.hiveJdbcUrl = hiveJdbcUrl;
        this.username = username;
        this.password = password;
        this.hiveThriftUrl = hiveThriftUrl;
    }

    private void getHiveConnect() throws MetaException {
        if (hiveMetaStoreClient == null) {
            HiveConf hiveConf = new HiveConf();
            hiveConf.set("hive.metastore.uris", this.hiveThriftUrl);
            hiveMetaStoreClient = new HiveMetaStoreClient(hiveConf);
        }
    }

    /**
     * 所有数据库
     * @return
     */
    public List<String> getAllDataBases(){
        List<String> resList = new ArrayList<>(3);
        try {
            resList = hiveMetaStoreClient.getAllDatabases();
        } catch (MetaException e) {
            e.printStackTrace();
        }
        return resList;
    }

}
