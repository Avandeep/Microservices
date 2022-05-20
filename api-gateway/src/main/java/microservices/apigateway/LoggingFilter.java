package microservices.apigateway;

import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;


import reactor.core.publisher.Mono;
import org.slf4j.Logger;


/*mIn the last step, we saw how you can actually add custom filters on specific paths. 
In API gateway you can also add global filters. In this step,
let's look at how you can add global filters.*/

@Component
public class LoggingFilter implements GlobalFilter{
	
	private Logger logger=LoggerFactory.getLogger(LoggingFilter.class);

	@Override
	public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
		
		logger.info("Path of the request receieved->{}",
				exchange.getRequest().getPath());
		
		return chain.filter(exchange);
	}

}
