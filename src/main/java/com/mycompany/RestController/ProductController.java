/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.RestController;

import com.mycompany.Model.Product;
import com.mycompany.Service.ProductService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LebyanaWT
 */
@RestController
public class ProductController {
   
    @Autowired
    private ProductService prodService;
    
    List<Product> prodList = new ArrayList<>();
    
    @RequestMapping(value="/products/all",method = RequestMethod.GET)
    public List<Product> allProducts(){
      return prodService.listProducts();
    }
    
    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public void addProduct(@RequestBody Product prod){
        prodService.addProduct(prod);
    }
    
    @RequestMapping(value = "/deleteProd")
    public void deleteProduct(@RequestBody Product prod){
        prodService.removeProd(prod);
    }
    
   
    
  
    
    
    
    
    
    
}
