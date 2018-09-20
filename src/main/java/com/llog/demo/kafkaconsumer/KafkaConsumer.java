package com.llog.demo.kafkaconsumer;

import com.llog.demo.pojo.Customer;
import com.llog.demo.service.CustomerRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: Llog
 * @Package: com.llog.demo.kafkaconsumer
 * @ClassName: KafkaConsumer
 * @Description: java类作用描述
 * @Author: liyanda
 * @CreateDate: 2018/9/17 16:15
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/9/17 16:15
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Component
public class KafkaConsumer {
    @Autowired
    private CustomerRepository repository;
    public static final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);
    @KafkaListener(topics = "logtopics")
    public void testConsumer(ConsumerRecord consumerRecord){
        logger.info("偏移{}", consumerRecord.offset());
        logger.info("value:{}", consumerRecord.value());
        logger.info("key:{}", consumerRecord.key());
        try {
            repository.save(new Customer(String.valueOf(System.currentTimeMillis()), "", "", consumerRecord.value().toString()));
        } catch (Exception ex) {
            logger.error("保存kafka到es错误", ex.fillInStackTrace());
        }

    }

}
