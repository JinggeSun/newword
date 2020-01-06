package com.sun.learn;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

import java.util.Arrays;

/**
 * @ClassName StreamingSocketWordCount
 * @Description: TODO
 * @Author zcm
 * @Date 2020-01-01
 * @Version V1.0
 **/
public class StreamingSocketWordCount {

    public static void main(String[] args) throws Exception {

        int prot = 0;

        try {
            ParameterTool tool = ParameterTool.fromArgs(args);
            prot = tool.getInt("port");
        }catch (Exception e){
            prot = 9999;
        }

        System.out.println(prot);


        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();


        DataStreamSource<String> source =  env.socketTextStream("127.0.0.1",9999);

        source.flatMap(new FlatMapFunction<String, Tuple2<String,Integer>>() {
            @Override
            public void flatMap(String s, Collector<Tuple2<String, Integer>> collector) throws Exception {
                Arrays.stream(s.split(",")).forEach(w->collector.collect(new Tuple2<>(w,1)));
            }
        }).keyBy(0).timeWindow(Time.seconds(10)).sum(1).print();

        env.execute();


    }
}
