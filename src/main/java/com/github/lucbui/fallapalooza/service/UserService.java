package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.User;
import com.github.lucbui.fallapalooza.exception.UserNotFoundException;
import com.github.lucbui.fallapalooza.model.team.IdType;
import com.github.lucbui.fallapalooza.model.user.CreateUserRequest;
import com.github.lucbui.fallapalooza.model.user.UpdateUserRequest;
import com.github.lucbui.fallapalooza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    /**
     * Create a user
     * @param request The user creation request
     * @return The created user
     */
    public User create(CreateUserRequest request) {
        User user = new User(request.getName());
        user.setPronouns(request.getPronouns());
        user.setBlurb(request.getBlurb());
        user.setCrownCount(request.getCrownCount());
        user.setTwitchId(request.getTwitchId());
        user.setDiscordId(request.getDiscordId());
        user.setTwitterId(request.getTwitterId());
        return userRepository.save(user);
    }

    /**
     * Update a user
     * @param request The user update request
     * @return The updated user
     * @throws UserNotFoundException User does not exist
     */
    public User update(UpdateUserRequest request) {
        User user = getById(request.getId());
        user.setName(request.getName());
        user.setPronouns(request.getPronouns());
        user.setBlurb(request.getBlurb());
        user.setCrownCount(request.getCrownCount());
        return userRepository.save(user);
    }

    /**
     * Retrieve a user by the prime ID
     * @param id The ID of the user
     * @return The user
     * @throws UserNotFoundException User does not exist
     */
    public User getById(long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Retrieve a user by some ID
     * @param id The ID of the user
     * @param idType The ID type
     * @return The user
     * @throws UserNotFoundException User does not exist
     */
    public User getById(String id, IdType idType) {
        Optional<User> user;
        switch (idType) {
            case TWITCH: user = userRepository.getUserByTwitchId(id); break;
            case DISCORD: user = userRepository.getUserByDiscordId(id); break;
            case TWITTER: user = userRepository.getUserByTwitterId(id); break;
            default: user = Optional.empty();
        }
        return user.orElseThrow(() -> new UserNotFoundException(id, idType));
    }
}
