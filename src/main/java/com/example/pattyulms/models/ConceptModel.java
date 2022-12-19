package com.example.pattyulms.models;

public class ConceptModel 
{
    private int _id;
    private int conceptID;
    private String title;
    private String description;
    

    //Default Constructor
    public ConceptModel()
    {

    }

    public ConceptModel(int _id, int conceptID, String title, String description)
    {
        this._id = _id;
        this.conceptID = conceptID;
        this.title = title;
        this.description = description;
        
    }

    
}
