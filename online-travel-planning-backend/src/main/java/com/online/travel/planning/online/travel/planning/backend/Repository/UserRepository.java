package com.online.travel.planning.online.travel.planning.backend.Repository;


import org.apache.catalina.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByUserId(String userId);
    User findByUserEmail(String username);
    Optional<User> findUsernameByUserId(String userId);
}
