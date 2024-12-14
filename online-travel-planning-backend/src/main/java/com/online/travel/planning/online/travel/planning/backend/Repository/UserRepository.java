package com.online.travel.planning.online.travel.planning.backend.Repository;


import com.online.travel.planning.online.travel.planning.backend.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<com.online.travel.planning.online.travel.planning.backend.Model.User,String> {

    Optional<com.online.travel.planning.online.travel.planning.backend.Model.User> findByUserId(String userId);
    com.online.travel.planning.online.travel.planning.backend.Model.User findByUserEmail(String username);

    Optional<User> findUsernameByUserId(String userId);
}

