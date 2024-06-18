package com.jimmy.rest.webservices.restfulwebservices.user;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import jakarta.validation.Valid;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    private UserDaoService service;
    // constructor injection
    public UserResource(UserDaoService service){
        this.service = service;
    }

    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }


    @GetMapping(path = "/users/{id}")
    public EntityModel<User> getUserById(@PathVariable Integer id){
        User user = service.findOne(id);

        if(user == null){
            throw new UserNotFoundException("id: " + id);
        }

        EntityModel<User> entityModel = EntityModel.of(user);

        WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));

        return entityModel;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping(path = "/users/{id}")
    public void deleteUserById(@PathVariable Integer id){
        service.deleteById(id);
    }
}
