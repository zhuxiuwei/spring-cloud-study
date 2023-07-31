package com.xiuwei.feign.pojo;

import lombok.Data;
/**
 * 230731
 * p34 Feign最佳实践-方式二实操
 */
@Data
public class Order {
    private Long id;
    private Long price;
    private String name;
    private Integer num;
    private Long userId;
    private User user;
}