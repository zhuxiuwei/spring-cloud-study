package com.xiuwei.feign.httpclients;

import com.xiuwei.feign.pojo.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * 230731
 * p34 Feign最佳实践-方式二实操
 */
@FeignClient("userservice")
public interface UserClient {

    @GetMapping("/user/{id}")
    User findBuId(@PathVariable("id") Long id);
}
