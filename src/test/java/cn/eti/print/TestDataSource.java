package cn.eti.print;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestDataSource {


    @Autowired
    @Qualifier("primaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate1;

    @Autowired
    @Qualifier("secondaryJdbcTemplate")
    protected JdbcTemplate jdbcTemplate2;

    @Test
    @Transactional
    public void test() {
        Assert.assertNotNull(jdbcTemplate1);
//        String sql = "insert into user_info(fd_id,user_id) values (?,?)";
//        jdbcTemplate1.update(sql,new Object[]{"666666","99991119999"},new int[]{1,2});
        Assert.assertNotNull(jdbcTemplate2);

    }
}
