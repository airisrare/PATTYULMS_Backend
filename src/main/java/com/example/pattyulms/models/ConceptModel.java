package com.example.pattyulms.models;

import org.springframework.data.annotation.Id;

public class ConceptModel 
{
    @Id
    private String conceptID;
    private String title;
    private String description;
    //private Binary image;
    
    
    

    //Default Constructor
    public ConceptModel()
    {

    }

    public ConceptModel(String conceptID, String title, String description)
    {
        this.conceptID = conceptID;
        this.title = title;
        this.description = description;
        
    }

    public String getConceptID() {
        return conceptID;
    }

    public void setConceptID(String conceptID) {
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

    @Override
    public String toString() {
        return "ConceptModel [conceptID=" + conceptID + ", title=" + title + ", description=" + description + "]";
    }

    
    
}
