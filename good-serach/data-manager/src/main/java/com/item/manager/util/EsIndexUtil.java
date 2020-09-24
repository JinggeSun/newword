package com.item.manager.util;


import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexAction;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.client.indices.GetIndexResponse;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 索引操作
 * @author zcm
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EsIndexUtil {

    private final RestHighLevelClient restHighLevelClient;

    /**
     * 创建索引
     * @throws IOException
     */
    public boolean createIndex() {
        boolean flag = false;
        try {
            CreateIndexRequest createIndexRequest = new CreateIndexRequest("goods");
            CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
            flag = true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 判断索引是否存在 true 存在，false 不存在
     * @param index
     * @return
     */
    public boolean existIndex(String index) {
        boolean flag = false;
        try {
            GetIndexRequest indexRequest = new GetIndexRequest(index);
            flag = restHighLevelClient.indices().exists(indexRequest,RequestOptions.DEFAULT);
        }catch (IOException e){
               e.printStackTrace();
        }
        return flag;
    }

    /**
     * 删除索引
     * @param index
     * @return
     */
    public boolean deleteIndex(String index){
        boolean flag = false;
        try {
            DeleteIndexRequest indexRequest = new DeleteIndexRequest(index);
            AcknowledgedResponse acknowledgedResponse = restHighLevelClient.indices().delete(indexRequest, RequestOptions.DEFAULT);
            flag = acknowledgedResponse.isAcknowledged();
        }catch (IOException e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 获取所有索引
     * @return
     */
    public  Map<String,List<AliasMetaData>> getAllIndex(){
        Map<String,List<AliasMetaData>> map = new HashMap<>();
        try {
            GetIndexRequest indexRequest = new GetIndexRequest("*");
            GetIndexResponse indexResponse = restHighLevelClient.indices().get(indexRequest, RequestOptions.DEFAULT);
            return indexResponse.getAliases();
        }catch (IOException e){
            e.printStackTrace();
        }
        return map;
    }


}
