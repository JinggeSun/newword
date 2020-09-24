package com.item.manager.util;

import com.alibaba.fastjson.JSON;
import com.item.data.mode.Goods;
import com.item.store.data.JDData;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.List;

import static com.item.manager.util.EsUtil.MY_INDEX;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EsUtilTest {

    private final RestHighLevelClient restHighLevelClient;

    @Test
    public void saveOneData() throws IOException {
        List<Goods> goodsList = JDData.getData("耐克");
        IndexRequest indexRequest = new IndexRequest(MY_INDEX);
        indexRequest.id(UUIDUtil.getUniqueId())
                .source(JSON.toJSONString(goodsList.get(0)), XContentType.JSON);
        IndexResponse indexResponse = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println(indexResponse);
    }
}
