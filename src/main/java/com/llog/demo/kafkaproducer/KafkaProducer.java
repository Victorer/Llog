package com.llog.demo.kafkaproducer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @ProjectName: Llog
 * @Package: com.llog.demo.kafkaproducer
 * @ClassName: KafkaProducer
 * @Description: java类作用描述
 * @Author: liyanda
 * @CreateDate: 2018/9/17 16:35
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/9/17 16:35
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Component
public class KafkaProducer {
    @Autowired
    KafkaTemplate kafkaTemplate;
    public void kafkaSend(String type,String content){
        kafkaTemplate.send("logtopics",type,content);
    }

}
