package com.example.pattyulms.repository;

import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Update;



@Service
public class ProductSequenceGenService {
    
    private MongoOperations mongoOperations;

    @Autowired
    public ProductSequenceGenService(MongoOperations mongoOperations){
        this.mongoOperations = mongoOperations;
    }

    public Long generateSequence(String seqName){
        ProductSequence counter = mongoOperations.findAndModify(query(where("productID").is(seqName)),
        new Update().inc("seq", 1), options().returnNew(true).upsert(true),ProductSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
}
