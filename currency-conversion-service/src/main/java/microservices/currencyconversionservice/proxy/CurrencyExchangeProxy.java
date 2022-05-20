package microservices.currencyconversionservice.proxy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import microservices.currencyconversionservice.bean.CurrencyConversion;

//@FeignClient(name="currency-exchange", url="localhost:8000")
//problem is here,We are hard coding the user of the currency exchange service.
//Feign, provide an option where you can hard coding multiple Jarrell's in here, even
//that would not be a good solution because.
//so, we go for something called a service registry or a naming server.

//to loan balancing between multiple instance
//will talk to Eureka and do load balancing; load balancer (used by feign) connecting to naming server
//and running multiple instances; this one is dynamic structure

//previous load balancer was ribbon

@FeignClient(name="currency-exchange")

public interface CurrencyExchangeProxy {

	@GetMapping("/currency-exchange/from/{from}/to/{to}")
	public CurrencyConversion retrieveExchangeValue(
			@PathVariable String from,
			@PathVariable String to);
	
		
	
}
