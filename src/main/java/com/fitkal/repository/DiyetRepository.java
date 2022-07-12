package com.fitkal.repository;

import com.fitkal.models.Diyet;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DiyetRepository extends MongoRepository<Diyet,String> {
    Optional<Diyet> findById(String id);
}
