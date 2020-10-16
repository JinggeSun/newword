package com.sun.myquartz.job;

import com.sun.myquartz.util.DateUtil;
import javafx.scene.input.DataFormat;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 收集日志的job
 * @author zcm
 */
public class CollectLogJob implements Job {

    /**
     * 执行的方法
     * @param jobExecutionContext
     * @throws JobExecutionException
     */
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //1. 获取从页面传递的调度配置参数
        System.err.println(jobExecutionContext.getJobDetail().getJobDataMap().get("name"));
        //2. 调度执行
        System.out.println("调度开始运行：" + DateUtil.getNowString());

    }
}
