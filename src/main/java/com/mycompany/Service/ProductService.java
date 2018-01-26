/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.mycompany.Model.Product;
import com.mycompany.Repositories.ProductRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LebyanaWT
 */
@Service
public class ProductService {
    
    @Autowired
    private ProductRepository prodRep;
    
    public List<Product> listProducts(){
        List<Product> allProducts = new ArrayList<>();
        prodRep.findAll().forEach(allProducts::add);
        return allProducts;
    }
    
    public void addProduct(Product prod){
        prodRep.save(prod);
    }
    
    public void removeProd(Product prod){
        prodRep.delete(prod);
    }
}
  