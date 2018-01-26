/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Service;

import com.mycompany.Model.ProductsCopy;
import com.mycompany.Repositories.ProductCopyRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LebyanaWT
 */
@Service
public class ProductsCopyService {
    
    @Autowired
    private ProductCopyRepository productCopyRepository;
    private List<ProductsCopy> products;
    
    public List<ProductsCopy> findAllproducts()
    {
        products = new ArrayList<>();
        productCopyRepository.findAll().forEach(products::add);
        return products; 
    }
    
    public void saveProductsCopy(ProductsCopy prodCopy){
        ProductsCopy prodInCart = new ProductsCopy();
        prodInCart.setProduct_id(prodCopy.getProduct_id());
        productCopyRepository.save(prodInCart);
    }
}
