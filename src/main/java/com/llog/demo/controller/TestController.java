package com.llog.demo.controller;

import com.llog.demo.kafkaproducer.KafkaProducer;
import com.llog.demo.pojo.Customer;
import com.llog.demo.service.CustomerRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import java.util.ArrayList;
import java.util.List;



/**
 * @ProjectName: Llong
 * @Package: com.llog.demo.controller
 * @ClassName: TestController
 * @Description: java类作用描述
 * @Author: liyanda
 * @CreateDate: 2018/8/14 13:55
 * @UpdateUser: 更新者
 * @UpdateDate: 2018/8/14 13:55
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
@Controller
public class TestController {
    @Autowired
    private CustomerRepository repository;
    @Autowired
    private KafkaProducer kafkaProducer;

    @RequestMapping("/save")
    @ResponseBody
    public String save(){
        try {
            String id = System.currentTimeMillis()+"";
            repository.save(new Customer(id,"11o","11u","{\"Latitude\":\"46.066396\",\"Location\":\"15342106631111\",\"Longitude\":\"125.988238\",\"androidID\":\"3a971cd0d6f6482daaaa\",\"applicationList\":[{\"acId\":\"com.android.browser\",\"acName\":\"%E6%B5%8F%E8%A7%88%E5%99%A8\"},{\"acId\":\"com.android.calendar\",\"acName\":\"%E6%97%A5%E5%8E%86\"},{\"acId\":\"com.android.contacts\",\"acName\":\"%E7%94%B5%E8%AF%9D%E6%9C%AC\"}"));
            return id;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return "";
        }
    }
    @RequestMapping("/get")
    @ResponseBody
    public List<Customer> get(String q){
       List<Customer> list = new ArrayList<>();
        try {
            QueryStringQueryBuilder builder = new QueryStringQueryBuilder(q);
            Iterable<Customer> iter = repository.search(builder);
            iter.forEach(e->{
                list.add(e);
            });
            return list;
        }
        catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(){
        repository.deleteAll();
        return "";
    }
    @RequestMapping("/search")
    public String search(String keywords,Model model){
        List<Customer> list = new ArrayList<>();
        if (keywords==null){
            keywords="";
        }
        QueryBuilder queryBuilder = QueryBuilders.matchPhraseQuery("message", keywords);
        Iterable<Customer> iter = repository.search(queryBuilder);
        iter.forEach(e->{
            list.add(e);
        });
        model.addAttribute("page",list);
        return "LogList";
    }
    @RequestMapping("/sendLog")
    @ResponseBody
    public String send(String type,String content){
        try {
            kafkaProducer.kafkaSend(type, content);
        } catch (Exception ex) {

            return "发送失败！";
        }
        return "发送成功！";
    }

}
