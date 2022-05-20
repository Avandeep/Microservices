package com.rest.webservices.dynamic_filtering;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ser.FilterProvider;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

@RestController
public class FilteringController_Dynamic {
	
	@GetMapping("/dynamicfiltering")
	public MappingJacksonValue someBeanFiltering()
	{
		 SomeBean_Dynamic bean = new SomeBean_Dynamic("value1","value2","value3");
		 
		 SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter
				 .filterOutAllExcept("field1","field2");
		
		 FilterProvider filters=new SimpleFilterProvider()
				 .addFilter("SomeBeanFiltering", filter);
		
		 MappingJacksonValue mapping=new MappingJacksonValue(bean);
		 
		 mapping.setFilters(filters);
		 
		 return mapping;
		
	}
	

	@GetMapping("/dynamicfiltering-list")
	public MappingJacksonValue someBeanListFiltering()
	{
		 List<SomeBean_Dynamic> list = Arrays.asList(new SomeBean_Dynamic("value1","value2","value3"),
				new SomeBean_Dynamic("value11","value12","value13"));
		 
		 SimpleBeanPropertyFilter filter= SimpleBeanPropertyFilter
				 .filterOutAllExcept("field2","field3");
		
		 FilterProvider filters=new SimpleFilterProvider()
				 .addFilter("SomeBeanFiltering", filter);
		
		 MappingJacksonValue mapping=new MappingJacksonValue(list);
		 
		 mapping.setFilters(filters);
		 
		return mapping;
		
	}

}
