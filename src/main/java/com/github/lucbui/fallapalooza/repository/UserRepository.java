package com.github.lucbui.fallapalooza.repository;

import com.github.lucbui.fallapalooza.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}
