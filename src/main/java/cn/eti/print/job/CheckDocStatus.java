package cn.eti.print.job;

import cn.eti.print.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.transaction.annotation.Transactional;


/**
 * @author wqh
 * @date 2019-7-11
 */
@Slf4j
public class CheckDocStatus extends QuartzJobBean {

    @Autowired
    private RedisUtil redisUtil;


    @Autowired
    @Qualifier("primaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    private JdbcTemplate jdbcTemplate2;


    public CheckDocStatus() {
        super();
    }

    @Override
    @Transactional
    protected void executeInternal(JobExecutionContext arg0) {
        System.out.println("CheckDocStatus --------------11--------");
   }
}
