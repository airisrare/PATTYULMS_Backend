package com.example.pattyulms.models;

import java.util.Arrays;

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
    //Price must be a string when used in form data, It is their text, or file through postman
    private String price;
    private String gender;
    private String size;
    private String description;
    //Unique ID for images of only products for now 
    private String styleID;


     
    //urls for images in our s3 bucket
    private String imageURL = "";
    private String[] moreImageURLs = new String [0];

    //aws details
    private String version = "1";

    //Default Constructor
    public ProductModel(){

    }
    
    public ProductModel(String productID, String title, String price, String gender, String size, String description, String imageURL, String[] moreImageURLs, String styleID)
    {
        super();
        this.productID = productID;
        this.title = title;
        this.price = price;
        this.gender = gender;
        this.size = size;
        this.description = description;
        this.imageURL = imageURL;
        this.moreImageURLs = moreImageURLs;
        this.styleID = styleID;
    }





     //Getter and setters
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

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String[] getMoreImageURLs() {
        return moreImageURLs;
    }

    public void setMoreImageURLs(String[] moreImageURLs) {
        this.moreImageURLs = moreImageURLs;
    }

    
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    

    
    public String getStyleID() {
        return styleID;
    }
    
    public void setStyleID(String styleID) {
        this.styleID = styleID;
    }

    @Override
    public String toString() {
        return "ProductModel [productID=" + productID + ", title=" + title + ", price=" + price + ", gender=" + gender
                + ", size=" + size + ", description=" + description + ", styleID=" + styleID + ", imageURL=" + imageURL
                + ", moreImageURLs=" + Arrays.toString(moreImageURLs) + ", version=" + version + "]";
    }       
} 
