package com.example.pattyulms.business;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.pattyulms.models.ProductModel;

import io.micrometer.core.ipc.http.HttpSender.Response;

/*
 * OrdersBusiness Inteface class that will decalare
 * the methods used in the orders service class.
 */
public interface ProductBusinessInterface {

    /* List returning all the orders from the database
	 * @Param- List<ProductModel>
	 */
    public List<ProductModel>getAllProducts();

    //This will create a product using our product model 
    public ProductModel createProduct(ProductModel productModel);

    //This will delete a product by id. Using the Int we created
    public int deleteProduct(String id);
    
}
