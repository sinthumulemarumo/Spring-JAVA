
package com.mycompany.Service;

import com.mycompany.Model.Customers;
import com.mycompany.Repositories.CustomerRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author LebyanaWT
 */
@Service
public class CustomerService {
    
    @Autowired
    private CustomerRepository custRepo;
    
    public void addCustomer(Customers cust){
        custRepo.save(cust);
    }
    
    
    public List<Customers> getCustomers(){
        List<Customers> custs = new ArrayList<>();
        custRepo.findAll().forEach(custs::add);
        return custs;
    }
    
    public Customers login(String username,String password){
        Customers customer =  null;
        String status = null;
          List<Customers> customers = getCustomers();
          for(int i = 0;i< customers.size();i++){
              if(customers.get(i).getUsername().equals(username) && customers.get(i).getPassword().equals(password)){
                  status = "You have successfully Logged In";
                  customer = customers.get(i);
                  break;
              }else{
                  status = "Invalid Credentials";
              }
          }
          return customer;
    }
     
    
}
