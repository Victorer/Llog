package com.llog.demo.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

/**
 * @ProjectName: Llog
 * @Package: com.llog.demo.pojo
 * @ClassName: Customer
 * @Description: java类作用描述
 * @Author: liyanda
 * @CreateDate: 2018/8/14 11:29
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/14 11:29
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Document(indexName = "customer", type = "log", shards = 1, replicas = 0, refreshInterval = "-1")
public class Customer {
    @Id
    public String id;
    public String orderId;
    public String userId;
    public String content;
    public Customer() {
    }
    public Customer(String id,String orderId,String userId,String content) {
        this.id = id;
        this.content = content;
        this.orderId = orderId;
        this.userId = userId;
    }
    public String  getId() {
        return id;
    }

    public void setId(String  id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
