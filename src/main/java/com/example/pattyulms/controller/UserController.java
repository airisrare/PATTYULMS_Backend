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

import com.example.pattyulms.models.UserModel;
import com.example.pattyulms.repository.UserRepo;
import com.example.pattyulms.repository.UserSequenceGenService;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserSequenceGenService userSequenceGenService;
    
    @GetMapping("/everyone")
    //Get all users will display all the users from the database
    public ResponseEntity<List<UserModel>>getAllUsers(@RequestParam(required = false)String username)
    {
        try{
            List<UserModel> userModel = new ArrayList<UserModel>();
        //if title is null, find all documents from product repository, for each document, add. :: means perform action, in this case, add to our list
            if(username == null)
            userRepo.findAll().forEach(userModel::add);
            else
                userRepo.findByUsername(username).forEach(userModel::add);

            //if the documents are empty, we will return no content
            if(userModel.isEmpty()){
                return new ResponseEntity<>(userModel,HttpStatus.NO_CONTENT);
            }
            //return our entity with our user model
            return new ResponseEntity<>(userModel,HttpStatus.OK);
        }
        //If their is an exception, we will print the exception and return the new status of Server error
        catch(Exception e){
            System.out.println(e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //This will insert a user into the database using Post
    /**
     * @param userModel
     * @return
     */
    @PostMapping("/everyone")
    //Use the createUser function, the request will be from our model class
    public ResponseEntity<UserModel> createUser(@RequestBody UserModel userModel){
        try{

            //Auto-Increment ID
            userModel.setUserID(userSequenceGenService.generateSequence(userModel.SEQUENCE_NAME));

             //Use the save method that is implemented from mongoDB repository
                //Creating a return value from our model to insert into the user repository
                UserModel returnVal = userRepo.insert(userModel);
                //Return our new entity with our return value
                return new ResponseEntity<>(returnVal,HttpStatus.CREATED);
        }catch(Exception e){
            //If there is an exception, we will print the exception and return an error
            //Nothing will be posted if their is an error
            System.out.println(e);
            return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

         //In this function, we will delete a product by our ID from the database
         @DeleteMapping(value ="/everyone/{id}")
         public ResponseEntity<UserModel> deleteUser(@PathVariable String id)
         {
         try{
             //Using function from the implimented Mongo Repository
             userRepo.deleteById(id);
             //Technically we are not returning anything because we are deleting a product
             //However in our business service we will return a 1 if our product was deleted, and return 0 if nothing was deleted
             return new ResponseEntity<>(HttpStatus.NO_CONTENT);
         }catch (Exception e){
              //If there is an exception, we will print the exception and return an error
             System.out.println(e);
             return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
             }
         }

         //Put mapping will allow us to update the product. The page (value) that will be updated is 
         //the everyone page with the specific ID we are changing
         @PutMapping(value="/everyone/{id}")
         public ResponseEntity<UserModel> updateUser(@PathVariable("id") String id, @RequestBody UserModel userModel){
            //Here we will find the id that we will update
            Optional<UserModel> userDataUpdate = userRepo.findById(id);
            //If the ID we are looking for is present, we will get and set the new user details
            if(userDataUpdate.isPresent()){
                UserModel user = userDataUpdate.get();
                user.setUserID(userModel.getUserID());
                user.setFirstname(userModel.getFirstname());
                user.setLastname(userModel.getLastname());
                user.setEmail(userModel.getEmail());
                user.setUsername(userModel.getUsername());
                user.setPassword(userModel.getPassword());

                // HttpStatus.OK is an enumeration value in the HttpStatus class that 
                // represents a status code of 200. It indicates that the request was successful 
                // and the requested information has been returned.

                return new ResponseEntity<>(userRepo.save(user),HttpStatus.OK);
            }else{
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
            }
            
            //Here we will get a product by its ID
            //Passing the ID variable in our url
            @GetMapping(value ="/everyone/{id}")
            public ResponseEntity<UserModel> getUserID(@PathVariable String id)
            {
                Optional<UserModel> userDataID = userRepo.findById(id);
                try{
                    //Attempt to find ID
                    userRepo.findById(id);
                    //If found, get the entity
                    return new ResponseEntity<>(userDataID.get(), HttpStatus.OK);
                    //If we get an exception or nothing found we will print the exception, and return NOT FOUND
        }           catch (Exception e){
                    System.out.println(e);
                    return new ResponseEntity<>(HttpStatus.NOT_FOUND);
                }
            }

         }


