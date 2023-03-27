package com.example.pattyulms.repository;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.pattyulms.models.ConceptModel;

public interface ConceptRepo extends MongoRepository<ConceptModel, String> {
     //MongoRepository’s methods: save(), findOne(), findById(), findAll(), count(), delete(), deleteById()
     //Find by title method is not one implimented by the mongo repo. 
     //Find by title can be used in search methods, also the find all method
     @Query(value="{}", fields="{'moreImageURLs' : 0}")
     public List<ConceptModel> findByTitle(String title);

     
}
