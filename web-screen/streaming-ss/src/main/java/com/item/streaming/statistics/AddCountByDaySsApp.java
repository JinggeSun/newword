package com.item.streaming.statistics;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.*;
import scala.Tuple2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * wordcount
 * 从文本中获取数据
 */
public class AddCountByDaySsApp {

    public static void main(String[] args) {

        //第一步：创建conf对象。
        SparkConf conf = new SparkConf()
                .setAppName("wordcount")
                .setMaster("local");
//第二步：创建context对象。
        JavaSparkContext sc = new JavaSparkContext(conf);

//第三步：创建RDD，调用RDD算子。
        JavaRDD<String> lines = sc.textFile("/Users/zcm/Documents/GitHub/newword/web-screen/log-server/hello.txt");

        System.out.println(lines.count());


        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Iterator<String> call(String line) throws Exception {
                System.out.println(line);
                return Arrays.asList(line.split(" ")).iterator();
            }
        });

        JavaPairRDD<String, Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Tuple2<String, Integer> call(String word) throws Exception {
                return new Tuple2<String, Integer>(word, 1);
            }
        });

        JavaPairRDD<String, Integer> wordCounts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {

            private static final long serialVersionUID = 1L;

            @Override
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        wordCounts.foreach(new VoidFunction<Tuple2<String, Integer>>() {

            private static final long serialVersionUID = 1L;

            @Override
            public void call(Tuple2<String, Integer> wordCount) throws Exception {
                System.out.println(wordCount._1 + "--->" + wordCount._2);
            }
        });
        //别忘了关闭sparkContext
        sc.close();
    }
}
