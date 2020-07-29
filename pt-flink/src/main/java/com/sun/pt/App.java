package com.sun.pt;

import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.windowing.time.Time;
import org.apache.flink.util.Collector;

public class App {

    public static final String HOST = "127.0.0.1";
    public static final int PORT = 8897;

    public static void main(String[] args) throws Exception {

        StreamExecutionEnvironment executionEnvironment = StreamExecutionEnvironment.getExecutionEnvironment();

        //SOURCE
        DataStreamSource<String> source = executionEnvironment.socketTextStream(HOST,PORT);

        DataStream<WordCount> dataStream = source.flatMap(new FlatMapFunction<String, WordCount>() {
            @Override
            public void flatMap(String value, Collector<WordCount> out) throws Exception {
                String[] val = value.split("\n");
                for (String str : val){
                    out.collect(new WordCount(str,1));
                }
            }
        }).keyBy("word")
                .timeWindow(Time.seconds(5))
            .reduce(new ReduceFunction<WordCount>() {
                @Override
                public WordCount reduce(WordCount value1, WordCount value2) throws Exception {
                    return new WordCount(value1.word,value1.count+value2.count);
                }
            });

        dataStream.print().setParallelism(1);

        executionEnvironment.execute("Socket Window WordCount");


    }

    public static class WordCount {
        public String word;
        public long count;

        public WordCount() {}

		public WordCount(String word, long count) {
            this.word = word;
            this.count = count;
        }

        @Override
        public String toString() {
            return word + " : " + count;
        }
    }
}


