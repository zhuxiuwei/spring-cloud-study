package cn.itcast.order.web;

import cn.itcast.order.service.OrderService;
import com.xiuwei.feign.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("order")
public class OrderController {

   @Autowired
   private OrderService orderService;

    @GetMapping("{orderId}")
    public Order queryOrderByUserId(@PathVariable("orderId") Long orderId,
                                    @RequestHeader(value = "newHeaderKey", required = false) String newHeaderKey) {   //p38 路由过滤器工长测试) {
        // 根据id查询订单并返回
//        return orderService.queryOrderById(orderId);
        System.out.println("newHeaderKey：" + newHeaderKey);   //p38 路由过滤器工长测试。效果：打印【newHeaderKey：newHeaderValue --- default all filters!!!】
        return orderService.queryOrderByIdByFeign(orderId);

    }
}
