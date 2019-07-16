package cn.eti.print;

import cn.eti.print.job.CheckAnotherStatus;
import cn.eti.print.job.CheckDocStatus;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wqh
 * @date 2019-7-11
 */
@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail teatQuartzDetail1(){
        return JobBuilder.newJob(CheckDocStatus.class)
                .withIdentity("checkDocStatus","OA")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger testQuartzTrigger1(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMinutes(1)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(teatQuartzDetail1())
                .withIdentity("checkDocStatus","OA")
                .withSchedule(scheduleBuilder)
                .build();
    }


    @Bean
    public JobDetail teatQuartzDetail2(){
        return JobBuilder.newJob(CheckAnotherStatus.class)
                .withIdentity("checkAnotherStatus","OA")
                .storeDurably()
                .build();
    }

    @Bean
    public Trigger testQuartzTrigger2(){
        SimpleScheduleBuilder scheduleBuilder = SimpleScheduleBuilder.simpleSchedule()
                .withIntervalInMinutes(1)
                .repeatForever();
        return TriggerBuilder.newTrigger().forJob(teatQuartzDetail2())
                .withIdentity("checkAnotherStatus","OA")
                .withSchedule(scheduleBuilder)
                .build();
    }

}
