package com.github.lucbui.fallapalooza.controller;

import com.github.lucbui.fallapalooza.entity.User;
import com.github.lucbui.fallapalooza.model.team.IdType;
import com.github.lucbui.fallapalooza.model.user.CreateUserRequest;
import com.github.lucbui.fallapalooza.model.user.UpdateUserRequest;
import com.github.lucbui.fallapalooza.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> getUsers(@PageableDefault(20) Pageable pageable) {
        return userService.getByPageable(pageable);
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.getById(id);
    }

    @GetMapping("/{type}/{id}")
    public User getUserByAlternateId(@PathVariable IdType type, @PathVariable String id) {
        return userService.getById(id, type);
    }

    @PostMapping
    public User createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.create(request);
    }

    @PutMapping
    public User updateUser(@Valid @RequestBody UpdateUserRequest request) {
        return userService.update(request);
    }
}
