package com.example.pattyulms.repository;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.example.pattyulms.models.ProductModel;

//String because that is our id Variable we can search and delete by

public interface ProductRepo extends MongoRepository<ProductModel, String> {
    //MongoRepository’s methods: save(), findOne(), findById(), findAll(), count(), delete(), deleteById()

    @Query(value="{}", fields="{'moreImageURLs' : 0}")
    
    //New find by title method, used for our find all method
    public List<ProductModel> findByTitle(String title);
}
