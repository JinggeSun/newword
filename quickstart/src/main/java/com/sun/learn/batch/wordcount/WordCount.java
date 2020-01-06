package com.sun.learn.batch.wordcount;

import com.sun.learn.batch.wordcount.util.WordCountData;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.util.Collector;
import org.apache.flink.util.Preconditions;
import scala.Int;

import java.util.Arrays;

/**
 * @ClassName WordCount
 * @Description: TODO
 * @Author zcm
 * @Date 2020-01-04
 * @Version V1.0
 **/
public class WordCount {

    public static void main(String[] args) throws Exception {

        final ParameterTool param  = ParameterTool.fromArgs(args);

        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        //web
        env.getConfig().setGlobalJobParameters(param);

        //datasource
        DataSet<String> text = null;
        if (param.has("input")){
            for (String input : param.get("input").split("\\W+")){
                if (text == null){
                    text = env.readTextFile(input);
                }else {
                    text = text.union(env.readTextFile(input));
                }
            }

            Preconditions.checkNotNull(text,"数据为null");
        }else{
            System.out.println("使用原始数据");
            text = WordCountData.getTextLineDataSet(env);
        }

        // dataset api
        DataSet<Tuple2<String,Integer>> counts = text.flatMap(new Tokenizer())
                .groupBy(0)
                .sum(1);

        // sink
        if (param.has("output")){
            counts.writeAsCsv(param.get("output"),"\n"," ");
            env.execute("WordCount");
        }else {
            counts.print();
        }


    }

    /**
     * 内部类
     * 使用类的方式编写flatmapfunction
     */
    public static class  Tokenizer implements FlatMapFunction<String,Tuple2<String, Integer>> {

        /**
         *
         * @param s 输入参数，输出参数
         * @param collector
         * @throws Exception
         */
        @Override
        public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
            Arrays.stream(s.split(",")).forEach((w) -> collector.collect(new Tuple2<>(w,1)));
        }
    }
}
