package com.llog.demo.service;

import com.llog.demo.pojo.Customer;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @ProjectName: Llong
 * @Package: com.llog.demo.service
 * @ClassName: CustomerRepository
 * @Description: java类作用描述
 * @Author: liyanda
 * @CreateDate: 2018/8/14 11:18
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/14 11:18
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public interface CustomerRepository extends ElasticsearchRepository<Customer,String> {

}
