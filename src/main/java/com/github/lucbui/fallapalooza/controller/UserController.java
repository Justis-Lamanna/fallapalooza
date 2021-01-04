package com.github.lucbui.fallapalooza.controller;

import com.github.lucbui.fallapalooza.entity.User;
import com.github.lucbui.fallapalooza.model.team.IdType;
import com.github.lucbui.fallapalooza.model.user.CreateUserRequest;
import com.github.lucbui.fallapalooza.model.user.UpdateUserRequest;
import com.github.lucbui.fallapalooza.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
@ApiOperation("Operations relating to User data")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    @ApiOperation(value = "Retrieve a 'page' of User data", notes = "${api.pageable}")
    public Page<User> getUsers(@PageableDefault(20) Pageable pageable) {
        return userService.getByPageable(pageable);
    }

    @GetMapping("/{id}")
    @ApiOperation("Retrieve a user by their ID")
    public User getUserById(@PathVariable @ApiParam("User ID") long id) {
        return userService.getById(id);
    }

    @GetMapping("/{type}/{id}")
    @ApiOperation("Retrieve a user by their Twitch, Twitter, or Discord ID")
    public User getUserByAlternateId(@PathVariable @ApiParam("ID Type") IdType type,
                                     @PathVariable @ApiParam("ID of the type to search") String id) {
        return userService.getById(id, type);
    }

    @PostMapping
    @ApiOperation("Create a user")
    public User createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.create(request);
    }

    @PutMapping
    @ApiOperation(value = "Update a user", notes = "${api.updateAsPatch}")
    public User updateUser(@Valid @RequestBody UpdateUserRequest request) {
        return userService.update(request);
    }
}
