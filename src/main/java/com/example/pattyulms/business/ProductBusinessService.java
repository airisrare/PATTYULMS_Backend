package com.example.pattyulms.business;

import java.io.Console;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.pattyulms.models.ProductModel;
import com.example.pattyulms.repository.ProductRepo;

import io.micrometer.core.ipc.http.HttpSender.Response;
@Service
public class ProductBusinessService implements ProductBusinessInterface{
    
    //Using Repository functions
    @Autowired
    private ProductRepo productRepo;

    //Methods from our interface
    //Get all products, return find all function from repo
    @Override
    public List<ProductModel> getAllProducts() {
        // TODO Auto-generated method stub
        return productRepo.findAll();
    }


    //Delete product
    @Override
    public int deleteProduct(String id) 
    //Passing string ID
    // TODO Auto-generated method stub
    {
        //If we delete, return 1
        try{
            productRepo.deleteById(id);
            return 1;
            //If we do not delete, print exception and return 0
        }catch(Exception e){
            System.out.println(e);
        return 0;
        }
        
    }


    // @Override
    // public String findProductTitle(String title) {
    //     // TODO Auto-generated method stub
    //     try{
    //         productRepo.findByTitle(title);
    //         return title;

    //     }catch(Exception e){
    //         System.out.println(e);
    //         return "Nothing Found";
    //     }
    // }


    //Create product function
    @Override
    public ProductModel createProduct(ProductModel productModel) {
        //Using our model to create
        // TODO Auto-generated method stub
        //Insert into product repo, using product model
        return productRepo.insert(productModel);
    }



}
