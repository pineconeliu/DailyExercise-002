import com.lss.Application;
import com.lss.strategymode.first.enu.enumTest;
import com.lss.strategymode.first.impl.impl;
import com.lss.strategymode.second.*;
import net.jodah.expiringmap.ExpirationPolicy;
import net.jodah.expiringmap.ExpiringMap;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 *  策略模式
 * @author liusongsheng
 * @date 2021/10/8 16:37
 * @return null
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TestStrategy {

    private Logger logger = LoggerFactory.getLogger(TestStrategy.class);

    @Autowired
    Map<enumTest, impl> integration;
    @Test
    public void testFirst() {
       logger.info("满减："+integration.get(enumTest.manjian).operator(new BigDecimal(100),new BigDecimal(10)));
        logger.info("直减："+integration.get(enumTest.zhijian).operator(new BigDecimal(100),new BigDecimal(20)));
        logger.info("n元购："+integration.get(enumTest.nPurchase).operator(new BigDecimal(100),new BigDecimal(30)));
        logger.info("折扣："+integration.get(enumTest.discount).operator(new BigDecimal(100),new BigDecimal(10)));

    }
    @Test
    public void testSecond() {
        logger.info("满减："+new choose<BigDecimal>(new Manjian()).operator(new BigDecimal(100),new BigDecimal(10)));
        logger.info("直减："+new  choose<BigDecimal>(new Zhijian()).operator(new BigDecimal(100),new BigDecimal(20)));
        logger.info("n元购："+new choose<BigDecimal>(new NPurchase()).operator(new BigDecimal(100),new BigDecimal(30)));
        logger.info("折扣："+new choose<String>(new Discount()).operator(new BigDecimal(100),"10"));

    }


    public static void main(String[] args) throws InterruptedException {
        ExpiringMap<String,String> map = ExpiringMap.builder()
                .maxSize(100)
                .expiration(1, TimeUnit.SECONDS)
                .expirationPolicy(ExpirationPolicy.ACCESSED)
                .variableExpiration()
                .build();
        map.put("test","test123");
        Thread.sleep(5000);
        String test= map.get("test");
        System.err.println(test);
    }




}
