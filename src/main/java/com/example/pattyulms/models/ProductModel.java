package com.example.pattyulms.models;

import org.bson.types.Decimal128;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

//import jakarta.websocket.Decoder.Binary;


//Document will override collection names to the one in our database
@Document(collection = "products")
public class ProductModel {

    //ID given by mongo
    // private ObjectId _id;
    
    //ID annotation for the integer ID in the database
    @Id
    private String productID;
    private String title;
    private Decimal128 price;
    private String gender;
    private String size;
    private String description;
     
    //private Binary image;

    //Default Constructor
    public ProductModel(){

    }
    
    public ProductModel(String productID, String title, Decimal128 price, String gender, String size, String description)
    {
        super();
        this.productID = productID;
        this.title = title;
        this.price = price;
        this.gender = gender;
        this.size = size;
        this.description = description;
        //this.image=image;
    }

    //Getters and setters
    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Decimal128 getPrice() {
        return price;
    }

    public void setPrice(Decimal128 price) {
        this.price = price;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //To string to put our model together
    @Override
    public String toString() {
        return "ProductModel [productID=" + productID + ", title=" + title + ", price=" + price
                + ", gender=" + gender + ", size=" + size + ", description=" + description + "]";
    }

    
    // public Binary getImage() {
        //     return image;
        // }
        
        // public void setImage(Binary image) {
            //     this.image = image;
            // }
            
        
            

}
