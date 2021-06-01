package com.xie.jop;

import com.mysql.fabric.xmlrpc.base.Data;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;



public class MessageJob  implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("我是谢海卿");
        for (int i = 0; i <2000 ; i++) {
            System.out.println("我爱王巧林");
        }

    }
}
