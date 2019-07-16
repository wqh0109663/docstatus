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

import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    @Transactional()
    public void test() {
        Assert.assertNotNull(jdbcTemplate2);
        String sql = "insert into TC_AAA_FILE(TC_AAA01,TC_AAA02,TC_AAADATE)" +
                " values (?,?,to_date(?,'yyyy-mm-dd hh24:mi:ss'))";
        jdbcTemplate2.update(sql,
                new Object[]{"12315125125", "1", new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date())}
                , new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR});

    }
}
