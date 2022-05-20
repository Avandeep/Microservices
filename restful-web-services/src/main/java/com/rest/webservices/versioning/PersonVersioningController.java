package com.rest.webservices.versioning;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PersonVersioningController {
	
	
	//known as URI versioning
	//used by Twitter
	
	@GetMapping("v1/person")
	public PersonV1 personV1()
	{
		return new PersonV1("Avan");
		
	}
	

	@GetMapping("v2/person")
	public PersonV2 personV2()
	{
		return new PersonV2(new Name("Avan","Kaur"));
		
	}
	
	//known as request parameter versioning
	//used by Amazon
	
	@GetMapping(value="/person/param",params="version=1")
	public PersonV1 paramV1()
	{
		return new PersonV1("Avan");
		
	}
	

	@GetMapping(value="/person/param",params="version=2")
	public PersonV2 paramV2()
	{
		return new PersonV2(new Name("Avan","Kaur"));
		
	}
	
	//known as (Custom) header versioning
	//used by Microsoft

	@GetMapping(value="/person/header",headers="X-API-VERSION=1")
	public PersonV1 headerV1()
	{
		return new PersonV1("Avan");
		
	}
	

	@GetMapping(value="/person/header",headers="X-API-VERSION=2")
	public PersonV2 headerV2()
	{
		return new PersonV2(new Name("Avan","Kaur"));
		
	}
	
	//known as accept header or media type or content negotiation versioning
	//used by GitHub
	
	@GetMapping(value="/person/produces",produces="application/vnd.company.app-v1+json")
	public PersonV1 producesV1()
	{
		return new PersonV1("Avan");
		
	}
	

	@GetMapping(value="/person/produces",produces="application/vnd.company.app-v2+json")
	public PersonV2 producesV2()
	{
		return new PersonV2(new Name("Avan","Kaur"));
		
	}

}
