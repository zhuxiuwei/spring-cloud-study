package cn.itcast.order.httpclients;

import cn.itcast.order.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 230709
 * Feign客户端
 */
@FeignClient("userservice")
public interface UserClient {

    @GetMapping("/user/{id}")
    User findBuId(@PathVariable("id") Long id);
}
