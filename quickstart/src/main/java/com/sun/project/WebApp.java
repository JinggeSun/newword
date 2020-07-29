package com.sun.project;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;
import java.util.Random;

/**
 *
 * @author zcm
 */
public class WebApp {



    public static void main(String[] args) throws InterruptedException {

        // kafka配置
        Properties properties = new Properties();
        // server
        properties.setProperty("bootstrap.servers", "127.0.0.1:9092");
        // key
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        // value
        properties.setProperty("value.serializer",StringSerializer.class.getName());

        // produce
        KafkaProducer<String,String> producer = new KafkaProducer<String, String>(properties);

        // topic
        String topic = "biglaker";

        int count = 1;

        while (count < 100){

            StringBuilder builder = new StringBuilder();
            builder.append("sun").append("\t")
                    .append("CN").append("\t")
                    .append(getLevels()).append("\t")
                    .append(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))).append("\t")
                    .append(getIps()).append("\t")
                    .append(getDomains()).append("\t")
                    .append(getTraffic()).append("\t");

            System.out.println(builder.toString());

            producer.send(new ProducerRecord<String,String>(topic,builder.toString()));

            Thread.sleep(2000);

            count += 1;
        }


    }

    // 等级
    public static String getLevels(){
        String[] levels = new String[]{"M","E"};
        return levels[new Random().nextInt(levels.length)];
    }

    // ip
    public static String getIps(){
        String[] ips = new String[]{
                "223.104.18.110",
                "113.101.75.194",
                "27.17.127.135",
                "183.225.139.16",
                "112.1.66.34",
                "175.148.211.190",
                "183.227.58.21",
                "59.83.198.84",
                "117.28.38.28",
                "117.59.39.169"
        };
        return ips[new Random().nextInt(ips.length)];
    }


    private static String getDomains() {
        String[] domains = new String[]{
                "v1.go2yd.com",
                "v2.go2yd.com",
                "v3.go2yd.com",
                "v4.go2yd.com",
                "vmi.go2yd.com"
        };

        return domains[new Random().nextInt(domains.length)];
    }

    // 流量
    public static long getTraffic(){
        return new Random().nextInt(10000);
    }

}
