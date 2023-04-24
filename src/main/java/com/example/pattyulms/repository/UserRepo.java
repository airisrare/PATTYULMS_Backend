package com.example.pattyulms.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.pattyulms.models.UserModel;

public interface UserRepo extends MongoRepository<UserModel, Long> {
    // MongoRepository’s methods: save(), findOne(), findById(), findAll(), count(),
    // delete(), deleteById()
    public List<UserModel> findAllByUsername(String username);

    Optional<UserModel> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
