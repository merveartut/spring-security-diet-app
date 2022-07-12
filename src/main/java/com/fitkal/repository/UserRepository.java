package com.fitkal.repository;


import com.fitkal.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {
    Optional<User> findByEmail(String email);

    User findByEmailAndPassword(String email,String password);


    Boolean existsByEmail(String email);
}
