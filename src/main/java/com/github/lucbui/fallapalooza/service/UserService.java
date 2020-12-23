package com.github.lucbui.fallapalooza.service;

import com.github.lucbui.fallapalooza.entity.User;
import com.github.lucbui.fallapalooza.exception.UserNotFoundException;
import com.github.lucbui.fallapalooza.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service which handles user retrieval
 */
@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    /**
     * Retrieve users using a Page system
     * @param pageable The page
     * @return The users for that page
     */
    public Iterable<User> getUsers(Pageable pageable) {
        return repository.findAll(pageable);
    }

    /**
     * Get a user by its ID
     * @param id The ID of the user
     * @return The user, if it exists
     * @throws UserNotFoundException User with that ID does not exist
     */
    public Optional<User> getUser(long id) {
        return repository.findById(id);
    }

    /**
     * Get users by a name
     * @param name The name to search
     * @return The users with that name
     */
    public List<User> getUserByName(String name) {
        return repository.getUserByName(name);
    }

    /**
     * Get a user by their Discord ID
     * @param discordId The Discord ID
     * @return The user, if it exists
     * @throws UserNotFoundException User with that ID does not exist
     */
    public Optional<User> getUserByDiscordId(String discordId) {
        return repository.getUserByDiscordId(discordId);
    }

    /**
     * Get a user by their Twitch ID
     * @param twitchId The Twitch ID
     * @return The user, if it exists
     * @throws UserNotFoundException User with that ID does not exist
     */
    public Optional<User> getUserByTwitchId(String twitchId) {
        return repository.getUserByTwitchId(twitchId);
    }

    /**
     * Upsert a User
     * @param user The user to upsert
     * @return The user, after changes were applied
     */
    public User createOrUpdateUser(User user) {
        return repository.save(user);
    }

    /**
     * Delete a user
     * @param id The ID of the user to delete
     */
    public void deleteUser(long id) {
        repository.deleteById(id);
    }
}
