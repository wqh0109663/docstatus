package cn.eti.print.job;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

/**
 * @author Administrator
 * @date 2019-7-16
 */
public class CheckAnotherStatus extends QuartzJobBean {
    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("CheckAnotherStatus execute ...2."+System.currentTimeMillis());


    }
}
