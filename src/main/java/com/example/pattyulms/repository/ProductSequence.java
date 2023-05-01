package com.example.pattyulms.repository;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "product_sequences")
// Add 1 to id sequence in mongo
public class ProductSequence {

    private String productID;
    private Long seq;

    public ProductSequence() {

    }

    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public Long getSeq() {
        return seq;
    }

    public void setSeq(Long seq) {
        this.seq = seq;
    }
}
