package com.item.store.data;


import com.item.data.mode.Goods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zcm
 */
public class JDData {

    public static final String DATA_URL = "https://search.jd.com/Search?keyword=";

    public static List<Goods> getData(String keyword) throws IOException {
        Document doc = Jsoup.connect(DATA_URL+keyword).get();
        System.out.println(doc.title());
        Element divContent = doc.select("#J_goodsList > ul").get(0);
        Elements elementsByTag = divContent.getElementsByTag("li");

        List<Goods> goodList = new ArrayList<>(elementsByTag.size());

        int i = 1;
        for (Element element : elementsByTag){
            //价格
            String price = element.select("div.p-price > strong > i").text();
            //名称
            Elements titleElements = element.getElementsByClass("p-name");
            String title = titleElements.get(0).getElementsByTag("em").text();
            //图片
            Element imgElements = element.select("img").get(0);
            String src = imgElements.attr("src");
            System.out.println(src);
            //出版社
            Element publishElement = element.getElementsByClass("curr-shop hd-shopname").get(0);
            String publish = publishElement.getElementsByTag("a").text();

            goodList.add(Goods.builder().img(src).price(Double.parseDouble(price)).name(title).shop(publish).build());
            i += 1;
        }

        return goodList;
    }

    public static void main(String[] args) throws IOException {
        List<Goods> goods = getData("java");
    }


}
