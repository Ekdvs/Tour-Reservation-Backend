package com.online.travel.planning.online.travel.planning.backend.Repository;

import com.online.travel.planning.online.travel.planning.backend.Model.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUserId(String userId);
    User findByUserEmail(String username);
    Optional<User> findUsernameByUserId(String userId);
}
