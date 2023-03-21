package com.example.pattyulms.models;

import java.util.Arrays;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "concepts")
public class ConceptModel 
{

    @Transient
    public static final String SEQUENCE_NAME = "concept_sequence";


    @Id
    private Long conceptID;
    private String title;
    private String description;
    //Unique ID for images of only products for now 
    private String styleID;

    //urls for images in our s3 bucket
    private String imageURL = "";
    private String[] moreImageURLs = new String [0];

    //aws details
    private String version = "1";

    
    

    //Default Constructor
    public ConceptModel()
    {

    }

    public ConceptModel(Long conceptID, String title, String description,String imageURL, String[] moreImageURLs, String styleID)
    {
        super();
        this.conceptID = conceptID;
        this.title = title;
        this.description = description;
        this.imageURL = imageURL;
        this.moreImageURLs = moreImageURLs;
        this.styleID = styleID;
        
    }

    //getters and setters
    public Long getConceptID() {
        return conceptID;
    }

    public void setConceptID(Long conceptID) {
        this.conceptID = conceptID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStyleID() {
        return styleID;
    }

    public void setStyleID(String styleID) {
        this.styleID = styleID;
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

    @Override
    public String toString() {
        return "ConceptModel [conceptID=" + conceptID + ", title=" + title + ", description=" + description
                + ", styleID=" + styleID + ", imageURL=" + imageURL + ", moreImageURLs="
                + Arrays.toString(moreImageURLs) + ", version=" + version + "]";
    }
    
}
