/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.RestController;

import com.mycompany.Model.Customers;
import com.mycompany.Service.CustomerService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author LebyanaWT
 */
@RestController
public class CustomerController {
    
    @Autowired
    public  CustomerService custService;
    
    public List<Customers> custAvailable = new ArrayList<>();
    
    @RequestMapping(value="/register",method=RequestMethod.POST)
    public void registerCustomer(@RequestBody Customers cust,String registerStatus ){
        custService.addCustomer(cust);
        registerStatus = "Congradulations" + " " + cust.getFirstname() + " " + "You have successfully registered";
    }
    
    @RequestMapping(value="/login/user", produces ={MediaType.APPLICATION_JSON_VALUE})
    public Customers login(@RequestParam String username,String password ){
        return custService.login(username, password);
    }
    
    @RequestMapping(value="/customers/all",method=RequestMethod.GET)
    public List allCustomers(){
        return custService.getCustomers();
    }
}
