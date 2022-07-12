package com.fitkal.repository;

import com.fitkal.models.Diyetisyen;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiyetsyenRepository extends MongoRepository<Diyetisyen,String> {
    Diyetisyen findBy(String id);
    Boolean existsByEmail(String email);
}
