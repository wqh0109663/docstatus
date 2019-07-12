package cn.eti.print;

import cn.eti.print.util.RedisUtil;
import cn.eti.print.util.SerializeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author 吴启欢
 * @version 1.0
 * @date 19-7-12 下午10:57
 */
@SpringBootTest
@Slf4j
@RunWith(SpringRunner.class)
public class RedisTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void test1() {

        redisUtil.set("20182018","这是一条测试数据", 1);
        Long resExpire = redisUtil.expire("20182018", 60,1);//设置key过期时间
        System.out.println("resExpire:"+resExpire);
        //logger.info("resExpire="+resExpire);
        String res = redisUtil.get("20182018",1);
        System.out.println("res"+res);
        //测试Redis保存对象

        redisUtil.set("20181017".getBytes(), SerializeUtil.serialize("abcdefg"),1);
        byte[] str = redisUtil.get("20181017".getBytes(),1);
        String st = (String) SerializeUtil.unserialize(str);
        System.out.println("object="+st);

    }


}
