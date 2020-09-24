package com.item.manager.index;

import com.alibaba.fastjson.JSON;
import com.item.data.mode.Goods;
import com.item.manager.util.EsIndexUtil;
import com.item.manager.util.EsUtil;
import com.item.store.data.JDData;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.cluster.metadata.AliasMetaData;
import org.elasticsearch.search.SearchHits;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author zcm
 */
@RestController
@RequestMapping("/index")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class IndexController {

    private final EsUtil esUtil;
    private final EsIndexUtil esIndexUtil;

    @GetMapping("/{keyword}")
    public boolean getIndex(@PathVariable String keyword) throws IOException {
        System.out.println("keyword="+keyword);
        List<Goods> goodsList = JDData.getData(keyword);
        System.out.println("good list:"+goodsList.size());
        return esUtil.saveBatchData(goodsList);
    }

    @GetMapping("/create")
    public String createIndex() throws IOException {
        esIndexUtil.createIndex();
        return "success";
    }

    @GetMapping("/save/{keyword}")
    public String save(@PathVariable String keyword) throws IOException {
        List<Goods> goodsList = JDData.getData(keyword);
        IndexResponse indexResponse = esUtil.saveOneData(goodsList.get(0));
        System.out.println(indexResponse);
        return "success";
    }

    @GetMapping
    public String getAllIndex(){
        Map<String, List<AliasMetaData>> allIndex = esIndexUtil.getAllIndex();
        return JSON.toJSONString(allIndex);
    }


    @GetMapping("/data/{id}")
    public String getAllIndex(@PathVariable String id) throws IOException {
        Map<String, Object> data = esUtil.getDataById(id);
        return JSON.toJSONString(data);
    }

    @GetMapping("/search/{keyword}/{start}/{size}")
    public String search(@PathVariable String keyword, @PathVariable String start, @PathVariable String size) throws IOException {
        SearchHits searchHits = esUtil.searchData(keyword, Integer.parseInt(start), Integer.parseInt(size));
        return JSON.toJSONString(searchHits);
    }
}
