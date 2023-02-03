package com.example.pattyulms.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.pattyulms.models.ConceptModel;
import com.example.pattyulms.repository.ConceptRepo;

@RestController
@RequestMapping("/concepts")
public class ConceptController {
    
    @Autowired
    ConceptRepo conceptRepo;

    @GetMapping("/gallery")
    //Get all concepts from the database
    public ResponseEntity<List<ConceptModel>>getAllConcepts(@RequestParam(required = false)String title)
    {
        try{
            List<ConceptModel> conceptModel = new ArrayList<ConceptModel>();
            //If the title is null, find all documents from the concept Repository.  for each document, add. :: means perform action, in this case, add to our list
            if(title == null)
            conceptRepo.findAll().forEach(conceptModel::add);
            else
                conceptRepo.findByTitle(title).forEach(conceptModel::add);

                //If the documents are empty, we will return no content
                if(conceptModel.isEmpty()){
                    return new ResponseEntity<>(conceptModel,HttpStatus.OK);
                }
                //Return new entity with our concept model
                return new ResponseEntity<>(conceptModel,HttpStatus.OK);
        }

        catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    //This will insert concepts into the database
    @PostMapping("/concepts")
    //Use the createConcept function, the request will be from our model class
    public ResponseEntity<ConceptModel> createConcept(@RequestBody ConceptModel conceptModel){
        try{
        //Use the save method that is implemented from mongoDB repository
        //Creating a return value from our model to insert into the concept repo
        ConceptModel returnVal = conceptRepo.insert(conceptModel);
        //Return our new entity with the return value we created
        return new ResponseEntity<>(returnVal, HttpStatus.CREATED);
    }catch (Exception e){
        System.out.println(e);
        return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

     //In this function, we will delete a product by our ID from the database
     @DeleteMapping(value ="/concepts/{id}")
     public ResponseEntity<ConceptModel> deleteConcept(@PathVariable String id){
        {
            try{
                //Using function from the implimented mongo Repository ConceptRepo
                conceptRepo.deleteById(id);
                //Technically we are not returning anything because we are deleting a concept
                //However in our business service we will return 1 if our concept was delete. and return 0 if nothing was delete
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }catch(Exception e){
                //If there is an exception, we will print the exception and return an error
                System.out.println(e);
                return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        
    }
        //Concept Update function, finding the Concept ID and updating the document based on the ID
        @PutMapping(value ="/concepts/{id}")
        //Path Variable - Id. Meaning we will pass ID in our URL
        public ResponseEntity<ConceptModel> updateConcept(@PathVariable("id") String id, @RequestBody ConceptModel conceptModel){
            //Here we will find the id that we will update
            Optional<ConceptModel> conceptDataUpdate = conceptRepo.findById(id);
            //If the document ID is present, we will get and set the new fields
            if(conceptDataUpdate.isPresent()){
                ConceptModel concept = conceptDataUpdate.get();

                concept.setConceptID(conceptModel.getConceptID());
                concept.setTitle(conceptModel.getTitle());
                concept.setDescription(conceptModel.getDescription());

                return new ResponseEntity<>(conceptRepo.save(concept), HttpStatus.OK);
            } else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        }

        //Here we will get a product by its ID
        //Passing the ID Variable in our URL
        @GetMapping(value = "/concepts/{id}")
        public ResponseEntity<ConceptModel> getConceptID(@PathVariable String id)
        {
            //Here we will use the find by ID function implimented from the mongo repository
            Optional<ConceptModel> conceptDataID = conceptRepo.findById(id);
            try{
                //Attempt to find ID
                conceptRepo.findById(id);
                //If found, get the entity
                return new ResponseEntity<>(conceptDataID.get(), HttpStatus.OK);
                //If we get an exception or nothing found we will print the exception, and return NOT FOUND
            } catch (Exception e){
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
} 
