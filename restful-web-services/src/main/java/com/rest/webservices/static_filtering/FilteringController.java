package com.rest.webservices.static_filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FilteringController {
	
	@GetMapping("/filtering")
	public SomeBean someBeanFiltering()
	{
		return new SomeBean("value1","value2","value3");
		
	}
	

	@GetMapping("/filtering-list")
	public List<SomeBean> someBeanListFiltering()
	{
		return Arrays.asList(new SomeBean("value1","value2","value3"),
				new SomeBean("value11","value12","value13"));
		
	}

}
