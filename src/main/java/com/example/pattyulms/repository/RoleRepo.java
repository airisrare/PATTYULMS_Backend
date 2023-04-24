package com.example.pattyulms.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.pattyulms.models.EUserRole;
import com.example.pattyulms.models.UserRole;

public interface RoleRepo extends MongoRepository<UserRole, String> {
    Optional<UserRole> findByName(EUserRole name);
}
