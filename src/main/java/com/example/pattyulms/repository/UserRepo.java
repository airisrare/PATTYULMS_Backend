package com.example.pattyulms.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.pattyulms.models.UserModel;

public interface UserRepo extends MongoRepository<UserModel, String> {
//MongoRepository’s methods: save(), findOne(), findById(), findAll(), count(), delete(), deleteById()
public List<UserModel> findByUsername(String username);
}
