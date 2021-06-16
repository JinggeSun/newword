package com.item.streaming.streaming;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.item.model.common.ModelConst;
import com.item.model.entity.NameNodeMsg;
import com.item.streaming.sink.MySqlSink;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.TimeCharacteristic;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer011;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Properties;

/**
 * @author zcm
 */
public class StreamingApp {

    public static void main(String[] args) {

        try {
            StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
            //要设置启动检查点
            env.enableCheckpointing(500);
            env.setStreamTimeCharacteristic(TimeCharacteristic.EventTime);
            env.setParallelism(1);

            System.out.println("开始执行");

            //kafka
            Properties properties = new Properties();
            properties.setProperty("bootstrap.servers", ModelConst.SERVERS);
            properties.setProperty("group.id",  ModelConst.GROUP_ID);
            //设置数据key和value的序列化处理类
            properties.put("key.deserializer", StringDeserializer.class);
            properties.put("value.deserializer", StringDeserializer.class);

            //数据源
            FlinkKafkaConsumer011<String> kafkaContent = new FlinkKafkaConsumer011<String>(ModelConst.TOPIC, new SimpleStringSchema(), properties);

            /**
             * setStartFromEarliest 最早记录
             * setStartFromLatest 最新记录
             * setStartFromTimestamp(null) 从指定的epoch时间戳（毫秒）开始；
             * setStartFromGroupOffsets(); // 默认行为，从上次消费的偏移量进行继续消费。
             */
            kafkaContent.setStartFromLatest(); //- 从最新记录开始；

            System.out.println("执行策略");


            DataStreamSource<String> source = env.addSource(kafkaContent);

            //从kafka中消费，直接将topic的消息转换为实体
            //不需要过滤与计算
            //TODO 后期可以考虑数据过滤和告警
            SingleOutputStreamOperator<NameNodeMsg> map = source.map(new MapFunction<String, NameNodeMsg>() {
                @Override
                public NameNodeMsg map(String value) throws Exception {
                    try {
                        JSONObject jsonObject = JSON.parseObject(value);
                        System.out.println(jsonObject);
                        return JSON.toJavaObject(jsonObject,NameNodeMsg.class);
                    }catch (Exception e){
                        e.printStackTrace();
                        return new NameNodeMsg();
                    }
                }
            });
            //定义一个5秒的翻滚窗口
            map.timeWindowAll(Time.seconds(10));

            System.out.println("执行sink");

            //sink
            map.addSink(new MySqlSink());

            env.execute("kafka job");
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}
