package com.item.collect.util;

import com.item.model.common.ModelConst;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

/**
 * @author zcm
 */
public class KafkaUtil {

    private static Properties props;
    static {
        //配置信息
        props = new Properties();
        //kafka服务器地址
        props.put("bootstrap.servers", ModelConst.SERVERS);
        //必须指定消费者组
        props.put("group.id", ModelConst.GROUP_ID);
        //设置数据key和value的序列化处理类
        props.put("key.serializer", StringSerializer.class);
        props.put("value.serializer", StringSerializer.class);
    }


    /**
     * kafka发送数据
     * @param mess
     */
    public static void initKafka(String mess){
        //创建生产者实例
        KafkaProducer<String,String> producer = new KafkaProducer<>(props);
        ProducerRecord<String,String> record = new ProducerRecord<String, String>(ModelConst.TOPIC, ModelConst.KEY, mess);
        //发送记录
        producer.send(record);
        producer.close();
    }
}
