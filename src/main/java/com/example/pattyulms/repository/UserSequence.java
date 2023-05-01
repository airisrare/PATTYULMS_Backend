package com.example.pattyulms.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_sequences")
public class UserSequence {
    // Add 1 to id sequence in mongo
    @Id
    private String userID;
    private long seq;

    public UserSequence() {

    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }

}
