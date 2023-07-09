package cn.itcast.order.service;

import cn.itcast.order.httpclients.UserClient;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import cn.itcast.order.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserClient userClient;  //feign客户端

    //用RestTemplate当httpclient
    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        try {
            // 2. 利用rest template发起http请求，查询用户
            // 注意！！！ 使用nacos服务发现，userservice必须小写。使用eureka做服务发现时，大小写没关系。
            String url = "http://userservice/user/" + order.getUserId();
            User user = restTemplate.getForObject(url, User.class);
            // 3. 封装user到order
            order.setUser(user);
        }catch (Exception e){
            System.out.println("远程调用user service失败：");
            //捕获远程调用异常
            e.printStackTrace();
        }
        // 4.返回
        return order;
    }

    //用Feign当httpclient
    public Order queryOrderByIdByFeign(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        try {
            // 2. 利用feign发起http请求，查询用户.
            // 变成风格比RestTemplate好很多。和本地调用语法一致，看不出来是远程调用。
            User user = userClient.findBuId(order.getUserId());
            // 3. 封装user到order
            order.setUser(user);
        }catch (Exception e){
            System.out.println("远程调用user service失败：");
            //捕获远程调用异常
            e.printStackTrace();
        }
        // 4.返回
        return order;
    }
}
