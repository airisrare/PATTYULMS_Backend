package com.example.pattyulms.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.pattyulms.models.ProductModel;
import com.example.pattyulms.repository.ProductRepo;

@Service
public class ProductBusinessService implements ProductBusinessInterface {

    // Using Repository functions
    @Autowired
    private ProductRepo productRepo;

    // Methods from our interface
    // Get all products, return find all function from repo
    @Override
    public List<ProductModel> getAllProducts() {
        // TODO Auto-generated method stub
        return productRepo.findAll();
    }

    // Delete product
    @Override
    public int deleteProduct(Long id)
    // Passing string ID
    // TODO Auto-generated method stub
    {
        // If we delete, return 1
        try {
            productRepo.deleteById(id);
            return 1;
            // If we do not delete, print exception and return 0
        } catch (Exception e) {
            System.out.println(e);
            return 0;
        }

    }

    // Create product function
    @Override
    public ProductModel createProduct(ProductModel productModel) {
        // Using our model to create
        // TODO Auto-generated method stub
        // Insert into product repo, using product model
        return productRepo.insert(productModel);
    }

    // @Override
    // public ProductModel getProductById(Long id) {

    // return productRepo.getProduct(id);

    // }

}
