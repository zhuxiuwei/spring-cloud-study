package cn.itcast.order.service;

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

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        try {
            // 2. 利用rest template发起http请求，查询用户
            String url = "http://localhost:8081/user/" + order.getUserId();
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
}
