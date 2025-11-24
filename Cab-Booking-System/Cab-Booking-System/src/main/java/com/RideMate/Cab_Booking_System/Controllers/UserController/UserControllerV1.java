package com.RideMate.Cab_Booking_System.Controllers.UserController;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.RideMate.Cab_Booking_System.Entities.User;
import com.RideMate.Cab_Booking_System.Services.UserService;
import com.fasterxml.jackson.databind.ser.impl.SimpleBeanPropertyFilter;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1/users")
public class UserControllerV1 {

    private UserService service;

    public UserControllerV1(UserService service) {
        this.service = service;
    }

    @GetMapping
    public MappingJacksonValue getAllUsers() {
        List<User> users = service.getAllUsers();

        MappingJacksonValue mapping = new MappingJacksonValue(users);

        SimpleFilterProvider filters = new SimpleFilterProvider()
                .addFilter("UserFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id","name","phone"));

        mapping.setFilters(filters);
        return mapping;
    }

    @GetMapping("/{id}")
    public MappingJacksonValue getUserById(@PathVariable Integer id) {
        User user = service.getUserById(id);

        MappingJacksonValue mapping = new MappingJacksonValue(user);

        SimpleFilterProvider filters = new SimpleFilterProvider()
                .addFilter("UserFilter", SimpleBeanPropertyFilter.filterOutAllExcept("id","name","phone"));

        mapping.setFilters(filters);
        return mapping;
    }

    @PostMapping
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
        service.createNewUser(user);

        URI location = ServletUriComponentsBuilder
                .fromCurrentContextPath()
                .path("/v1/users/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public String updateUserById(@Valid @RequestBody User user, @PathVariable Integer id) {
        service.updateExistingUser(user, id);
        return "User Updated successfully";
    }

    @DeleteMapping("/{id}")
    public String DeleteUserById(@PathVariable Integer id) {
        service.deleteExistingUser(id);
        return "User Deleted successfully";
    }
}
