package cn.itcast.user.web;

import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

    // <p26 微服务读取nacos配置>: 测试nacos配置文件读取
    @Value("${pattern.dateformat}")
    private String dateformat;
    @GetMapping("/now")
    public String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat));
    }

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id,
                          @RequestHeader(value = "newHeaderKey", required = false) String newHeaderKey) {   //p38 路由过滤器工长测试
        System.out.println("newHeaderKey：" + newHeaderKey);   //p38 路由过滤器工长测试。效果：打印【newHeaderKey：newHeaderValue --- user-service only!!!】
        return userService.queryById(id);
    }
}
