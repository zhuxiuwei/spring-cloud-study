package cn.itcast.user.web;

import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 测试p27 通过"onfigurationProperties"实现热更新
 */
@Slf4j
@RestController
@RequestMapping("/user_TestConfigHotRefreshUsingConfigurationProperties")
public class UserController_TestConfigHotRefreshUsingConfigurationProperties {
    //p27 通过"ConfigurationProperties"实现热更新
    @Autowired
    PatternProperties patternProperties;
    @GetMapping("/now")
    public String now() {
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(patternProperties.getDateformat()));
    }

    //p28 多环境配置共享。显示当前PatternProperties配置的属性列表。
    @GetMapping("/prop")
    public PatternProperties showPatternProperties(){
        return patternProperties;
    }
}
