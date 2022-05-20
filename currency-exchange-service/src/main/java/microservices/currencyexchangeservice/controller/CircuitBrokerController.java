package microservices.currencyexchangeservice.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CircuitBrokerController {
	
	@GetMapping("/sample-api")
	public String SampleApi()
	{
		return "Sample API";
	}

}
