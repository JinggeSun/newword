package com.sun.myquartz.controller;


import com.sun.myquartz.annotation.LogAnnotation;
import com.sun.myquartz.job.CollectLogJob;
import org.quartz.*;
import org.quartz.impl.matchers.GroupMatcher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author zcm
 */
@RestController
@RequestMapping("/quartz")
public class QuartzManagerController {

    @Autowired
    private Scheduler scheduler;

    /**
     * 创建job
     */
    @GetMapping("/create")
    public String createJob() throws SchedulerException {

        //1。 调度开始时间
        Date startTime = new Date(System.currentTimeMillis() + 1000 * 10);

        //2.配置 JobDetail
        JobDetail jobDetail = JobBuilder.newJob(CollectLogJob.class)
                .usingJobData("name","sunjg")
                //group
                .withIdentity("12345678","group_1")
                .build();

        //3. 配置Trigger
        Trigger trigger = TriggerBuilder.newTrigger()
                .usingJobData("name","sunjg")
                .withIdentity("trigger11","group_1")
                .startAt(startTime)
                .withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * * * ? "))
                .build();
        //4. 添加到定时任务中
        scheduler.scheduleJob(jobDetail,trigger);
        if (scheduler.isShutdown()){
            scheduler.start();
        }

        return "success";
    }

    @LogAnnotation
    @GetMapping("/list")
    public Set<JobKey> getJobList() throws SchedulerException {
        //Set<JobKey> jobKeySet = scheduler.getJobKeys(GroupMatcher);
        GroupMatcher<JobKey> matcher = GroupMatcher.groupEquals("group_1");
        Set<JobKey> jobKeySet = scheduler.getJobKeys(matcher);
        return jobKeySet;
    }

    /**
     * 停止job
     * @param orderNo
     * @return
     * @throws IOException
     * @throws SchedulerException
     */
    @PostMapping("/shutdown")
    @ResponseBody
    public Object shutdown(@RequestParam("orderNo")  String orderNo) throws SchedulerException {
        scheduler.pauseTrigger(TriggerKey.triggerKey(orderNo));
        return "";
    }

    /**
     * 恢复
     * @param orderNo
     * @return
     * @throws IOException
     * @throws SchedulerException
     */
    @PostMapping("/resume")
    @ResponseBody
    public Object resume(@RequestParam("orderNo")  String orderNo) throws SchedulerException {
        scheduler.resumeTrigger(TriggerKey.triggerKey(orderNo));
        return "ok";
    }

    /**
     * 删除
     * @param orderNo
     * @return
     * @throws IOException
     * @throws SchedulerException
     */
    @PostMapping("/del")
    @ResponseBody
    public Object del(@RequestParam("orderNo")  String orderNo) throws SchedulerException {
        //暂停触发器
        scheduler.pauseTrigger(TriggerKey.triggerKey(orderNo));
        //移除触发器
        scheduler.unscheduleJob(TriggerKey.triggerKey(orderNo));
        //删除Job
        scheduler.deleteJob(JobKey.jobKey(orderNo));
        return "success";
    }


}
