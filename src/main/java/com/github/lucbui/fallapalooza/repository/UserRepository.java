package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repository to hold user data
 */
public interface UserRepository extends JpaRepository<User, Long> {
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
    Optional<User> getUserByDiscordId(String discordId);

    /**
     * Retrieve a user by its Twitch ID
     * @param twitchId The Discord ID to search
     * @return The matched user, if present
     */
    Optional<User> getUserByTwitchId(String twitchId);

    /**
     * Retrieve a user by its Twitter ID
     * @param twitterId The Twitter ID to search
     * @return The matched user, if present
     */
    Optional<User> getUserByTwitterId(String twitterId);
}
