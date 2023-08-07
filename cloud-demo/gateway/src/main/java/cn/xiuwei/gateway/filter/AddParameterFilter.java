package cn.xiuwei.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * p39 全局过滤器 GlobalFilter
 * p40 过滤器的执行顺序
 * 作用等同P38的gateway application.yml里配置的俩AddRequestHeader filter
 */
//@Order(-1)  //定义过滤器执行顺序的优先级。数字越大，优先级越小。默认是Int MaxValue。
@Component  //类设置为springboot组件
public class AddParameterFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //打印headers，检查P38的gateway application.yml里配置的俩filter也执行了
        HttpHeaders headers = exchange.getRequest().getHeaders();
        headers.keySet().forEach(x -> System.out.println(x + ": " + headers.get(x)));   //结果：newHeaderKey: [newHeaderValue --- user-service only!!!] （2个AddRequestHeader都生效时）

        ServerHttpRequest request = exchange.getRequest().mutate().header("newHeaderKey",
                "newHeaderValue --- GlobalFilter!!!").build();
        //把新的 exchange放回到过滤链
        return chain.filter(exchange.mutate().request(request).build());
    }
}