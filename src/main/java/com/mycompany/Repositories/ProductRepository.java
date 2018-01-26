/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Repositories;

import com.mycompany.Model.Product;
import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author LebyanaWT
 */
public interface ProductRepository extends CrudRepository<Product, Integer>{
    
}