package com.online.travel.planning.online.travel.planning.backend.Repository;
import com.online.travel.planning.online.travel.planning.backend.Model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public class UserRepository extends MongoRepository<User,String> {

    Optional<User> findByUserId(String userId);
    User findByUserEmail(String username);

}
