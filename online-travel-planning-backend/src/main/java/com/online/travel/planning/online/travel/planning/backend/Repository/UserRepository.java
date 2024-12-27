package com.online.travel.planning.online.travel.planning.backend.Repository;


import com.online.travel.planning.online.travel.planning.backend.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<com.online.travel.planning.online.travel.planning.backend.Model.User,String> {

    Optional<User> findByUserId(String userId);
    User findByUserEmail(String username);
    //List<User> findTop5ByOrderBydateRegisteredDesc();
    Optional<User> findUsernameByUserId(String userId);
    // List<User> findTop5ByDateRegisteredOrderByDateRegisteredDesc(LocalDate dateRegistered);

    List<User> findByLastLoginAfter(LocalDateTime lastLoginTime);
}

