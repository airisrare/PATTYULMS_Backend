package com.example.pattyulms.repository;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;


import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;
import org.springframework.data.mongodb.core.query.Update;

@Service
public class ConceptSequenceGenService {

    private MongoOperations mongoOperations;

    @Autowired
    public ConceptSequenceGenService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Long generateSequence(String seqName) {
        ConceptSequence counter = mongoOperations.findAndModify(query(where("conceptID").is(seqName)),
         new Update().inc("seq",1), options().returnNew(true).upsert(true),
         ConceptSequence.class);
         return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }
    
}
