package com.sun.learn;

import org.apache.flink.streaming.api.functions.source.SourceFunction;

/**
 * @ClassName CustormSource
 * @Description: TODO
 * @Author zcm
 * @Date 2020-01-03
 * @Version V1.0
 **/
public class CustormSource implements SourceFunction<Long> {

    @Override
    public void run(SourceContext<Long> sourceContext) throws Exception {

    }

    @Override
    public void cancel() {

    }
}
