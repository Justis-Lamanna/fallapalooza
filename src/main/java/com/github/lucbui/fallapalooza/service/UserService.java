package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.User;
import com.github.lucbui.fallapalooza.exception.UserNotFoundException;
import com.github.lucbui.fallapalooza.model.team.IdType;
import com.github.lucbui.fallapalooza.model.user.CreateUserRequest;
import com.github.lucbui.fallapalooza.model.user.UpdateUserRequest;
import com.github.lucbui.fallapalooza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        User user = new User(request.getName(), request.getDiscordId(), request.getTwitchId());
        user.setPronouns(request.getPronouns());
        user.setBlurb(request.getBlurb());
        user.setCrownCount(request.getCrownCount());
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
        if(request.getName() != null) { user.setName(request.getName()); }
        if(request.getPronouns() != null) { user.setPronouns(request.getPronouns()); }
        if(request.getBlurb() != null) { user.setBlurb(request.getBlurb()); }
        if(request.getCrownCount() != null) { user.setCrownCount(request.getCrownCount()); }
        if(request.getTwitterId() != null) { user.setTwitterId(request.getTwitterId()); }
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

    public Page<User> getUsersWithPageable(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
