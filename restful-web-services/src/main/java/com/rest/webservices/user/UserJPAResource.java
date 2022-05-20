package com.rest.webservices.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJPAResource {
	

@Autowired
private UserRepository userRepository;

@Autowired
private PostRepository postRepository;

@GetMapping("/jpa/users")
public List<User> retrieveAllUsers()
{
	return userRepository.findAll();
	
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

@GetMapping("/jpa/users/{id}")
public EntityModel<User> retrieveUser(@PathVariable int id)
{
	 Optional<User> user = userRepository.findById(id);
	 
	 if(!user.isPresent())
	 throw new UserNotFoundException("id:"+id);
	 
	 EntityModel<User> model= EntityModel.of(user.get());
	 
	 WebMvcLinkBuilder linkToUsers=linkTo(methodOn(this.getClass()).retrieveAllUsers());
			
	 
	 model.add(linkToUsers.withRel("all-users"));
	 
	 //HATEOAS will help to return data along with link or links
	 //actions can perform on specific data
	 
	 return model;
	
}

@DeleteMapping("/jpa/users/{id}")
public void deleteUser(@PathVariable int id)
{
	userRepository.deleteById(id);
}

@PostMapping("/jpa/users")
public ResponseEntity<Object> createUser(@Valid @RequestBody User user)
//@RequestBody: to map with User parameter properties
{
	User savedUser = userRepository.save(user);
	
	//to return current request URI
	//to return correct HTTP status code and location
	
	URI location = ServletUriComponentsBuilder
	.fromCurrentRequest()
	.path("/{id}")
	.buildAndExpand(savedUser.getId()).toUri();
	
	return ResponseEntity.created(location).build();
	
}

@GetMapping("/jpa/users/{id}/posts")
public List<Post> retrieveAllUsers(@PathVariable Integer id)
{
	 Optional<User> findById = userRepository.findById(id);
	 
	 if(!findById.isPresent())
	 {
		 throw new UserNotFoundException("id:"+id); 
	 
	 }
	return findById.get().getPosts();
}

@PostMapping("/jpa/users/{id}/posts")
public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post)
//@RequestBody: to map with User parameter properties
{
 Optional<User> findById = userRepository.findById(id);
	 
	 if(!findById.isPresent())
	 {
		 throw new UserNotFoundException("id:"+id); 
	 
	 }
	 User user=findById.get();
	
	 post.setUser(user);
	 
	 postRepository.save(post);
	
	//to return current request URI
	//to return correct HTTP status code and location
	
	URI location = ServletUriComponentsBuilder
	.fromCurrentRequest()
	.path("/{id}")
	.buildAndExpand(post.getId()).toUri();
	
	return ResponseEntity.created(location).build();
	
}
}
