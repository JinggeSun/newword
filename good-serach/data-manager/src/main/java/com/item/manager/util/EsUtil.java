package com.item.manager.util;

import com.alibaba.fastjson.JSON;
import com.item.data.mode.Goods;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.MatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author zcm
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EsUtil {

    private final RestHighLevelClient restHighLevelClient;
    public static final String MY_INDEX = "goods";

    /**
     * 批量插入数据
     * @param list
     * @return
     * @throws IOException
     */
    public boolean saveBatchData(List<Goods> list) throws IOException {

        BulkRequest bulkRequest = new BulkRequest();

        final int[] objId = {1};
        list.forEach(goods -> {
            bulkRequest.add(new IndexRequest("goods").id(objId[0] +"").source(JSON.toJSONString(goods),XContentType.JSON));
            objId[0] += 1;
        });

        BulkResponse bulkResponse = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        return !bulkResponse.hasFailures();
    }

    /**
     * 保存一条数据
     * @param goods
     * @return
     */
    public IndexResponse saveOneData(Goods goods) throws IOException {
        IndexRequest indexRequest = new IndexRequest(MY_INDEX);
        indexRequest.id(UUIDUtil.getUniqueId())
                .source(JSON.toJSONString(goods),XContentType.JSON);
        return restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
    }

    /**
     * 根据id获取内容
     * @param id
     * @return
     * @throws IOException
     */
    public Map<String, Object> getDataById(String id) throws IOException {
        GetRequest request = new GetRequest(MY_INDEX,id);
        GetResponse response = restHighLevelClient.get(request, RequestOptions.DEFAULT);
        return response.getSource();
    }

    /**
     * 更新
     * @param id
     * @param goods
     * @return
     */
    public boolean updateDataById(String id,Goods goods){
        boolean flag = false;
        try{
            UpdateRequest request = new UpdateRequest(MY_INDEX, id);
            request.timeout(TimeValue.MINUS_ONE);
            request.doc(JSON.toJSONString(goods),XContentType.JSON);
            UpdateResponse update = restHighLevelClient.update(request, RequestOptions.DEFAULT);
            flag = true;
        }catch (IOException e){
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 查询
     * @param name
     * @param start
     * @param size
     * @return
     * @throws IOException
     */
    public SearchHits searchData(String name,Integer start,Integer size) throws IOException {
        SearchRequest searchRequest = new SearchRequest(MY_INDEX);
        SearchSourceBuilder builder = new SearchSourceBuilder();
        MatchQueryBuilder matchQueryBuilder = QueryBuilders.matchQuery("name", name);
        builder.query(matchQueryBuilder).from(start).size(size);
        searchRequest.source(builder);
        SearchResponse search = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
        return search.getHits();
    }

}
