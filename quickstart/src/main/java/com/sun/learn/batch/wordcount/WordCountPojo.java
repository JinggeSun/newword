package com.sun.learn.batch.wordcount;

import com.sun.learn.batch.wordcount.util.WordCountData;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.ReduceFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.core.fs.FileSystem;
import org.apache.flink.util.Collector;
import org.apache.flink.util.Preconditions;

import java.util.Arrays;

/**
 * @ClassName WordCountPoJo
 * @Description: TODO
 * @Author zcm
 * @Date 2020-01-04
 * @Version V1.0
 **/
public class WordCountPojo {

//    public static void main(String[] args) throws Exception {
//        final ParameterTool param  = ParameterTool.fromArgs(args);
//
//        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();
//
//        //web
//        env.getConfig().setGlobalJobParameters(param);
//
//        //datasource
//        DataSet<String> text = null;
//        if (param.has("input")){
//            for (String input : param.getRequired("input").split(",")){
//                if (text == null){
//                    text = env.readTextFile(input);
//                }else {
//                    text = text.union(env.readTextFile(input));
//                }
//            }
//
//            Preconditions.checkNotNull(text,"数据为null");
//        }else{
//            System.out.println("使用原始数据");
//            text = WordCountData.getTextLineDataSet(env);
//        }
//
//        //dataset
//        DataSet<Word> counts = text.flatMap(new FlatMapFunction<String, Word>() {
//            @Override
//            public void flatMap(String s, Collector<Word> collector) throws Exception {
//                Arrays.stream(s.split(",")).forEach((d)->collector.collect(new Word(d,1)));
//            }
//        }).groupBy("word")
//                .reduce(new ReduceFunction<Word>() {
//                    @Override
//                    public Word reduce(Word word, Word t1) throws Exception {
//                        return new Word(word.getWord(),word.getFrequency()+t1.getFrequency());
//                    }
//                });
//
//
//        // sink
//        if (param.has("output")){
//            counts.writeAsText(param.get("output"), FileSystem.WriteMode.OVERWRITE);
//            env.execute("WordCountPojo");
//        }else {
//            counts.print();
//        }
//
//    }

    //内部类
    public static class Word {
        private String word;
        private int frequency;

        public Word(String word, int frequency) {
            this.word = word;
            this.frequency = frequency;
        }

        public Word() {
        }

        public String getWord() {
            return word;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public int getFrequency() {
            return frequency;
        }

        public void setFrequency(int frequency) {
            this.frequency = frequency;
        }

        @Override
        public String toString() {
            return "Word{" +
                    "word='" + word + '\'' +
                    ", frequency=" + frequency +
                    '}';
        }
    }

}
