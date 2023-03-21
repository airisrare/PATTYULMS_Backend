package com.example.pattyulms.repository;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "concept_sequences")
public class ConceptSequence {

    @Id
    private String conceptID;
    private long seq;

    public ConceptSequence() {
        
    }

    public String getConceptID() {
        return conceptID;
    }

    public void setConceptID(String conceptID) {
        this.conceptID = conceptID;
    }

    public long getSeq() {
        return seq;
    }

    public void setSeq(long seq) {
        this.seq = seq;
    }    
}
