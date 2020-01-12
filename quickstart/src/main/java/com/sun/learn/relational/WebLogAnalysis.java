package com.sun.learn.relational;

import com.sun.learn.relational.util.WebLogData;
import org.apache.flink.api.common.functions.CoGroupFunction;
import org.apache.flink.api.common.functions.FilterFunction;
import org.apache.flink.api.java.DataSet;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.api.java.tuple.Tuple1;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.api.java.tuple.Tuple3;
import org.apache.flink.api.java.utils.ParameterTool;
import org.apache.flink.util.Collector;

public class WebLogAnalysis {

    public static void main(String[] args) throws Exception {

        final ParameterTool params = ParameterTool.fromArgs(args);

        final ExecutionEnvironment env = ExecutionEnvironment.getExecutionEnvironment();

        env.getConfig().setGlobalJobParameters(params);

        // datasource
        DataSet<Tuple2<String,String>> documents = WebLogData.getDocumentDataSet(env);
        DataSet<Tuple3<Integer,String,Integer>> ranks = WebLogData.getRankDateSet(env);
        DataSet<Tuple2<String,String>> visits = WebLogData.getVisitDataSet(env);
        // transformations
        // retain
        DataSet<Tuple1<String>> filterDocs = documents.filter(new FliterDocByKeyWords())
                .project(0);
        DataSet<Tuple3<Integer,String,Integer>> filterRanks = ranks.filter(new FilterFunction<Tuple3<Integer, String, Integer>>() {
            @Override
            public boolean filter(Tuple3<Integer, String, Integer> value) throws Exception {

                int RANKFILTER = 40;

                return (value.f0 > RANKFILTER);
            }
        });

        DataSet<Tuple1<String>> filterVisits = visits.filter(new FilterFunction<Tuple2<String, String>>() {
            @Override
            public boolean filter(Tuple2<String, String> value) throws Exception {

                int YEARFILTER = 2007;
                String dataString = value.f1;
                int year = Integer.parseInt(dataString.substring(0,4));
                return (year == YEARFILTER);
            }
        }).project(0);

        DataSet<Tuple3<Integer,String,Integer>> joinDocsRanks =
                filterDocs.join(filterRanks)
                    .where(0).equalTo(1)
                .projectSecond(0,1,2);

        DataSet<Tuple3<Integer,String,Integer>> result =
                joinDocsRanks.coGroup(filterVisits)
                .where(1).equalTo(0)
                .with(new AntiJoinVisits());

        // emit result
        if (params.has("output")) {
            result.writeAsCsv(params.get("output"), "\n", "|");
            // execute program
            env.execute("WebLogAnalysis Example");
        } else {
            System.out.println("Printing result to stdout. Use --output to specify output path.");
            result.print();
        }

    }

    /*******************/

    public static class FliterDocByKeyWords implements FilterFunction<Tuple2<String,String>>{

        private static final String[] KEYWORDS = {" editors " , " oscillations "};

        @Override
        public boolean filter(Tuple2<String, String> value) throws Exception {

            String docText = value.f1;

            for (String kw : KEYWORDS){
                if (!docText.contains(kw)){
                    return false;
                }
            }

            return true;
        }
    }

    public static class  AntiJoinVisits implements CoGroupFunction<Tuple3<Integer, String, Integer>, Tuple1<String>, Tuple3<Integer, String, Integer>>{


        @Override
        public void coGroup(Iterable<Tuple3<Integer, String, Integer>> ranks, Iterable<Tuple1<String>> visit, Collector<Tuple3<Integer, String, Integer>> out) throws Exception {
            if (!visit.iterator().hasNext()){
                for (Tuple3<Integer,String,Integer> next : ranks){
                    out.collect(next);
                }
            }
        }
    }
}
