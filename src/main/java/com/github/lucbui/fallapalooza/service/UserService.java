package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.User;
import com.github.lucbui.fallapalooza.exception.UserNotFoundException;
import com.github.lucbui.fallapalooza.model.user.CreateUserRequest;
import com.github.lucbui.fallapalooza.model.user.UpdateUserRequest;
import com.github.lucbui.fallapalooza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User create(CreateUserRequest request) {
        User user = new User(request.getName());
        user.setPronouns(request.getPronouns());
        user.setTwitchId(request.getTwitchId());
        user.setDiscordId(request.getDiscordId());
        return userRepository.save(user);
    }

    public User update(UpdateUserRequest request) {
        User user = userRepository.findById(request.getId())
                .orElseThrow(() -> new UserNotFoundException(request.getId()));
        user.setName(request.getName());
        user.setPronouns(request.getPronouns());
        user.setDiscordId(request.getDiscordId());
        return userRepository.save(user);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
