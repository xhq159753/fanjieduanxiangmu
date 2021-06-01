package com.xie.test;

import com.xie.jop.MessageJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class Dome1 {
    public static void main(String[] args) {
        //创建JobDetail对象
        JobDetail jobDetail = JobBuilder.newJob(MessageJob.class)//新建一个作业
                .withIdentity("messageJob", "group1")  //指定这个作业的名称与组名
                .build();
        //创建
        SimpleTrigger trigger = TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
                .startNow()//现在开始执行定时器
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(3)//第隔3秒执行一次
                                .withRepeatCount(10)//循环多少次

                ).build();

        //创建当前的调度器工厂
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        try {
            Scheduler scheduler = schedulerFactory.getScheduler();
            scheduler.scheduleJob(jobDetail,trigger);
            scheduler.start();//启动容器    会一直运行
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}
