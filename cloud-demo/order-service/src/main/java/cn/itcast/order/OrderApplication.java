package cn.itcast.order;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.client.RestTemplate;

@MapperScan("cn.itcast.order.mapper")
@SpringBootApplication
@EnableFeignClients(basePackages = "com.xiuwei.feign")  //p34: basePackages注解解决OrderApplication找不到feign-api里定义的 'com.xiuwei.feign.httpclients.UserClient'问题
//TODO: 230801 下面的scanBasePackages一直不work，有时间再看吧。
//@SpringBootApplication(scanBasePackages = {"cn.itcast.order", "com.xiuwei.feign.httpclients"})
//@EnableFeignClients
public class OrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }

    /**
     * 视频P08：创建RestTemplate并注册到Spring容器
     * 用于发送rest请求来进行远程调用。
     * @return
     */
    @Bean
    /**
     * p12 服务发现（拉取）
     * 注入RestTemplate客户端实现类。
     * 加@LoadBalanced这个注解，让RestTemplate调用客户端时负载均衡。
     * 不加的话，调用userservice失败，报错：org.springframework.web.client.ResourceAccessException: I/O error on GET request for "http://userservice/user/1": userservice; nested exception is java.net.UnknownHostException: userservice
     */
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}