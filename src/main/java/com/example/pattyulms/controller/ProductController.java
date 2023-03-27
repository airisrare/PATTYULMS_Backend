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
import org.springframework.web.multipart.MultipartFile;

import com.example.pattyulms.util.S3Util;
import com.example.pattyulms.config.s3Config;
import com.example.pattyulms.models.ProductModel;
import com.example.pattyulms.repository.ProductRepo;
import com.example.pattyulms.repository.ProductSequenceGenService;
import org.springframework.web.bind.annotation.CrossOrigin;

/*- Noah Roerig helped me configure the s3 bucket while Jack Blackwell 
//taught me how s3 worked overall. Thank you for being great classmates and 
teachers */

//Rest controller annotation will controll API request from clients
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ProductSequenceGenService productSequenceGenService;

    @Autowired
    S3Util s3Util;

    // This is how we will get configuration out of config file
    @Autowired
    s3Config s3Config;

    // Our ../api/products is the url for show all products
    @GetMapping("/products")
    // get all products will display all the products from the database
    public ResponseEntity<List<ProductModel>> getAllProducts(@RequestParam(required = false) String title)

    {
        try {
            // Create a new array list from database
            List<ProductModel> productModel = new ArrayList<ProductModel>();

            // if title is null find all documents from product repository, for each
            // document, add. :: means perform action, in this case, add to our list
            if (title == null)
                productRepo.findAll().forEach(productModel::add);
            else
                productRepo.findByTitle(title).forEach(productModel::add);

            // if the documents are empty, we will return no content
            if (productModel.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            // return our entity with our product model
            return new ResponseEntity<>(productModel, HttpStatus.OK);
        }
        // If their is an exception, we will print the exception and return the new
        // status of Server error
        catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // This will insert a product into the database using Post
    @PostMapping("/products")
    // Use the createProduct function, the request will be from our model class
    // request a "file" - picture for our product that will be stored into the s3
    // bucket
    public ResponseEntity<ProductModel> createProduct(@RequestParam("file") MultipartFile file,
            @RequestParam("additionalFiles") MultipartFile[] additionalFiles, ProductModel productModel) {

        try {
            String styleID = productModel.getStyleID();
            // add image to s3 storage and return url to add to database
            // Main Image
            String mainImageURLS3 = s3Util.uploadFile(styleID, "main", file.getInputStream(), null);
            // add images to s3 storage and return multiple urls into list
            List<String> moreS3ImageURLs = new ArrayList<String>();

            // Additional images if we would like
            for (MultipartFile otherFile : additionalFiles) {
                moreS3ImageURLs.add(
                        s3Util.uploadFile(styleID, otherFile.getOriginalFilename(), otherFile.getInputStream(), null));

            }

            productModel.setImageURL(mainImageURLS3);

            // list is converted back to array
            productModel.setMoreImageURLs(moreS3ImageURLs.toArray(new String[moreS3ImageURLs.size()]));
        } catch (Exception e) {
            // handle the exception
            // log the error message
            System.out.println("Error uploading file to S3: " + e.getMessage());
        }

        try {

            // Auto Increment ID
            productModel.setProductID(productSequenceGenService.generateSequence(productModel.SEQUENCE_NAME));

            // Use the save method that is implemented from mongoDB repository
            // Creating a return value from our model to insert into the product repository
            ProductModel returnVal = productRepo.insert(productModel);
            // Return our new entity with our return value
            return new ResponseEntity<>(returnVal, HttpStatus.CREATED);

        } catch (Exception e) {
            // If there is an exception, we will print the exception and return an error
            // Nothing will be posted if their is an error
            System.out.println(e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    // In this function, we will delete a product by our ID from the database
    @DeleteMapping(value = "/products/{id}")
    public ResponseEntity<ProductModel> deleteProduct(@PathVariable String id) {
        try {
            // Using function from the implimented Mongo Repository
            productRepo.deleteById(id);
            // Technically we are not returning anything because we are deleting a product
            // However in our business service we will return a 1 if our product was
            // deleted, and return 0 if nothing was deleted
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            // If there is an exception, we will print the exception and return an error
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Product Update function, finding the product ID and updating the document
    // based on ID
    @PutMapping(value = "/products/{id}")
    // Path Variable - ID. Meaning we will pass ID in our url
    public ResponseEntity<ProductModel> updateProduct(@PathVariable("id") String id,
            @RequestBody ProductModel productModel) {
        // Here we will find the id that we will update
        Optional<ProductModel> productDataUpdate = productRepo.findById(id);
        // If the doumentID is present, we will get and set the new fields
        if (productDataUpdate.isPresent()) {
            ProductModel product = productDataUpdate.get();
            product.setProductID(productModel.getProductID());
            product.setTitle(productModel.getTitle());
            product.setPrice(productModel.getPrice());
            product.setGender(productModel.getGender());
            product.setSize(productModel.getSize());
            product.setDescription(productModel.getDescription());
            product.setImageURL(productModel.getImageURL());

            // HttpStatus.OK is an enumeration value in the HttpStatus class that
            // represents a status code of 200. It indicates that the request was successful
            // and the requested information has been returned.
            return new ResponseEntity<>(productRepo.save(product), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Here we will get a product by its ID
    // Passing the ID variable in our url
    @GetMapping(value = "/products/{id}")
    public ResponseEntity<ProductModel> getProductID(@PathVariable String id) {
        // Here we will use the find by ID function implemented from the mongo
        // repository
        Optional<ProductModel> productDataID = productRepo.findById(id);
        try {
            // Attempt to find ID
            productRepo.findById(id);
            // If found, get the entity
            return new ResponseEntity<>(productDataID.get(), HttpStatus.OK);
            // If we get an exception or nothing found we will print the exception, and
            // return NOT FOUND
        } catch (Exception e) {
            System.out.println(e);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
