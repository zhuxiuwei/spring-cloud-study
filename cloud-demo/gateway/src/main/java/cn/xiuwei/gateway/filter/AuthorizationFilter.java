package cn.xiuwei.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * p39 全局过滤器 GlobalFilter
 * 定义全局过滤器，拦截并判断用户身份。主要是展示GlobalFilter用法，不要纠结于认证细节。
 * 需求：定义全局过滤器，拦截请求，判断请求的参数是否满足下面条件：
 * • 参数中是否有 authorization ，
 * • authorization 参数值是否为 admin
 * 如果同时满足则放行，否则拦截
 */
@Order(-1)  //定义过滤器执行顺序的优先级。数字越大，优先级越小。默认是Int MaxValue。
@Component  //类设置为springboot组件
public class AuthorizationFilter implements GlobalFilter {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //1.获取请求参数
        ServerHttpRequest request = exchange.getRequest();
        MultiValueMap<String, String> queryParams = request.getQueryParams();

        //2. 获取参数中的authorization参数
        String auth = queryParams.getFirst("authorization");

        //3. 判断参数值是否是admin
        if("admin".equals(auth)){
            //4. 是，放行
            return chain.filter(exchange);
        }
        else {
            //5. 否，拦截
            //设置状态码，让用户体验更友好【可选】
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);  //设置401
            return exchange.getResponse().setComplete();
        }
    }
}
