package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Repository to hold user data
 */
public interface UserRepository extends CrudRepository<User, Long> {
    /**
     * Retrieve users by their name
     * @param name The username
     * @return The matched users
     */
    List<User> getUserByName(String name);

    /**
     * Retrieve a user by its Discord ID
     * @param discordId The Discord ID to search
     * @return The matched user, if present
     */
    User getUserByDiscordId(String discordId);

    /**
     * Retrieve a user by its Twitch ID
     * @param twitchId The Discord ID to search
     * @return The matched user, if present
     */
    User getUserByTwitchId(String twitchId);
}
