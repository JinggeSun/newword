package com.sun.project;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * @author zcm
 */
public class WebFlinkJob {

    /**
     * log
     */
   private static Logger logger = LoggerFactory.getLogger(WebFlinkJob.class);


    public static void main(String[] args) {


        // env
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();

        // 设置时间
        env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);

        //kafka
        String topic = "big";
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers","127.0.0.1:9092");
        properties.setProperty("group.id","big-group");

        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<String>(topic,new SimpleStringSchema(),properties);

        //source
        DataStreamSource<String> source = env.addSource(consumer);

        // transformation
        source.flatMap(new FlatMapFunction<String, >() {
        })



    }

}
