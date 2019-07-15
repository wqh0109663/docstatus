package cn.eti.print.job.start;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Component;

/**
 * @author Administrator
 * @date 2019-7-15
 */

@Configuration
@EnableScheduling
@Component
public class SchedulerListener {

    @Autowired
    public SchedulerAllJob myScheduler;

    /**
     *  启动的时候执行该方法，或者是使用ApplicationListener，在启动的时候执行该方法
     *  具体使用见：http://blog.csdn.net/liuchuanhong1/article/details/77568187
     * @throws SchedulerException
     */
    @Scheduled(cron="0 08 18 ? * *")
    public void schedule() throws SchedulerException {
        myScheduler.scheduleJobs();
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(){
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        return schedulerFactoryBean;
    }
}
