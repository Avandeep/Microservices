package com.rest.webservices.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {
	
@Autowired
private UserDaoService service;

@GetMapping("/users")
public List<User> retrieveAllUsers()
{
	return service.findAll();
	
}

/* HATEOAS:- Hypermedia As The Engine Of Application State 
you can create a resource with the links very, very easily by using 
something called entity model.
So instead of returning a user back, what we would return is entity 
model of user. 
So we want to return the user data plus the links. 
And that's 
the reason why we are adding in entity model. */

/*@GetMapping("/users/{id}")
public User retrieveUser(@PathVariable int id)
{
	 User user = service.findOne(id);
	 
	 if(user==null)
	 throw new UserNotFoundException("id:"+id);
		 
	 return user;
	
}*/

@GetMapping("/users/{id}")
public EntityModel<User> retrieveUser(@PathVariable int id)
{
	 User user = service.findOne(id);
	 
	 if(user==null)
	 throw new UserNotFoundException("id:"+id);
	 
	 EntityModel<User> model= EntityModel.of(user);
	 
	 WebMvcLinkBuilder linkToUsers=linkTo(methodOn(this.getClass()).retrieveAllUsers());
			
	 
	 model.add(linkToUsers.withRel("all-users"));
	 
	 //HATEOAS will help to return data along with link or links
	 //actions can perform on specific data
	 
	 return model;
	
}

@DeleteMapping("/users/{id}")
public void deleteUser(@PathVariable int id)
{
	 User user = service.deleteById(id);
	 
	 if(user==null)
	 throw new UserNotFoundException("id:"+id);
		 
	
	
}

@PostMapping("/users")
public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
//@RequestBody: to map with User parameter properties
{
	User savedUser = service.save(user);
	
	//to return current request URI
	//to return correct HTTP status code and location
	
	URI location = ServletUriComponentsBuilder
	.fromCurrentRequest()
	.path("/{id}")
	.buildAndExpand(savedUser.getId()).toUri();
	
	return ResponseEntity.created(location).build();
	
}
}
