package com.xie.test;

import com.xie.jop.MessageJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.util.Date;

public class Demo {
    public static void main(String[] args) {
//        创建jobdetail对象
        JobDetail jobBuilder = JobBuilder.newJob(MessageJob.class)
//        新建一个作业
        .withIdentity("messageJob","group1")//指定一个作业的名称与组
        .build();
//        创建
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("name", "username")
                .startNow()//重现在开始
                .withSchedule(SimpleScheduleBuilder.simpleSchedule()
                        .withIntervalInSeconds(3)//  间隔三秒执行一次
                        .withRepeatCount(10)//循环的次数
                ).build();
//        创建当前的调度器工厂
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = stdSchedulerFactory.getScheduler();
            Date date = scheduler.scheduleJob(jobBuilder, trigger);
            //启动容器会一直运行
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }


    }
}
