package com.example.pattyulms.repository;

import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;

@Service
public class UserSequenceGenService {
    // Add 1 to id sequence in mongo

    private MongoOperations mongoOperations;

    @Autowired
    public UserSequenceGenService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Long generateSequence(String seqName) {
        UserSequence counter = mongoOperations.findAndModify(query(where("userID").is(seqName)),
                new Update().inc("seq", 1), options().returnNew(true).upsert(true),
                UserSequence.class);
        return !Objects.isNull(counter) ? counter.getSeq() : 1;
    }

}
